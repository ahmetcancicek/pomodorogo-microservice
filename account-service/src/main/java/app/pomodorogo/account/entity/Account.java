package app.pomodorogo.account.entity;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Document(collation = "accounts")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Account {

    @Id
    private String id;

    @NotNull
    @Length(min = 3, max = 50)
    private String firstName;

    @NotNull
    @Length(min = 3, max = 50)
    private String lastName;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Date lastSeen;
}
