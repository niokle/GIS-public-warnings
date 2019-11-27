package application.domain;

import application.repository.RssFeedRepository;
import application.repository.RssOldItemRepository;
import application.service.RssFeedService;
import application.service.RssOldItemService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EntityRssFeedAndRssOldItemTest {

    @Autowired
    RssFeedService rssFeedService;

    @Autowired
    RssOldItemService rssOldItemService;

    @Autowired
    RssFeedRepository rssFeedRepository;

    @Autowired
    RssOldItemRepository rssOldItemRepository;

    @Test
    public void deleteTest() {
        //given
        RssFeed rssFeed1 = new RssFeed("www1");
        RssFeed rssFeed2 = new RssFeed("www2");
        RssFeed rssFeedResult1 = rssFeedService.addFeed(rssFeed1);
        RssFeed rssFeedResult2 = rssFeedService.addFeed(rssFeed2);
        RssOldItem rssOldItem1 = new RssOldItem("rss1", "rsswww1", LocalDateTime.now(), rssFeed1);
        RssOldItem rssOldItem2 = new RssOldItem("rss2", "rsswww2", LocalDateTime.now(), rssFeed2);
        RssOldItem rssOldItem3 = new RssOldItem("rss3", "rsswww3", LocalDateTime.now(), rssFeed1);
        RssOldItem rssOldItem4 = new RssOldItem("rss4", "rsswww4", LocalDateTime.now(), rssFeed2);
        RssOldItem rssOldItemResult1 = rssOldItemService.addRecord(rssOldItem1);
        RssOldItem rssOldItemResult2 = rssOldItemService.addRecord(rssOldItem2);
        RssOldItem rssOldItemResult3 = rssOldItemService.addRecord(rssOldItem3);
        RssOldItem rssOldItemResult4 = rssOldItemService.addRecord(rssOldItem4);

        //when
        rssFeedService.removeFeed(rssFeedResult1.getFeedId());

        //then
        Assert.assertEquals(1, rssFeedRepository.count());
        Assert.assertEquals(2, rssOldItemRepository.count());

        //cleanup
        rssFeedService.removeFeed(rssFeedResult2.getFeedId());
    }
}