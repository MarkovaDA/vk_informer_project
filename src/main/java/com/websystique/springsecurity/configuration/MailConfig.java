
package com.websystique.springsecurity.configuration;

import java.util.Properties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@ComponentScan(basePackages = "com.websystique.springsecurity")
public class MailConfig {
    
    @Bean
    public JavaMailSender getMailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();        
        //Using gmail
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(25);
        mailSender.setProtocol("smtp");
        mailSender.setDefaultEncoding("utf-8");
        mailSender.setUsername("darya.towarddreams@gmail.com");
        mailSender.setPassword("19Istina95Google");
              
        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.starttls.enable", "true");
        javaMailProperties.put("mail.smtp.auth", "true");
        javaMailProperties.put("mail.debug", "true");
        javaMailProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        mailSender.setJavaMailProperties(javaMailProperties);       
        return mailSender;
    }
}
