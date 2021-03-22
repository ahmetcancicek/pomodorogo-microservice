package app.pomodorogo.account.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountCreateRequest {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "First name can not be null.")
    @Size(min = 3, max = 50,message = "First name must be at least 3 characters")
    private String firstName;

    @NotNull(message = "Last name can not be null.")
    @Size(min = 3, max = 50,message = "Last name must be at least 3 characters")
    private String lastName;

    @NotNull(message = "Username can not be null.")
    @Size(min = 3,max = 50,message = "Username must be at least 3 characters")
    private String username;

    @NotNull
    @Size(min = 10,max = 50,message = "Email must be at least 10 characters")
    private String email;

    @NotNull
    @Size(min = 4,max = 50,message = "Password must be at least 4 characters")
    private String password;
}
