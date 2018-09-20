//package com.danger.kafka.consumer;
//
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Component;
//
///**
// * Created by xiaojie.Ma on 2018/9/20.
// */
//@Component
//public class KafkaConsumer {
//
//    /**
//     * 监听test主题,有消息就读取
//     * @param message
//     */
//    @KafkaListener(topics = {"mytest-topic"})
//    public void consumer(String message){
//        System.out.println(message);
//    }
//
//}
