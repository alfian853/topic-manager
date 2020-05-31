package com.alfian.topicmanager.service.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;

import java.util.stream.Stream;

public abstract class StateConverter<T extends IntBitState>
  implements Converter<String, StateHolder<T>>, ApplicationContextAware {

  @Override
  public StateHolder<T> convert(String options) {
    StateHolder<T> stateHolder = new StateHolder<>();
    Stream.of(options.split(","))
      .map(this::getStateEnum)
      .forEach(stateHolder::putState);

    return stateHolder;
  }

  abstract T getStateEnum(String stateStr);

  abstract StateConverter<T> getInstance();

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    applicationContext.getBeansOfType(FormatterRegistry.class);
    applicationContext.getBean(FormatterRegistry.class).addConverter(getInstance());
  }
}
