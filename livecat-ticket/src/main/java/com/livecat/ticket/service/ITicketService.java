package com.livecat.ticket.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.livecat.dto.Ticket;

import java.util.List;

public interface ITicketService extends IService<Ticket> {
    List<Ticket> findTicketListByEventId(String eventId);
}
