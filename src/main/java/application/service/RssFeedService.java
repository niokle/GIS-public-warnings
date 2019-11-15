package application.service;

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
public class RssFeedService {
    private String url;
    private SyndFeed syndFeed;

    private static Logger LOGGER = LoggerFactory.getLogger(RssFeedService.class);

    public RssFeedService() {

    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void fillSyndFeed() throws IOException, FeedException {
        URL feedSource = new URL(url);
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed syndFeed = input.build(new XmlReader(feedSource));
        LOGGER.info("Pobieranie źródeł RSS z adresu: " + url);
        this.syndFeed = syndFeed;
    }

    public List<RssNewItem> getFeedItems() {
        List<RssNewItem> rssNewItemList = new ArrayList<>();
        String title;
        String url;
        LocalDateTime date;
        for (SyndEntry syndEntry : syndFeed.getEntries()) {
            title = syndEntry.getTitle();
            url = syndEntry.getUri();
            date = syndEntry.getPublishedDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            rssNewItemList.add(new RssNewItem(title, url, date));
        }
        return rssNewItemList;
    }
}
