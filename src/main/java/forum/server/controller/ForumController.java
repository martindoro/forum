package forum.server.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import forum.entity.ForumUser;
import forum.service.CommentService;
import forum.service.EmailService;
import forum.service.TopicService;
import forum.service.UserService;
import forum.service.impl.CategoryServiceJPA;

@Controller
public class ForumController {
	@Autowired
	private CommentService commentService;
	@Autowired
	private TopicService topicService;
	@Autowired
	private CategoryServiceJPA categoryService;
	@Autowired
	private UserService userService;
	@Autowired
	private UserController userController;
	@Autowired
	private EmailService emailService;
	
	private int topicId;
	private int categoryId;
	
/**
 * Mapping for index.html
 * @param model Current model
 * @return String of address
 */
	@RequestMapping("/")
	public String user(Model model) {
		fillModel(model);
		return "index";
	}
/**
 * Mapping for register.html
 * @param model Current model
 * @return String of address
 */
	@RequestMapping("/register")
	public String register(Model model) {
		userController.setLoginMsg("");
		return "register";
	}
	
	@RequestMapping("/contact")
	public String contact(Model model) {
		//userController.setLoginMsg("");
		//emailService.contactAdmin(from, content);
		return "contact";
	}
	
	@RequestMapping("/contactAdmin")
	public String contactAdmin(Model model, String from, String content) throws Exception {
		//userController.setLoginMsg("");
		emailService.contactAdmin(from, content);
		System.out.println(from);
		System.out.println(content);
		return "contact";
	}
/**
 * Mapping for comment.html,list all comments
 * @param ident ident
 * @param model Current model
 * @return String of address
 */
	@RequestMapping("/comment")
	public String comment(int ident, Model model) {
		topicId = ident;
		model.addAttribute("getTopicById", topicService.getContentById(topicId));
		model.addAttribute("topicId", topicId);
		model.addAttribute("topicState", topicService.getTopicState(topicId));
		fillModel(model);
		return "comment";
	}
/**
 * Mapping for profile.html, user config
 * @param model Current model
 * @return String of address
 */
	@RequestMapping("/profile")
	public String profile(Model model) {
		fillModel(model);
		userController.setLoginMsg("");
		return "profile";
	}
/**
 * Mapping for admin.html,admin rights
 * @param model Current model
 * @return String of address
 */
	@RequestMapping("/admin")
	public String admin(Model model) {
		fillModel(model);
		userController.setLoginMsg("");
		return "admin";
	}
/**
 * Mapping for topic.html, all topic list
 * @param ident ident
 * @param model Current model
 * @return String of address
 */
	@RequestMapping("/topic")
	public String topic(int ident, Model model) {
		categoryId = ident;
		model.addAttribute("getCategoryById", categoryService.getContentById(categoryId));
		fillModel(model);
		userController.setLoginMsg("");
		return "topic";
	}
	
	/**
	 * 
	 * @param value Value of topic(lock/unlock)
	 * @param lock boolean
	 * @param model Current model
	 * @return String to address
	 */
	
	@RequestMapping("/setTopicState")
	public String setTopicState(@RequestParam(value = "value", required = false) int value, @RequestParam(value = "lock", required = false) boolean lock, Model model) {
		//userServiceJPA.setRights(rhchange, setAdmin);
		fillModel(model);
		topicService.setTopicState(value, lock);
		return "forward:/";
	}
	
/**
 * Fill models 
 * @param model Current model
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
 * @param login username
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
 * @throws SQLException throws when is not connected to DB
 */
	public void updateDatabase() throws SQLException {
		ForumUser user = new ForumUser();
		user.setAdmin(1);
		user.setEmail("admin@admin");
		user.setLogin("admin");
		user.setPassword("Admin123");
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
