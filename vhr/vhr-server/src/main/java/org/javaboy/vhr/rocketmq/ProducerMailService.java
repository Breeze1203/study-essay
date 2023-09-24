package org.javaboy.vhr.rocketmq;

import org.javaboy.vhr.bean.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @projectName: vhr
 * @package: org.javaboy.vhr.rocketmq
 * @className: ProducerMailService
 * @author: pt3548297839
 * @description: TODO
 * @date: 2023/9/23 11:31
 * @version: 1.0
 */
@Component
public class ProducerMailService {
    @Autowired
    private Producer producer;
    public void send(Employee employee){
        producer.sendMessage(employee);
    }
}
