package app.pomodorogo.account.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequest {

    @NotNull
    @Length(min = 3, max = 50)
    private String firstName;

    @NotNull
    @Length(min = 3, max = 50)
    private String lastName;

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
