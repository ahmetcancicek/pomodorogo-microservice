package app.pomodorogo.auth.service;

import app.pomodorogo.auth.entity.User;

import java.util.Optional;

public interface UserService {
   User create(User user);
}
