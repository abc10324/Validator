package com.valid.model.dao;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.valid.model.User;

@Repository
public class UserDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private final String SELECT_BY_ID = "from com.valid.model.User a where a.id = :id";
	private final String SELECT_ALL = "from com.valid.model.User";
	
	public User findOne(String id) {
		
		try {
			return (User)entityManager.createQuery(SELECT_BY_ID)
									  .setParameter("id", id)
									  .getSingleResult();
		} catch (NoResultException e) {
//			e.printStackTrace();
			return null;
		}
	}
	
	public List<User> findAll(){
		return entityManager.createQuery(SELECT_ALL)
						   	.getResultList();
	}
	
	public User save(User entity) {
		try {
			entityManager.persist(entity);
			return entity;
		} catch (EntityExistsException e) {
//			e.printStackTrace();
			return null;
		}
	}
}
