package com.valid.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.valid.model.User;
import com.valid.model.service.UserService;

@RestController
@Validated
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/Regist")
	public Object regist(@Valid @RequestBody User user){
		User result = userService.addUser(user);
		
		return result != null ? result 
			 : ResponseEntity
			  .status(400)
			  .body(Collections.singletonMap("error", "user not existed"));
	}
	
	@GetMapping("/User")
	public Object findUser(@NotBlank String id){
		User result = userService.findUser(id);
		
		return result != null ? result 
			 : ResponseEntity
			  .status(404)
			  .body(Collections.singletonMap("error", "user not existed"));
	}
	
	@GetMapping("/Users")
	public Object findUsers(){
		List<User> result = userService.findAll();
		
		return result.size() != 0 ? result  
			 : ResponseEntity
			  .status(404)
			  .body(Collections.singletonMap("error", "no user"));
	}
	
	@GetMapping("/Test")
	public Map<String,Object> test(){
		
		Map<String,Object> map = new HashMap<String, Object>();
		
		map.put("error", "something goes wrong");
		
		return map;
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, Object> handleValidationExceptions(
	  MethodArgumentNotValidException ex) {
	    Map<String, Object> errors = new HashMap<>();
	    List<String> errorList = new ArrayList<String>();
	    
	    ex.getBindingResult().getFieldErrors().forEach((fieldError) -> {
	    	errorList.add(fieldError.getField() + " : " + fieldError.getDefaultMessage());
	    });
	    
	    errors.put("errors",errorList);
	    return errors;
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
	public Map<String, Object> handleParameterValidationExceptions(
			ConstraintViolationException ex) {
	    Map<String, Object> errors = new HashMap<>();
	    List<String> errorList = new ArrayList<String>();
	    
	    ex.getConstraintViolations().forEach((error) -> {
	    	errorList.add(((PathImpl)error.getPropertyPath()).getLeafNode().getName() + " : " + error.getMessage());
	    });
	    
	    errors.put("errors",errorList);
	    
	    return errors;
	}
	
}
