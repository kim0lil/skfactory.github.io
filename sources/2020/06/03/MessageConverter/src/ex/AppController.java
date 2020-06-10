package ex;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AppController {
	
	private final String prefix = "__";
	private final String suffix = "$$";
	
	@RequestMapping(value= {"/",""}, produces="application/todo")
	@ResponseBody
	public Todo home(Todo todo) {

		todo.setContents(prefix + todo.getContents() + suffix);

		return todo;
	}
}