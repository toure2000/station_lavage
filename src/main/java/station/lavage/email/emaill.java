package station.lavage.email;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class emaill {
    public static boolean sendEmail(String Email,String messageHTML) {
        boolean test = false;

        String toEmail = Email;
        SendEmail send=new SendEmail();
        String fromEmail = send.fromEmail;
        String password = send.password;

        try {

            // your host email smtp server details
            Properties pr = new Properties();
            pr.setProperty("mail.smtp.host", "smtp.gmail.com");
            pr.setProperty("mail.smtp.port", "587");
            pr.setProperty("mail.smtp.auth", "true");
            pr.setProperty("mail.smtp.starttls.enable", "true");
            pr.put("mail.smtp.socketFactory.port", "587");
            pr.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
 
            //get session to authenticate the host email address and password
            Session session = Session.getInstance(pr, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, password);
                }
            });

            //set email message details
            Message mess = new MimeMessage(session);

    		//set from email address
            mess.setFrom(new InternetAddress(fromEmail));
    		//set to email address or destination email address
            mess.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
    		
    		//set email subject
            mess.setSubject("Verification d'Email GESTION DE CABINET MEDICALE");
            MimeBodyPart minbodypart=new MimeBodyPart();
            minbodypart.setContent(messageHTML,"text/html");
    		Multipart mulp=new MimeMultipart();
    		mulp.addBodyPart(minbodypart);
    		mess.setContent(mulp);
            //set message text
            //send the message
            Transport.send(mess);
            
            test=true;
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return test;
    }
    public static boolean sendEmail(String Email,String messageHTML,String sujet) throws AddressException, MessagingException {
        boolean test = false;

        String toEmail = Email;
        SendEmail send=new SendEmail();
        String fromEmail = send.fromEmail;
        String password = send.password;

            // your host email smtp server details
            Properties pr = new Properties();
            pr.setProperty("mail.smtp.host", "smtp.gmail.com");
            pr.setProperty("mail.smtp.port", "587");
            pr.setProperty("mail.smtp.auth", "true");
            pr.setProperty("mail.smtp.starttls.enable", "true");
            pr.put("mail.smtp.socketFactory.port", "587");
            pr.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
 
            //get session to authenticate the host email address and password
            Session session = Session.getInstance(pr, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, password);
                }
            });

            //set email message details
            Message mess = new MimeMessage(session);

    		//set from email address
            mess.setFrom(new InternetAddress(fromEmail));
    		//set to email address or destination email address
            mess.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
    		
    		//set email subject
            mess.setSubject(sujet+" GESTION DE CABINET MEDICALE");
            MimeBodyPart minbodypart=new MimeBodyPart();
            minbodypart.setContent(messageHTML,"text/html");
    		Multipart mulp=new MimeMultipart();
    		mulp.addBodyPart(minbodypart);
    		mess.setContent(mulp);
            //set message text
            //send the message
            Transport.send(mess);
            
            test=true;
      
        return test;
    }
}
