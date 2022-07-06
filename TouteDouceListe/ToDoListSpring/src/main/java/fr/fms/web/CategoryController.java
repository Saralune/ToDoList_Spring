/**
 * 
 */
package fr.fms.web;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
		business.saveOrUpdateCategory(category);
		
		return "redirect:/editTasks";
	}
	
	@PostMapping("/updateCategory")
	public String updateCategory(Model model, @Valid Category category, BindingResult bindingResult,
            @RequestParam(value = "id") Long id) {
		 
		if (bindingResult.hasErrors()) {
			System.err.println(bindingResult.getAllErrors());
            return "editTasks";
        }
		
		Category cat = business.readCategoryById(id);

		if(cat != null) {
			business.saveOrUpdateCategory(category);
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
	
//    // affiche les articles par catégorie
//    @GetMapping("/editTasksByCategory")
//    public String editTasksByCategory(Model model, Task task, Category category,
//            @RequestParam(name = "page", defaultValue = "0") int page,
//            @RequestParam(name = "keyword", defaultValue = "1") Long id) {
//
//        Page<Task> tasksByCat;
//		try {
//			tasksByCat = business.readTasksByCategory(id, page, 5);
//			
//	        List<Category> categories = business.findAllCategories();
//	        Category cat = business.readCategoryById(id);
//	        
//	        if (tasksByCat.isEmpty()) {
//	            //throw new GlobalException("Aucune données");
//	        }
//
//	        if (cat==null) {
//	            //throw new GlobalException("Aucunes categories");
//	        }
//
//	        model.addAttribute("listCategoryArticles", tasksByCat.getContent());
//	        model.addAttribute("listCategories", categories);
//	        model.addAttribute("pages", new int[tasksByCat.getTotalPages()]);
//	        model.addAttribute("currentPage", page);
//	        model.addAttribute("keyword", id);
//	        model.addAttribute("categoryName", cat.getName());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//        
//        return "redirect:/editTasks?page=" + page + "&keyword=" + id;
//    }
    
    @GetMapping("/readTasksByCategory")
    public String readTasksByCategory(Model model, Task task, Category category,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "keyword", defaultValue = "1") Long id) {
    	try {
			Page<Task> tasksByCat = business.readTasksByCategory(id, page, 5);
			Category cat = business.readCategoryById(id);
			
			//if(cat.isDeleted()) 
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        
    	return "redirect:/editTasks?page=" + page + "&keyword=" + id;
    }
}
