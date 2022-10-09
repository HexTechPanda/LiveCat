package com.livecat.event.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.livecat.dto.Event;
import com.livecat.event.mapper.EventMapper;
import com.livecat.event.req.EventReq;
import com.livecat.event.service.IEventService;
import com.livecat.util.base.Result;
import com.livecat.vo.eventsummary.EventSummaryVo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl extends ServiceImpl<EventMapper, Event> implements IEventService {
    private static final Logger logger = LoggerFactory.getLogger(Result.class);

    @Override
    public Result findEventSummaryVoPage(EventReq req){
        try{
            QueryWrapper<Event> wrapper = new QueryWrapper();
            if(StringUtils.isNotEmpty(req.getTitle())){
                wrapper.like("title", req.getTitle());
            }
            List<Event> eventList = baseMapper.selectList(wrapper);
            return Result.ok(eventList);
//            IPage<Event> eventPage = baseMapper.selectPage(req.getPage(), wrapper);
//            return Result.ok(eventPage.convert(EventSummaryVo::new));
        }catch (Exception e){
            logger.error("Error in findEventSummaryVoPage. ", e);
            return Result.error("Error when find event summary!");
        }
    }

    @Override
    public Result findEventById(String eventId){
        return Result.ok(baseMapper.selectById(eventId));
    }



}
