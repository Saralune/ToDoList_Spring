/**
 * 
 */
package fr.fms.web;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class CategoryController {
	@Autowired
	IBusinessImpl business;
	
	private final Logger logger = LoggerFactory.getLogger(CategoryController.class);
	
	@PostMapping("/saveCategory")
	public String saveCategory(Model model, @Valid Category category) {	
		String mail = SecurityContextHolder.getContext().getAuthentication().getName();
		try {
			Users user = business.getUserByMail(mail);
			category.setUsers(user);
			
			business.saveOrUpdateCategory(category);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/editTasks";
	}
	
	@PostMapping("/updateCategory")
	public String updateCategory(Model model, @Valid Category category, BindingResult bindingResult,
            @RequestParam(value = "id") Long id) {
		 
		if (bindingResult.hasErrors()) {
			System.err.println(bindingResult.getAllErrors());
            return "editTasks";
        }
		
		try {
			Category cat = business.readCategoryById(id);
			if(cat != null) business.saveOrUpdateCategory(category);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/editTasks";
	}
	
	////////TODO ATTENTION, mettre une condition, si la catégorie est reliée à une tpache
	@GetMapping("/deleteCategory")
	public String deleteCategory(Model model, Long id, RedirectAttributes redirectAttrs) {
		try {
			business.deleteCategory(id);	

		} catch (Exception e) {
			//e.printStackTrace();
			//model.addAttribute("error", e);
			//model.addAttribute("errorMsg", e.getMessage());
			redirectAttrs.addAttribute("error",e.getMessage());
			logger.error("[LOG CATEGORY CONTROLLER : DELETE] " + e.getMessage());
		}
		
		return "redirect:/editTasks";
	}

    // affiche les articles par catégorie
    @GetMapping("/tasksByCategory")
    public String tasksByCategory(Model model, Long id, int page) {
    	try {
    		Category category = business.readCategoryById(id);
			Page<Task> tasksByCat = business.readTasksByCategory(id, page, 5);
			
			model.addAttribute("listTasks", tasksByCat);
		    //return "redirect:/readTasks?category=" + id;
	        
		} catch (Exception e) {
			e.printStackTrace();
		}

        return "redirect:/readTasks";
    }

}
