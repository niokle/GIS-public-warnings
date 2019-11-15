package application.controller;

import application.domain.EmailToAdd;
import application.service.EmailToAddService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*") //todo
@RestController
@RequestMapping("/email/add")
public class EmailToAddController {
    private EmailToAddService emailToAddService;

    public EmailToAddController(EmailToAddService emailToAddService) {
        this.emailToAddService = emailToAddService;
    }

    @PostMapping("/{email}")
    public EmailToAdd addEmailToAdd(@PathVariable String email) {
        return emailToAddService.addRecord(new EmailToAdd(email));
    }

    @PostMapping("/{id}")
    public boolean confirmEmailDelete(@PathVariable Long id) {
        return emailToAddService.confirmDelete(id);
    }
}
