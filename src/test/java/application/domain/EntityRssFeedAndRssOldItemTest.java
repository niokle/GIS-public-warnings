package application.domain;

import application.repository.RssFeedRepository;
import application.repository.RssOldItemRepository;
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
    RssFeedRepository rssFeedRepository;

    @Autowired
    RssOldItemRepository rssOldItemRepository;

    @Test
    public void deleteTest() {
        //given
        RssFeed rssFeed1 = new RssFeed("www1");
        RssFeed rssFeed2 = new RssFeed("www2");
        RssFeed rssFeedResult1 = rssFeedRepository.save(rssFeed1);
        RssFeed rssFeedResult2 = rssFeedRepository.save(rssFeed2);
        RssOldItem rssOldItem1 = new RssOldItem("rss1", "rsswww1", LocalDateTime.now(), rssFeed1);
        RssOldItem rssOldItem2 = new RssOldItem("rss2", "rsswww2", LocalDateTime.now(), rssFeed2);
        RssOldItem rssOldItem3 = new RssOldItem("rss3", "rsswww3", LocalDateTime.now(), rssFeed1);
        RssOldItem rssOldItem4 = new RssOldItem("rss4", "rsswww4", LocalDateTime.now(), rssFeed2);
        RssOldItem rssOldItemResult1 = rssOldItemRepository.save(rssOldItem1);
        RssOldItem rssOldItemResult2 = rssOldItemRepository.save(rssOldItem2);
        RssOldItem rssOldItemResult3 = rssOldItemRepository.save(rssOldItem3);
        RssOldItem rssOldItemResult4 = rssOldItemRepository.save(rssOldItem4);

        //when
        rssFeedRepository.delete(rssFeedResult1);

        //then
        Assert.assertEquals(1, rssFeedRepository.count());
        //todo Assert.assertEquals(2, rssOldItemRepository.count());

        //cleanup
        rssFeedRepository.delete(rssFeedResult2);
        rssOldItemRepository.delete(rssOldItemResult1);
        rssOldItemRepository.delete(rssOldItemResult2);
        rssOldItemRepository.delete(rssOldItemResult3);
        rssOldItemRepository.delete(rssOldItemResult4);
    }
}