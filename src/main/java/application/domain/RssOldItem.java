package application.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "rss_old_items")
public class RssOldItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String url;
    private LocalDateTime dateTime;
    private Long feedId; //todo

    public RssOldItem(String title, String url, LocalDateTime dateTime, Long feedId) {
        this.title = title;
        this.url = url;
        this.dateTime = dateTime;
        this.feedId = feedId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RssOldItem that = (RssOldItem) o;
        return Objects.equals(getTitle(), that.getTitle()) &&
                Objects.equals(getUrl(), that.getUrl()) &&
                Objects.equals(getDateTime(), that.getDateTime()) &&
                getFeedId().equals(that.getFeedId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getUrl(), getDateTime(), getFeedId());
    }
}
