package application.service;

import application.domain.EmailToAdd;
import application.repository.EmailToAddRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EmailToAddService {
    private EmailToAddRepository emailToAddRepository;
    private EmailActiveService emailActiveService;
    private static Logger LOGGER = LoggerFactory.getLogger(EmailToAddService.class);

    public EmailToAddService(EmailToAddRepository emailToAddRepository, EmailActiveService emailActiveService) {
        this.emailToAddRepository = emailToAddRepository;
        this.emailActiveService = emailActiveService;
    }

    public EmailToAdd addRecord(EmailToAdd emailToAdd) {
        if (!emailActiveService.isEmailActiveExists(emailToAdd.getEmail())) {
            LOGGER.info("Dodanie rekordu: " + emailToAdd.getEmail());
            return emailToAddRepository.save(emailToAdd);
        }
        LOGGER.info("Rekord istnieje, nie dodano rekordu: " + emailToAdd.getEmail());
        //todo
        return null;
    }

    public void removeRecord(EmailToAdd emailToAdd) {
        LOGGER.info("Usowanie rekordu: " + emailToAdd.getEmail());
        emailToAddRepository.delete(emailToAdd);
    }
}
