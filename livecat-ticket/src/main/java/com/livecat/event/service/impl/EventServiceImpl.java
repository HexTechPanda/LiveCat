package com.livecat.event.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.livecat.dto.Event;
import com.livecat.event.mapper.EventMapper;
import com.livecat.event.req.EventReq;
import com.livecat.event.service.IEventService;
import com.livecat.util.base.Result;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl extends ServiceImpl<EventMapper, Event> implements IEventService {
    private static final Logger logger = LoggerFactory.getLogger(Result.class);

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    public Result findEventSummaryVoPage(EventReq req){
        try{
            // get from cache first
            String eventListJson = stringRedisTemplate.opsForValue().get("all-event-list");
            if(StringUtils.isNotEmpty(eventListJson)) {
                List<Event> redisEventList = JSONObject.parseArray(eventListJson.trim(), Event.class);
                if(StringUtils.isNotEmpty(req.getTitle())){
                    return Result.ok(redisEventList
                            .stream()
                            .filter(event -> req.getTitle().equals(event.getTitle()))
                            .collect(Collectors.toList()));
                }
                return Result.ok(redisEventList);
            }
            // if cache not target, then get from database
            QueryWrapper<Event> wrapper = new QueryWrapper();
            if(StringUtils.isNotEmpty(req.getTitle())){
                wrapper.like("title", req.getTitle());
            }
            List<Event> eventList = baseMapper.selectList(wrapper);
            stringRedisTemplate.opsForValue().set("all-event-list", JSON.toJSONString(eventList), 30);
            return Result.ok(eventList);
        }catch (Exception e){
            logger.error("Error in findEventSummaryVoPage. ", e);
            return Result.error("Error when find event summary!");
        }
    }

    @Override
    public Result findEventById(String eventId){
        return Result.ok(baseMapper.selectById(eventId));
    }

    @Override
    public String findEventTitleById(String eventId){
        if(StringUtils.isEmpty(eventId)){
            return "";
        }
        QueryWrapper<Event> wrapper = new QueryWrapper();
        wrapper.select("title").eq("id", eventId);
        Event event = baseMapper.selectOne(wrapper);
        if(event == null){
            return "";
        }
        return event.getTitle();
    }

}
