package org.example.mail;

import org.example.bean.Employee;
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


    public void sentThymeleafHtml(Employee employee){
        Context context=new Context();
        context.setVariable("name", employee.getName());
        context.setVariable("posName", employee.getPosition().getName());
        context.setVariable("joblevelName", employee.getjObLevel().getName());
        context.setVariable("departmentName", employee.getDepartment().getName());
        String emailTemplate =this.templateEngine.process("emailTemplate", context);
        mailService.sendHtmlMail(emailTemplate,employee);
    }
}
