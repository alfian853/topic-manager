package com.alfian.topicmanager.service.impl;

import com.alfian.topicmanager.entity.Topic;
import com.alfian.topicmanager.repository.TopicRepository;
import com.alfian.topicmanager.service.TopicService;
import com.alfian.topicmanager.service.model.TopicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicServiceImpl implements TopicService {

  @Autowired
  private TopicRepository topicRepository;

  @Override
  public TopicResponse getTopicData(String topicName) {
    Topic topic = topicRepository.findByTopicName(topicName);
    TopicResponse topicResponse = new TopicResponse();
    topicResponse.setTopicName(topicName);

    if(topic != null){
      topicResponse.setPublisherServices(topic.getPublisherServices());
      topicResponse.setSubscriberServices(topic.getSubscriberServices());
    }
    return topicResponse;
  }

}
