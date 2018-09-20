package com.danger.kafka.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by xiaojie.Ma on 2018/9/20.
 */
@Component
public class KafkaSender {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    /**
     * 发送消息到kafka,主题为test
     */
    public void sendTest(){
        kafkaTemplate.send("mytest-topic","hello,kafka  "+new Date());
    }

}
