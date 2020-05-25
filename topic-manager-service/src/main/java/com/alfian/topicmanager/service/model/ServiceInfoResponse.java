package com.alfian.topicmanager.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceInfoResponse {

  private String serviceName;

  private List<String> publisherOfTopics = new ArrayList<>();

  private List<String> subscriberOfTopics = new ArrayList<>();

}
