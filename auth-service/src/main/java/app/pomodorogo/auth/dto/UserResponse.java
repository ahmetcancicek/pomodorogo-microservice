package app.pomodorogo.auth.dto;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String username;

    private String email;
}
