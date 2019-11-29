package application.service;

import application.configuration.ApplicationConfiguration;
import application.domain.EmailActive;
import application.domain.EmailMessage;
import application.domain.EmailToAdd;
import application.exception.EmailToAddNotFoundException;
import application.repository.EmailToAddRepository;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailToAddService {
    private EmailToAddRepository emailToAddRepository;
    private EmailActiveService emailActiveService;
    private SendEmailService sendEmailService;
    private ApplicationConfiguration applicationConfiguration;
    private static Logger LOGGER = LoggerFactory.getLogger(EmailToAddService.class);

    public EmailToAddService(EmailToAddRepository emailToAddRepository, EmailActiveService emailActiveService,
                             SendEmailService sendEmailService, ApplicationConfiguration applicationConfiguration) {
        this.emailToAddRepository = emailToAddRepository;
        this.emailActiveService = emailActiveService;
        this.sendEmailService = sendEmailService;
        this.applicationConfiguration = applicationConfiguration;
    }

    public EmailToAdd addRecord(@NotNull EmailToAdd emailToAdd) {
        if (!emailActiveService.isEmailActiveExists(emailToAdd.getEmail()) && !isEmailToAddExists(emailToAdd.getEmail())) {
            LOGGER.info("Dodanie rekordu: " + emailToAdd.getEmail());
            EmailToAdd emailToAddSaved = emailToAddRepository.save(emailToAdd);
            EmailMessage emailMessage = new EmailMessage(emailToAdd.getEmail(), "Potwierdzenie dodania adresu",
                    "Kliknij na poniższy link w celu potwierdzenia dodania adresu email:" + System.lineSeparator()
                            + applicationConfiguration.getBaseUrl() + applicationConfiguration.getEmailAddUrl()
                            + "/" + emailToAddSaved.getRecordKey());
            sendEmailService.sendMessage(emailMessage);
            return emailToAddSaved;
        }
        LOGGER.info("Rekord istnieje, nie dodano rekordu: " + emailToAdd.getEmail());
        //todo
        return new EmailToAdd("");
    }

    public void removeRecord(EmailToAdd emailToAdd) {
        LOGGER.info("Usowanie rekordu: " + emailToAdd.getEmail());
        emailToAddRepository.delete(emailToAdd);
    }

    public boolean isEmailToAddExists(String email) {
        return emailToAddRepository.findByEmail(email).isPresent();
    }

    //todo
    //public boolean isEmailToAddExists(Long id) {
    //    return emailToAddRepository.findById(id).isPresent();
    //}

    public boolean isEmailToAddExistsByRecordKey(String recordKey) {
        return emailToAddRepository.findByRecordKey(recordKey).isPresent();
    }

    public boolean confirmAdd(String recordKey) throws EmailToAddNotFoundException {
        if (isEmailToAddExistsByRecordKey(recordKey)) {
            LOGGER.info("Udane potwierdzone usuniecie rekordu o kluczu: " + recordKey);
            emailActiveService.addRecord(new EmailActive(emailToAddRepository.findByRecordKey(recordKey).orElseThrow(EmailToAddNotFoundException::new).getEmail()));
            emailToAddRepository.delete(emailToAddRepository.findByRecordKey(recordKey).orElseThrow(EmailToAddNotFoundException::new));
            return true;
        }
        LOGGER.info("Nieudane potwierdzenie usuniecia rekordu o kluczu: " + recordKey);
        return false;
    }

    public List<EmailToAdd> getAllEmailToAdd() {
        return emailToAddRepository.findAll();
    }
}
