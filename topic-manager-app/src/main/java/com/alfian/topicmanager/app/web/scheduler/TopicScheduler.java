package com.alfian.topicmanager.app.web.scheduler;

import com.alfian.topicmanager.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TopicScheduler {

  public static final long MINUTES_10_IN_MS = 1000  * 60 * 10;

  @Autowired
  private TopicRepository topicRepository;

  @Scheduled(fixedRate = TopicScheduler.MINUTES_10_IN_MS)
  public void removeJunkTopic(){
    topicRepository.deleteJunkTopic();
  }

}
