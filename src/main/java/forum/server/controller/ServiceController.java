package forum.server.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
import forum.service.CommentService;
import forum.service.FavoriteService;
import forum.service.TopicService;
import forum.service.impl.CategoryServiceJPA;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class ServiceController {

	@Autowired
	private CategoryServiceJPA categoryService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private FavoriteService favoriteService;
	@Autowired
	private TopicService topicService;
	@Autowired
	private UserController userController;

	List<String> profanities = new ArrayList<String>(Arrays.asList("lukas", "mato", "jakub", "matus"));

	/**
	 * Mapping for addComment method, this method will add post/reply after submit
	 * with badword filter
	 * 
	 * @param topicId
	 *            - id of topic
	 * @param content
	 *            of comment
	 * @param model
	 *            - current model
	 * @return String of new comment
	 */
	@RequestMapping("/add_comment")
	public String comment(@RequestParam(value = "topicId", required = false) String topicId,
			@RequestParam(value = "content", required = false) String content, Model model) {
		int badWords = 0;
		for (int i = 0; i < profanities.size(); i++) {
			if (content.toLowerCase().contains(profanities.get(i))) {
				badWords++;
			}
		}
		if (badWords == 0) {
			commentService.addComment(
					new Comment(userController.getLoggedPlayer().login, Integer.parseInt(topicId), content));
		}

		return "forward:/comment?ident=" + topicId;
	}

	/**
	 * Mapping for editComment method, with this method we can edit existing
	 * post/comment.
	 * 
	 * @param ident
	 *            - id of edited comment
	 * @param content
	 *            - of comment
	 * @param topicId
	 *            - id of topic
	 * @param model
	 *            - current model
	 * @return edited String of content from comment
	 */
	@RequestMapping("/edit_comment")
	public String editComment(@RequestParam(value = "ident", required = false) String ident,
			@RequestParam(value = "content", required = false) String content,
			@RequestParam(value = "topicId", required = false) String topicId, Model model) {
		if (content.contains("*edit*")) {
			commentService.editComment(Integer.parseInt(ident), content);
		} else {
			commentService.editComment(Integer.parseInt(ident), "*edit*" + content);
		}
		return "forward:/comment?ident=" + topicId;
	}

	/**
	 * Mapping for addReply method, with this method we can send reply on existing
	 * post/comment.
	 * 
	 * @param topicId
	 *            - id of topic
	 * @param replyto
	 *            - id of topic to which have reply be mapped
	 * @param content
	 *            - content of reply
	 * @param model
	 *            - current model
	 * @return String of reply for existing post/comment.
	 */
	@RequestMapping("/add_reply")
	public String reply(@RequestParam(value = "topicId", required = false) String topicId,
			@RequestParam(value = "replyto") int replyto, @RequestParam(value = "content") String content,
			Model model) {
		int topicIdent = Integer.parseInt(topicId);
		commentService.addComment(new Comment(userController.getLoggedPlayer().login, topicIdent, content, replyto));
		return "forward:/comment?ident=" + topicId;
	}

	/**
	 * Mapping for addTopic method, with this method we can add new topic.
	 * 
	 * @param categoryId
	 *            - id of category to which have be new topic added
	 * @param content
	 *            - content of category
	 * @param model
	 *            - current model
	 * @return new topic with ident
	 */
	@RequestMapping("/add_topic")
	public String topic(@RequestParam(value = "categoryId", required = false) int categoryId,
			@RequestParam(value = "content", required = false) String content, Model model) {
		topicService.addTopic(new Topic(userController.getLoggedPlayer().getLogin(), categoryId, content));
		return "forward:/topic?ident=" + categoryId;
	}

	/**
	 * Mapping for addCategory, this option is visible only for users with Admin
	 * rights, with this method we can add new category
	 * 
	 * @param content
	 *            - content of new category
	 * @param model
	 *            - current model
	 * @return new category with ident
	 * @throws PSQLException
	 *             when something wrong happens with database
	 */
	@RequestMapping("/add_category")
	public String category(String content, Model model) throws PSQLException {
		categoryService.addCategory(new Category(content));
		return "forward:/";
	}

	/**
	 * Mapping for like/favourite buttons
	 * 
	 * @param topicId
	 *            - id of topic which have to be liked
	 * @param ident
	 *            of user who liked post
	 * @param model
	 *            - current model
	 * @return value of like button plus
	 */
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

	/**
	 * Mapping for like/favourite buttons
	 * 
	 * @param topicId
	 *            - id of topic which have to be liked
	 * @param ident
	 *            - ident of user who liked post
	 * @param model
	 *            - current model
	 * @return value of like button minus
	 */
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
