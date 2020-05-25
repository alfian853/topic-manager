package com.alfian.topicmanager.service;

import com.alfian.topicmanager.service.model.ServiceAnalyzeRequest;
import com.alfian.topicmanager.service.model.ServiceAnalyzeResponse;
import com.alfian.topicmanager.service.model.ServiceInfoResponse;

public interface ServiceGroupInfoService {

  ServiceInfoResponse getServiceTopicInfo(String serviceName);

  ServiceAnalyzeResponse analyzeService(ServiceAnalyzeRequest serviceAnalyzeRequest);

}
