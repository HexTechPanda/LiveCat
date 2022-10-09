package com.livecat.purchase.service.impl;

import com.livecat.dto.Order;
import com.livecat.dto.Ticket;
import com.livecat.event.service.IEventService;
import com.livecat.feign.IFeignOrderController;
import com.livecat.oauth2.config.AuthUtil;
import com.livecat.purchase.service.IPurchaseService;
import com.livecat.ticket.service.ITicketService;
import com.livecat.util.UUIDUtil;
import com.livecat.util.base.Result;
import com.livecat.util.enums.ResultEnum;
import com.livecat.vo.purchase.PurchaseRequestVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class PurchaseServiceImpl implements IPurchaseService {

    @Autowired
    ITicketService ticketService;

    @Autowired
    IEventService eventService;

    @Autowired
    IFeignOrderController feignOrderController;

    @Transactional
    @Override
    public Result doPurchase(PurchaseRequestVo purchaseRequestVo) {
        String customerId = AuthUtil.getUserInfo().getId();
        if(StringUtils.isEmpty(customerId)){
            return Result.error("Authentication exception");
        }
        if(purchaseRequestVo == null || purchaseRequestVo.getQuantity() == null){
            return Result.error("Bad request.");
        }
        Integer quantity = purchaseRequestVo.getQuantity();
        Ticket targetTicket = ticketService.findTicketById(purchaseRequestVo.getTicketId());
        if(targetTicket == null){
            Result.error("Ticket Object not found.");
        }
        Integer stock = targetTicket.getStockCount();
        if(stock != null && stock - quantity >= 0){
            boolean reduceStatus = ticketService.reduceTicketStockById(targetTicket.getId(), quantity);
            if(!reduceStatus){
                return Result.build(ResultEnum.TICKETS_SOLD_OUT);
            }
            Order order = new Order();
            order.setTicketId(purchaseRequestVo.getTicketId());
            order.setTicketCount(purchaseRequestVo.getQuantity());
            order.setEventId(targetTicket.getEventId());
            order.setCustomerId(customerId);
            order.setTotalPrice(targetTicket.getPrice().multiply(BigDecimal.valueOf(purchaseRequestVo.getQuantity())));
            order.setStatus(1);
            Date createTime = new Date();
            Date payExpireTime = new Date(createTime.getTime() + 1800000);
            order.setCreateTime(createTime);
            // Mock payment
            order.setPaymentId(mockGetPaymentId());
            order.setPaymentLink(mockGetPaymentLink(order.getPaymentId()));

            order.setPayExpireTime(payExpireTime);
            order.setDeliveryEmail(purchaseRequestVo.getDeliveryEmail());
            order.setDeliveryAddress(purchaseRequestVo.getDeliveryAddress());
            order.setPhone(purchaseRequestVo.getPhone());
            return feignOrderController.createOrder(order);
        }else{
            return Result.build(ResultEnum.TICKETS_SOLD_OUT);
        }
    }

    private String mockGetPaymentId(){
        return UUIDUtil.uuid();
    }

    private String mockGetPaymentLink(String paymentId){
        return "/order/mock-payment/" + paymentId;
    }
}
