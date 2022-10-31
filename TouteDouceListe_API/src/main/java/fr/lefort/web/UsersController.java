package fr.lefort.web;

import fr.lefort.business.IBusinessUsersImpl;
import fr.lefort.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//TODO vérifier s'il faut le placer dans le test ou le garder

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class UsersController {

  @Autowired
  private IBusinessUsersImpl iBusiness;

  // test login provisoire pour creer user dans localstorage
  @GetMapping("/login/{mail}")
  public Users userLogin(@PathVariable("mail") String mail) throws Exception {
    return iBusiness.getUserByMail(mail).get();
  }

}
