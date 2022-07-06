/**
 * 
 */
package fr.fms.web;

import javax.servlet.http.HttpSession;

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
	
	//TODO Voir quel login garder ??
	
	
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
		
	@GetMapping("/home")
	public String home(Model model) {
		return "demo";
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
	public String accueil(HttpSession session) {
		//TODO INDIQUER LE NB DE TACHES ????
//		int length = business.sizeCaddy();
//		session.setAttribute("isAuthentified", authentified);
		return "editTasks";
	}
	
}
