package application.repository;

import application.domain.EmailToAdd;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmailToAddRepository extends CrudRepository<EmailToAdd, Long> {
    Optional<EmailToAdd> findByEmail(String email);

    Optional<EmailToAdd> findById(Long id);

    Optional<EmailToAdd> findByRecordKey(String recordKey);

    List<EmailToAdd> findAll();
}
