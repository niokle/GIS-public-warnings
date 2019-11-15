package application.service;

import application.domain.EmailActive;
import application.domain.EmailToDelete;
import application.repository.EmailActiveRepository;
import application.repository.EmailToDeleteRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailToDeleteServiceTest {

    @Autowired
    EmailActiveRepository emailActiveRepository;

    @Autowired
    EmailActiveService emailActiveService;

    @Autowired
    EmailToDeleteRepository emailToDeleteRepository;

    @Autowired
    EmailToDeleteService emailToDeleteService;

    @Test
    public void addRecord() {
        EmailActive emailActive1 = new EmailActive("m1");
        EmailActive emailActive2 = new EmailActive("m2");
        EmailActive emailActive3 = new EmailActive("m3");
        emailActiveService.addRecord(emailActive1);
        emailActiveService.addRecord(emailActive2);
        emailActiveService.addRecord(emailActive3);
        EmailToDelete emailToDelete1 = new EmailToDelete("m1");
        EmailToDelete emailToDelete2 = new EmailToDelete("n1");

        //when
        EmailToDelete result1 = emailToDeleteService.addRecord(emailToDelete1);
        EmailToDelete result2 = emailToDeleteService.addRecord(emailToDelete2);

        //then
        //todo
        Assert.assertEquals("m1", result1.getEmail());
        Assert.assertEquals(null, result2);

        //cleanup
        emailActiveRepository.delete(emailActive1);
        emailActiveRepository.delete(emailActive2);
        emailActiveRepository.delete(emailActive3);
        emailToDeleteRepository.delete(emailToDelete2);
    }

    @Test
    public void removeRecord() {
        //given
        String email = "m1";
        emailActiveService.addRecord(new EmailActive(email));
        EmailToDelete resultEmailToDelete = emailToDeleteService.addRecord(new EmailToDelete(email));
        Long resultId = resultEmailToDelete.getId();

        //when
        emailToDeleteService.removeRecord(resultEmailToDelete);

        //then
        Assert.assertFalse(emailToDeleteRepository.findById(resultId).isPresent());
        Assert.assertTrue(emailActiveRepository.findByEmail(email).isPresent());

        //cleanup
        emailActiveService.removeRecord(emailActiveRepository.findByEmail(email).get());
    }
}