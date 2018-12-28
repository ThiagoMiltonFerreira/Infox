
package envioEmail;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;


public class JavaMail {
    
          //public static void main(String[] args) {
    public boolean enviaEmail(String destinatario){
            Properties props = new Properties();
            /** Parâmetros de conexão com servidor Gmail */
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");
 
            Session session = Session.getDefaultInstance(props,
                        new javax.mail.Authenticator() {
                             protected PasswordAuthentication getPasswordAuthentication() 
                             {
                                   return new PasswordAuthentication("thiagomilton.f@gmail.com", "30081996");
                             }
                        });
 
            /** Ativa Debug para sessão */
            session.setDebug(true);
 
            try {
 
                  Message message = new MimeMessage(session);
                  message.setFrom(new InternetAddress("thiagomilton.f@gmail.com")); //Remetente
 
                  Address[] toUser = InternetAddress //Destinatário(s)
                             .parse(destinatario);  
 
                  message.setRecipients(Message.RecipientType.TO, toUser);
                  message.setSubject("Obrigado por se cadastrar no Infox Ordem de serviço.");//Assunto
                  message.setText("Prezados,Obrigado Por ultilizar o Infox Gerenciador de ordem de serviços! Para Suporte ou atendimento comercial entre em contato pelo numero (31)9-9390-3976.Atenciosamente,Equipe Infox Software. ");
                  /**Método para enviar a mensagem criada*/
                  Transport.send(message);
 
                  //System.out.println("Feito!!!");
 
             } catch (MessagingException e) {
                 // throw new RuntimeException(e);
                 JOptionPane.showMessageDialog(null,"E-mail Não Enviado! favor entre em contato com nossa equipe de suporte!");
                 return true;
            }
            return false;
      }
}
