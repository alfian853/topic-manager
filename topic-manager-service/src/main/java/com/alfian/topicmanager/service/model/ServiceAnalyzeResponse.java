package com.alfian.topicmanager.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceAnalyzeResponse {

  private List<String> publishedTopicsWarning;
  private List<String> subscribedTopicsWarning;

}
