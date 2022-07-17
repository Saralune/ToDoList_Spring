/**
 * 
 */
package fr.fms.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import fr.fms.entities.Users;

/**
 * @author Stagiaires10P
 *
 */
@Controller
public class UsersController {
		
   @GetMapping("/login")
    public String login(Model model, Users users) {
        return "login";
    }
    
    @PostMapping("/login")
    public String connect() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/demo";
    }

    @PostMapping("/logout")
    public String disconnect() {
        return "redirect:/demo";
    }
	
	@GetMapping("/403")
	public String error403() {
		return "403";
	}
	
	@GetMapping("/404")
	public String error404() {
		return "404";
	}
	
	@GetMapping("/demo")
	public String demo(Model model) {
		return "demo";
	}
	
	@GetMapping("/index")
	public String index(Model model) {
		
		return "readTasks";
	}

	@GetMapping("/")
	public String accueil() {
		return "demo";
	}
	
	@GetMapping("")
	public String localhost() {
		return "demo";
	}
	
}
