package com.danger;

import com.danger.kafka.sender.KafkaSender;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author xiaojie.Ma
 * Created by xiaojie.Ma on 2018/5/9.
 */
@SpringBootApplication
@ServletComponentScan
@MapperScan("com.danger.bean")
public class DangerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DangerApplication.class,args);
    }


}
