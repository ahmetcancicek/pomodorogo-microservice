package app.pomodorogo.account.service;

import app.pomodorogo.account.client.AuthServiceFeignClient;
import app.pomodorogo.account.domain.Account;
import app.pomodorogo.account.dto.AccountRequest;
import app.pomodorogo.account.dto.AccountResponse;
import app.pomodorogo.account.dto.UserRegisterRequest;
import app.pomodorogo.account.dto.UserResponse;
import app.pomodorogo.account.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    private AuthServiceFeignClient authClient;

    private AccountRepository repository;

    private ModelMapper modelMapper;

    private AccountServiceImpl(AuthServiceFeignClient authClient, AccountRepository repository, ModelMapper modelMapper) {
        this.authClient = authClient;
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public AccountResponse findByName(String name) {
        return modelMapper.map(repository.findByName(name), AccountResponse.class);
    }

    @Override
    public AccountResponse create(AccountRequest accountRequest) {
        Account existing = repository.findByName(accountRequest.getUsername());
        Assert.isNull(existing, "account already exists: " + accountRequest.getUsername());


        UserRegisterRequest user = UserRegisterRequest.builder()
                .password(accountRequest.getPassword())
                .email(accountRequest.getEmail())
                .username(accountRequest.getUsername())
                .build();
        authClient.createUser(user);

        Account account = Account.builder()
                .name(accountRequest.getUsername())
                .lastSeen(new Date())
                .firstName(accountRequest.getFirstName())
                .lastName(accountRequest.getLastName())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        repository.save(account);

        log.info("new account has been created: " + account.getName());

        // TODO: Email or username
        return modelMapper.map(account, AccountResponse.class);
    }

    @Override
    @Transactional
    public void update(String id, AccountRequest update) {
        // TODO: Should update account. Also, update auth service
    }
}
