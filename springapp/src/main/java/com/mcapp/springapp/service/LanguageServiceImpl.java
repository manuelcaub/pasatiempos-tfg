package com.mcapp.springapp.service;

import com.mcapp.springapp.repository.LanguageRepository;
import com.mcapp.springapp.service.interfaces.LanguageService;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mcapp.springapp.domain.Language;

@Service
public class LanguageServiceImpl implements LanguageService {

	@Resource
	private LanguageRepository repLanguage;
	
	public List<Language> getLanguages(){
		return this.repLanguage.findAll();
	}
}
