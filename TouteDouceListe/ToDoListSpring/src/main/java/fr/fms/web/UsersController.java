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
        return "redirect:/login";
    }

//	    @PostMapping("/logout")
//	    public String disconnect() {
//	        return "redirect:/login";
//	    }
    
    
}
