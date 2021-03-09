package app.pomodorogo.auth.service;

import app.pomodorogo.auth.domain.User;
import app.pomodorogo.auth.enums.Authorities;
import app.pomodorogo.auth.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        throwIfUsernameExists(user.getUsername());
        throwIfEmailExists(user.getEmail());

        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        // TODO: Should send email with code for activation
        user.setActivated(Boolean.TRUE);
        user.setAuthorities(new HashSet<>(Collections.singletonList(Authorities.ROLE_USER)));

        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("username is not found for given username: " + username);
                });
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("email is not found for given email: " + email);
                });
    }

    private void throwIfUsernameExists(String username) {
        Optional<User> existingUser = userRepository.findByUsername(username);
        existingUser.ifPresent((it) -> {
            throw new IllegalArgumentException("username is not found for given username: " + username);
        });
    }

    private void throwIfEmailExists(String email) {
        Optional<User> existingUser = userRepository.findByEmail(email);
        existingUser.ifPresent((it) -> {
            throw new IllegalArgumentException("email is not found for given email: " + email);
        });
    }
}
