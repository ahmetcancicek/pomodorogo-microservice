package app.pomodorogo.account.client;

import app.pomodorogo.account.dto.UserRegisterRequest;
import app.pomodorogo.account.dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "auth-service")
public interface AuthServiceFeignClient {

    @PostMapping(value = "/uaa/users")
    UserResponse createUser(@RequestBody UserRegisterRequest user);
}
