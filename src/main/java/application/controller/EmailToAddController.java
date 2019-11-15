package application.controller;

import application.domain.EmailToAdd;
import application.exception.EmailToAddNotFoundException;
import application.service.EmailToAddService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*") //todo
@RestController
@RequestMapping("emails/add")
public class EmailToAddController {
    private EmailToAddService emailToAddService;

    public EmailToAddController(EmailToAddService emailToAddService) {
        this.emailToAddService = emailToAddService;
    }

    @PostMapping
    public EmailToAdd addEmailToAdd(@RequestParam(name = "email") String email) {
        return emailToAddService.addRecord(new EmailToAdd(email));
    }

    @PostMapping("/{id}")
    public boolean confirmEmailDelete(@PathVariable Long id) throws EmailToAddNotFoundException {
        return emailToAddService.confirmAdd(id);
    }
}
