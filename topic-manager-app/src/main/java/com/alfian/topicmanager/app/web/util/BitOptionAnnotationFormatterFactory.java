package com.alfian.topicmanager.app.web.util;

import com.alfian.topicmanager.service.util.IntBitState;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.EmbeddedValueResolutionSupport;
import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Formatter;
import org.springframework.format.Parser;
import org.springframework.format.Printer;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.*;

@Component
public class BitOptionAnnotationFormatterFactory extends EmbeddedValueResolutionSupport
  implements AnnotationFormatterFactory<BitStateOption> {

  @Autowired
  private ObjectMapper objectMapper;

  private TypeReference<List<IntBitState>> type = new TypeReference<List<IntBitState>>() {};

  @Override
  public Set<Class<?>> getFieldTypes() {
    Set<Class<?>> fieldTypes = new HashSet<>();
    fieldTypes.add(type.getClass());
    return Collections.unmodifiableSet(fieldTypes);
  }

  @Override
  public Printer<?> getPrinter(BitStateOption bitStateOption, Class<?> aClass) {
    return configureFormatterFrom(bitStateOption);
  }

  @Override
  public Parser<?> getParser(BitStateOption bitStateOption, Class<?> aClass) {
    return configureFormatterFrom(bitStateOption);
  }

  private Formatter<Integer> configureFormatterFrom(BitStateOption annotation) {

    return new Formatter<Integer>() {

      @Override
      public Integer parse(String s, Locale locale) throws ParseException {
        System.out.println("parse log this: " + s);
        try {
          return objectMapper.readValue(s, type).stream()
            .map(IntBitState::getState)
            .reduce(0, (cur, bit) -> cur | bit);
        } catch (JsonProcessingException e) {
          e.printStackTrace();
          throw new ParseException(e.getMessage(), 57);
        }
      }

      @Override
      public String print(Integer integer, Locale locale) {
        return Integer.toBinaryString(integer); }
    };

  }

}