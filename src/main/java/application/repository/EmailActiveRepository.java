package application.repository;

import application.domain.EmailActive;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmailActiveRepository extends CrudRepository<EmailActive, Long> {
    Optional<EmailActive> findByEmail(String email);

    List<EmailActive> findAll();
}
