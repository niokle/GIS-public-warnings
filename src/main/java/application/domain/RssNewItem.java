package application.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class RssNewItem {
    private String title;
    private String url;
    private LocalDateTime dateTime;
    private RssFeed rssFeed;

    public RssNewItem(String title, String url, LocalDateTime dateTime, RssFeed rssFeed) {
        this.title = title;
        this.url = url;
        this.dateTime = dateTime;
        this.rssFeed = rssFeed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RssNewItem that = (RssNewItem) o;
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
