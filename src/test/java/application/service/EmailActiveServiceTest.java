package application.service;

import application.domain.EmailActive;
import application.repository.EmailActiveRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailActiveServiceTest {

    @Autowired
    EmailActiveRepository emailActiveRepository;

    @Autowired
    EmailActiveService emailActiveService;

    @Test
    public void addRecord() {
        //given
        EmailActive emailActive1 = new EmailActive("m1");
        EmailActive emailActive2 = new EmailActive("m2");
        EmailActive emailActive3 = new EmailActive("m3");

        //when
        EmailActive result1 = emailActiveService.addRecord(emailActive1);
        EmailActive result2 = emailActiveService.addRecord(emailActive2);
        EmailActive result3 = emailActiveService.addRecord(emailActive3);

        //then
        Assert.assertEquals(3, emailActiveRepository.count());

        //cleanup
        emailActiveRepository.delete(emailActive1);
        emailActiveRepository.delete(emailActive2);
        emailActiveRepository.delete(emailActive3);
    }

    @Test
    public void removeRecord() {
        //given
        EmailActiveService emailActiveService = new EmailActiveService(emailActiveRepository);
        EmailActive emailActive1 = new EmailActive("m1");
        EmailActive emailActive2 = new EmailActive("m2");
        EmailActive emailActive3 = new EmailActive("m3");

        //when
        emailActiveService.addRecord(emailActive1);
        emailActiveService.addRecord(emailActive2);
        emailActiveService.addRecord(emailActive3);
        emailActiveService.removeRecord(emailActive1);
        emailActiveService.removeRecord(emailActive2);

        //then
        Assert.assertEquals(false, emailActiveRepository.findByEmail("m1").isPresent());
        Assert.assertEquals(false, emailActiveRepository.findByEmail("m2").isPresent());
        Assert.assertEquals(true, emailActiveRepository.findByEmail("m3").isPresent());

        //cleanup
        emailActiveService.removeRecord(emailActive3);
    }

    @Test
    public void isEmailActiveExists() {
        //given
        EmailActiveService emailActiveService = new EmailActiveService(emailActiveRepository);
        EmailActive emailActive1 = new EmailActive("m1");

        //when
        emailActiveService.addRecord(emailActive1);
        boolean resultTrue = emailActiveService.isEmailActiveExists("m1");
        boolean resultFalse = emailActiveService.isEmailActiveExists("xx");

        //then
        Assert.assertTrue(resultTrue);
        Assert.assertFalse(resultFalse);

        //cleanup
        emailActiveRepository.delete(emailActive1);
    }
}