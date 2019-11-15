package application.service;

import application.domain.RssFeed;
import application.exception.RssFeedNotFoundException;
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
    private static Logger LOGGER = LoggerFactory.getLogger(RssFeedService.class);

    public RssFeedService(RssFeedRepository rssFeedRepository) {
        this.rssFeedRepository = rssFeedRepository;
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
        LOGGER.info("Usuwanie rekordu o id: " + id);
        rssFeedRepository.deleteById(id);
    }
}
