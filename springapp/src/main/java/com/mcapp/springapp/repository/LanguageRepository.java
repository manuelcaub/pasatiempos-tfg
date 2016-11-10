package com.mcapp.springapp.repository;

import org.springframework.stereotype.Repository;

import com.mcapp.springapp.domain.Language;

@Repository
public class LanguageRepository extends AbstractRepository<Language> {

	public LanguageRepository() {
		super(Language.class);
	}
}
