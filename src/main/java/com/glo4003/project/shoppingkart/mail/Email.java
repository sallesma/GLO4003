package com.glo4003.project.shoppingkart.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.glo4003.project.user.model.UserConcreteModel;

public class Email {
	private static String USER_NAME = "equipeb2.glo4003";  // GMail user name (just the part before "@gmail.com")
    private static String PASSWORD = "equipeb2_glo4003"; // GMail password
    private String RECIPIENT;
    
    
    public Email(UserConcreteModel user) {
		// TODO Auto-generated constructor stub
		this.RECIPIENT = user.getEmail();
	}
    
    public Email(String recipientAdress){
    	this.RECIPIENT = recipientAdress;
    }

    public void sendFromGMail() {
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", USER_NAME);
        props.put("mail.smtp.password", PASSWORD);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);
        
        String[] to = { RECIPIENT };

        try {
            message.setFrom(new InternetAddress(USER_NAME));
            InternetAddress[] toAddress = new InternetAddress[to.length];

            // To get the array of addresses
            for( int i = 0; i < to.length; i++ ) {
                toAddress[i] = new InternetAddress(to[i]);
            }

            for( int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }

            message.setSubject("[Billeterie Rouge&Or]Confirmation de votre commande");
            message.setText("Votre commande de billets sur le site de Rouge et Or s'est bien passée.");
            Transport transport = session.getTransport("smtp");
            transport.connect(host, USER_NAME, PASSWORD);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        catch (AddressException ae) {
            ae.printStackTrace();
        }
        catch (MessagingException me) {
            me.printStackTrace();
        }
    }
    
    public String getRecipient(){
    	return this.RECIPIENT;
    }
    
    public void setRecipient(String entry){
    	this.RECIPIENT = entry;
    }
}
