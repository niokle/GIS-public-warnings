package application.repository;

import application.domain.EmailToDelete;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailToDeleteRepository extends CrudRepository<EmailToDelete, Long> {
}
