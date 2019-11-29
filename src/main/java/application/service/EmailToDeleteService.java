package application.service;

import application.configuration.ApplicationConfiguration;
import application.domain.EmailMessage;
import application.domain.EmailToDelete;
import application.exception.EmailActiveNotFoundException;
import application.exception.EmailToDeleteNotFoundException;
import application.repository.EmailToDeleteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailToDeleteService {
    private EmailToDeleteRepository emailToDeleteRepository;
    private EmailActiveService emailActiveService;
    private SendEmailService sendEmailService;
    private ApplicationConfiguration applicationConfiguration;
    private static Logger LOGGER = LoggerFactory.getLogger(EmailToDeleteService.class);

    public EmailToDeleteService(EmailToDeleteRepository emailToDeleteRepository, EmailActiveService emailActiveService,
                                SendEmailService sendEmailService, ApplicationConfiguration applicationConfiguration) {
        this.emailToDeleteRepository = emailToDeleteRepository;
        this.emailActiveService = emailActiveService;
        this.sendEmailService = sendEmailService;
        this.applicationConfiguration = applicationConfiguration;
    }

    public EmailToDelete addRecord(EmailToDelete emailToDelete) {
        if (emailActiveService.isEmailActiveExists(emailToDelete.getEmail()) && !isEmailToDeleteExists(emailToDelete.getEmail())) {
            LOGGER.info("Dodanie rekordu: " + emailToDelete.getEmail());
            EmailToDelete emailToDeleteSaved = emailToDeleteRepository.save(emailToDelete);
            EmailMessage emailMessage = new EmailMessage(emailToDelete.getEmail(), "Potwierdzenie usuniecia adresu",
                    "Kliknij na poniższy link w celu potwierdzenia usuniecia adresu email:" + System.lineSeparator()
                            + applicationConfiguration.getBaseUrl() + applicationConfiguration.getEmailDeleteUrl()
                            + "/" + emailToDeleteSaved.getRecordKey());
            sendEmailService.sendMessage(emailMessage);
            return emailToDeleteSaved;
        }
        LOGGER.info("Rekord nie istnieje, nie dodano rekordu: " + emailToDelete.getEmail());
        //todo
        return new EmailToDelete("");
    }

    public void removeRecord(EmailToDelete emailToDelete) {
        LOGGER.info("Usowanie rekordu: " + emailToDelete.getEmail());
        emailToDeleteRepository.delete(emailToDelete);
    }

    public boolean isEmailToDeleteExists(String email) {
        return emailToDeleteRepository.findByEmail(email).isPresent();
    }

    public boolean isEmailToDeleteExists(Long id) {
        return emailToDeleteRepository.findById(id).isPresent();
    }

    public boolean isEmailToDeleteExistsByRecordKey(String recordKey) {
        return emailToDeleteRepository.findByRecordKey(recordKey).isPresent();
    }

    public boolean confirmDelete(String recordKey) throws EmailToDeleteNotFoundException, EmailActiveNotFoundException {
        if (isEmailToDeleteExistsByRecordKey(recordKey)) {
            LOGGER.info("Udane potwierdzone usuniecie rekordu o kluczu: " + recordKey);
            emailActiveService.removeRecord(emailActiveService.findEmailActiveByEmail(emailToDeleteRepository.findByRecordKey(recordKey).orElseThrow(EmailToDeleteNotFoundException::new).getEmail()));
            emailToDeleteRepository.delete(emailToDeleteRepository.findByRecordKey(recordKey).orElseThrow(EmailToDeleteNotFoundException::new));
            return true;
        }
        LOGGER.info("Nieudane potwierdzenie usuniecia rekordu o id: " + recordKey);
        return false;
    }

    public List<EmailToDelete> getAllEmailToDelete() {
        return emailToDeleteRepository.findAll();
    }
}
