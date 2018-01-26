package forum.server.controller;

import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;

import forum.entity.Category;
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
	@Autowired
	private UserController userController;

	@RequestMapping("/add_comment")
	public String comment(@RequestParam(value = "topicId", required = false) String topicId,
			@RequestParam(value = "content", required = false) String content, Model model) {
		int topicIdent = Integer.parseInt(topicId);
		commentService.addComment(new Comment(userController.getLoggedPlayer().login, topicIdent, content));
		return "forward:/comment?ident=" + topicId;
	}

	@RequestMapping("/add_reply")
	public String reply(Comment comment, int replyto, Model model) {
		comment.setReplyto(replyto);
		commentService.addComment(comment);
		return "topic";
	}

	@RequestMapping("/add_topic")
	public String topic(@RequestParam(value = "categoryId", required = false) int categoryId,
			@RequestParam(value = "content", required = false) String content, Model model) {
		topicService.addTopic(new Topic(userController.getLoggedPlayer().getLogin(), categoryId, content));
		return "forward:/topic?ident=" + categoryId;
	}

	@RequestMapping("/add_category")
	public String category(String content, Model model) throws PSQLException {
		categoryService.addCategory(new Category(content));
		return "forward:/";
	}

	@RequestMapping("/favorite")
	public String favorite(Favorite favorite, Model model) {
		favoriteService.setFavorite(favorite);
		return "comment";
	}

}
