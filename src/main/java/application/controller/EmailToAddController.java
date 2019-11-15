package application.controller;

import application.domain.EmailToAdd;
import application.service.EmailToAddService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*") //todo
@RestController("/email/add")
public class EmailToAddController {
    private EmailToAddService emailToAddService;

    public EmailToAddController(EmailToAddService emailToAddService) {
        this.emailToAddService = emailToAddService;
    }

    @PostMapping(name = "/{email}")
    public EmailToAdd addEmailToAdd(@RequestParam String email) {
        return emailToAddService.addRecord(new EmailToAdd(email));
    }

    @PostMapping(name = "/{id}")
    public boolean confirmEmailDelete(@RequestParam Long id) {
        return emailToAddService.confirmDelete(id);
    }
}
