package forum.server.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
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
	private UserController userController;
	private int topicId;
	private int categoryId;
	private ForumUser loggedPlayer;
	
	public ForumUser getLoggedPlayer() {
		return loggedPlayer;
	}

	public void setLoggedPlayer(ForumUser loggedPlayer) {
		this.loggedPlayer = loggedPlayer;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public int getTopicId() {
		return topicId;
	}

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

	@RequestMapping("/test1")
	public String test1(Model model) {
		return "test1";
	}

	@RequestMapping("/test2")
	public String test2(Model model) {
		fillModel(model);
		return "test2";
	}

	@RequestMapping("/test3")
	public String test3(Model model) {
		fillModel(model);
		return "test3";
	}

	@RequestMapping("/comment")
	public String comment(int ident, Model model) {
		topicId = ident;
		model.addAttribute("getTopicById", topicService.getContentById(topicId));
		fillModel(model);
		return "/comment";
	}

	@RequestMapping("/profile")
	public String profile(Model model) {
		fillModel(model);
		return "/profile";
	}

	@RequestMapping("/admin")
	public String admin(Model model) {
		fillModel(model);
		return "/admin";
	}

	@RequestMapping("/topic")
	public String topic(int ident, Model model) {
		categoryId = ident;
		model.addAttribute("getCategoryById", categoryService.getContentById(categoryId));
		fillModel(model);
		return "/topic";
	}


	private void fillModel(Model model) {
		model.addAttribute("controller", this);
		model.addAttribute("getComments", commentService.getCommentsTopic(topicId));
		model.addAttribute("getTopics", topicService.getTopicList(categoryId));
		model.addAttribute("categories", categoryService.getCategory());
		model.addAttribute("total_comments", commentService.getCommentCount());
		model.addAttribute("total_users", userService.getUserCount());
		model.addAttribute("total_topics", topicService.getTopicCount());
		
		
	}

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
		} catch (Exception e) {
			/*
			 * TODO Upravit exception
			 */
			System.out.println("daco je zle ");
		}
		return "data:image/png;base64," + finalImage;
	}
}
