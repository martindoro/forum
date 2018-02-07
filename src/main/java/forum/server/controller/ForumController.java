package forum.server.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;

import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import forum.entity.ForumUser;
import forum.service.CategoryServiceJPA;
import forum.service.CommentServiceJPA;
import forum.service.TopicServiceJPA;
import forum.service.UserServiceJPA;

@Controller
public class ForumController {
	@Autowired
	private CommentServiceJPA commentService;
	@Autowired
	private TopicServiceJPA topicService;
	@Autowired
	private CategoryServiceJPA categoryService;
	@Autowired
	private UserServiceJPA userService;
	@Autowired
	private UserController userController;
	private int topicId;
	private int categoryId;
	
/**
 * Mapping for index.html
 * @param model
 * @return String of address
 */
	@RequestMapping("/")
	public String user(Model model) {
		fillModel(model);
		return "index";
	}
/**
 * Mapping for register.html
 * @param model
 * @return String of address
 */
	@RequestMapping("/register")
	public String register(Model model) {
		userController.setLoginMsg("");
		return "register";
	}
/**
 * Mapping for comment.html,list all comments
 * @param ident
 * @param model
 * @return String of address
 */
	@RequestMapping("/comment")
	public String comment(int ident, Model model) {
		topicId = ident;
		model.addAttribute("getTopicById", topicService.getContentById(topicId));
		model.addAttribute("topicId", topicId);
		model.addAttribute("topicState", topicService.getTopicState(topicId));
		fillModel(model);
		return "/comment";
	}
/**
 * Mapping for profile.html, user config
 * @param model
 * @return String of address
 */
	@RequestMapping("/profile")
	public String profile(Model model) {
		fillModel(model);
		userController.setLoginMsg("");
		return "/profile";
	}
/**
 * Mapping for admin.html,admin rights
 * @param model
 * @return String of address
 */
	@RequestMapping("/admin")
	public String admin(Model model) {
		fillModel(model);
		userController.setLoginMsg("");
		return "/admin";
	}
/**
 * Mapping for topic.html, all topic list
 * @param ident
 * @param model
 * @return String of address
 */
	@RequestMapping("/topic")
	public String topic(int ident, Model model) {
		categoryId = ident;
		model.addAttribute("getCategoryById", categoryService.getContentById(categoryId));
		fillModel(model);
		userController.setLoginMsg("");
		return "/topic";
	}
/**
 * Fill models 
 * @param model
 */
	private void fillModel(Model model) {
		model.addAttribute("controller", this);
		model.addAttribute("getComments", commentService.getCommentsTopic(topicId));
		model.addAttribute("getTopics", topicService.getTopicList(categoryId));
		model.addAttribute("categories", categoryService.getCategory());
		model.addAttribute("total_comments", commentService.getCommentCount());
		model.addAttribute("total_users", userService.getUserCount());
		model.addAttribute("total_topics", topicService.getTopicCount());
		model.addAttribute("ForumUser", userService.getForumUser());
	}
/**
 * Pull image(byteArray) from DB and decode it to image for html page
 * @param login
 * @return String of image information
 */
	public String decodeToImage(String login) {
		String finalImage = "";
		BufferedImage image;
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(userService.getImage(login));
			image = ImageIO.read(bis);
			bis.close();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(image, "png", baos);
			baos.flush();
			byte[] imageInByteArray = baos.toByteArray();
			baos.close();
			finalImage = javax.xml.bind.DatatypeConverter.printBase64Binary(imageInByteArray);
		} catch (IOException e) {

		} catch (NullPointerException ee) {

		}
		return "data:image/png;base64," + finalImage;
	}
/**
 * Add default admin to DB on first run
 * @throws SQLException
 */
	public void updateDatabase() throws SQLException {
		ForumUser user = new ForumUser();
		user.setAdmin(1);
		user.setEmail("admin@admin");
		user.setLogin("admin");
		user.setPassword("admin");
		try {
			userService.register(user);
		} catch (DataIntegrityViolationException e) {
		}
	}
	/**
	 * Category ident
	 * @return Category ident
	 */
	public int getCategoryId() {
		return categoryId;
	}
/**
 * Topic ident
 * @return Topic ident
 */
	public int getTopicId() {
		return topicId;
	}
}
