package com.livecat.ticket.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.livecat.dto.Ticket;
import com.livecat.ticket.mapper.TicketMapper;
import com.livecat.ticket.service.ITicketService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl extends ServiceImpl<TicketMapper, Ticket> implements ITicketService {

    @Override
    public List<Ticket> findTicketListByEventId(String eventId) {
        QueryWrapper<Ticket> wrapper = new QueryWrapper();
        if(StringUtils.isNotEmpty(eventId)){
            wrapper.eq("event_id", eventId);
        }
        return baseMapper.selectList(wrapper);
    }
}
