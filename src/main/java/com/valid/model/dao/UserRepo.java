package com.valid.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.valid.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, String> {

}
