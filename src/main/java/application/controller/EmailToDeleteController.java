package application.controller;

import application.domain.EmailToDelete;
import application.service.EmailToDeleteService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*") //todo
@RestController
@RequestMapping("/email/delete")
public class EmailToDeleteController {
    private EmailToDeleteService emailToDeleteService;

    public EmailToDeleteController(EmailToDeleteService emailToDeleteService) {
        this.emailToDeleteService = emailToDeleteService;
    }

    @PostMapping("/{email}")
    public EmailToDelete addEmailToDelete(@PathVariable String email) {
        return emailToDeleteService.addRecord(new EmailToDelete(email));
    }

    @PostMapping("/{id}")
    public boolean confirmEmailDelete(@PathVariable Long id) {
        return emailToDeleteService.confirmDelete(id);
    }

}
