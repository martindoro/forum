package forum.server.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import forum.entity.ForumUser;
import forum.service.EmailServiceTLS;
import forum.service.UserServiceJPA;

@Controller
public class UserController {
	private String errormsg;
	private ForumUser loggedPlayer;
	private boolean admin;
	private boolean ban;

	public void setBan(boolean ban) {
		this.ban = ban;
	}

	private String loginMsg;
	@Autowired
	private UserServiceJPA userServiceJPA;
	@Autowired
	private EmailServiceTLS emailServiceTLS;

	public String getErrormsg() {
		return errormsg;
	}

	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}

	public boolean isAdmin() {
		if (isLogged()) {
			admin = userServiceJPA.isAdmin(loggedPlayer.getLogin());
		}
		return admin;
	}

	public boolean isBan() {
		if (isLogged()) {
			ban = userServiceJPA.isBan(loggedPlayer.getLogin());
		}
		return ban;
	}

	public boolean isLogged() {
		return loggedPlayer != null;
	}

	public String getLoginMsg() {
		return loginMsg;
	}

	public void setLoginMsg(String loginMsg) {
		this.loginMsg = loginMsg;
	}

	public ForumUser getLoggedPlayer() {
		return loggedPlayer;
	}

	public void setLoggedPlayer(ForumUser loggedPlayer) {
		this.loggedPlayer = loggedPlayer;
	}

	/**
	 * Mapping for login, log user into system
	 * 
	 * @param forumUser
	 *            Object user to be logged
	 * @param model
	 * @return String for address
	 */
	@RequestMapping("/login")
	public String login(ForumUser forumUser, Model model) {
		loggedPlayer = userServiceJPA.login(forumUser.getLogin(), forumUser.getPassword());
		loginMsg = "";
		if (loggedPlayer == null) {
			loginMsg = "Username or password wrong";
		} else if (loggedPlayer.getRights() == -1) {
			loginMsg = "your account is banned, contact administrator";
			loggedPlayer = null;
		}
		errormsg = "";
		fillModel(model);
		return "redirect:/";
	}

	/**
	 * Register new user to DB
	 * 
	 * @param file
	 *            File image
	 * @param forumUser
	 *            Object User to be saved to DB
	 * @param password_check
	 *            String for check if password is valid
	 * @param checkbox
	 *            String to check if checkbox was marked
	 * @param model
	 * @return String to Address
	 * @throws IOException
	 * @throws ServletException
	 * @throws SQLException
	 */
	@RequestMapping("/register_sub")
	public String register_sub(@RequestParam("file") MultipartFile file, ForumUser forumUser, String password_check,
			String checkbox, Model model) throws IOException, ServletException, SQLException {
		if ("checkbox".equals(checkbox)) {
			if (!file.isEmpty()) {
				byte[] bytes = file.getBytes();
				forumUser.setPic(bytes);
			}
			if (!userServiceJPA.isPlayer(forumUser.getLogin())) {
				userServiceJPA.register(forumUser);
				loggedPlayer = userServiceJPA.login(forumUser.getLogin(), forumUser.getPassword());
			} else {
				errormsg = "Login already exists!!";
			}
		} else {
			errormsg = "Please agree with rules";
		}

		return isLogged() ? "redirect:/" : "register";
	}

	/**
	 * Mapping for logout user from system
	 * 
	 * @param model
	 * @return String to address
	 */
	@RequestMapping("/logout")
	public String login(Model model) {
		loggedPlayer = null;
		return "redirect:/";
	}

	/**
	 * Mapping for rights change from admin to standard users
	 * 
	 * @param forumUser
	 *            Object user
	 * @param setAdmin
	 *            Number for state(-1 ban, 0 standard, 1 admin)
	 * @param rhchange
	 *            String login who is going to change
	 * @param removeUser
	 * @param model
	 * @return String to address
	 */
	@RequestMapping("/rhchange")
	public String rhchange(ForumUser forumUser, @RequestParam(value = "value", required = false) int setAdmin,
			@RequestParam(value = "rhchange", required = false) String rhchange, Model model) {
		userServiceJPA.setRights(rhchange, setAdmin);
		return "forward:/admin";
	}

	/**
	 * MApping for change user parameters
	 * 
	 * @param file
	 *            File image
	 * @param forumUser
	 *            Object user
	 * @param model
	 * @return String to address
	 * @throws IOException
	 */
	@RequestMapping("/userSettingsChange")
	public String userSettingsChange(@RequestParam("file") MultipartFile file, ForumUser forumUser, Model model)
			throws IOException {
		userServiceJPA.emailChange(loggedPlayer.getLogin(), forumUser.getEmail());
		userServiceJPA.passChange(loggedPlayer.getLogin(), forumUser.getPassword());
		if (!file.isEmpty()) {
			byte[] bytes = file.getBytes();
			userServiceJPA.updateImage(loggedPlayer.getLogin(), bytes);
		}
		return "forward:/profile";
	}

	/**
	 * Mapping for password change in admin
	 * 
	 * @param forumUser
	 *            Object user
	 * @param rhchange
	 *            String login
	 * @param password
	 *            String new password
	 * @param model
	 * @return String to address
	 * @throws IOException
	 */
	@RequestMapping("/userPassChange")
	public String userPassChange(ForumUser forumUser, String rhchange, String password, Model model)
			throws IOException {
		userServiceJPA.passChange(rhchange, password);
		return "forward:/admin";
	}

	/**
	 * Mapping for block user by administrator
	 * 
	 * @param rhchange
	 *            String login
	 * @param email
	 *            String new email
	 * @param model
	 * @return String to address
	 */
	@RequestMapping("/userBlock")
	public String userBlock(String rhchange, String email, Model model) {
		userServiceJPA.setRights(rhchange, -1);
		try {
			emailServiceTLS.sendBanMail(email);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "forward:/admin";
	}

	/**
	 * Fill models
	 * 
	 * @param model
	 */
	private void fillModel(Model model) {
		model.addAttribute("controller", this);
		model.addAttribute("ForumUser", userServiceJPA.getForumUser());
	}

}
