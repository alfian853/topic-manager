package com.alfian.topicmanager.service.util;

import org.springframework.stereotype.Component;

@Component
public class AnalyzeOptionStateConverter extends StateConverter<AnalyzeOption> {

  @Override
  AnalyzeOption getStateEnum(String stateStr) {
    return AnalyzeOption.valueOf(stateStr);
  }

  @Override
  StateConverter<AnalyzeOption> getInstance() {
    return this;
  }
}
