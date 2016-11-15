package com.mcapp.springapp.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mcapp.springapp.domain.Topic;

@Repository
public class TopicRepository extends AbstractRepository<Topic> {
	
	public TopicRepository() {
		super(Topic.class);
	}
	
	public List<Topic> getTopics () {
		return this.findAll();
	}
}