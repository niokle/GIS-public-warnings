package application.controller;

import application.domain.RssFeed;
import application.service.RssFeedService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("${config.address.feeds.url}")
public class RssFeedController {
    private RssFeedService rssFeedService;

    public RssFeedController(RssFeedService rssFeedService) {
        this.rssFeedService = rssFeedService;
    }

    @GetMapping("{id}")
    public Optional<RssFeed> getFeed(@PathVariable Long id) {
        return rssFeedService.getFeed(id);
    }

    @GetMapping
    public List<RssFeed> getFeeds() {
        return rssFeedService.getFeeds();
    }

    @PostMapping
    public RssFeed addFeed(@RequestParam String url) {
        return rssFeedService.addFeed(new RssFeed(url));
    }

    @DeleteMapping("{id}")
    public void removeFeed(@PathVariable Long id) {
        rssFeedService.removeFeed(id);
    }
}
