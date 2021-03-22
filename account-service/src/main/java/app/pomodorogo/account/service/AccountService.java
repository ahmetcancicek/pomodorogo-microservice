package app.pomodorogo.account.service;

import app.pomodorogo.account.dto.AccountCreateRequest;
import app.pomodorogo.account.dto.AccountResponse;

public interface AccountService {

    /***
     * Finds account by given nme
     * @param name
     * @return found account
     */
    AccountResponse findByName(String name);


    /***
     * Checks if account with given name already exists
     * Invokes Auth Service user creation
     * Creates new account
     * @param accountCreateRequest
     * @return created account
     */
    AccountResponse create(AccountCreateRequest accountCreateRequest);


    /***
     *
     * @param id
     * @param update
     */
    void update(String id, AccountCreateRequest update);
}
