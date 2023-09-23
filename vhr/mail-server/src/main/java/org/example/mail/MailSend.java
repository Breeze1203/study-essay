package org.example.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * @projectName: vhr
 * @package: org.example.mail
 * @className: MailSend
 * @author: pt3548297839
 * @description: TODO
 * @date: 2023/9/22 21:36
 * @version: 1.0
 */
@Component
public class MailSend {
    @Autowired
    public MailService mailService;

    @Autowired
    public TemplateEngine templateEngine;


    public void sentThymeleafHtml(User user){
        System.out.println("send:"+user);
        Context context=new Context();
        context.setVariable("name", user.getName());
        context.setVariable("posName", user.getPosName());
        context.setVariable("joblevelName", user.getJoblevelName());
        context.setVariable("departmentName", user.getDepartmentName());
        String emailTemplate =this.templateEngine.process("emailTemplate", context);
        mailService.sendHtmlMail(emailTemplate,user);
    }
}
