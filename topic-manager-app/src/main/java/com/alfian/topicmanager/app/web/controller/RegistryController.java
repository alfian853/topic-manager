package com.alfian.topicmanager.app.web.controller;

import com.alfian.topicmanager.service.RegistryService;
import com.alfian.topicmanager.service.model.RegistryRequest;
import com.alfian.topicmanager.service.model.RegistryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistryController {

  @Autowired
  private RegistryService registryService;

  @PostMapping("/register")
  public RegistryResponse register(@RequestBody RegistryRequest request){
    return registryService.register(request);
  }

}
