package com.wps.job;

import com.wps.mail.SendMailService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Configuration
@EnableScheduling
public class TestJob {
    Logger logger = LogManager.getLogger(TestJob.class);

    @Autowired
    private SendMailService sendMailService;

    @Scheduled(cron = "*/50 * * * * *")
    public void run(){
        logger.info(">>>>>>>定时器运行了！......");
        //sendMailService.sendQQ();
    }
}
