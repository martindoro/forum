package forum.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import forum.service.CommentServiceJPA;

@Controller
public class ForumController {
	@Autowired
	private CommentServiceJPA commentService;
	

	@RequestMapping("/")
	public String user(Model model) {
		fillModel(model);
		return "index";

	}
	
	@RequestMapping("/register")
	public String register(Model model) {
		return "register";
	}
	
	
	private void fillModel(Model model) {
		model.addAttribute("getComments", commentService.getCommentsTopic(0));
	}
	
	
}
