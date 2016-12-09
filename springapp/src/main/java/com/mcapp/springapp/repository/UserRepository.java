package com.mcapp.springapp.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mcapp.springapp.common.dto.SimpleUserDto;
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

	@Transactional(readOnly = true)
	public List<SimpleUserDto> getSimpleUsersExceptAdmin() {
		return this.hibernate().createNativeQuery("select u.name name, u.email email, GROUP_CONCAT(r.role) roles from user u join user2role ur ON ur.user = u.id join role r ON r.id = ur.role where u.id NOT IN (select u.id from user u join user2role ur ON ur.user = u.id join role r ON r.id = ur.role AND r.role = \"ROLE_ADMIN\")  group by u.id;").setResultTransformer( Transformers.aliasToBean(SimpleUserDto.class)).getResultList();
	}

	@Transactional
	public void removeUserByEmail(String email) {
		User user = this.getUserByEmail(email);
		this.hibernate().remove(user);
	}
}