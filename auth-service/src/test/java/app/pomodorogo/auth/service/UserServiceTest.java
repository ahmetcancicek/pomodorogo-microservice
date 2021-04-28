package app.pomodorogo.auth.service;

import app.pomodorogo.auth.domain.User;
import app.pomodorogo.auth.repository.UserRepository;
import app.pomodorogo.auth.service.Impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    public void it_should_return_user_when_save_user() {
        // given
        User user = User.builder()
                .username("username")
                .password("password")
                .email("username@username.com")
                .password("password")
                .build();

        // when
        when(userRepository.save(user)).thenReturn(user);

        // then
        User createdUser = userService.create(user);
        verify(userRepository, times(1)).save(user);
        assertEquals(createdUser.getUsername(), user.getUsername());
    }

    @Test
    public void it_should_throw_exception_when_save_user_with_existing_username() {
        // given
        User user = User.builder()
                .username("username")
                .password("password")
                .email("username@username.com")
                .password("password")
                .build();

        // when
        when(userRepository.findById(user.getUsername())).thenReturn(Optional.of(user));

        // then
        assertThrows(IllegalArgumentException.class, () -> {
            userService.create(user);
        });
    }

    @Test
    public void it_should_throw_exception_when_save_user_with_existing_email() {
        // given
        User user = User.builder()
                .username("username")
                .password("password")
                .email("username@username.com")
                .password("password")
                .build();

        // when
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        // then
        assertThrows(IllegalArgumentException.class, () -> {
            userService.create(user);
        });
    }

    @Test
    public void it_should_return_user_when_find_user_byId() {
        // given
        User user = User.builder()
                .username("username")
                .password("password")
                .email("username@username.com")
                .password("password")
                .build();

        // when
        when(userRepository.findById(user.getUsername())).thenReturn(Optional.of(user));

        // then
        assertEquals(userService.findById(user.getUsername()).getUsername(), user.getUsername());
    }

    @Test
    public void it_should_return_user_when_find_user_byEmail() {
        // given
        User user = User.builder()
                .username("username")
                .password("password")
                .email("username@username.com")
                .password("password")
                .build();

        // when
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        // then
        assertEquals(userService.findByEmail(user.getEmail()).getEmail(), user.getEmail());

    }
}