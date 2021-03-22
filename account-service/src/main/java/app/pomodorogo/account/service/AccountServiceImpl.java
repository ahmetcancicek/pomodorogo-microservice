package app.pomodorogo.account.service;

import app.pomodorogo.account.client.AuthServiceFeignClient;
import app.pomodorogo.account.domain.Account;
import app.pomodorogo.account.dto.AccountCreateRequest;
import app.pomodorogo.account.dto.AccountResponse;
import app.pomodorogo.account.dto.UserCreateRequest;
import app.pomodorogo.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;


@Service
@Slf4j
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AuthServiceFeignClient authClient;
    private final AccountRepository repository;
    private final ModelMapper modelMapper;

    @Override
    public AccountResponse findByName(String name) {
        return modelMapper.map(repository.findByName(name), AccountResponse.class);
    }

    @Override
    public AccountResponse create(AccountCreateRequest accountCreateRequest) {
        Account existing = repository.findByName(accountCreateRequest.getUsername());
        Assert.isNull(existing, "account already exists: " + accountCreateRequest.getUsername());

        UserCreateRequest user = modelMapper.map(accountCreateRequest, UserCreateRequest.class);
        user.setEmail(accountCreateRequest.getEmail());
        authClient.createUser(user);

        Account account = createAccount(accountCreateRequest);

        log.info("new account has been created: " + account.getName());

        AccountResponse accountResponse = modelMapper.map(account, AccountResponse.class);
        accountResponse.setEmail(accountCreateRequest.getEmail());
        accountResponse.setUsername(accountCreateRequest.getUsername());
        return accountResponse;
    }

    public Account createAccount(AccountCreateRequest accountCreateRequest) {
        Account account = Account.builder()
                .name(accountCreateRequest.getUsername())
                .firstName(accountCreateRequest.getFirstName())
                .lastName(accountCreateRequest.getLastName())
                .build();
        return repository.save(account);
    }

    @Override
    public void update(String id, AccountCreateRequest update) {
        // TODO: Should update account. Also, update auth service
    }
}
