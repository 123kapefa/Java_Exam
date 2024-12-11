package controllers;

import database.entities.Todo_Role;
import database.entities.Todo_User;
import database.services.UserService;
import jakarta.validation.Valid;
import models.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import request.RegisterRequest;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

// http://localhost:8080/api/auth/register
@CrossOrigin(origins = "http://localhost:8080")
//@CrossOrigin
@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired private UserService service;
    @Autowired private PasswordEncoder passwordEncoder;

    @RequestMapping( method = RequestMethod.POST, path = "/auth/register")
    public ResponseEntity<MessageResponse> registerHandler(@Valid @RequestBody RegisterRequest request) {
        if (service.existsByEmail(request.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        Todo_User newUser = new Todo_User(
                request.getLogin(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword())
        );

        Set<String> strRoles = Collections.singleton(request.getRole());
        Set<Todo_Role> roles = new HashSet<>();

        if (strRoles == null) {

            service.save(newUser);
            return ResponseEntity.ok(new MessageResponse("User registered successfully"));
        } else {
            return (ResponseEntity<MessageResponse>) ResponseEntity.status(HttpStatus.ACCEPTED);
        }

    }

//    @GetMapping("/login")
//    public String login(Model model) {
//        LoginCredentials user = new LoginCredentials();
//        model.addAttribute("user", new LoginCredentials());
//        return "login";
//    }

//    @PostMapping("/auth/login")
//    //@ResponseBody
//    public String loginHandler(@ModelAttribute LoginCredentials body, Model model, HttpServletResponse response){
//        try {
//            UsernamePasswordAuthenticationToken authInputToken =
//                    new UsernamePasswordAuthenticationToken(body.getEmail(), body.getPassword());
//
//            authManager.authenticate(authInputToken);
//
//            String token = jwtUtil.generateToken(body.getEmail());
//
//            Optional<Todo_User> user = service.findByEmail(body.getEmail());
//
//            if(!user.isEmpty()) {
//                model.addAttribute("user_id", user.get().getId());
//                Cookie cookieToken = new Cookie("jwt-token", token);
//                cookieToken.setHttpOnly(true);
//                cookieToken.setSecure(true);
//                cookieToken.setPath("/");
//                cookieToken.setMaxAge(24 * 60 * 60);
//                response.addCookie(cookieToken);
//
//                Cookie cookieUser = new Cookie("userId", user.get().getId().toString());
//                cookieUser.setHttpOnly(true);
//                cookieUser.setSecure(true);
//                cookieUser.setPath("/");
//                cookieUser.setMaxAge(24 * 60 * 60);
//                response.addCookie(cookieUser);
//            } else {
//                return "login";
//            }
//            model.addAttribute("token", token);
//            return "redirect:/list";
//        }catch (AuthenticationException authExc){
//            throw new RuntimeException("Invalid Login Credentials");
//        }
//    }

//    @GetMapping("/register")
//    public String register(Model model) {
//        model.addAttribute("user", new Todo_User());
//        return "register";
//    }


}

