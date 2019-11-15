package application.service;

import application.domain.EmailActive;
import application.domain.EmailToDelete;
import application.repository.EmailToDeleteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EmailToDeleteService {
    private EmailToDeleteRepository emailToDeleteRepository;
    private EmailActiveService emailActiveService;
    private static Logger LOGGER = LoggerFactory.getLogger(EmailToDelete.class);

    public EmailToDeleteService(EmailToDeleteRepository emailToDeleteRepository, EmailActiveService emailActiveService) {
        this.emailToDeleteRepository = emailToDeleteRepository;
        this.emailActiveService = emailActiveService;
    }

    public EmailToDelete addRecord(EmailToDelete emailToDelete) {
        if (!emailActiveService.isEmailActiveExists(emailToDelete.getEmail()) && !isEmailToDeleteExists(emailToDelete.getEmail())) {
            LOGGER.info("Dodanie rekordu: " + emailToDelete.getEmail());
            return emailToDeleteRepository.save(emailToDelete);
        }
        LOGGER.info("Rekord istnieje, nie dodano rekordu: " + emailToDelete.getEmail());
        //todo
        return null;
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

    public boolean confirmDelete(Long id) {
        if (isEmailToDeleteExists(id)) {
            LOGGER.info("Udane potwierdzone usuniecie rekordu o id: " + id);
            emailActiveService.addRecord(new EmailActive(emailToDeleteRepository.findById(id).get().getEmail()));
            emailToDeleteRepository.delete(emailToDeleteRepository.findById(id).get());
            return true;
        }
        LOGGER.info("Nieudane potwierdzenie usuniecia rekordu o id: " + id);
        return false;
    }
}
