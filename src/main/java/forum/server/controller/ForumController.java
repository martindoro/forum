package forum.server.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import forum.entity.Category;
import forum.entity.Topic;
import forum.service.CategoryServiceJPA;
import forum.service.CommentServiceJPA;
import forum.service.TopicServiceJPA;

@Controller
public class ForumController {
	@Autowired
	private CommentServiceJPA commentService;
	@Autowired
	private TopicServiceJPA topicService;
	@Autowired
	private CategoryServiceJPA categoryService;
	private Category category;
	private Topic topic;

	@RequestMapping("/")
	public String user(Model model) {
		fillModel(model);
		return "index";

	}

	@RequestMapping("/register")
	public String register(Model model) {
		return "register";
	}

	@RequestMapping("/test")
	public String test(Model model) {
		return "test";
	}

	@RequestMapping("/comment")
	public String comment(int ident, Model model) {
		model.addAttribute("getComments", commentService.getCommentsTopic(ident));
		return "comment";
	}

	private void fillModel(Model model) {
		
		model.addAttribute("categories", categoryService.getCategory());
		model.addAttribute("topicsForHardware", topicService.getTopic(1));
		model.addAttribute("topicsForSoftware", topicService.getTopic(2));
		model.addAttribute("topicsForOther", topicService.getTopic(3));
		for (Category category : categoryService.getCategory()) {
			model.addAttribute(category.getContent(), topicService.getTopic(category.getIdent()));
		}
	}

	

}
