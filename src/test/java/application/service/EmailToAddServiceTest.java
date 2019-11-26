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
        EmailActive emailActiveResult1 = emailActiveService.addRecord(emailActive1);
        EmailActive emailActiveResult2 = emailActiveService.addRecord(emailActive2);
        EmailActive emailActiveResult3 = emailActiveService.addRecord(emailActive3);
        EmailToAdd emailToAdd1 = new EmailToAdd("m1");
        EmailToAdd emailToAdd2 = new EmailToAdd("n1");

        //when
        EmailToAdd emailToAddResult1 = emailToAddService.addRecord(emailToAdd1);
        EmailToAdd emailToAddResult2 = emailToAddService.addRecord(emailToAdd2);

        //then
        //todo
        Assert.assertEquals("", emailToAddResult1.getEmail());
        Assert.assertEquals("n1", emailToAddResult2.getEmail());

        //cleanup
        emailActiveRepository.delete(emailActiveResult1);
        emailActiveRepository.delete(emailActiveResult2);
        emailActiveRepository.delete(emailActiveResult3);
        emailToAddRepository.delete(emailToAddResult2);
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