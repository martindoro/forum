package forum.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommentController {

	@RequestMapping("/comment")
	public String user(Model model) {
		return "index";

	}
	
	
}
