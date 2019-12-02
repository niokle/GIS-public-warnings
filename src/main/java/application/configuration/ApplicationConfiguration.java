package application.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Getter
@EnableScheduling
@Configuration
public class ApplicationConfiguration {
    @Value("${config.address.base.url}")
    private String baseUrl;

    @Value("${config.address.email.add.url}")
    private String emailAddUrl;

    @Value("${config.address.email.delete.url}")
    private String emailDeleteUrl;

    @Value("${config.address.feeds.url}")
    private String feedsUrl;

    @Value("${config.address.administrator.email}")
    private String adminEmail;
}
