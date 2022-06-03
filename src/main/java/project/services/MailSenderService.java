package project.services;

import org.springframework.stereotype.Service;
import project.models.Email;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

@Service
public class MailSenderService {

    final String User_Email = "backtoraceapp@hotmail.com"; //your email
    final String Password = "Backtorace"; // your email password
    final String Sender = "backtoraceapp@hotmail.com"; // Insert Your email again

    String receiver = ""; // Insert Receiver's Email
    String email_Subject = "";
    String content = "";

    public boolean sendMail(Email mail) {

        if(
                mail!=null
                &&mail.getMessage()!=null
                &&mail.getReceiver()!=null
                &&mail.getSubject()!=null
        ){

            this.receiver=mail.getReceiver();
            this.content=mail.getMessage();
            this.email_Subject=mail.getSubject();
        } else return false;

        final Session newsession = Session.getInstance(this.Mail_Properties(), new Authenticator() {
            @Override
            // password authentication
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(User_Email, Password);
            }
        });
        // MimeMessage is used to create the email message
        try {
            final Message Demo_Message = new MimeMessage(newsession);
            Demo_Message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
            Demo_Message.setFrom(new InternetAddress(Sender));
            Demo_Message.setSubject(email_Subject); // email subject
            Demo_Message.setText(content); // The content of email
            Demo_Message.setSentDate(new Date());
            Transport.send(Demo_Message);// Transport the email
            System.out.println("Your Email has been sent successfully!");
            return true;
        }
        catch (final MessagingException e) { // exception to catch the errors
            System.out.println("Email Sending Failed"); // failed
            e.printStackTrace();
            return false;
        }
    }

    // The permanent  set of properties containing string keys, the following
    // setting the properties for SMPT function
    public Properties Mail_Properties() {
        final Properties Mail_Prop = new Properties();
        Mail_Prop.put("mail.smtp.host", "smtp.office365.com");
        Mail_Prop.put("mail.smtp.post", "587");
        Mail_Prop.put("mail.smtp.auth", true);
        Mail_Prop.put("mail.smtp.starttls.enable", true);
        Mail_Prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
        return Mail_Prop;
    }

}
