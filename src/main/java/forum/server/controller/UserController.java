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
import forum.service.UserServiceJPA;

@Controller
public class UserController {
	private String errormsg;
	private ForumUser loggedPlayer;
	private boolean admin;
	private String loginMsg;
	@Autowired
	private UserServiceJPA userServiceJPA;

	public ForumUser getLoggedPlayer() {
		return loggedPlayer;
	}

	public void setLoggedPlayer(ForumUser loggedPlayer) {
		this.loggedPlayer = loggedPlayer;
	}

	@RequestMapping("/login")
	public String login(ForumUser forumUser, Model model) {
		loggedPlayer = userServiceJPA.login(forumUser.getLogin(), forumUser.getPassword());
		loginMsg= "";
		if (loggedPlayer == null) {
			loginMsg = "Username or password wrong";
		}

		errormsg = "";
		fillModel(model);
		return "redirect:/";
	}
	@RequestMapping("/register_sub")
	public String register_sub(@RequestParam("file") MultipartFile file, ForumUser forumUser, String password_check,
			String checkbox, Model model) throws IOException, ServletException, SQLException {		 
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

		return isLogged() ? "redirect:/" : "register";
	}

	@RequestMapping("/logout")
	public String login(Model model) {
		loggedPlayer = null;
		return "redirect:/";
	}

	public boolean isLogged() {
		return loggedPlayer != null;
	}

	@RequestMapping("/rhchange")
	public String rhchange(ForumUser forumUser,@RequestParam(value="value", required = false) int setAdmin, @RequestParam(value="rhchange", required = false) String rhchange, String removeUser, Model model) {

			userServiceJPA.setAdmin(rhchange, setAdmin);

		return "forward:/admin";
	}
	
	@RequestMapping("/removeUser")
	public String removeUSer(ForumUser forumUser, String rhchange, String removeUser, Model model) {

		System.out.println(rhchange);
		System.out.println(removeUser);
	
		if ("removeUser".equals(removeUser)) {
			userServiceJPA.removeUser(rhchange);
	}
		return "forward:/admin";
	}
	
	@RequestMapping("/echange")
	public String echange(ForumUser forumUser, Model model) {
		
			userServiceJPA.emailChange(loggedPlayer.getLogin(), forumUser.getEmail());
			
		System.out.println(loggedPlayer.getLogin());
		System.out.println(forumUser.getEmail());
		return "forward:/profile";
	}
	
	@RequestMapping("/userSettingsChange")
	public String userSettingsChange(@RequestParam("file") MultipartFile file, ForumUser forumUser, Model model) throws IOException {		
			userServiceJPA.emailChange(loggedPlayer.getLogin(), forumUser.getEmail());		
			userServiceJPA.passChange(loggedPlayer.getLogin(), forumUser.getPassword());
			if (!file.isEmpty()) {
			byte[] bytes = file.getBytes();
		
			userServiceJPA.updateImage(loggedPlayer.getLogin(), bytes);
			}
		return "forward:/profile";
	}
	
	@RequestMapping("/userPassChange")
	public String userPassChange(ForumUser forumUser, String rhchange, String password, Model model) throws IOException {		
				System.out.println(rhchange);
				System.out.println(password);
			userServiceJPA.passChange(rhchange, password);

		return "forward:/admin";
	}

	private void fillModel(Model model) {
		model.addAttribute("controller", this);
		model.addAttribute("ForumUser", userServiceJPA.getForumUser());
		
	}

	public String getErrormsg() {
		return errormsg;
	}
	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}

	public boolean isAdmin() {
		if(isLogged()) {
			admin = userServiceJPA.isAdmin(loggedPlayer.getLogin());
		}		
		return admin ;
	}
	public String getLoginMsg() {
		return loginMsg;
	}
	public void setLoginMsg(String loginMsg) {
		this.loginMsg = loginMsg;
	}

}
