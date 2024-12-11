package controllers;

import database.services.UserService;
import jakarta.validation.Valid;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@RestController
public class EmailController {

    @Autowired
    UserService service;

//    @RequestMapping(method = RequestMethod.GET, path = "/todo")
//    public List<TodoEntity> getAll(){
//        return service.getAll();
//    }
//
//    @RequestMapping(method = RequestMethod.POST, path = "/new")
//    public boolean add(@Valid @RequestBody TodoEntity entity){
//        return todoService.add(entity);
//    }
//
//    @RequestMapping(method = RequestMethod.PUT, path = "/edit")
//    public boolean edit(@Valid @RequestBody TodoEntity entity){
//        return todoService.edit(entity);
//    }
//
//    //It wasn't mentioned in the exercise, but I added it for convenience
//    @RequestMapping(method = RequestMethod.DELETE, path = "/delete/{id}")
//    public boolean delete(@PathVariable("id") long id){
//        return todoService.delete(id);
//    }
//
//    //It would be better to return an object containing for ex. list of field names and error messages,
//    //but I return a boolean in accordance with the description of exercise
//    @ExceptionHandler
//    public boolean handleValidationException(MethodArgumentNotValidException exception){
//        return false;
//    }

}