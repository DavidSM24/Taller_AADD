package project.models;

import java.io.Serializable;

public class Email implements Serializable {

    private String subject;
    private String receiver;
    private String message;

    public Email() {
    }

    public Email (String subject, String receiver, String message) {
        this.subject = subject;
        this.receiver = receiver;
        this.message = message;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Email{" +
                "subject='" + subject + '\'' +
                ", receiver='" + receiver + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
