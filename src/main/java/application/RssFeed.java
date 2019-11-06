package application;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import application.domain.RssItem;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class RssFeed {
    private String url;
    private SyndFeed syndFeed;

    public RssFeed(String url) throws IOException, FeedException {
        this.url = url;
        this.syndFeed = getSyndFeed();
    }

    private SyndFeed getSyndFeed() throws IOException, FeedException {
        URL feedSource = new URL(url);
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed syndFeed = input.build(new XmlReader(feedSource));
        return syndFeed;
    }

    public List<RssItem> getFeedItems() {
        List<RssItem> rssItemList = new ArrayList<>();
        String title;
        String url;
        LocalDateTime date;
        for (SyndEntry syndEntry : syndFeed.getEntries()) {
            title = syndEntry.getTitle();
            url = syndEntry.getUri();
            date = syndEntry.getPublishedDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            rssItemList.add(new RssItem(title, url, date));
        }
        return rssItemList;
    }
}
