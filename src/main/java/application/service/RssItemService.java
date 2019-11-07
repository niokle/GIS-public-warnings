package application.service;

import application.domain.RssItem;
import application.repository.RssItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class RssItemService {
    private RssItemRepository rssItemRepository;
    private static Logger LOGGER = LoggerFactory.getLogger(RssItemService.class);

    public RssItemService(RssItemRepository rssItemRepository) {
        this.rssItemRepository = rssItemRepository;
    }

    public RssItem addRecord(RssItem rssItem) {
        LOGGER.info("Dodanie rekordu: " + rssItem);
        return rssItemRepository.save(rssItem);
    }

    public boolean isRssItemExists(String title, String url, LocalDateTime dateTime) {
        return rssItemRepository.findByTitleAndUrlAndAndDateTime(title, url, dateTime).isPresent();
    }
}
