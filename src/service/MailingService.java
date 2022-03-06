/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;


/**
 *
 * @author EYA
 */
    import javax.mail.Authenticator;


import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;
import model.Commande;

public class MailingService {


 
    public static void sendMail(String recepient,String code,int pourcentage) throws Exception {
        // TODO code application logic here
        Properties properties=new Properties();
        properties.put("mail.smtp.auth","true");
         properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");
        
        String myacount="eyabensalem0902@gmail.com";
        String password="sefirinkizi1605";
        
        Session session=Session.getInstance(properties,new Authenticator(){
        @Override
        protected PasswordAuthentication getPasswordAuthentication()
        {
            return new PasswordAuthentication(myacount,password);
            
        }
    });
        
        Message message=prepareMessage(session,myacount,code,pourcentage);
        Transport.send(message);
        System.out.println("done");
    }
    private static Message prepareMessage(Session session, String myacount,String code,int pourcentage){
        
       Message message=new MimeMessage(session);
        try{ 
        message.setFrom(new InternetAddress(myacount));
        message.setRecipient(Message.RecipientType.TO,new InternetAddress("eyabensalem0902@gmail.com"));
       
        message.setSubject("Code réduction");
                
                message.setText("Votre code promo est disponible tapez "+ code + " pour l'utiliser et obtenir jusqu'a "+pourcentage + " % de reduction sur l'achat");

               
                return message;
                }catch(MessagingException e)
                {
                    
                }
        return message;
    }
}


