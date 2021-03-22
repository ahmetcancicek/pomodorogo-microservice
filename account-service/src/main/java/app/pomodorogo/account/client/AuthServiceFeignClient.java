package app.pomodorogo.account.client;

import app.pomodorogo.account.dto.UserCreateRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "auth-service")
public interface AuthServiceFeignClient {

    @PostMapping(value = "/uaa/users")
    void createUser(UserCreateRequest userCreateRequest);
}
