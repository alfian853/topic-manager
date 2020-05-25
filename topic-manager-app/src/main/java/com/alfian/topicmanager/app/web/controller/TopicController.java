package com.alfian.topicmanager.app.web.controller;

import com.alfian.topicmanager.service.TopicService;
import com.alfian.topicmanager.service.model.TopicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topic")
public class TopicController {

  @Autowired
  private TopicService topicService;

  @GetMapping("/{topicName}")
  public TopicResponse getTopicInfo(@PathVariable("topicName") String topicName){
    return topicService.getTopicData(topicName);
  }

}
