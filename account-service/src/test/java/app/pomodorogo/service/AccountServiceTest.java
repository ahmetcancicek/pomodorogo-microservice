package app.pomodorogo.service;

import app.pomodorogo.account.client.AuthServiceFeignClient;
import app.pomodorogo.account.repository.AccountRepository;
import app.pomodorogo.account.service.AccountServiceImpl;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

    @InjectMocks
    private AccountServiceImpl accountService;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AuthServiceFeignClient authServiceFeignClient;

    @Before
    public void setUp(){

    }
}
