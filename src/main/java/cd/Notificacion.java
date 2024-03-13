package cd;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class Notificacion {

    private static String remitente = "documentalletras@gmail.com";
    private static String password = "ebdd txxw qxgt kalt";

    public static void SendEmail(String destinatario){                                    
        
        // Configuración del servidor SMTP
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        
        // Crear sesión
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remitente, password);
            }
        });
        
        try {
            // Crear mensaje
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(remitente));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            message.setSubject("Hola, recuerda que tienes un libro pendiente.");
            message.setText("<div style='background-color: #000;'>Hola, recuerda que tienes un libro pendiente. Tienes hasta el 19 de Julio para devolverlo. Gracias.</div>");
            
            // Enviar mensaje
            Transport.send(message);
            System.out.println("Correo enviado correctamente.");
        } catch (MessagingException e) {
            System.out.println("Error al enviar el correo: " + e.getMessage());
        }
    }
    
}
