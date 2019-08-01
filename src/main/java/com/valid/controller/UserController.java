package com.valid.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.valid.model.User;
import com.valid.model.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	
	@PostMapping("/Regist")
	public Object regist(@RequestBody User user){
		User result = userService.addUser(user);
		
		if(result == null) {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("error", "id already registed");
			return map;
		}
		
		return result;
	}
	
	@GetMapping("/User")
	public User findUser(String id){
//		User result = userService.findUser(id);
		
//		Map<String,Object> map = new HashMap<String, Object>();
//		
//		if(result != null) {
//			map.put("userId", result.getId());
//			map.put("userName", result.getName());
//		} else {
//			map.put("error", "user not existed");
//		}
		
		
//		return map;
		return userService.findUser(id);
	}
	
	@GetMapping("/Users")
	public Map<String,Object> findUsers(){
		List<User> result = userService.findAll();
		
		Map<String,Object> map = new HashMap<String, Object>();
		
		if(result.size() != 0) {
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			
			for(User user : result) {
				Map<String,Object> innerMap = new HashMap<String, Object>();
				innerMap.put("userId", user.getId());
				innerMap.put("userName", user.getName());
				list.add(innerMap);
			}
			
			map.put("userList",list);
			
		} else {
			map.put("error", "something goes wrong");
		}
		
		
		return map;
	}
	
	@GetMapping("/Test")
	public Map<String,Object> test(){
		
		Map<String,Object> map = new HashMap<String, Object>();
		
		map.put("error", "something goes wrong");
		
		return map;
	}
	
}
