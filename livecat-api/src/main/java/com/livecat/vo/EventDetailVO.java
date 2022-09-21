package com.livecat.vo;

import com.livecat.dto.Event;
import com.livecat.dto.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class EventDetailVO {
    private static final long serialVersionUID = 8936280590797037466L;
    private Event event;
    private List<Ticket> ticketList;
}
