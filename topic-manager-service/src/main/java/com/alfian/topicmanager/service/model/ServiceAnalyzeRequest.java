package com.alfian.topicmanager.service.model;

import com.alfian.topicmanager.service.util.AnalyzeOption;
import com.alfian.topicmanager.service.util.StateHolder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceAnalyzeRequest {

  private String serviceName;

  private StateHolder<AnalyzeOption> optionsState;

}
