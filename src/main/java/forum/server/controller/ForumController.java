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
	
	
	private void fillModel(Model model) {	
		for (Category category : categoryService.getCategory()) {
			List<Topic> topics = new ArrayList<>();
		topics.addAll(topicService.getTopic(category.getIdent()));
		model.addAttribute("",)
		}
		try {
//			model.addAttribute("getComments", commentService.getCommentsTopic(topic.getIdent()));
			model.addAttribute("getTopics", );
		} catch (NullPointerException e) {
			
		}		
		model.addAttribute("getCategories", categoryService.getCategory());
	}
	
	public void  setIdentTotopic(List<Category> categories){		
		
		
	}
	
	
}
