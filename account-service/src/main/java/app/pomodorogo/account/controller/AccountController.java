package app.pomodorogo.account.controller;

import app.pomodorogo.account.dto.AccountCreateRequest;
import app.pomodorogo.account.dto.AccountResponse;
import app.pomodorogo.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public AccountResponse createNewAccount(@Valid @RequestBody AccountCreateRequest accountCreateRequest) {
        AccountResponse accountResponse = accountService.create(accountCreateRequest);
        return accountResponse;
    }
}
