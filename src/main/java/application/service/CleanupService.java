package application.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class CleanupService {
    private EmailToAddService emailToAddService;
    private EmailToDeleteService emailToDeleteService;
    private static Logger LOGGER = LoggerFactory.getLogger(CleanupService.class);

    public CleanupService(EmailToAddService emailToAddService, EmailToDeleteService emailToDeleteService) {
        this.emailToAddService = emailToAddService;
        this.emailToDeleteService = emailToDeleteService;
    }

    private void cleanupEmailToAdd(LocalDateTime localDateTime) {
        LOGGER.info("Rozpoczęcie cleanupu dla EmailToAdd");
        emailToAddService.getAllEmailToAdd().stream()
                .filter(emailToAdd -> emailToAdd.getDateTime().isBefore(localDateTime))
                .forEach(emailToAdd -> emailToAddService.removeRecord(emailToAdd));
    }

    private void cleanupEmailToDelete(LocalDateTime localDateTime) {
        LOGGER.info("Rozpoczęcie cleanupu dla EmailToDelete");
        emailToDeleteService.getAllEmailToDelete().stream()
                .filter(emailToDelete -> emailToDelete.getDateTime().isBefore(localDateTime))
                .forEach(emailToDelete -> emailToDeleteService.removeRecord(emailToDelete));
    }

    public void cleanup(LocalDateTime localDateTime) {
        cleanupEmailToAdd(localDateTime);
        cleanupEmailToDelete(localDateTime);
    }
}
