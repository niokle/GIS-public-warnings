package application.repository;

import application.domain.EmailToAdd;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailToAddRepository extends CrudRepository<EmailToAdd, Long> {
}
