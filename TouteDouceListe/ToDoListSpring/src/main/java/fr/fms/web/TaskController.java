/**
 * 
 */
package fr.fms.web;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.fms.business.IBusinessImpl;
import fr.fms.entities.Category;
import fr.fms.entities.Task;
import fr.fms.entities.Users;

/**
 * @author Stagiaires10P
 *
 */
@Controller
public class TaskController {
	@Autowired
	IBusinessImpl business;

	@GetMapping("/readTasks")
	public String readTasks(Model model, 
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "keyword", defaultValue = "") String kw, 
			@RequestParam(name = "category", defaultValue = "") Long idCat) {
		
		Page<Task> listTasks;
		try {
			listTasks = business.readByDescriptionContains(kw, page, 5);
			List<Category> listCategories = business.findAllCategories();
			Page<Task> tasksByCat = business.readTasksByCategory(idCat, page, 5);
			
			model.addAttribute("listCategories", listCategories);
			model.addAttribute("pages", new int[tasksByCat.getTotalPages()]);
			model.addAttribute("listTasks", tasksByCat.getContent());
			model.addAttribute("idCat", idCat);
			
			if(idCat == null) {
				model.addAttribute("listTasks", listTasks.getContent());
				model.addAttribute("pages", new int[listTasks.getTotalPages()]);
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}

		model.addAttribute("currentPage", page);
		model.addAttribute("keyword", kw);

		return "readTasks";
	}

	@GetMapping("/editTasks")
	public String tasks(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "keyword", defaultValue = "") String kw) {
		Page<Task> listTasks;
		try {
			listTasks = business.readByDescriptionContains(kw, page, 5);
			List<Category> listCategories = business.findAllCategories();

			model.addAttribute("listCategories", listCategories);
			model.addAttribute("listTasks", listTasks.getContent());
			model.addAttribute("pages", new int[listTasks.getTotalPages()]);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("currentPage", page);
		model.addAttribute("keyword", kw);

		return "editTasks";
	}

	@PostMapping("/saveTask")
	public String saveTask(Model model, @Valid Task task) {
		String mail = SecurityContextHolder.getContext().getAuthentication().getName();
		
		try {
			List<Category> listCategories = business.findAllCategories();
			model.addAttribute("listCategories", listCategories);
			
			Users user = business.getUserByMail(mail);
			task.setUsers(user);
			
			business.saveOrUpdateTask(task);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/editTasks";
	}

	@GetMapping("/deleteTask")
	public String deleteTask(Long id, int page, String keyword) {
		try {
			business.deleteTask(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/editTasks?page=" + page + "&keyword=" + keyword;
	}

	@PostMapping("/updateTask")
	public String updateArticle(@Valid Task task, BindingResult bindingResult, @RequestParam(value = "id") Long id,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "keyword", defaultValue = "") String keyword) {
		if (bindingResult.hasErrors()) return "editTasks";

		try {
			Task taskToEdit = business.readTasksById(id);

			if (taskToEdit != null) business.saveOrUpdateTask(task);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/editTasks?page=" + page + "&keyword=" + keyword;
	}
}
