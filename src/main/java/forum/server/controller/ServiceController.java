package forum.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import forum.entity.Comment;
import forum.entity.Favorite;
import forum.entity.Topic;
import forum.service.CategoryServiceJPA;
import forum.service.CommentServiceJPA;
import forum.service.FavoriteServiceJPA;
import forum.service.TopicServiceJPA;
import forum.service.UserServiceJPA;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class ServiceController {

	@Autowired
	private CategoryServiceJPA categoryService;
	@Autowired
	private CommentServiceJPA commentService;
	@Autowired
	private FavoriteServiceJPA favoriteService;
	@Autowired
	private TopicServiceJPA topicService;
	@Autowired
	private UserServiceJPA userService;
	
	@RequestMapping("/add_comment")
	public String comment(Comment comment, Model model) {
		commentService.addComment(comment);
		return "topic";
	}
	
	@RequestMapping("/add_reply")
	public String reply(Comment comment, int replyto, Model model) {
		comment.setReplyto(replyto);
		commentService.addComment(comment);
		return "topic";
	}
	
	@RequestMapping("/add_topic")
	public String topic(Topic topic, Model model) {
		topicService.addTopic(topic);
		return "index";
	}
	
	@RequestMapping("/category")
	public String category(Model model) {
		return "index";
	}
	
	@RequestMapping("/favorite")
	public String favorite(Favorite favorite, Model model) {
		favoriteService.setFavorite(favorite);
		return "comment";
	}
}
