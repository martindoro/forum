package forum.service;

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

@Transactional
public class EmailServiceTLS {
	private final String FROM = "javaacademy@zoho.eu";
	private String fromName = "Forum Administrator";
	private final String SMTP_USERNAME = "javaacademy";
	private final String SMTP_PASSWORD = "aA123456";
	private final String HOST = "smtp.zoho.eu";
	private final int PORT = 587;
	
	/**
	 *Sends(in separate thread) email alert
	 *that forum user has been banned from using forum.
	 *Requires user having working email account.
	 *
	 * @param to forum user`s email address
	 * @throws Exception Will throw exception when there is some connection problem
	 */
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
	
	/**
	 * Sends email from contact form to both admin and sender if valid email address provided
	 * @param from forum user email address
	 * @param content a message from user
	 * @throws Exception can throw an exception when some error occurs on sending
	 */
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