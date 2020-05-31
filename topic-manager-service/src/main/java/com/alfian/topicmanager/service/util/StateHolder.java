package com.alfian.topicmanager.service.util;

public class StateHolder<T extends IntBitState> {
  int state;

  public void reset(){state = 0;}

  public void putState(T bitState){state = state | bitState.getState();}

  public boolean isTrue(T bitState){return (state | bitState.getState()) > 0;}
}
