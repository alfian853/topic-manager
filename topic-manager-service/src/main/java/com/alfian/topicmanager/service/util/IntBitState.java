package com.alfian.topicmanager.service.util;

public interface IntBitState {
  int getState();
  default boolean isTrue(int queryState){
    return (getState() & queryState) > 0;
  }
}
