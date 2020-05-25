package com.alfian.topicmanager.app.web.configuration;

import com.alfian.topicmanager.app.web.util.BitOptionAnnotationFormatterFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigurer implements WebMvcConfigurer {

  @Autowired
  private BitOptionAnnotationFormatterFactory bitOptionAnnotationFormatterFactory;

  @Override
  public void addFormatters(FormatterRegistry registry) {
    registry.addFormatterForFieldAnnotation(bitOptionAnnotationFormatterFactory);
  }
}
