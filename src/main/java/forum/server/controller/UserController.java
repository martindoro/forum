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
	private String chkbx;
	private ForumUser loggedPlayer;

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
		fillModel(model);
		return "forward:";
	}

	@RequestMapping("/register_sub")
	public String register_sub(@RequestParam("file") MultipartFile file,ForumUser forumUser, String password_check, String checkbox, Model model) throws IOException, ServletException {
		 if (!file.isEmpty()) {
	            byte[] bytes = file.getBytes();
		 forumUser.setPic(bytes);
	    }	
		userServiceJPA.register(forumUser);
		loggedPlayer = userServiceJPA.login(forumUser.getLogin(), forumUser.getPassword());
		return "forward:/";
	}

	@RequestMapping("/logout")
	public String login(Model model) {
		loggedPlayer = null;
		return "forward:/";
	}

	public boolean isLogged() {
		return loggedPlayer != null;
	}

	@RequestMapping("/rhchange")
	public String rhchange(ForumUser forumUser, String setbox, Model model) {
		//userServiceJPA.register(forumUser);
		//loggedPlayer = userServiceJPA.login(forumUser.getLogin(), forumUser.getPassword());
		return "forward:/";
	}
	
	@RequestMapping("/rhchange")
	public String rhchange(ForumUser forumUser, String setAdmin, String setUser, String rhchange, Model model) {
		//userServiceJPA.register(forumUser);
		//System.out.println(rhchange);
		if ("setAdmin".equals(setAdmin)) {
		userServiceJPA.setAdmin(rhchange);
		} else if ("setUser".equals(setUser)) {
			userServiceJPA.setUser(rhchange);
		}
		return "forward:/admin";
	}
	
	private void fillModel(Model model) {
		model.addAttribute("admin", userServiceJPA.isAdmin(loggedPlayer.getLogin()));
	}
}
