package application.service;

import application.domain.RssFeed;
import application.domain.RssNewItem;
import application.domain.RssOldItem;
import com.rometools.rome.io.FeedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class RssOldItemAddService {
    private RssFeedService rssFeedService;
    private RssService rssService;
    private RssOldItemService rssOldItemService;

    private static Logger LOGGER = LoggerFactory.getLogger(RssOldItemAddService.class);

    public RssOldItemAddService(RssFeedService rssFeedService, RssService rssService, RssOldItemService rssOldItemService) {
        this.rssFeedService = rssFeedService;
        this.rssService = rssService;
        this.rssOldItemService = rssOldItemService;
    }

    public void rssAddToRssOldItem() throws IOException, FeedException {
        for (RssFeed rssFeed : rssFeedService.getFeeds()) {
            LOGGER.info("Pobieranie danych z adresu: " + rssFeed.getUrl());
            rssService.setRssFeed(rssFeed);
            rssService.fillSyndFeed();
            for (RssNewItem rssNewItem : rssService.getFeedItems()) {
                if (!rssOldItemService.isRssItemExists(rssNewItem.getTitle(), rssNewItem.getUrl(), rssNewItem.getDateTime(), rssFeed)) {
                    LOGGER.info("Zapis rekordu, [title]: " + rssNewItem.getTitle() + " [url]: " + rssNewItem.getUrl()
                    + " [datetime]: " + rssNewItem.getDateTime() + " [feed id]: " + rssFeed.getFeedId());
                    rssOldItemService.addRecord(new RssOldItem(rssNewItem.getTitle(), rssNewItem.getUrl(), rssNewItem.getDateTime(), rssFeed));
                } else {
                    LOGGER.info("Rekord istnieje, [title]: " + rssNewItem.getTitle() + " [url]: " + rssNewItem.getUrl()
                            + " [datetime]: " + rssNewItem.getDateTime() + " [feed id]: " + rssFeed.getFeedId());
                }
            }
        }
    }
}
