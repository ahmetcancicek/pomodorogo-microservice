package app.pomodorogo.auth.controller;

import app.pomodorogo.auth.dto.UserCreateRequest;
import app.pomodorogo.auth.domain.User;
import app.pomodorogo.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @GetMapping("/current")
    public Principal getUser(Principal principal) {
        return principal;
    }

    @PostMapping
    @PreAuthorize("#oauth2.hasScope('server')")
    public void createUser(@Valid @RequestBody UserCreateRequest userCreateRequest) {
        User user = modelMapper.map(userCreateRequest, User.class);
        userService.create(user);
    }
}
