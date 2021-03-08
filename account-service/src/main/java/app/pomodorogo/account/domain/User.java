package app.pomodorogo.account.domain;


import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    private String id;

    @Field("username")
    @NotNull
    @Length(min = 4, max = 50)
    @Indexed(unique = true)
    private String username;

    @Field("email")
    @NotNull
    @Length(min = 6, max = 50)
    @Indexed(unique = true)
    private String email;

    @Field("password")
    @NotNull
    @Length(min = 6, max = 50)
    private String password;
}
