package application.service;

import application.domain.RssFeed;
import application.exception.RssFeedsNotFoundException;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import application.domain.RssNewItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
public class RssService {
    private RssFeed rssFeed;
    private SyndFeed syndFeed;

    private static Logger LOGGER = LoggerFactory.getLogger(RssService.class);

    public void setRssFeed(RssFeed rssFeed) {
        this.rssFeed = rssFeed;
    }

    public void fillSyndFeed() throws IOException, FeedException, RssFeedsNotFoundException {
        syndFeed = null;
        URL feedSource = new URL(rssFeed.getUrl());
        SyndFeedInput input = new SyndFeedInput();
        try {
            SyndFeed syndFeed = input.build(new XmlReader(feedSource));
            LOGGER.info("Pobieranie źródeł RSS z adresu: " + rssFeed.getUrl());
            this.syndFeed = syndFeed;
        } catch (Exception e) {
            LOGGER.info("Błąd pobierania źródeł RSS z adresu: " + rssFeed.getUrl());
            throw new RssFeedsNotFoundException();
        }
    }

    public List<RssNewItem> getFeedItems() {
        List<RssNewItem> rssNewItemList = new ArrayList<>();
        String titleItem;
        String urlItem;
        LocalDateTime dateItem;
        for (SyndEntry syndEntry : syndFeed.getEntries()) {
            titleItem = syndEntry.getTitle();
            urlItem = syndEntry.getUri();
            dateItem = syndEntry.getPublishedDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            rssNewItemList.add(new RssNewItem(titleItem, urlItem, dateItem, rssFeed));
        }
        return rssNewItemList;
    }
}
