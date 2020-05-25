package com.alfian.topicmanager.repository;

import com.alfian.topicmanager.entity.Topic;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TopicRepository extends MongoRepository<Topic, String> {
  Topic findByTopicName(String topicName);
}
