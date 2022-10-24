package com.livecat.event.controller;

import com.livecat.dto.Event;
import com.livecat.dto.Ticket;
import com.livecat.event.req.EventReq;
import com.livecat.event.service.IEventService;
import com.livecat.ticket.service.ITicketService;
import com.livecat.util.base.Result;
import com.livecat.vo.eventdetail.EventDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // All method will return json response
@RequestMapping("/api") // open api
public class ApiEventController {

    @Autowired
    private IEventService eventService;

    @Autowired
    private ITicketService ticketService;

    @GetMapping("/events")
    public Result getEventSummaryList(@RequestParam(required = false) String title){
        EventReq req = new EventReq();
        req.setTitle(title);
        return eventService.findEventSummaryVoPage(req);
    }

    @GetMapping("/events/{event_id}")
    public Result getEventDetail(@PathVariable("event_id") String eventId){
        Event event = (Event) eventService.findEventById(eventId).getData();
        List<Ticket> ticketList = ticketService.findTicketListByEventId(eventId);
        if(event == null){
            return Result.error("No event found.");
        }
        EventDetailVo eventDetail = new EventDetailVo(event, ticketList);
        return Result.ok(eventDetail);
    }
}
