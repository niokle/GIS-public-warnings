package application.repository;

import application.domain.RssFeed;
import org.springframework.data.repository.CrudRepository;

public interface RssFeedRepository extends CrudRepository<RssFeed, Long> {
}
