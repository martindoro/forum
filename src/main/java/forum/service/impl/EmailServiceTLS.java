package forum.service.impl;

import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.transaction.annotation.Transactional;

import forum.service.EmailService;

@Transactional
public class EmailServiceTLS implements EmailService {
	private final String FROM = "javaacademy@zoho.eu";
	private String fromName = "Forum Administrator";
	private final String SMTP_USERNAME = "javaacademy";
	private final String SMTP_PASSWORD = "aA123456";
	private final String HOST = "smtp.zoho.eu";
	private final int PORT = 587;
	
	/* (non-Javadoc)
	 * @see forum.service.impl.EmailService#sendBanMail(java.lang.String)
	 */
	@Override
	public void sendBanMail(String to) throws Exception {
		String subject = "Ban notification";
		String body = "You have been banned, please reply to this email for more info!";
		Properties props = System.getProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.port", PORT);
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(props);

		MimeMessage msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(FROM, fromName));
		msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
		msg.setSubject(subject);
		msg.setContent(body, "text/html");

		Transport transport = session.getTransport();

		ExecutorService emailExecutor = Executors.newSingleThreadExecutor();
        emailExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
        			transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);
        			transport.sendMessage(msg, msg.getAllRecipients());
        			} catch (MessagingException e) {
						e.printStackTrace();
                } finally {
                	try {
						transport.close();
					} catch (MessagingException e) {
						e.printStackTrace();
					}
                }
            }
        });
        emailExecutor.shutdown();
	}
	
	/* (non-Javadoc)
	 * @see forum.service.impl.EmailService#contactAdmin(java.lang.String, java.lang.String)
	 */
	@Override
	public void contactAdmin(String from, String content) throws Exception {
		String subject = "Message from" + " " + from;
		String body = content;
		Properties props = System.getProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.port", PORT);
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(props);

		MimeMessage msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(FROM, fromName));
		msg.setRecipient(Message.RecipientType.TO, new InternetAddress("javaacademy@zoho.eu"));
		msg.setRecipient(Message.RecipientType.CC, new InternetAddress(from));
		msg.setSubject(subject);
		msg.setContent(body, "text/html");

		Transport transport = session.getTransport();

		ExecutorService emailExecutor = Executors.newSingleThreadExecutor();
        emailExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
        			transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);
        			transport.sendMessage(msg, msg.getAllRecipients());
        			} catch (MessagingException e) {
						e.printStackTrace();
                } finally {
                	try {
						transport.close();
					} catch (MessagingException e) {
						e.printStackTrace();
					}
                }
            }
        });
        emailExecutor.shutdown();
	}
}