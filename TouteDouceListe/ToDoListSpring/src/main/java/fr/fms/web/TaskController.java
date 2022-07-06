/**
 * 
 */
package fr.fms.web;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

	@GetMapping("/readTasks")
	public String readTasks(Model model, 
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "keyword", defaultValue = "") String kw,
			@RequestParam(name="idCat" , defaultValue = "0") Long idCat) {
		Page<Task> listTasks = business.readByDescriptionContains(kw, page, 5);
		List<Category> listCategories = business.findAllCategories();

		model.addAttribute("listCategories", listCategories);
		model.addAttribute("currentPage", page);
		model.addAttribute("keyword", kw);
		model.addAttribute("listTasks", listTasks.getContent());
		model.addAttribute("pages", new int[listTasks.getTotalPages()]);
		model.addAttribute("idCat",idCat);

		return "readTasks";
	}

	@GetMapping("/editTasks")
	public String tasks(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "keyword", defaultValue = "") String kw) {
		Page<Task> listTasks = business.readByDescriptionContains(kw, page, 5);
		List<Category> listCategories = business.findAllCategories();

		model.addAttribute("listCategories", listCategories);
		model.addAttribute("currentPage", page);
		model.addAttribute("keyword", kw);
		model.addAttribute("listTasks", listTasks.getContent());
		model.addAttribute("pages", new int[listTasks.getTotalPages()]);

		return "editTasks";
	}

	@PostMapping("/saveTask")
	public String saveTask(Model model, @Valid Task task) {
		List<Category> listCategories = business.findAllCategories();

		model.addAttribute("listCategories", listCategories);
		
		
		
		task.setUsers(null);
		business.saveOrUpdateTask(task);

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
		if (bindingResult.hasErrors())
			return "editTasks";

		Task taskToEdit = business.readTasksById(id);

		if (taskToEdit != null)
			business.saveOrUpdateTask(task);

		return "redirect:/editTasks?page=" + page + "&keyword=" + keyword;
	}

//	@PostMapping("/updateInputChecked()")
//	public boolean updateInputChecked(boolean checked) {
//		return business.updateInput(checked);
//	}
}
