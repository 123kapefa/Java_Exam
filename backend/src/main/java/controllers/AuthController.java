package controllers;

import database.entities.Todo_Role;
import database.entities.Todo_User;
import database.services.RoleService;
import database.services.UserService;
import database.services.implServices.UserServiceImpl;
import jakarta.validation.Valid;
import models.MessageResponse;
import models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import request.RegisterRequest;
import response.AuthResponse;
import security.jwt.JwtProvider;
//import security.userDetails.UserDetailsServiceImpl;

@RestController
public class AuthController {

    @Autowired private UserService userService;
    @Autowired private RoleService roleService;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private UserServiceImpl customUserDetails;

    @RequestMapping( method = RequestMethod.POST, path = "/api/auth/register")
    public ResponseEntity<AuthResponse> registerHandler(@Valid @RequestBody RegisterRequest request) throws Exception {
        if (userService.existsByEmail(request.getEmail())) {
            throw new Exception("Email is already used with another account");
        }

        Todo_User newUser = new Todo_User(
                request.getUsername(),
                request.getLogin(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword())
        );


        String strRole = request.getRole();
        switch (strRole) {
            case "admin": {
                Todo_Role todoRole = roleService.findByName(Role.ROLE_ADMIN)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found!"));

                newUser.setRole(todoRole);
                break;
            }
            case "mod": {
                Todo_Role todoRole = roleService.findByName(Role.ROLE_MODERATOR)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found!"));

                newUser.setRole(todoRole);
                break;
            }
            default: {
                Todo_Role todoRole = roleService.findByName(Role.ROLE_USER)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found!"));

                newUser.setRole(todoRole);
                break;
            }
        }

        Todo_User savedUser = userService.save(newUser);
        Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = JwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(token);
        authResponse.setMessage("Register Success");
        authResponse.setStatus(true);
        return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.OK);
        //return ResponseEntity.ok(new MessageResponse("User registered successfully"));
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

    private Authentication authenticate(String username, String password) {

        System.out.println(username+"---++----"+password);

        UserDetails userDetails = customUserDetails.loadUserByUsername(username);

        System.out.println("Sig in in user details"+ userDetails);

        if(userDetails == null) {
            System.out.println("Sign in details - null" + userDetails);

            throw new BadCredentialsException("Invalid username and password");
        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())) {
            System.out.println("Sign in userDetails - password mismatch"+userDetails);

            throw new BadCredentialsException("Invalid password");

        }
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

    }



}

