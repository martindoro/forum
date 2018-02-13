package forum.service;

public interface EmailService {

	/**
	 *Sends(in separate thread) email alert
	 *that forum user has been banned from using forum.
	 *Requires user having working email account.
	 *
	 * @param to forum user`s email address
	 * @throws Exception Will throw exception when there is some connection problem
	 */
	void sendBanMail(String to) throws Exception;

	/**
	 * Sends email from contact form to both admin and sender if valid email address provided
	 * @param from forum user email address
	 * @param content a message from user
	 * @throws Exception can throw an exception when some error occurs on sending
	 */
	void contactAdmin(String from, String content) throws Exception;

}