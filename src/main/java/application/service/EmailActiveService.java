package application.service;

import application.domain.EmailActive;
import application.exception.EmailActiveNotFoundException;
import application.repository.EmailActiveRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailActiveService {
    private EmailActiveRepository emailActiveRepository;
    //todo
    private static Logger LOGGER = LoggerFactory.getLogger(EmailActiveService.class);

    public EmailActiveService(EmailActiveRepository emailActiveRepository) {
        this.emailActiveRepository = emailActiveRepository;
    }

    public EmailActive addRecord(EmailActive emailActive) {
        if (!isEmailActiveExists(emailActive.getEmail())) {
            LOGGER.info("Dodanie rekordu: " + emailActive.getEmail());
            return emailActiveRepository.save(emailActive);
        }
        //todo
        LOGGER.info("Rekord istnieje, nie dodano rekordu: " + emailActive.getEmail());
        return new EmailActive("");
    }

    public void removeRecord(EmailActive emailActive) {
        LOGGER.info("Usuniecie rekordu: " + emailActive.getEmail());
        emailActiveRepository.delete(emailActive);
    }

    public boolean isEmailActiveExists(String email) {
        return emailActiveRepository.findByEmail(email).isPresent();
    }

    public EmailActive findEmailActiveByEmail(String email) throws EmailActiveNotFoundException {
        return emailActiveRepository.findByEmail(email).orElseThrow(EmailActiveNotFoundException::new);
    }

    public List<EmailActive> getAllEmailActive() {
        return emailActiveRepository.findAll();
    }
}
