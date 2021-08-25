package com.email;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class App 
{
    private static Properties properties;

	public static void main( String[] args )
    {
    	System.out.println("preparing to send message");
    	String message="this message for security check";
    	String subject="Learning Mail API";
    	String to="md.thtsme@gmail.com";
    	String from="mujahedshaikh404@gmail.com";
    	
    	sendEmail(message,subject,to,from);
    }

    //this methode is responsible to send email.....
	private static void sendEmail(String message, String subject, String to, String from) {
		//variable for gmail host
		String host="smtp.gmail.com";
		
		//get the system properties
		Properties properties = System.getProperties();
		System.out.println("PROPERTIES    "+properties);
		
		//setting important information to properties object
		
		//host set
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable","true");
		properties.put("mail.smtp.auth","true");
		
		
		//step:1 to get the session object
		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("mujahedshaikh404@gmail.com", "*********");
			}
		});
		session.setDebug(true);
		
		//step2: compose the message [text,multi media]
		MimeMessage mimemessage=new MimeMessage(session);
		try {
			//from email
			mimemessage.setFrom(from);
			
			//adding recipients
			mimemessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			
			//adding subject to message
			mimemessage.setSubject(subject);
			
			//adding text to message
			mimemessage.setText(message);
			
			
			//step3: send the message using Transport class
			Transport.send(mimemessage);
			System.out.println("sent success");
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
    
}
