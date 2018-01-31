package forum.server.controller;

import java.io.IOException;

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
		errormsg= "";
		fillModel(model);
		return "redirect:/";
	}

	@RequestMapping("/register_sub")
	public String register_sub(@RequestParam("file") MultipartFile file, ForumUser forumUser, String password_check,
			String checkbox, Model model) throws IOException, ServletException {		
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
	public String rhchange(ForumUser forumUser, String setAdmin, String setUser, String rhchange, Model model) {
		// userServiceJPA.register(forumUser);
		// System.out.println(rhchange);
		if ("setAdmin".equals(setAdmin)) {
			userServiceJPA.setAdmin(rhchange);
		} else if ("setUser".equals(setUser)) {
			userServiceJPA.setUser(rhchange);
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
	public String userSettingsChange(ForumUser forumUser, Model model) {
		
			userServiceJPA.emailChange(loggedPlayer.getLogin(), forumUser.getEmail());
			
			userServiceJPA.passChange(loggedPlayer.getLogin(), forumUser.getPassword());
			
		return "forward:/profile";
	}

	private void fillModel(Model model) {
		model.addAttribute("controller", this);
		
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

}
