package app.pomodorogo.account.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRegisterRequest {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "Username can not be null.")
    @Size(min = 3,max = 50,message = "Username must be at least 3 characters")
    private String username;

    @NotNull
    @Size(min = 10,max = 50)
    private String email;

    @NotNull
    @Size(min = 4,max = 50)
    private String password;
}