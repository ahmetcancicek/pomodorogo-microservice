package app.pomodorogo.auth.service;

import app.pomodorogo.auth.domain.User;
import app.pomodorogo.auth.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setUp() {

    }

    @Test
    public void When_UserIsNotNullAndCreateUserCalled_Expect_SavedToDB() {
        User user = User.builder()
                .username("username")
                .password("password")
                .email("username@username.com")
                .password("password")
                .build();

        when(userRepository.save(user)).thenReturn(user);
        User createdUser = userService.create(user);

        assertNotNull(createdUser);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void When_UsernameAlreadyExists_Expect_ReturnFail() {
        User user = User.builder()
                .username("username")
                .password("password")
                .email("username@username.com")
                .password("password")
                .build();

        when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            userService.create(user);
        });
    }

    @Test
    public void When_EmailAlreadyExists_Expect_ReturnFail() {
        User user = User.builder()
                .username("username")
                .password("password")
                .email("username@username.com")
                .password("password")
                .build();

        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            userService.create(user);
        });
    }

    @Test
    public void When_UsernameExistAndFindByUsernameCalled_Expect_ReturnUser() {
        User user = User.builder()
                .username("username")
                .password("password")
                .email("username@username.com")
                .password("password")
                .build();

        when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));
        assertNotNull(userService.findByUsername(user.getUsername()));
    }

    @Test
    public void When_EmailExistAndFindByEmailCalled_Expect_ReturnUser() {
        User user = User.builder()
                .username("username")
                .password("password")
                .email("username@username.com")
                .password("password")
                .build();

        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        assertNotNull(userService.findByEmail(user.getEmail()));
    }
}
