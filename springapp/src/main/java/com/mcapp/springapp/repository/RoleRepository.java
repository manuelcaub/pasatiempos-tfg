package com.mcapp.springapp.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mcapp.springapp.domain.Role;

@Repository
public class RoleRepository extends AbstractRepository<Role> {
	public RoleRepository() {
		super(Role.class);
	}

	@Transactional(readOnly = true)
	public Role getByName(String role) {
		return (Role) this.hibernate().createQuery("from Role as r where r.role = :role").setParameter("role", role).getResultList().get(0);
	}
}
