package forum.server.controller;

import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String register_sub(ForumUser forumUser, String password_check, String checkbox, Model model) {
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

	private void fillModel(Model model) {
		model.addAttribute("admin", userServiceJPA.isAdmin(loggedPlayer.getLogin()));
	}
}
