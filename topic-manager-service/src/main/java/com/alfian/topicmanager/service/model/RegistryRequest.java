package com.alfian.topicmanager.service.model;

import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
public class RegistryRequest {

  private String serviceName;

  private List<String> publishedTopics = Collections.emptyList();

  private List<String> subscribedTopics = Collections.emptyList();

}
