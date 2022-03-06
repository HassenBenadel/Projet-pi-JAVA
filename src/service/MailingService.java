package service;
    
import javax.mail.Authenticator;


import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;

public class MailingService {


 
    public void sendMail(String recepient , int code) throws Exception {
        // TODO code application logic here
        Properties properties=new Properties();
        properties.put("mail.smtp.auth","true");
         properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");
        
        String myacount="hassenbenadel37@gmail.com";
        String password="tectecca1";
        
        Session session=Session.getInstance(properties,new Authenticator(){
        @Override
        protected PasswordAuthentication getPasswordAuthentication()
        {
            return new PasswordAuthentication(myacount,password);
            
        }
    });
        Message message=prepareMessage(session,myacount ,code);
        Transport.send(message);
        
        System.out.println("done");
    }
    private static Message prepareMessage(Session session, String myacount , int code){
       Message message=new MimeMessage(session);
        try{ 
        message.setFrom(new InternetAddress(myacount));
        message.setRecipient(Message.RecipientType.TO,new InternetAddress("hassenbenadel37@gmail.com"));
       
        message.setSubject("password reset");
                
                message.setText("le code est " + code  );

               
                return message;
                }catch(MessagingException e)
                {
                    
                }
        return message;
    }
}
