
package com.websystique.springsecurity.service;

import com.websystique.springsecurity.model.Student;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.MimeMessageHelper;

@Service("mailService")
public class MailService {
    
    @Autowired
    JavaMailSender mailSender;
    
   
    public void sendMail(String messageHTML, List<Student> students) {
       
        try 
        {   //получатели сообщений
            InternetAddress[] adresats = new InternetAddress[students.size()];            
            for(int j=0; j< adresats.length; j++){              
                adresats[j] = new InternetAddress(students.get(j).getMail());
            }                                                
            MimeMessage  mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");  
            mimeMessage.setSubject("ВК-информер: оповещение от преподавателей", "utf-8");
            mimeMessage.setContent(messageHTML, "text/html; charset=UTF-8"); 
            helper.setTo(adresats);          
            mailSender.send(mimeMessage);
        } 
        catch (MessagingException ex) {
            Logger.getLogger(MailService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
