package forum.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

import forum.service.CategoryServiceJPA;
import forum.service.CommentServiceJPA;
import forum.service.FavoriteServiceJPA;
import forum.service.TopicServiceJPA;
import forum.service.UserServiceJPA;

@SpringBootApplication
// @EnableWs
@EnableAutoConfiguration
@EntityScan({ "forum.entity" })
public class ForumServer {
	public static void main(String[] args) {
		SpringApplication.run(ForumServer.class, args);
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
	public FavoriteServiceJPA favoriteService() {
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
}
