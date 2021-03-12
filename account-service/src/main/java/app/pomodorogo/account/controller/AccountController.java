package app.pomodorogo.account.controller;

import app.pomodorogo.account.dto.AccountRequest;
import app.pomodorogo.account.dto.AccountResponse;
import app.pomodorogo.account.service.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class AccountController {

    private final AccountService accountService;
    private final ModelMapper modelMapper;

    public AccountController(AccountService accountService, ModelMapper modelMapper) {
        this.accountService = accountService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<AccountResponse> createNewAccount(@Valid @RequestBody AccountRequest accountRequest) {
        AccountResponse accountResponse = accountService.create(accountRequest);
        return new ResponseEntity<>(accountResponse, HttpStatus.CREATED);
    }

    @RequestMapping(path = "/hello", method = RequestMethod.POST)
    public String hello() {
        return "Hello";
    }
}
