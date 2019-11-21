package application.service;

import application.domain.RssFeed;
import application.domain.RssOldItem;
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
public class RssOldItemServiceTest {

    @Autowired
    RssOldItemRepository rssOldItemRepository;

    @Autowired
    RssOldItemService rssOldItemService;

    @Autowired
    RssFeedRepository rssFeedRepository;

    @Test
    public void addRecord() {
        //given
        RssFeed rssFeed = new RssFeed("www1");
        rssFeedRepository.save(rssFeed);
        RssOldItem rssOldItem = new RssOldItem("title1", "url1", LocalDateTime.of(2019, 10, 10,
                10, 10, 10), rssFeed);
        //when
        RssOldItem result = rssOldItemService.addRecord(rssOldItem);

        //then
        Assert.assertEquals("title1", rssOldItemRepository.findById(result.getItemId()).get().getTitle());

        //cleanup
        rssOldItemRepository.delete(rssOldItem);
        rssFeedRepository.delete(rssFeed);
    }

    @Test
    public void isRssItemExists() {
        //given
        RssFeed rssFeed1 = new RssFeed("www1");
        rssFeedRepository.save(rssFeed1);
        RssOldItem rssOldItem1 = new RssOldItem("title1", "url1", LocalDateTime.of(2019, 10, 10,
                10, 10, 10), rssFeed1);
        RssOldItem rssOldItem2 = new RssOldItem("title2", "url2", LocalDateTime.of(2019, 10, 10,
                10, 10, 10), rssFeed1);
        RssOldItem rssOldItem3 = new RssOldItem("title3", "url3", LocalDateTime.of(2019, 10, 10,
                10, 10, 10), rssFeed1);
        RssOldItem rssOldItemResult1 = rssOldItemService.addRecord(rssOldItem1);
        RssOldItem rssOldItemResult2 = rssOldItemService.addRecord(rssOldItem2);
        RssOldItem rssOldItemResult3 = rssOldItemService.addRecord(rssOldItem3);

        //when
        boolean resultTrue1 = rssOldItemService.isRssItemExists("title1", "url1", LocalDateTime.of(2019, 10, 10,
                10, 10, 10), rssFeed1);
        boolean resultTrue2 = rssOldItemService.isRssItemExists("title2", "url2", LocalDateTime.of(2019, 10, 10,
                10, 10, 10), rssFeed1);
        boolean resultTrue3 = rssOldItemService.isRssItemExists("title3", "url3", LocalDateTime.of(2019, 10, 10,
                10, 10, 10), rssFeed1);
        boolean resultFalse = rssOldItemService.isRssItemExists("title1", "url1", LocalDateTime.of(2019, 10, 10,
                10, 10, 11), rssFeed1);

        //then
        Assert.assertTrue(resultTrue1);
        Assert.assertTrue(resultTrue2);
        Assert.assertTrue(resultTrue3);
        Assert.assertFalse(resultFalse);

        //cleanup
        rssOldItemRepository.delete(rssOldItemResult1);
        rssOldItemRepository.delete(rssOldItemResult2);
        rssOldItemRepository.delete(rssOldItemResult3);
        rssFeedRepository.delete(rssFeed1);
    }
}