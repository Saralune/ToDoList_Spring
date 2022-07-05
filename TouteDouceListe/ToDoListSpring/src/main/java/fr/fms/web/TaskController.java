/**
 * 
 */
package fr.fms.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.fms.business.IBusinessImpl;
import fr.fms.entities.Category;
import fr.fms.entities.Task;

/**
 * @author Stagiaires10P
 *
 */
@Controller
public class TaskController {
	@Autowired
	IBusinessImpl business;

	@GetMapping("/home")
	public String home(Model model) {
		return "home";
	}
	
	@GetMapping("/index")
	public String index(Model model) {
		return "home";
	}

	@GetMapping("/")
	public String accueil(HttpSession session) {
		//TODO INDIQUER LE NB DE TACHES ????
//		int length = business.sizeCaddy();
//		session.setAttribute("caddySize", length);
		return "home";
	}
	
	@GetMapping("/403")
	public String error403() {
		return "403";
	}
	
	@GetMapping("/404")
	public String error404() {
		return "404";
	}
	
	@GetMapping("tasks")
	public String tasks(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "keyword", defaultValue = "") String kw) {
		Page<Task> listTasks = business.readByDescriptionContains(kw, page, 10);
		List<Category> listCategories = business.findAllCategories();
		
		model.addAttribute("listCategories", listCategories);
		model.addAttribute("currentPage", page);
		model.addAttribute("keyword", kw);
		model.addAttribute("listTasks", listTasks.getContent());
		model.addAttribute("pages", new int[listTasks.getTotalPages()]);
		
		return "tasks";
	}
	
	@PostMapping("/addTask")
	public String addTask(Model model) {
		List<Category> listCategories = business.findAllCategories();
		
		model.addAttribute("listCategories", listCategories);
		return "tasks";
	}
}
