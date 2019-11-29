package application.service;

import application.domain.EmailActive;
import application.domain.EmailMessage;
import application.domain.RssOldItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SendingNotSentRssFeedsService {
    private SendEmailService sendEmailService;
    private RssOldItemService rssOldItemService;
    private EmailActiveService emailActiveService;
    private static Logger LOGGER = LoggerFactory.getLogger(SendingNotSentRssFeedsService.class);

    public SendingNotSentRssFeedsService(SendEmailService sendEmailService, RssOldItemService rssOldItemService, EmailActiveService emailActiveService) {
        this.sendEmailService = sendEmailService;
        this.rssOldItemService = rssOldItemService;
        this.emailActiveService = emailActiveService;
    }

    public String getEmailBody() {
        String bodyMail = "";
        if (rssOldItemService.isAtLeastRssItemNotSent()) {
            for (RssOldItem rssOldItem : rssOldItemService.getAll()) {
                bodyMail += "------ " + rssOldItem.getRssFeed().getFeedName() + " ------" + System.lineSeparator();
                bodyMail += rssOldItem.getTitle() + System.lineSeparator();
                bodyMail += rssOldItem.getUrl() + System.lineSeparator();
                bodyMail += rssOldItem.getDateTime().toLocalDate() + " " + rssOldItem.getDateTime().toLocalTime() + System.lineSeparator();
                bodyMail += "------ " + rssOldItem.getRssFeed().getFeedName() + " ------" + System.lineSeparator();
                bodyMail += System.lineSeparator();
            }
            LOGGER.info("Uteorzono body emaila: " + System.lineSeparator() + bodyMail);
        }
        return bodyMail;
    }

    public void sendEmail() {
        String emailBody = getEmailBody();
        if (emailActiveService.getAllEmailActive().size() > 0 && !emailBody.equals("")) {
            LOGGER.info("Wysyłanie emaili do " + emailActiveService.getAllEmailActive().size() + " odbiorców");
            for (EmailActive emailActive : emailActiveService.getAllEmailActive()) {
                EmailMessage emailMessage = new EmailMessage(emailActive.getEmail(), "Test", emailBody);
                LOGGER.info("Wysyłanie maila na adres: " + emailActive.getEmail());
                sendEmailService.sendMessage(emailMessage);
            }
            LOGGER.info("Zmiana statusów rss feeds na wysłane");
            rssOldItemService.changeStatusToSent();
        } else {
            LOGGER.info("Brak adresów email w bazie, zeby wysłać informacje lub body maila jest puste");
        }
    }
}
