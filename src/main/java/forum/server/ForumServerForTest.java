package forum.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

import forum.server.controller.ForumController;
import forum.server.controller.UserController;
import forum.service.CategoryServiceJPA;
import forum.service.CommentServiceJPA;
import forum.service.EmailServiceTLS;
import forum.service.FavoriteService;
import forum.service.FavoriteServiceJPA;
import forum.service.TopicServiceJPA;
import forum.service.UserServiceJPA;

@SpringBootApplication
@EnableAutoConfiguration
@EntityScan({ "forum.entity" })
public class ForumServerForTest {
	/**
	 * Run Server
	 * @param args args
	 */	  
	public static void main(String[] args) {
		SpringApplication.run(ForumServerForTest.class, args);
	}

	@Bean
	public CategoryServiceJPA categoryService() {
		return new CategoryServiceJPA();
	}

	@Bean
	public CommentServiceJPA commentService() {
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
	public UserServiceJPA userService() {
		return new UserServiceJPA();
	}
	
	@Bean
	public EmailServiceTLS emailService() {
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
