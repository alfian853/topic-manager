package com.alfian.topicmanager.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan({"com.alfian.topicmanager"})
@EntityScan({"com.alfian.topicmanager.entity"})
@EnableMongoRepositories({"com.alfian.topicmanager.repository"})
@EnableDiscoveryClient
public class TopicManagerApplication {

  public static void main(String[] args) {
    SpringApplication.run(TopicManagerApplication.class, args);
  }

}
