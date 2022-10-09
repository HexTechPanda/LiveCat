package com.livecat.vo.eventdetail;

import com.livecat.dto.Event;
import com.livecat.dto.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
@AllArgsConstructor
public class EventDetailVo implements Serializable {
    private static final long serialVersionUID = 8936280590797037466L;
    private Event event;
    private List<Ticket> ticketList;
}
