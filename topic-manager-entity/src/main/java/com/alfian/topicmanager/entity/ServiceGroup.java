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
  public static final String PUBLISHER_OF_TOPICS = "publishedTopics";
  public static final String SUBSCRIBER_OF_TOPICS = "subscribedTopics";

  @Id
  private String id;

  private String serviceName;

  private List<String> publishedTopics = new ArrayList<>();

  private List<String> subscribedTopics = new ArrayList<>();

}
