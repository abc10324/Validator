package com.valid.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.valid.model.User;
import com.valid.model.dao.UserRepo;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	public User addUser(User user) {
		if(userRepo.findOne(user.getId()) != null)
			return null;
		
		userRepo.save(user);
		return userRepo.findOne(user.getId());
	}
	
	public User findUser(String id) {
		return userRepo.findOne(id);
	}
	
	public List<User> findAll(){
		return userRepo.findAll();
	}
}
