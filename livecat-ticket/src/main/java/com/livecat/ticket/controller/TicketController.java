package com.livecat.ticket.controller;

import com.livecat.dto.Ticket;
import com.livecat.ticket.service.ITicketService;
import com.livecat.util.base.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TicketController {

    @Autowired
    ITicketService ticketService;

    @GetMapping("/tickets")
    public Result findTicketListByEventId(@RequestParam String eventId){
        if(StringUtils.isEmpty(eventId)){
            return Result.error("No result.");
        }
        List<Ticket> ticketList = ticketService.findTicketListByEventId(eventId);
        return Result.ok(ticketList);
    }
}
