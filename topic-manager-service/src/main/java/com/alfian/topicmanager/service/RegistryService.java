package com.alfian.topicmanager.service;

import com.alfian.topicmanager.service.model.RegistryRequest;
import com.alfian.topicmanager.service.model.RegistryResponse;

public interface RegistryService {

  RegistryResponse register(RegistryRequest registryRequest);

}
