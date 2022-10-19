package com.livecat.event.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.livecat.dto.Event;
import com.livecat.event.req.EventReq;
import com.livecat.util.base.Result;

public interface IEventService extends IService<Event> {
    Result findEventSummaryVoPage(EventReq req);
    Result findEventById(String eventId);
    String findEventTitleById(String eventId);
}
