package com.mcapp.springapp.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mcapp.springapp.domain.Topic;
import com.mcapp.springapp.repository.TopicRepository;

import com.mcapp.springapp.service.interfaces.TopicService;

@Service
public class TopicServiceImpl implements TopicService {
	
	@Resource
	private TopicRepository repTopic;
	
	public List<Topic> getTopics(){
		return this.repTopic.getTopics();
	}
}