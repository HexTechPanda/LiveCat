package com.livecat.ticket.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.livecat.dto.Ticket;
import com.livecat.util.base.Result;

import java.util.List;

public interface ITicketService extends IService<Ticket> {
    List<Ticket> findTicketListByEventId(String eventId);
    Ticket findTicketById(String ticketId);
    boolean reduceTicketStockById(String ticketId, Integer quantity);
    List<Ticket> listTicket();
}
