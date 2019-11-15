package application.controller;

import application.domain.EmailToDelete;
import application.service.EmailToDeleteService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*") //todo
@RestController("/email/delete")
public class EmailToDeleteController {
    private EmailToDeleteService emailToDeleteService;

    public EmailToDeleteController(EmailToDeleteService emailToDeleteService) {
        this.emailToDeleteService = emailToDeleteService;
    }

    @PostMapping(name = "/{email}")
    public EmailToDelete addEmailToDelete(@RequestParam String email) {
        return emailToDeleteService.addRecord(new EmailToDelete(email));
    }

    @PostMapping(name = "/{id}")
    public boolean confirmEmailDelete(@RequestParam Long id) {
        return emailToDeleteService.confirmDelete(id);
    }

}
