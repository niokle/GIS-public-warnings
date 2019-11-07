package application.repository;

import application.domain.RssItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface RssItemRepository extends CrudRepository<RssItem, Long> {
    Optional<RssItem> findByTitleAndUrlAndAndDateTime(String title, String url, LocalDateTime dateTime);
}
