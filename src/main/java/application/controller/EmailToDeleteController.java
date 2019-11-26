package application.controller;

import application.domain.EmailToDelete;
import application.exception.EmailToDeleteNotFoundException;
import application.service.EmailToDeleteService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*") //todo
@RestController
@RequestMapping("emails/delete")
public class EmailToDeleteController {
    private EmailToDeleteService emailToDeleteService;

    public EmailToDeleteController(EmailToDeleteService emailToDeleteService) {
        this.emailToDeleteService = emailToDeleteService;
    }

    @PostMapping
    public EmailToDelete addEmailToDelete(@RequestParam(name = "email") String email) {
        return emailToDeleteService.addRecord(new EmailToDelete(email));
    }

    @PostMapping("/{recordKey}")
    public boolean confirmEmailDelete(@PathVariable String recordKey) throws EmailToDeleteNotFoundException {
        return emailToDeleteService.confirmDelete(recordKey);
    }

}
