package com.alfian.topicmanager.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.LinkedList;
import java.util.List;

@Data
@Document("topic")
public class Topic {

  public static final String TOPIC_NAME = "topicName";
  public static final String PUBLISHER_SERVICES = "publisherServices";
  public static final String SUBSCRIBER_SERVICES = "subscriberServices";

  @Id
  private String id;

  private String topicName;

  private List<String> publisherServices = new LinkedList<>();

  private List<String> subscriberServices = new LinkedList<>();

}
