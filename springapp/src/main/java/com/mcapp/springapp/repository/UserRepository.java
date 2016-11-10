package com.mcapp.springapp.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mcapp.springapp.domain.User;

@Repository
public class UserRepository extends AbstractRepository<User> {
	
	public UserRepository() {
		super(User.class);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public User getUserByEmail(String email) {
		Optional<User> user = this.getSessionFactory().getCurrentSession()
										.createQuery("from User as u left outer join fetch u.roles where u.email = :email")
										.setParameter("email", email)
										.uniqueResultOptional();
		if(!user.isPresent() || !user.get().getClass().equals(User.class)) {
			return null;
		}
		
		return user.get();
	}
}