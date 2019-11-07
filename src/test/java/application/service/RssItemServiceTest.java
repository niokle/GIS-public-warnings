package application.service;

import application.domain.RssItem;
import application.repository.RssItemRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RssItemServiceTest {

    @Autowired
    RssItemRepository rssItemRepository;

    @Autowired
    RssItemService rssItemService;

    @Test
    public void addRecord() {
        //given
        RssItem rssItem = new RssItem("title1", "url1", LocalDateTime.of(2019, 10, 10,
                10, 10, 10));
        //when
        RssItem result = rssItemService.addRecord(rssItem);

        //then
        Assert.assertEquals("title1", rssItemRepository.findById(result.getId()).get().getTitle());

        //cleanup
        rssItemRepository.delete(rssItem);
    }

    @Test
    public void isRssItemExists() {
        //given
        RssItem rssItem1 = new RssItem("title1", "url1", LocalDateTime.of(2019, 10, 10,
                10, 10, 10));
        RssItem rssItem2 = new RssItem("title2", "url2", LocalDateTime.of(2019, 10, 10,
                10, 10, 10));
        RssItem rssItem3 = new RssItem("title3", "url3", LocalDateTime.of(2019, 10, 10,
                10, 10, 10));
        rssItemService.addRecord(rssItem1);
        rssItemService.addRecord(rssItem2);
        rssItemService.addRecord(rssItem3);

        //when
        boolean resultTrue1 = rssItemService.isRssItemExists("title1", "url1", LocalDateTime.of(2019, 10, 10,
                10, 10, 10));
        boolean resultTrue2 = rssItemService.isRssItemExists("title2", "url2", LocalDateTime.of(2019, 10, 10,
                10, 10, 10));
        boolean resultTrue3 = rssItemService.isRssItemExists("title3", "url3", LocalDateTime.of(2019, 10, 10,
                10, 10, 10));
        boolean resultFalse = rssItemService.isRssItemExists("title1", "url1", LocalDateTime.of(2019, 10, 10,
                10, 10, 11));

        //then
        Assert.assertTrue(resultTrue1);
        Assert.assertTrue(resultTrue2);
        Assert.assertTrue(resultTrue3);
        Assert.assertFalse(resultFalse);

        //cleanup
        rssItemRepository.delete(rssItem1);
        rssItemRepository.delete(rssItem2);
        rssItemRepository.delete(rssItem3);
    }
}