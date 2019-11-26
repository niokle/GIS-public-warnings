package application.domain;

import lombok.AllArgsConstructor;
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
@Table(name = "emails_to_delete")
public class EmailToDelete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String key;
    private String email;
    private LocalDateTime dateTime;

    public EmailToDelete(String email) {
        key = UUID.randomUUID().toString().replaceAll("-", "");
        this.email = email;
        dateTime = LocalDateTime.now();
    }
}
