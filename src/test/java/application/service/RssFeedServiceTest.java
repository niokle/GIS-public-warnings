package application.service;

import application.domain.RssFeed;
import application.repository.RssFeedRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RssFeedServiceTest {

    @Autowired
    RssFeedService rssFeedService;

    @Autowired
    RssFeedRepository rssFeedRepository;

    @Test
    public void getFeed() {
        //given
        RssFeed rssFeed = new RssFeed("a");
        RssFeed rssFeedResult = rssFeedRepository.save(rssFeed);

        //when
        String url = rssFeedService.getFeed(rssFeedResult.getId()).get().getUrl();

        //then
        Assert.assertEquals("a", url);

        //cleanup
        rssFeedRepository.delete(rssFeedResult);
    }

    @Test
    public void getFeeds() {
        //given
        RssFeed rssFeed1 = new RssFeed("a");
        RssFeed rssFeed2 = new RssFeed("b");
        RssFeed rssFeed3 = new RssFeed("c");
        RssFeed rssFeedResult1 = rssFeedRepository.save(rssFeed1);
        RssFeed rssFeedResult2 = rssFeedRepository.save(rssFeed2);
        RssFeed rssFeedResult3 = rssFeedRepository.save(rssFeed3);

        //when
        int result = rssFeedService.getFeeds().size();

        //then
        Assert.assertEquals(3, result);

        //cleanup
        rssFeedRepository.delete(rssFeedResult1);
        rssFeedRepository.delete(rssFeedResult2);
        rssFeedRepository.delete(rssFeedResult3);
    }

    @Test
    public void addFeed() {
        //given
        RssFeed rssFeed1 = new RssFeed("a");
        RssFeed rssFeed2 = new RssFeed("b");
        RssFeed rssFeed3 = new RssFeed("c");
        RssFeed rssFeedResult1 = rssFeedService.addFeed(rssFeed1);
        RssFeed rssFeedResult2 = rssFeedService.addFeed(rssFeed2);
        RssFeed rssFeedResult3 = rssFeedService.addFeed(rssFeed3);

        //when
        long result = rssFeedRepository.count();

        //then
        Assert.assertEquals(3, result);

        //cleanup
        rssFeedRepository.delete(rssFeedResult1);
        rssFeedRepository.delete(rssFeedResult2);
        rssFeedRepository.delete(rssFeedResult3);
    }

    @Test
    public void removeFeed() {
        //given
        RssFeed rssFeed1 = new RssFeed("a");
        RssFeed rssFeed2 = new RssFeed("b");
        RssFeed rssFeed3 = new RssFeed("c");
        RssFeed rssFeedResult1 = rssFeedRepository.save(rssFeed1);
        RssFeed rssFeedResult2 = rssFeedRepository.save(rssFeed2);
        RssFeed rssFeedResult3 = rssFeedRepository.save(rssFeed3);

        //when
        rssFeedService.removeFeed(rssFeedResult1.getId());
        long result = rssFeedRepository.count();

        //then
        Assert.assertEquals(2, result);

        //cleanup
        rssFeedRepository.delete(rssFeedResult2);
        rssFeedRepository.delete(rssFeedResult3);
    }
}