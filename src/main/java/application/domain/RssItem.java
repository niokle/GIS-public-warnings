package application.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "rss_last_items")
public class RssItem {
    @Id
    private Long id;
    private String title;
    private String url;
    private LocalDateTime dateTime;

    public RssItem(String title, String url, LocalDateTime dateTime) {
        this.title = title;
        this.url = url;
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RssItem rssItem = (RssItem) o;
        return Objects.equals(getTitle(), rssItem.getTitle()) &&
                Objects.equals(getUrl(), rssItem.getUrl()) &&
                Objects.equals(getDateTime(), rssItem.getDateTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getUrl(), getDateTime());
    }
}
