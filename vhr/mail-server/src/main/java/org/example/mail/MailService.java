package org.example.mail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.example.bean.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

/**
 * @projectName: vhr
 * @package: org.example.mail
 * @className: MailService
 * @author: pt3548297839
 * @description: TODO
 * @date: 2023/9/22 21:31
 * @version: 1.0
 */
@Component
public class MailService {
    @Autowired
    JavaMailSender javaMailSender;

    public void sendHtmlMail(String context, Employee employee){
//        利用template模版创建发送html邮件
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom("3548297839@qq.com");
            mimeMessageHelper.setSubject("入职通知");
            mimeMessageHelper.setText(context,true);
            mimeMessageHelper.setTo(employee.getEmail());
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
