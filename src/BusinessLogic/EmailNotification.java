package BusinessLogic;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


import javax.activation.*;  



public class EmailNotification{

    public static void SendNotification(String user1,String user2,String subject,String messages,String date){
        

        
        final String user="dayna.ashley2017@gmail.com"; 
        final String password="dayna44123456";  
          
        String to=user1;
        String to1=user2;  
        
         //Get the session object  
         Properties props = new Properties();  
         props.put("mail.smtp.auth", "true");  
         props.put("mail.smtp.starttls.enable","true");  
         props.put("mail.smtp.host","smtp.gmail.com");  
         props.put("mail.smtp.port","587"); 
                    
         Session session = Session.getInstance(props,  new Authenticator() {  
            protected PasswordAuthentication getPasswordAuthentication() {  
          return new PasswordAuthentication(user,password);  
            }  
          });  
        
          
          try {  
           MimeMessage message = new MimeMessage(session);  
           message.setFrom(new InternetAddress(user));  
           message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
           message.addRecipient(Message.RecipientType.TO,new InternetAddress(to1));  
           message.setSubject(subject+"   "+date);  
           
          message.setText(messages);

            
             
          
           Transport.send(message);  
        
           System.out.println("Message Sent Successfully...");  
         
           } catch (MessagingException e) {e.printStackTrace();}  
       }  



       public static void SendAttachment(String user1,String subject,String messages,String pdf,String date){
          
          final String user="dayna.ashley2017@gmail.com";
          final String password="dayna44123456";  
            
          String to=user1;
           
          
           
          Properties props = new Properties();  
          props.put("mail.smtp.auth", "true");  
          props.put("mail.smtp.starttls.enable","true");  
          props.put("mail.smtp.host","smtp.gmail.com");  
          props.put("mail.smtp.port","587"); 
                      
          Session session = Session.getInstance(props,  new Authenticator() {  
              protected PasswordAuthentication getPasswordAuthentication() {  
            return new PasswordAuthentication(user,password);  
              }  
            });  
          
         
            try {  
            MimeMessage message = new MimeMessage(session);  
            message.setFrom(new InternetAddress(user));  
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
          
            message.setSubject(subject+"   "+date);  
            
            
            BodyPart messageBodyPart1 = new MimeBodyPart();  
            messageBodyPart1.setText(messages);  
        
               
            MimeBodyPart messageBodyPart2 = new MimeBodyPart();  
          
            String filename = pdf;
            DataSource source = new FileDataSource(filename);  
            messageBodyPart2.setDataHandler(new DataHandler(source));  
            messageBodyPart2.setFileName("Pay Slip.pdf");  
            
            
          
            Multipart multipart = new MimeMultipart();  
            multipart.addBodyPart(messageBodyPart1);  
            multipart.addBodyPart(messageBodyPart2);  
          
        
            message.setContent(multipart );  
          
  
              
          
            Transport.send(message);  
          
            System.out.println("Message Sent Successfully...");  
          
            } catch (MessagingException e) {e.printStackTrace();}  
        }  
}

