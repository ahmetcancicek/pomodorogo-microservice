package app.pomodorogo.auth.controller;

import app.pomodorogo.auth.dto.UserResponse;
import app.pomodorogo.auth.dto.UserRegisterRequest;
import app.pomodorogo.auth.entity.User;
import app.pomodorogo.auth.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
    }

    @GetMapping("/current")
    public Principal getUser(Principal principal) {
        return principal;
    }

    @PostMapping
    @PreAuthorize("#oauth2.hasScope('server')")
    public UserResponse createUser(@Valid @RequestBody UserRegisterRequest userRegistration) {
        User createdUser = userService.create(toUser(userRegistration));
        return toDto(createdUser);
    }

    private UserResponse toDto(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setUsername(user.getUsername());
        userResponse.setEmail(user.getEmail());
        return userResponse;
    }

    private User toUser(UserRegisterRequest userRegistration) {
        User user = new User();
        user.setUsername(userRegistration.getUsername());
        user.setPassword(userRegistration.getPassword());
        user.setEmail(userRegistration.getEmail());
        return user;
    }
}