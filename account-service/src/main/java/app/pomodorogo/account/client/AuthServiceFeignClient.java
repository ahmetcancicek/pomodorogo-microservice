package app.pomodorogo.account.client;

import app.pomodorogo.account.dto.UserRegisterRequest;
import app.pomodorogo.account.dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "auth-service")
public interface AuthServiceFeignClient {

    @RequestMapping(method = RequestMethod.POST, value = "/uaa/users")
    void createUser(UserRegisterRequest userRegisterRequest);
}
