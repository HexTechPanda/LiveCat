package com.livecat.purchase.service.impl;

import com.livecat.dto.Order;
import com.livecat.dto.Ticket;
import com.livecat.event.service.IEventService;
import com.livecat.feign.IFeignOrderController;
import com.livecat.purchase.service.IPurchaseService;
import com.livecat.ticket.service.ITicketService;
import com.livecat.util.UUIDUtil;
import com.livecat.util.base.Result;
import com.livecat.util.enums.ResultEnum;
import com.livecat.vo.purchase.PurchaseRequestVo;
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
    public Result doPurchase(PurchaseRequestVo purchaseRequestVo, String customerId) {
        Integer quantity = purchaseRequestVo.getQuantity();
        // get target ticket
        Ticket targetTicket = ticketService.findTicketById(purchaseRequestVo.getTicketId());
        if(targetTicket == null){
            return Result.error("Ticket Object not found.");
        }
        // reduce ticket stock count
        Integer stock = targetTicket.getStockCount();
        if(stock != null && stock - quantity >= 0){
            boolean reduceStatus = ticketService.reduceTicketStockById(targetTicket.getId(), quantity);
            if(!reduceStatus){
                return Result.build(ResultEnum.TICKETS_SOLD_OUT);
            }
            Order order = new Order();
            order.setTicketId(purchaseRequestVo.getTicketId());
            order.setTicketType(targetTicket.getType());
            order.setTicketCount(purchaseRequestVo.getQuantity());
            order.setEventId(targetTicket.getEventId());
            order.setEventTitle(eventService.findEventTitleById(targetTicket.getEventId()));
            order.setCustomerId(customerId);
            order.setTotalPrice(targetTicket.getPrice().multiply(BigDecimal.valueOf(purchaseRequestVo.getQuantity())));
            order.setStatus(1);
            Date createTime = new Date();
            Date payExpireTime = new Date(createTime.getTime() + 1800000);
            order.setCreateTime(createTime);
            // generate mock payment id and link.
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
