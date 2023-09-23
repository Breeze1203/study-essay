package org.example;


import org.example.mail.MailConsumer;
import org.example.mail.MailSend;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Unit test for simple MailServerApplication.
 */
@SpringBootTest
public class MailServerApplicationTest {
    @Autowired
    MailConsumer mailConsumer;
    @Autowired
    MailSend mailSend;

    @Test
    void contextLoads() {
//		mailSend.sendText();
        mailConsumer.receive();
        //mailSend.sendAttachment();
    }
}
