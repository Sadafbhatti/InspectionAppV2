package com.example.inspectionapp;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import java.io.IOException;

// using SendGrid's Java Library
// https://github.com/sendgrid/sendgrid-java
public class sendMail {

     final static String SENDGRID_API_KEY ="5H2orXwOSVq5CrWP9H6ANw";
    public static void main(String[] args) throws IOException {
        Email from = new Email("test@example.com");
        String subject = "Sending with SendGrid is Fun";
        Email to = new Email("sadafbhatti@gmail.com");
        Content content = new Content("text/plain", "and easy to do anywhere, even with Java");
        Mail mail = new Mail(from, subject, to, content);

       // SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
        SendGrid sg = new SendGrid(SENDGRID_API_KEY);

        Request request= new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            System.out.println("Error sending using sendgrid :"+ex);
            throw ex;
        }
    }
}