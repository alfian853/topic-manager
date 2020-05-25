package com.alfian.topicmanager.app.web.controller;

import com.alfian.topicmanager.app.web.util.BitStateOption;
import com.alfian.topicmanager.service.ServiceGroupInfoService;
import com.alfian.topicmanager.service.model.ServiceAnalyzeRequest;
import com.alfian.topicmanager.service.model.ServiceAnalyzeResponse;
import com.alfian.topicmanager.service.model.ServiceInfoResponse;
import com.alfian.topicmanager.service.util.AnalyzeOption;
import com.alfian.topicmanager.service.util.ListBitStateToIntStateConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/service")
public class ServiceGroupController {

  @Autowired
  private ServiceGroupInfoService serviceGroupInfoService;

  @GetMapping("/{serviceName}")
  public ServiceInfoResponse getServiceInfo(@PathVariable("serviceName") String serviceName){
    return serviceGroupInfoService.getServiceTopicInfo(serviceName);
  }

  @GetMapping("/{serviceName}/analyze")
  public ServiceAnalyzeResponse analyzeService(
    @PathVariable("serviceName") String serviceName,
    @RequestParam("options") List<AnalyzeOption> options){

    return serviceGroupInfoService.analyzeService(new ServiceAnalyzeRequest(serviceName,
      ListBitStateToIntStateConverter.staticConvert(options)));
  }

}
