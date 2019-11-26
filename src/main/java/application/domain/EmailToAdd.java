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
@Entity
@Table(name = "emails_to_add")
public class EmailToAdd {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String recordKey;
    private String email;
    private LocalDateTime dateTime;

    public EmailToAdd(String email) {
        recordKey = UUID.randomUUID().toString().replaceAll("-", "");
        this.email = email;
        dateTime = LocalDateTime.now();
    }
}
