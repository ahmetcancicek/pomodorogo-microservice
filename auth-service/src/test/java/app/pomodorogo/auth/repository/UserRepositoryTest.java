package app.pomodorogo.auth.repository;

import app.pomodorogo.auth.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserRepositoryTest {

    User user;

    @Autowired
    UserRepository repository;

    @BeforeEach
    public void setUp() {
        user = User.builder()
                .username("username")
                .password("password")
                .email("email@email.com")
                .build();
        repository.save(user);
    }

    @AfterEach
    public void doClean() {
        repository.deleteById(user.getId());
    }

    @Test
    public void When_UserIsFoundAndSaveCalled_Expect_ReturnFail() {
        User user = User.builder()
                .username("username")
                .password("password")
                .email("email@email.com")
                .build();

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            repository.save(user);
        });
    }

    @Test
    public void When_UsernameExitsAndFindByUsernameCalled_Expect_ReturnUser() {
        Optional<User> existingUser = repository.findByUsername("username");
        assertEquals(existingUser.isPresent(), true);
    }

    @Test
    public void When_EmailExitsAndFindByEmailCalled_Expect_ReturnUser() {
        Optional<User> existingUser = repository.findByEmail("username");
        assertEquals(existingUser.isPresent(), true);
    }

    @Test
    public void When_UsernameDoesNotExitsAndFindByUsernameCalled_Expect_ReturnFail() {
        Optional<User> existingUser = repository.findByUsername("test");
        assertEquals(existingUser.isPresent(), false);
    }

    @Test
    public void When_EmailDoesNotExitsAndFindByEmailCalled_Expect_ReturnFail() {
        Optional<User> existingUser = repository.findByEmail("test");
        assertEquals(existingUser.isPresent(), false);
    }

}
