package application.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ApplicationScheduler {
    private static Logger LOGGER = LoggerFactory.getLogger(ApplicationScheduler.class);

    //todo @Scheduled(cron = "*/2 * * * * *")
    public void test() {
        LOGGER.info("Uruchomiono zadanie schedulera");
    }
}
