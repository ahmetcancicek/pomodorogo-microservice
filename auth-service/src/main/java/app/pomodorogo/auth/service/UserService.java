package app.pomodorogo.auth.service;

import app.pomodorogo.auth.domain.User;

public interface UserService {
   User create(User user);
   User findByUsername(String username);
   User findByEmail(String email);
}
