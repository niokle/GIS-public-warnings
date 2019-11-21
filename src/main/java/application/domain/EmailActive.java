package application.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "emails_active")
public class EmailActive {
    @Id
    private String email;
    private LocalDateTime dateTime;

    public EmailActive(String email) {
        this.email = email;
        dateTime = LocalDateTime.now();
    }
}
