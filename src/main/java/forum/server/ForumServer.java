package forum.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;




	@SpringBootApplication
	//@EnableWs
	@EnableAutoConfiguration
	public class ForumServer {
		public static void main(String[] args) {
			SpringApplication.run(ForumServer.class, args);
		}

//		@Bean
//		/*TODO doplnit JPA services /* 
//

	}


