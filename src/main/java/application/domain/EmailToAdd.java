package application.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "emails_to_add")
public class EmailToAdd {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private LocalDateTime dateTime;

    public EmailToAdd(String email) {
        this.email = email;
        dateTime = LocalDateTime.now();
    }
}
