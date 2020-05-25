package com.alfian.topicmanager.service.model;

import com.alfian.topicmanager.service.util.ListBitStateToIntStateConverter;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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

  @JsonSerialize(converter = ListBitStateToIntStateConverter.class)
  private Integer optionsState;

}
