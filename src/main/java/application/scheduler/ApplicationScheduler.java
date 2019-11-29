package application.scheduler;

import application.exception.RssFeedsNotFoundException;
import application.service.CleanupService;
import application.service.RssOldItemAddService;
import application.service.SendingNotSentRssFeedsService;
import com.rometools.rome.io.FeedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class ApplicationScheduler {

    @Autowired
    RssOldItemAddService rssOldItemAddService;

    @Autowired
    SendingNotSentRssFeedsService sendingNotSentRssFeedsService;

    @Autowired
    CleanupService cleanupService;

    private static Logger LOGGER = LoggerFactory.getLogger(ApplicationScheduler.class);

    //todo @Scheduled(cron = "*/2 * * * * *")
    public void test() {
        LOGGER.info("Uruchomiono zadanie schedulera");
    }

    @Scheduled(cron = "*/2 * * * * *") //todo zmienic na co godzine
    public void addNewRssFeedsScheduler() throws IOException, FeedException, RssFeedsNotFoundException {
        rssOldItemAddService.rssAddToRssOldItem();
    }

    @Scheduled(cron = "*/2 * * * * *") //todo zmienic na co godzine
    public void addSendNewRssFeedsScheduler() {
        sendingNotSentRssFeedsService.sendEmail();
    }

    //todo ustawić na co godzinę
    public void cleanNotConfirmedEmailToAddAndEmailToDelete() {
        cleanupService.cleanup(LocalDateTime.now().plusHours(24));
    }
}
