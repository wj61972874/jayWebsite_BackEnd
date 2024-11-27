package com.jayblog.weisite.service.impl;

import com.jayblog.weisite.domain.EventLog;
import com.jayblog.weisite.repository.EventLogRepository;
import com.jayblog.weisite.service.EventLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventLogServiceImpl implements EventLogService {

    @Autowired
    private EventLogRepository eventLogRepository;

    @Override
    public void saveEventLog(EventLog eventLog) {
        eventLogRepository.save(eventLog);
    }
}