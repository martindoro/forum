package forum.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

import forum.server.controller.ForumController;
import forum.server.controller.UserController;
import forum.service.CommentService;
import forum.service.EmailService;
import forum.service.FavoriteService;
import forum.service.UserService;
import forum.service.impl.CategoryServiceJPA;
import forum.service.impl.CommentServiceJPA;
import forum.service.impl.EmailServiceTLS;
import forum.service.impl.FavoriteServiceJPA;
import forum.service.impl.TopicServiceJPA;
import forum.service.impl.UserServiceJPA;

@SpringBootApplication
@EnableAutoConfiguration
@EntityScan({ "forum.entity" })
public class ForumServerForTest {
	/**
	 * Run Server
	 * 
	 * @param args
	 *            args
	 */
	public static void main(String[] args) {
		SpringApplication.run(ForumServerForTest.class, args);
	}

	@Bean
	public CategoryServiceJPA categoryService() {
		return new CategoryServiceJPA();
	}

	@Bean
	public CommentService commentService() {
		return new CommentServiceJPA();
	}

	@Bean
	public FavoriteService favoriteService() {
		return new FavoriteServiceJPA();
	}

	@Bean
	public TopicServiceJPA topicService() {
		return new TopicServiceJPA();
	}

	@Bean
	public UserService userService() {
		return new UserServiceJPA();
	}

	@Bean
	public EmailService emailService() {
		return new EmailServiceTLS();
	}

	@Bean
	public ForumController forumController() {
		return new ForumController();
	}

	@Bean
	public UserController userController() {
		return new UserController();
	}

}
