/**
 * 
 */
package fr.fms.web;

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
public class CategoryController {
	@Autowired
	IBusinessImpl business;
		
	@PostMapping("/saveCategory")
	public String saveCategory(Model model, @Valid Category category, 
			RedirectAttributes redirectAttrs) {	
		String mail = SecurityContextHolder.getContext().getAuthentication().getName();
		try {
			Users user = business.getUserByMail(mail);
			category.setUsers(user);
			
			business.saveOrUpdateCategory(category);
		} catch (Exception e) {
			redirectAttrs.addAttribute("error",e.getMessage());
		}
		
		return "redirect:/editTasks";
	}
	
	@PostMapping("/updateCategory")
	public String updateCategory(Model model, @Valid Category category, BindingResult bindingResult,
            @RequestParam(value = "id") Long id, 
			RedirectAttributes redirectAttrs) {
		String mail = SecurityContextHolder.getContext().getAuthentication().getName();
		 
		if (bindingResult.hasErrors()) {
			System.err.println(bindingResult.getAllErrors());
            return "editTasks";
        }
		
		try {
			Users user = business.getUserByMail(mail);
			Category cat = business.readCategoryById(id);
			
			category.setUsers(user);
			if(cat != null) business.saveOrUpdateCategory(category);
		} catch (Exception e) {
			redirectAttrs.addAttribute("error",e.getMessage());
		}

		return "redirect:/editTasks";
	}
	
	@GetMapping("/deleteCategory")
	public String deleteCategory(Model model, Long id, RedirectAttributes redirectAttrs) {
		try {
			business.deleteCategory(id);	

		} catch (Exception e) {
			redirectAttrs.addAttribute("error",e.getMessage());
		}
		
		return "redirect:/editTasks";
	}

    @GetMapping("/tasksByCategory")
    public String tasksByCategory(Model model, Long id, int page, 
			RedirectAttributes redirectAttrs) {
    	try {
			Page<Task> tasksByCat = business.readTasksByCategory(id, page, 5);
			
			model.addAttribute("listTasks", tasksByCat);
	        
		} catch (Exception e) {
			redirectAttrs.addAttribute("error",e.getMessage());
		}

        return "redirect:/readTasks";
    }

}
