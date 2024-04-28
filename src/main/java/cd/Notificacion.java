package cd;

import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class Notificacion {

    private static String remitente = "documentalletras@gmail.com";
    private static String password = "ebdd txxw qxgt kalt";








    public static void sendEmailPrestamo(String destinatario, JsonObject datos){

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remitente, password);
            }
        });
        
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(remitente));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            message.setSubject("Notificación de préstamo");

            MimeBodyPart htmlPart = new MimeBodyPart();
            String mensajeHTML =
                    "<html lang='es'>"+
                            "<head>"+
                                "<meta charset='UTF-8'>"+
                                "<meta name='viewport' content='width=device-width, initial-scale=1.0'>"+
                                "<title>Correo para informar del préstamo</title>"+
                            "</head>"+
                            "<body style='margin: 0; padding: 0; font-family: Arial, sans-serif;'>"+

                                "<table cellpadding='0' cellspacing='0' border='0' width='100%'>"+
                                    "<tr>"+
                                    "<td bgcolor='#0958a0' style='color: white; padding: 10px 20px;'>"+
                                    "<table cellpadding='0' cellspacing='0' border='0' width='100%'>"+
                                    "<tr>"+
                                        "<td>"+
                                            "<h2>Centro Documental</h2>"+
                                            "<h3>Facultad de Letras Españolas</h3>"+
                                            "</td>"+
                                            "<td>"+
                                            "<img src='cid:imagen' width='100' style='display: block;'>"+
                                            "</td>"+
                                            "</tr>"+
                                            "</table>"+
                                        "</td>"+
                                    "</tr>"+
                                    "<tr>"+
                                        "<td bgcolor='#fafbfc' style='padding: 20px;'>"+
                                            "<h3>Estimado/a "+ datos.get("nombre") +" </h3>"+
                                            "<p>Se ha registrado un nuevo préstamo de material bibliográfico en el Centro Documental de la Facultad de Letras Españolas.</p>"+
                                            "<p>Los datos de la solicitud son los siguientes:</p>"+
                                            "<ul>"+
                                                "<li>Nombre del solicitante:" + datos.get("nombre") +"</li>"+
                                                "<li>Material solicitado:"+ datos.get("titulo") +"</li>"+
                                                "<li>Fecha de solicitud: "+ datos.get("fechaSolicitud") +"</li>"+
                                                "<li>Fecha de devolución: "+ datos.get("fechaDevolucion") +"</li>"+
                                            "</ul>"+
                                            "<p>Saludos cordiales.</p>"+
                                        "</td>"+
                                    "</tr>"+
                                "</table>"+
                            "</body>"+
                            "</html>";


            htmlPart.setContent(mensajeHTML, "text/html");

            MimeBodyPart adjuntoPart = new MimeBodyPart();
            adjuntoPart.attachFile("src/main/images/Logo.png");
            adjuntoPart.setContentID("<imagen>");


            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(htmlPart);
            multipart.addBodyPart(adjuntoPart);
            message.setContent(multipart);

            Transport.send(message);
            System.out.println("Correo enviado correctamente.");
        } catch (MessagingException e) {
            System.out.println("Error al enviar el correo: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void sendEmialDevolucion(String destinatario, JsonObject datos){

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remitente, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(remitente));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            message.setSubject("Notificación de devolución");

            MimeBodyPart htmlPart = new MimeBodyPart();
            String correoPrestamo =
                    "<html lang='es'>"+
                            "<head>"+
                            "<meta charset='UTF-8'>"+
                            "<meta name='viewport' content='width=device-width, initial-scale=1.0'>"+
                            "<title>Correo para informar del préstamo</title>"+
                            "</head>"+
                            "<body style='margin: 0; padding: 0; font-family: Arial, sans-serif;'>"+

                            "<table cellpadding='0' cellspacing='0' border='0' width='100%'>"+
                            "<tr>"+
                            "<td bgcolor='#0958a0' style='color: white; padding: 10px 20px;'>"+
                            "<table cellpadding='0' cellspacing='0' border='0' width='100%'>"+
                            "<tr>"+
                            "<td>"+
                            "<h2>Centro Documental</h2>"+
                            "<h3>Facultad de Letras Españolas</h3>"+
                            "</td>"+
                            "<td>"+
                            "<img src='cid:imagen' width='100' style='display: block;'>"+
                            "</td>"+
                            "</tr>"+
                            "</table>"+
                            "</td>"+
                            "</tr>"+
                            "<tr>"+
                            "<tr>"+
                            "<td bgcolor='#fafbfc' style='padding: 20px;'>"+
                            "<h3>Estimado/a " + datos.get("nombre") + "</h3>"+
            "<p>Se ha registrado un nuevo préstamo de material bibliográfico en el Centro Documental de la Facultad de Letras Españolas.</p>"+
                            "<p>Los datos de la solicitud son los siguientes:</p>"+
                            "<ul>"+
                            "<li>Nombre del solicitante: " + datos.get("nombre") +"</li>"+
                            "<li>Material solicitado: "+ datos.get("titulo") +"</li>"+
                            "<li>Fecha de solicitud: "+ datos.get("fechaSolicitud") +"</li>"+
                            "<li>Fecha de devolución: "+ datos.get("fechaDevolucion") +"</li>"+
                            "</ul>"+
                            "<p>Saludos cordiales.</p>"+
                            "</td>"+
                            "</tr>"+
                            "</table>"+
                            "</body>"+
                            "</html>";
            ;


            htmlPart.setContent(correoPrestamo, "text/html");

            MimeBodyPart adjuntoPart = new MimeBodyPart();
            adjuntoPart.attachFile("src/main/images/Logo.png");
            adjuntoPart.setContentID("<imagen>");


            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(htmlPart);
            multipart.addBodyPart(adjuntoPart);
            message.setContent(multipart);

            Transport.send(message);
            System.out.println("Correo enviado correctamente.");
        } catch (MessagingException e) {
            System.out.println("Error al enviar el correo: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
}
