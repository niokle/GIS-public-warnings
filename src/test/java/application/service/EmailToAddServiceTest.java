package application.service;

import application.domain.EmailActive;
import application.domain.EmailToAdd;
import application.repository.EmailActiveRepository;
import application.repository.EmailToAddRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailToAddServiceTest {

    @Autowired
    EmailActiveRepository emailActiveRepository;

    @Autowired
    EmailActiveService emailActiveService;

    @Autowired
    EmailToAddRepository emailToAddRepository;

    @Autowired
    EmailToAddService emailToAddService;

    @Test
    public void addRecord() {
        //given
        EmailActive emailActive1 = new EmailActive("m1");
        EmailActive emailActive2 = new EmailActive("m2");
        EmailActive emailActive3 = new EmailActive("m3");
        emailActiveService.addRecord(emailActive1);
        emailActiveService.addRecord(emailActive2);
        emailActiveService.addRecord(emailActive3);
        EmailToAdd emailToAdd1 = new EmailToAdd("m1");
        EmailToAdd emailToAdd2 = new EmailToAdd("n1");

        //when
        EmailToAdd result1 = emailToAddService.addRecord(emailToAdd1);
        EmailToAdd result2 = emailToAddService.addRecord(emailToAdd2);

        //then
        //todo
        Assert.assertEquals(null, result1);
        Assert.assertEquals("n1", result2.getEmail());

        //cleanup
        emailActiveRepository.delete(emailActive1);
        emailActiveRepository.delete(emailActive2);
        emailActiveRepository.delete(emailActive3);
        emailToAddRepository.delete(emailToAdd2);
    }

    @Test
    public void removeRecord() {
        //given
        EmailToAdd emailToAdd = new EmailToAdd("m1");
        EmailToAdd resultEmailToAdd = emailToAddService.addRecord(emailToAdd);
        Long resultId = resultEmailToAdd.getId();

        //when
        emailToAddService.removeRecord(emailToAdd);

        //then
        Assert.assertEquals(false, emailToAddRepository.findById(resultId).isPresent());
    }
}