package application.service;

import application.domain.RssFeed;
import application.repository.RssFeedRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RssFeedService {
    private RssFeedRepository rssFeedRepository;
    private RssOldItemService rssOldItemService;
    private static Logger LOGGER = LoggerFactory.getLogger(RssFeedService.class);

    public RssFeedService(RssFeedRepository rssFeedRepository, RssOldItemService rssOldItemService) {
        this.rssFeedRepository = rssFeedRepository;
        this.rssOldItemService = rssOldItemService;
    }

    public Optional<RssFeed> getFeed(Long id) {
        LOGGER.info("Pobieranie rekordu o id: " + id);
        return rssFeedRepository.findById(id);
    }

    public List<RssFeed> getFeeds() {
        LOGGER.info("Pobieranie wszystkich rekordów");
        List<RssFeed> feeds = new ArrayList<>();
        for (RssFeed feed : rssFeedRepository.findAll()) {
            feeds.add(feed);
        }
        return feeds;
    }

    public RssFeed addFeed(RssFeed rssFeed) {
        LOGGER.info("Dodanie rekordu, adres: " + rssFeed.getUrl());
        return rssFeedRepository.save(rssFeed);
    }

    public void removeFeed(Long id) {
        if (rssFeedRepository.findById(id).isPresent()) {
            RssFeed rssFeed = rssFeedRepository.findById(id).get();
            LOGGER.info("Usuwanie dzieci rekordu o id: " + id);
            rssOldItemService.removeRecordsByRssFeed(rssFeed);
            LOGGER.info("Usuwanie rekordu o id: " + id);
            rssFeedRepository.deleteById(id);
        } else {
            LOGGER.info("Nie znaleziono rekordu do usunięcia o id: " + id);
        }
    }
}
