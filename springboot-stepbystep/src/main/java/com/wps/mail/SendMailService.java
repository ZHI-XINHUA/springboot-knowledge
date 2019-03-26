package com.wps.mail;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * 邮件发送
 */
@Service
public class SendMailService {
    Logger logger = LogManager.getLogger(SendMailService.class);

    @Autowired
    JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromSend;

    public void sendQQ(){
        logger.info(">>>>>>>>>>>发送邮件信息!!!!!!!!");
        MimeMessage mimeMessage =  mailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage);

        try {
            //邮件发送方
            message.setFrom(fromSend);
            //邮寄主题
            message.setSubject("今晚打老虎");
            //邮件接收方
            message.setTo("837904664@qq.com");
            //邮件内容
            message.setText("全场9.9元......手快有手慢无");

           // mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
