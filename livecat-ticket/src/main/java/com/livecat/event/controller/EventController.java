package com.livecat.event.controller;

import com.livecat.dto.Event;
import com.livecat.dto.Ticket;
import com.livecat.event.req.EventReq;
import com.livecat.event.service.IEventService;
import com.livecat.ticket.service.ITicketService;
import com.livecat.util.base.Result;
import com.livecat.vo.EventDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // All method will return json response
public class EventController {

    @Autowired
    private IEventService eventService;

    @Autowired
    private ITicketService ticketService;

    @GetMapping("/events")
    public Result getEventSummaryList(EventReq req){
        if(req.getSize() == 0){
            req.setSize(5);
        }
        return eventService.findEventSummaryVoPage(req);
    }

    @GetMapping("/events/{event_id}")
    public Result getEventDetail(@PathVariable("event_id") String eventId){
        Event event = (Event) eventService.findEventById(eventId).getData();
        List<Ticket> ticketList = ticketService.findTicketListByEventId(eventId);
        if(event == null){
            return Result.error("No event found.");
        }
        EventDetailVO eventDetail = new EventDetailVO(event, ticketList);
        return Result.ok(eventDetail);
    }
}
