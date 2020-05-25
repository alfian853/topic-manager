package com.alfian.topicmanager.service.util;

public enum  AnalyzeOption implements IntBitState {
  SUBSCRIBED_TOPIC(1), PUBLISHED_TOPIC(1<<1);

  private int state;

  AnalyzeOption(int state) {
    this.state = state;
  }

  @Override
  public int getState() {
    return state;
  }

}
