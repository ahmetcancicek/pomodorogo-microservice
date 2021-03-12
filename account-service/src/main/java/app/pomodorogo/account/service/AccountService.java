package app.pomodorogo.account.service;

import app.pomodorogo.account.dto.AccountRequest;
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
     * @param accountRequest
     * @return created account
     */
    AccountResponse create(AccountRequest accountRequest);



    void update(String id, AccountRequest update);
}
