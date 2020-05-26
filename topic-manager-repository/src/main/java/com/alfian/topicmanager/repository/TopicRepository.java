package com.alfian.topicmanager.repository;

import com.alfian.topicmanager.entity.Topic;
import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface TopicRepository extends MongoRepository<Topic, String> {
  Topic findByTopicName(String topicName);

  String EMPTY_SIZE = "{$size: 0}";
  @DeleteQuery(
    "{"+Topic.PUBLISHER_SERVICES+": "+EMPTY_SIZE+", " +
    ""+Topic.SUBSCRIBER_SERVICES+":"+EMPTY_SIZE+"}"
  )
  void deleteJunkTopic();
}
