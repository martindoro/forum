package forum.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import forum.service.UserServiceJPA;

import forum.entity.ForumUser;

@Controller
public class UserController {
	
	private String errormsg;
	
	private String chkbx;
	
	public ForumUser getLoggedPlayer() {
		return loggedPlayer;
	}


	public void setLoggedPlayer(ForumUser loggedPlayer) {
		this.loggedPlayer = loggedPlayer;
	}

		
	private ForumUser loggedPlayer;
	
	@Autowired 
	private UserServiceJPA userServiceJPA;
	
	@RequestMapping("/login")
	public String login(ForumUser forumUser, Model model) {
		
		loggedPlayer = userServiceJPA.login(forumUser.getLogin(), forumUser.getPassword());
		
		//return isLogged() ? "index" : "login";
		return "index";
		
	}

	
	@RequestMapping("/register_sub")
	public String register_sub(ForumUser forumUser , String password_check, String checkbox, Model model) {
		
			
		userServiceJPA.register(forumUser);
		
		loggedPlayer = userServiceJPA.login(forumUser.getLogin(), forumUser.getPassword());
		

		
		//return isLogged() ? "login" : "login";
		return "index";

}

	
	@RequestMapping("/logout")
	public String login(Model model) {
		loggedPlayer = null;
		return "index";
	}
	
}

