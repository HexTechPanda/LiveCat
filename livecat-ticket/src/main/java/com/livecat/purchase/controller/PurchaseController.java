package com.livecat.purchase.controller;

import com.livecat.dto.Ticket;
import com.livecat.oauth2.config.AuthUtil;
import com.livecat.purchase.service.IPurchaseService;
import com.livecat.ticket.service.ITicketService;
import com.livecat.util.RedisKeyPrefix;
import com.livecat.util.base.Result;
import com.livecat.util.enums.ResultEnum;
import com.livecat.vo.purchase.PurchaseRequestVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
public class PurchaseController implements InitializingBean {

    @Autowired
    IPurchaseService purchaseService;

    @Autowired
    ITicketService ticketService;

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @PostMapping("/purchase")
    public Result purchaseTickets(@Valid @RequestBody PurchaseRequestVo purchaseRequestVo){
        String customerId = AuthUtil.getUserInfo().getId();
        if(StringUtils.isEmpty(customerId)){
            return Result.error("Authentication exception");
        }
        if(purchaseRequestVo == null || purchaseRequestVo.getQuantity() == null){
            return Result.error("Bad request.");
        }
        Integer quantity = purchaseRequestVo.getQuantity();
        String ticketId = purchaseRequestVo.getTicketId();

        // check ticket stock in redis
        String preStock = stringRedisTemplate.opsForValue().get(RedisKeyPrefix.TICKET_STOCK_ + ticketId);
        if(preStock == null){
            return Result.build(ResultEnum.TICKET_NOT_FOUND);
        }else if(Integer.parseInt(preStock) - quantity < 0){
            return Result.build(ResultEnum.INSUFFICIENT_STOCK);
        }
        // reduce ticket stock in redis
        Long stock = stringRedisTemplate.opsForValue().decrement(RedisKeyPrefix.TICKET_STOCK_ + ticketId, quantity.longValue());
        if(stock == null){
            return Result.build(ResultEnum.TICKET_NOT_FOUND);
        }
        if(stock < 0){
            stringRedisTemplate.opsForValue().increment(RedisKeyPrefix.TICKET_STOCK_ + purchaseRequestVo.getTicketId(), quantity);
            return Result.build(ResultEnum.INSUFFICIENT_STOCK);
        }
        return purchaseService.doPurchase(purchaseRequestVo, customerId);
    }

    @Override
    public void afterPropertiesSet() {
        List<Ticket> ticketList = ticketService.listTicket();
        if(ticketList == null){
            return;
        }
        Set<String> keys = stringRedisTemplate.keys(RedisKeyPrefix.TICKET_STOCK_ + "*");
        if(keys != null && !keys.isEmpty()){
            stringRedisTemplate.delete(keys);
        }
        for (Ticket ticket: ticketList){
            stringRedisTemplate.opsForValue().set(RedisKeyPrefix.TICKET_STOCK_ + ticket.getId(), String.valueOf(ticket.getStockCount()), 0);
        }
    }
}
