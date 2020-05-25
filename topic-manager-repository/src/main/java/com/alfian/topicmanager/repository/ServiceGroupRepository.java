package com.alfian.topicmanager.repository;

import com.alfian.topicmanager.entity.ServiceGroup;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ServiceGroupRepository extends MongoRepository<ServiceGroup, String> {

  ServiceGroup findByServiceName(String serviceName);

  boolean existsByServiceName(String serviceName);

  @Query(value = "{serviceName: ?0}", fields = "{" + ServiceGroup.SUBSCRIBER_OF_TOPICS+ " : 0}")
  ServiceGroup getServiceGroupIgnoreSubscribedTopic(String serviceName);

  @Query(value = "{serviceName: ?0}", fields = "{" + ServiceGroup.PUBLISHER_OF_TOPICS + " : 0}")
  ServiceGroup getServiceGroupIgnorePublishedTopic(String serviceName);

}
