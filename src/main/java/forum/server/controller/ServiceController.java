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
		commentService.addComment(new Comment(userController.getLoggedPlayer().login, Integer.parseInt(topicId), content));
		return "forward:/comment?ident=" + topicId;
	}

	@RequestMapping("/edit_comment")
	public String editComment(@RequestParam(value = "ident", required = false) String ident,
			@RequestParam(value = "content", required = false) String content,
			@RequestParam(value = "topicId", required = false) String topicId, Model model) {
		if(content.contains("*edit*")) {
			commentService.editComment(Integer.parseInt(ident), content);
		}else {
			commentService.editComment(Integer.parseInt(ident), "*edit*" + content);
		}
		return "forward:/comment?ident=" + topicId;
	}

	@RequestMapping("/add_reply")
	public String reply(@RequestParam(value = "topicId", required = false) String topicId,
			@RequestParam(value = "replyto") int replyto, @RequestParam(value = "content") String content,
			Model model) {
		int topicIdent = Integer.parseInt(topicId);
		commentService.addComment(new Comment(userController.getLoggedPlayer().login, topicIdent, content, replyto));
		return "forward:/comment?ident=" + topicId;
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

	@RequestMapping("/favoritePlus")
	public String favoritePlus(@RequestParam(value = "topicId", required = false) String topicId,
			@RequestParam(value = "ident", required = false) int ident, Model model) {
		if (favoriteService.isFavorite(userController.getLoggedPlayer().getLogin(), ident)) {
			if (favoriteService.getFavorite(userController.getLoggedPlayer().getLogin(), ident).getValue() < 1) {
				favoriteService.updateFavorite(userController.getLoggedPlayer().getLogin(),
						favoriteService.getFavorite(userController.getLoggedPlayer().getLogin(), ident).getIdent(), 1);
				commentService.setCommentValue(ident, 1);
			}
		} else {
			Favorite favorite = new Favorite(userController.getLoggedPlayer().login, ident);
			favorite.setValue(1);
			favoriteService.setFavorite(favorite);
			commentService.setCommentValue(ident, 1);
		}
		return "redirect:/comment?ident=" + topicId;
	}

	@RequestMapping("/favoriteMinus")
	public String favoriteMinus(@RequestParam(value = "topicId", required = false) String topicId,
			@RequestParam(value = "ident", required = false) int ident, Model model) {
		if (favoriteService.isFavorite(userController.getLoggedPlayer().getLogin(), ident)) {
			if (favoriteService.getFavorite(userController.getLoggedPlayer().getLogin(), ident).getValue() > -1) {
				favoriteService.updateFavorite(userController.getLoggedPlayer().getLogin(),
						favoriteService.getFavorite(userController.getLoggedPlayer().getLogin(), ident).getIdent(), -1);
				commentService.setCommentValue(ident, -1);
			}
		} else {
			Favorite favorite = new Favorite(userController.getLoggedPlayer().login, ident);
			favorite.setValue(-1);
			favoriteService.setFavorite(favorite);
			commentService.setCommentValue(ident, -1);
		}
		return "redirect:/comment?ident=" + topicId;
	}
}
