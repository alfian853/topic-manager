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

import java.util.stream.Collectors;

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
      .publishedTopics(serviceGroup.getPublishedTopics())
      .subscribedTopics(serviceGroup.getSubscribedTopics())
      .build();
  }

  @Override
  public ServiceAnalyzeResponse analyzeService(ServiceAnalyzeRequest serviceAnalyzeRequest) {

    if(!serviceGroupRepository.existsByServiceName(serviceAnalyzeRequest.getServiceName())) return null;

    ServiceAnalyzeResponse response = new ServiceAnalyzeResponse();

    if(serviceAnalyzeRequest.getOptionsState().isTrue(AnalyzeOption.PUBLISHED_TOPIC)){
      response.setPublishedTopicsWarning(
        serviceGroupRepository.getServiceGroupIgnoreSubscribedTopic(serviceAnalyzeRequest.getServiceName()).getPublishedTopics()
          .stream().map(topic -> topic + ": has no registered subscriber").collect(Collectors.toList())
      );
    }

    if(serviceAnalyzeRequest.getOptionsState().isTrue(AnalyzeOption.SUBSCRIBED_TOPIC)){
      response.setSubscribedTopicsWarning(
        serviceGroupRepository.getServiceGroupIgnorePublishedTopic(serviceAnalyzeRequest.getServiceName()).getSubscribedTopics()
          .stream().map(topic -> topic + ": has no registered publisher").collect(Collectors.toList())
      );
    }

    return response;
  }


}
