package forum.server;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ForumController {

	@RequestMapping("/")
	public String user(Model model) {
		return "index";

	}
	
	
}
