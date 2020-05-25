package com.alfian.topicmanager.service.util;

import com.fasterxml.jackson.databind.util.StdConverter;

import java.util.List;

public class ListBitStateToIntStateConverter extends StdConverter<List<IntBitState>, Integer> {

  @Override
  public Integer convert(List<IntBitState> analyzeOptions) {
    return staticConvert(analyzeOptions);
  }

  public static Integer staticConvert(List<? extends IntBitState> analyzeOptions){
    return analyzeOptions.stream().map(IntBitState::getState).reduce(0, (ac, state)-> ac|state);
  }
}
