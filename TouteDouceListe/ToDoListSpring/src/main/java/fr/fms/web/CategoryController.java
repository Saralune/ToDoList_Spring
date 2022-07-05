/**
 * 
 */
package fr.fms.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import fr.fms.business.IBusinessImpl;
import fr.fms.entities.Category;

/**
 * @author Stagiaires10P
 *
 */
@Controller
public class CategoryController {
	@Autowired
	IBusinessImpl business;
	
	@PostMapping("/saveCategory")
	public String saveTask(Model model, @Valid Category category) {		
		business.saveCategory(category);
		
		return "redirect:/tasks";
	}
}
