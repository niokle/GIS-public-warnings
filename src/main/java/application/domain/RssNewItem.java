package application.domain;

import lombok.EqualsAndHashCode;
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
    private Long feedId;

    public RssNewItem(String title, String url, LocalDateTime dateTime, Long feedId) {
        this.title = title;
        this.url = url;
        this.dateTime = dateTime;
        this.feedId = feedId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RssNewItem that = (RssNewItem) o;
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
