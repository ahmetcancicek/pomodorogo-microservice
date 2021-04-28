package app.pomodorogo.auth.repository;

import app.pomodorogo.auth.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@DataMongoTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Test
    public void it_should_return_user_when_save_user() {
        // given
        User user = User.builder()
                .username("username")
                .password("password")
                .email("username@username.com")
                .password("password")
                .build();


        // then
        Optional<User> existingUser = repository.findById(user.getUsername());
        assertTrue(existingUser.isPresent());
        assertEquals(user.getUsername(), existingUser.get().getUsername());
        assertEquals(user.getEmail(), existingUser.get().getEmail());
    }
}

