package application.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "rss_old_items")
public class RssOldItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;
    private String title;
    private String url;
    private LocalDateTime dateTime;
    @ManyToOne
    @JoinColumn(name = "rssFeed.feedId")
    private RssFeed rssFeed;
    private boolean sent;

    public RssOldItem(String title, String url, LocalDateTime dateTime, RssFeed rssFeed) {
        this.title = title;
        this.url = url;
        this.dateTime = dateTime;
        this.rssFeed = rssFeed;
        this.sent = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RssOldItem that = (RssOldItem) o;
        return Objects.equals(getTitle(), that.getTitle()) &&
                Objects.equals(getUrl(), that.getUrl()) &&
                Objects.equals(getDateTime(), that.getDateTime()) &&
                Objects.equals(getRssFeed().getFeedId(), that.getRssFeed().getFeedId()) &&
                Objects.equals(getRssFeed().getUrl(), that.getRssFeed().getUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getUrl(), getDateTime(), getRssFeed());
    }
}
