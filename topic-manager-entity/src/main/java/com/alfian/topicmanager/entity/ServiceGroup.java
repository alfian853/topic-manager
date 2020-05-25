package com.alfian.topicmanager.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document("service_group")
public class ServiceGroup {

  public static final String SERVICE_NAME = "serviceName";
  public static final String PUBLISHER_OF_TOPICS = "publisherOfTopics";
  public static final String SUBSCRIBER_OF_TOPICS = "subscriberOfTopics";

  @Id
  private String id;

  private String serviceName;

  private List<String> publisherOfTopics = new ArrayList<>();

  private List<String> subscriberOfTopics = new ArrayList<>();

}
