package application.scheduler;

import application.exception.RssFeedsNotFoundException;
import application.service.CleanupService;
import application.service.RssOldItemAddService;
import application.service.SendEmailService;
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
    private RssOldItemAddService rssOldItemAddService;
    private SendingNotSentRssFeedsService sendingNotSentRssFeedsService;
    private CleanupService cleanupService;

    private static Logger LOGGER = LoggerFactory.getLogger(ApplicationScheduler.class);

    public ApplicationScheduler(RssOldItemAddService rssOldItemAddService, SendingNotSentRssFeedsService sendingNotSentRssFeedsService,
                                CleanupService cleanupService) {
        this.rssOldItemAddService = rssOldItemAddService;
        this.sendingNotSentRssFeedsService = sendingNotSentRssFeedsService;
        this.cleanupService = cleanupService;
    }
    //@Scheduled(cron = "*/2 * * * * *") //todo
    public void test() {
        LOGGER.info("Uruchomiono zadanie schedulera");
    }

    @Scheduled(cron = "*/2 * * * * *") //todo zmienic na co godzine
    public void addNewRssFeedsScheduler() throws IOException, FeedException, RssFeedsNotFoundException {
        LOGGER.info("Start zadania schedulera");
        rssOldItemAddService.rssAddToRssOldItem();
        LOGGER.info("Koniec zadania schedulera");
    }

    //@Scheduled(cron = "*/2 * * * * *") //todo zmienic na co godzine
    public void addSendNewRssFeedsScheduler() {
        LOGGER.info("Start zadania schedulera");
        sendingNotSentRssFeedsService.sendEmail();
        LOGGER.info("Koniec zadania schedulera");
    }

    //todo ustawić na co godzinę
    public void cleanNotConfirmedEmailToAddAndEmailToDelete() {
        LOGGER.info("Start zadania schedulera");
        cleanupService.cleanup(LocalDateTime.now().plusHours(24));
        LOGGER.info("Koniec zadania schedulera");
    }
}
