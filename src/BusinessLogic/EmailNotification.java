package BusinessLogic;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.itextpdf.text.Document;
import javax.activation.*;  



public class EmailNotification{

    public static void SendNotification(String user1,String user2,String subject,String messages,String date){
        

        
        final String user="dayna.ashley2017@gmail.com";//change accordingly  
        final String password="dayna44123456";//change accordingly  
          
        String to=user1;
        String to1=user2;//change accordingly  
        
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
        
         //Compose the message  
          try {  
           MimeMessage message = new MimeMessage(session);  
           message.setFrom(new InternetAddress(user));  
           message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
           message.addRecipient(Message.RecipientType.TO,new InternetAddress(to1));  
           message.setSubject(subject+"   "+date);  
           
          message.setText(messages);

            
             
          //send the message  
           Transport.send(message);  
        
           System.out.println("Message Sent Successfully...");  
         
           } catch (MessagingException e) {e.printStackTrace();}  
       }  



       public static void SendAttachment(String user1,String subject,String messages,String pdf,String date){
          
          final String user="dayna.ashley2017@gmail.com";//change accordingly  
          final String password="dayna44123456";//change accordingly  
            
          String to=user1;
           
          
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
          
          //Compose the message  
            try {  
            MimeMessage message = new MimeMessage(session);  
            message.setFrom(new InternetAddress(user));  
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
          
            message.setSubject(subject+"   "+date);  
            
            
            BodyPart messageBodyPart1 = new MimeBodyPart();  
            messageBodyPart1.setText(messages);  
        
            //4) create new MimeBodyPart object and set DataHandler object to this object      
            MimeBodyPart messageBodyPart2 = new MimeBodyPart();  
          
            String filename = pdf;//change accordingly  
            DataSource source = new FileDataSource(filename);  
            messageBodyPart2.setDataHandler(new DataHandler(source));  
            messageBodyPart2.setFileName("Pay Slip.pdf");  
            
            
            //5) create Multipart object and add MimeBodyPart objects to this object      
            Multipart multipart = new MimeMultipart();  
            multipart.addBodyPart(messageBodyPart1);  
            multipart.addBodyPart(messageBodyPart2);  
          
            //6) set the multiplart object to the message object  
            message.setContent(multipart );  
          
  
              
            //send the message  
            Transport.send(message);  
          
            System.out.println("Message Sent Successfully...");  
          
            } catch (MessagingException e) {e.printStackTrace();}  
        }  
}

