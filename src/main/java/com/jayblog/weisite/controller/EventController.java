package com.jayblog.weisite.controller;

import com.jayblog.weisite.common.ResponseBean;
import com.jayblog.weisite.domain.EventLog;
import com.jayblog.weisite.service.EventLogService;
import com.jayblog.weisite.service.impl.ArticleServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/analytics")
public class EventController {
    private static final Logger logger = LoggerFactory.getLogger(EventController.class);

    @Autowired
    private EventLogService eventLogService;

    @PostMapping("/event-log")
    public ResponseBean<String> trackEvent(@RequestBody EventLog eventLog) {
        System.out.println("eventLog=======: " + eventLog);

        eventLogService.saveEventLog(eventLog);
        return ResponseBean.success(null);
    }
}
