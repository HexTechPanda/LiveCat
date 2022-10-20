package com.livecat.ticket.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.livecat.dto.Ticket;
import org.apache.ibatis.annotations.Param;

public interface TicketMapper extends BaseMapper<Ticket> {
    int reduceStack(@Param("ticketId") String ticketId, @Param("quantity") Integer quantity);
}
