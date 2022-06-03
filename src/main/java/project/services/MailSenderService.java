package project.services;

import project.models.Email;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailSenderService {

    public boolean sendMail(Email mail) {
        //Sesión

        if(
            mail!=null
            &&mail.getSubject()!=null
            &&mail.getReceiver()!=null
            &&mail.getMessage()!=null){

            Properties props = new Properties();

            // Nombre del host de correo, es smtp.gmail.com
            props.setProperty("mail.smtp.host", "smtp.gmail.com");

            // TLS si está disponible
            props.setProperty("mail.smtp.starttls.enable", "true");

            // Puerto de gmail para envio de correos
            props.setProperty("mail.smtp.port","587");

            // Nombre del usuario
            props.setProperty("mail.smtp.user", "finalShowdown@gmail.com");

            // Si requiere o no usuario y password para conectarse.
            props.setProperty("mail.smtp.auth", "true");

            Session session = Session.getDefaultInstance(props);

            // Para obtener un log de salida más extenso
            session.setDebug(false);

            //Mensaje

            MimeMessage message = new MimeMessage(session);

            // Quien envia el correo
            try {
                message.setFrom(new InternetAddress("finalshowdown@gmail.com"));
            } catch (AddressException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return false;
            } catch (MessagingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return false;
            }

            // A quien va dirigido
            try {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(mail.getReceiver()));
            } catch (MessagingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return false;
            }

            try {
                message.setSubject(mail.getSubject());
            } catch (MessagingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return false;
            }
            try {
                message.setText(mail.getMessage());
            } catch (MessagingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return false;
            }

            //Enviamos el mensaje

            try {
                Transport t = session.getTransport("smtp");
                t.connect("finalshowdownrecover@gmail.com","magnokurai24");
                t.sendMessage(message,message.getAllRecipients());

            } catch (MessagingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return false;
            }

            return true;
        }
        else return false;


    }

}
