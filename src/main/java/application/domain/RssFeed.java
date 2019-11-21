package application.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "rss_feeds")
public class RssFeed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedId;
    private String url;
    @OneToMany(targetEntity = RssOldItem.class, mappedBy = "rssFeed", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<RssOldItem> rssOldItems = new ArrayList<>();

    public RssFeed(String url) {
        this.url = url;
    }
}
