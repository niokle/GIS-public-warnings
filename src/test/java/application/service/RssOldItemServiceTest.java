package application.service;

import application.domain.RssOldItem;
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

    @Test
    public void addRecord() {
        //given
        RssOldItem rssOldItem = new RssOldItem("title1", "url1", LocalDateTime.of(2019, 10, 10,
                10, 10, 10), 1L);
        //when
        RssOldItem result = rssOldItemService.addRecord(rssOldItem);

        //then
        Assert.assertEquals("title1", rssOldItemRepository.findById(result.getId()).get().getTitle());

        //cleanup
        rssOldItemRepository.delete(rssOldItem);
    }

    @Test
    public void isRssItemExists() {
        //given
        RssOldItem rssOldItem1 = new RssOldItem("title1", "url1", LocalDateTime.of(2019, 10, 10,
                10, 10, 10), 1L);
        RssOldItem rssOldItem2 = new RssOldItem("title2", "url2", LocalDateTime.of(2019, 10, 10,
                10, 10, 10), 1L);
        RssOldItem rssOldItem3 = new RssOldItem("title3", "url3", LocalDateTime.of(2019, 10, 10,
                10, 10, 10), 1L);
        rssOldItemService.addRecord(rssOldItem1);
        rssOldItemService.addRecord(rssOldItem2);
        rssOldItemService.addRecord(rssOldItem3);

        //when
        boolean resultTrue1 = rssOldItemService.isRssItemExists("title1", "url1", LocalDateTime.of(2019, 10, 10,
                10, 10, 10), 1L);
        boolean resultTrue2 = rssOldItemService.isRssItemExists("title2", "url2", LocalDateTime.of(2019, 10, 10,
                10, 10, 10), 1L);
        boolean resultTrue3 = rssOldItemService.isRssItemExists("title3", "url3", LocalDateTime.of(2019, 10, 10,
                10, 10, 10), 1L);
        boolean resultFalse = rssOldItemService.isRssItemExists("title1", "url1", LocalDateTime.of(2019, 10, 10,
                10, 10, 11), 1L);

        //then
        Assert.assertTrue(resultTrue1);
        Assert.assertTrue(resultTrue2);
        Assert.assertTrue(resultTrue3);
        Assert.assertFalse(resultFalse);

        //cleanup
        rssOldItemRepository.delete(rssOldItem1);
        rssOldItemRepository.delete(rssOldItem2);
        rssOldItemRepository.delete(rssOldItem3);
    }
}