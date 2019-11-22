package application.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "emails_to_add")
public class EmailToAdd {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String key;
    private String email;
    private LocalDateTime dateTime;

    public EmailToAdd(String email) {
        key = UUID.randomUUID().toString().replaceAll("-", "");
        this.email = email;
        dateTime = LocalDateTime.now();
    }
}
