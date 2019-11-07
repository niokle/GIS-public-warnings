package application.repository;

import application.domain.EmailActive;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailActiveRepository extends CrudRepository<EmailActive, String> {
    Optional<EmailActive> findByEmail(String email);
}
