package com.valid.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/Regist")
	public Object regist(@Valid @RequestBody User user){
		Map<String,Object> map = new HashMap<String, Object>();
		
		User result = userService.addUser(user);
		
		if(result == null) {
			map.put("error", "id already registed");
			return map;
		}
		
		return result;
	}
	
	@GetMapping("/User")
	public Object findUser(String id){
		User result = userService.findUser(id);
		
		Map<String,Object> map = new HashMap<String, Object>();
		
		if(result == null) 
			map.put("error", "user not existed");
		
		
		return map.isEmpty() ? result : map;
	}
	
	@GetMapping("/Users")
	public Object findUsers(){
		List<User> result = userService.findAll();
		
		Map<String,Object> map = new HashMap<String, Object>();
		
		if(result.size() == 0) 
			map.put("error", "something goes wrong");
		
		return map.isEmpty() ? result : map;
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
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        errorList.add(error.getDefaultMessage());
	    });
	    
	    errors.put("errors",errorList);
	    return errors;
	}
	
}
