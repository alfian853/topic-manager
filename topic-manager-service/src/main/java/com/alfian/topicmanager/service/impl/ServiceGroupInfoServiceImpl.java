package com.alfian.topicmanager.service.impl;

import com.alfian.topicmanager.entity.ServiceGroup;
import com.alfian.topicmanager.repository.ServiceGroupRepository;
import com.alfian.topicmanager.service.ServiceGroupInfoService;
import com.alfian.topicmanager.service.model.ServiceAnalyzeRequest;
import com.alfian.topicmanager.service.model.ServiceAnalyzeResponse;
import com.alfian.topicmanager.service.model.ServiceInfoResponse;
import com.alfian.topicmanager.service.util.AnalyzeOption;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ServiceGroupInfoServiceImpl implements ServiceGroupInfoService {

  @Autowired
  private ServiceGroupRepository serviceGroupRepository;

  @Override
  public ServiceInfoResponse getServiceTopicInfo(String serviceName) {
    ServiceGroup serviceGroup = serviceGroupRepository.findByServiceName(serviceName);

    if(serviceGroup == null)return null;

    return ServiceInfoResponse.builder()
      .serviceName(serviceName)
      .publisherOfTopics(serviceGroup.getPublisherOfTopics())
      .subscriberOfTopics(serviceGroup.getSubscriberOfTopics())
      .build();
  }

  @Override
  public ServiceAnalyzeResponse analyzeService(ServiceAnalyzeRequest serviceAnalyzeRequest) {

    if(!serviceGroupRepository.existsByServiceName(serviceAnalyzeRequest.getServiceName())) return null;

    ServiceAnalyzeResponse response = new ServiceAnalyzeResponse();

    if(AnalyzeOption.PUBLISHED_TOPIC.isTrue(serviceAnalyzeRequest.getOptionsState())){
      response.setPublishedTopicsWarning(
        serviceGroupRepository.getServiceGroupIgnoreSubscribedTopic(serviceAnalyzeRequest.getServiceName()).getPublisherOfTopics()
      );
    }

    if(AnalyzeOption.SUBSCRIBED_TOPIC.isTrue(serviceAnalyzeRequest.getOptionsState())){
      response.setSubscribedTopicsWarning(
        serviceGroupRepository.getServiceGroupIgnorePublishedTopic(serviceAnalyzeRequest.getServiceName()).getSubscriberOfTopics()
      );
    }

    return response;
  }


}
