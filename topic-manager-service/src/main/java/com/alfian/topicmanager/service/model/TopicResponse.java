package com.alfian.topicmanager.service.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TopicResponse {

  private String topicName;
  private List<String> publisherServices = new LinkedList<>();
  private List<String> subscriberServices = new LinkedList<>();

}
