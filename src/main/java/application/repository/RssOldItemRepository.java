package application.repository;

import application.domain.RssOldItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface RssOldItemRepository extends CrudRepository<RssOldItem, Long> {
    Optional<RssOldItem> findByTitleAndUrlAndAndDateTimeAndFeedId(String title, String url, LocalDateTime dateTime, Long feedId);
}
