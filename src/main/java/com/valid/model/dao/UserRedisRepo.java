package com.valid.model.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.valid.model.User;

@Repository
public interface UserRedisRepo extends CrudRepository<User, String> {

}
