package application.repository;

import application.domain.EmailToDelete;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmailToDeleteRepository extends CrudRepository<EmailToDelete, Long> {
    Optional<EmailToDelete> findByEmail(String email);

    Optional<EmailToDelete> findById(Long id);

    Optional<EmailToDelete> findByRecordKey(String recordKey);

    List<EmailToDelete> findAll();
}
