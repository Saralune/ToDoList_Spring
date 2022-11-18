package fr.lefort.web;

import fr.lefort.dao.RoleRepository;
import fr.lefort.dao.UsersRepository;
import fr.lefort.entities.Role;
import fr.lefort.entities.Users;
import fr.lefort.security.payload.request.LoginRequest;
import fr.lefort.security.payload.request.SignupRequest;
import fr.lefort.security.payload.response.JwtResponse;
import fr.lefort.security.payload.response.MessageResponse;
import fr.lefort.security.security.jwt.JwtUtils;
import fr.lefort.security.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UsersRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
    System.out.println("plouf");
    System.out.println(loginRequest.getUsername() + " -*-*- " + loginRequest.getPassword());

    Authentication authentication = authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    System.out.println("authentication");

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    List<String> roles = userDetails.getAuthorities().stream()
      .map(item -> item.getAuthority())
      .collect(Collectors.toList());

    return ResponseEntity.ok(new JwtResponse(jwt,
      userDetails.getId(),
      userDetails.getUsername(),
      userDetails.getMail(),
      roles));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
        .badRequest()
        .body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByMail(signUpRequest.getMail())) {
      return ResponseEntity
        .badRequest()
        .body(new MessageResponse("Error: Email is already in use!"));
    }

    // Create new user's account
    List<Role> newRole = new ArrayList<>();
    Role user = new Role("USER");
    newRole.add(user);

    Users users = new Users(null, (signUpRequest.getUsername()),
      signUpRequest.getMail(),
      encoder.encode(signUpRequest.getPassword()), true, newRole);

    Set<String> strRoles = signUpRequest.getRole();
    List<Role> roles = new ArrayList<>();

    if (strRoles == null) {
      Role userRole = roleRepository.findByRole("USER")
        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
          case "ADMIN":
            Role adminRole = roleRepository.findByRole("ADMIN")
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            System.out.println("adminrole" + adminRole.getRole());
            roles.add(adminRole);

            break;
          default:
            Role userRole = roleRepository.findByRole("USER")
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            System.out.println("userrole" + userRole.getRole());
            roles.add(userRole);
        }
      });
    }

    users.setRoles(roles);
    userRepository.save(users);

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }
}
