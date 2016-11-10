package com.mcapp.springapp.repository;

import org.springframework.stereotype.Repository;

import com.mcapp.springapp.domain.Country;

@Repository
public class CountryRepository extends AbstractRepository<Country> {

	public CountryRepository() {
		super(Country.class);
	}
}
