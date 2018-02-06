package forum.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

@Transactional
public class EmailServiceTLS {
	private final String FROM = "javaacademy@zoho.eu";
	private String fromName = "Forum Administrator";
	private final String SMTP_USERNAME = "javaacademy";
	private final String SMTP_PASSWORD = "aA123456";
	private final String HOST = "smtp.zoho.eu";
	private final int PORT = 587;

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

		try {
			transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);
			transport.sendMessage(msg, msg.getAllRecipients());
		} catch (Exception ex) {
			
		} finally {
			transport.close();
		}
	}
}
