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
        Assert.assertEquals(null, result1);
        Assert.assertEquals("n1", result2.getEmail());

        //cleanup
        emailActiveRepository.delete(emailActive1);
        emailActiveRepository.delete(emailActive2);
        emailActiveRepository.delete(emailActive3);
        emailToDeleteRepository.delete(emailToDelete2);
    }

    @Test
    public void removeRecord() {
        //given
        EmailToDelete emailToDelete = new EmailToDelete("m1");
        EmailToDelete resultEmailToDelete = emailToDeleteService.addRecord(emailToDelete);
        Long resultId = resultEmailToDelete.getId();

        //when
        emailToDeleteService.removeRecord(emailToDelete);

        //then
        Assert.assertEquals(false, emailToDeleteRepository.findById(resultId).isPresent());
    }
}