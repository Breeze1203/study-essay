package org.example;


import org.example.mail.MailConsumer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class MailServerApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MailServerApplication.class, args);
        context.getBean(MailConsumer.class).receive();
    }
}
