package com.alfian.topicmanager.service.impl;

import com.alfian.topicmanager.entity.ServiceGroup;
import com.alfian.topicmanager.entity.Topic;
import com.alfian.topicmanager.repository.ServiceGroupRepository;
import com.alfian.topicmanager.repository.TopicRepository;
import com.alfian.topicmanager.service.RegistryService;
import com.alfian.topicmanager.service.model.RegistryRequest;
import com.alfian.topicmanager.service.model.RegistryResponse;
import com.alfian.topicmanager.service.util.DiffResult;
import com.alfian.topicmanager.service.util.DiffUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
public class RegistryServiceImpl implements RegistryService {

  @Autowired
  private ServiceGroupRepository serviceGroupRepository;

  @Autowired
  private TopicRepository topicRepository;

  @Autowired
  private MongoTemplate mongoTemplate;

  @Override
  public RegistryResponse register(RegistryRequest registryRequest) {
    RegistryResponse response = new RegistryResponse();
    ServiceGroup service = serviceGroupRepository.findByServiceName(registryRequest.getServiceName());

    if(service == null){
      service = new ServiceGroup();
      service.setServiceName(registryRequest.getServiceName());
    }

    response.getMessages().addAll(this.updatePublisherAndGetMessages(service, registryRequest));
    response.getMessages().addAll(this.updateSubscriberAndGetMessages(service, registryRequest));

    service.setPublisherOfTopics(registryRequest.getPublishedTopics());
    service.setSubscriberOfTopics(registryRequest.getSubscribedTopics());

    serviceGroupRepository.save(service);

    return response;
  }

  private List<String> updatePublisherAndGetMessages(ServiceGroup serviceGroup, RegistryRequest registryRequest){
    List<String> messages = new LinkedList<>();

    BulkOperations updateOperations = mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, Topic.class);
    BulkOperations upsertOperations = mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, Topic.class);

    List<DiffResult<String>> diffResults
      = DiffUtil.getDiff(
      serviceGroup.getPublisherOfTopics(),//firtlist
      registryRequest.getPublishedTopics(),//secondlist
      String::compareTo
    );

    boolean hasUpdate = false;
    boolean hasUpsert = false;

    for (DiffResult<String> diffResult: diffResults){

      if(diffResult.isFirstListItem()){
        messages.add("unregister topic: " + diffResult.getItem());
        Query query = new Query();
        query.addCriteria(Criteria.where(Topic.TOPIC_NAME).is(diffResult.getItem()));

        Update update = new Update();
        update.pull(Topic.PUBLISHER_SERVICES, serviceGroup.getServiceName());

        updateOperations.updateMulti(query, update);
        log.info("unregister topic: " + diffResult.getItem());
        hasUpdate = true;
      }
      else{
        messages.add("register new topic: " + diffResult.getItem());
        Query query = new Query();
        query.addCriteria(Criteria.where(Topic.TOPIC_NAME).is(diffResult.getItem()));
        Update update = new Update();
        update.push(Topic.PUBLISHER_SERVICES, serviceGroup.getServiceName());
        updateOperations.updateMulti(query, update);

        update = new Update();
        update.setOnInsert(Topic.TOPIC_NAME, diffResult.getItem());
        update.setOnInsert(Topic.PUBLISHER_SERVICES, Collections.singleton(serviceGroup.getServiceName()));
        update.setOnInsert(Topic.SUBSCRIBER_SERVICES, Collections.emptyList());
        upsertOperations.upsert(query, update);

        log.info("register new topic: " + diffResult.getItem());
        hasUpdate = true;
        hasUpsert = true;
      }
    }

    if(hasUpdate){
      updateOperations.execute();
    }
    if(hasUpsert){
      upsertOperations.execute();
    }

    return messages;
  }

  private List<String> updateSubscriberAndGetMessages(ServiceGroup serviceGroup, RegistryRequest registryRequest){
    List<String> messages = new LinkedList<>();

    BulkOperations updateOperations = mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, Topic.class);
    BulkOperations upsertOperations = mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, Topic.class);

    boolean hasUpdate = false;
    boolean hasUpsert = false;

    List<DiffResult<String>> diffResults
      = DiffUtil.getDiff(
        serviceGroup.getSubscriberOfTopics(),//firtlist
        registryRequest.getSubscribedTopics(),//secondlist
        String::compareTo
    );

    for (DiffResult<String> diffResult: diffResults){
      if(diffResult.isFirstListItem()){
        messages.add("unsubscribe topic: " + diffResult.getItem());
        Query query = new Query();
        query.addCriteria(Criteria.where(Topic.TOPIC_NAME).is(diffResult.getItem()));

        Update update = new Update();
        update.pull(Topic.SUBSCRIBER_SERVICES, serviceGroup.getServiceName());
        updateOperations.updateMulti(query, update);
        log.info("unsubscribe topic: " + diffResult.getItem());
        hasUpdate = true;
      }
      else{
        messages.add("subscribe to new topic: " + diffResult.getItem());
        Query query = new Query();
        query.addCriteria(Criteria.where(Topic.TOPIC_NAME).is(diffResult.getItem()));

        Update update = new Update();
        update.push(Topic.SUBSCRIBER_SERVICES, serviceGroup.getServiceName());
        updateOperations.updateMulti(query, update);

        update = new Update();
        update.setOnInsert(Topic.TOPIC_NAME, diffResult.getItem());
        update.setOnInsert(Topic.SUBSCRIBER_SERVICES, Collections.singleton(serviceGroup.getServiceName()));
        update.setOnInsert(Topic.PUBLISHER_SERVICES, Collections.emptyList());

        upsertOperations.upsert(query, update);
        log.info("subscribe topic: " + diffResult.getItem());
        hasUpdate = true;
        hasUpsert = true;
      }
    }

    if(hasUpdate){
      updateOperations.execute();
    }
    if(hasUpsert){
      upsertOperations.execute();
    }

    return messages;
  }

}
