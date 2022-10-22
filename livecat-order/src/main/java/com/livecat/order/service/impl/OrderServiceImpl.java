package com.livecat.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.livecat.dto.Order;
import com.livecat.oauth2.config.AuthUtil;
import com.livecat.order.mapper.OrderMapper;
import com.livecat.order.service.IOrderService;
import com.livecat.util.base.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Override
    @Transactional
    public Result createOrder(Order order) {
        boolean status = super.save(order);
        if(!status){
            return Result.error("Create order fail.");
        }
        return Result.ok(order);
    }

    @Override
    public Result findByOrderId(String orderId){
        if(StringUtils.isBlank(orderId)){
            return Result.error("Order ID can not be blank");
        }
        String customerId = AuthUtil.getUserInfo().getId();
        if(StringUtils.isEmpty(customerId)){
            return Result.error("Authentication exception");
        }
        Order order = baseMapper.selectById(orderId);
        if(order == null || !order.getCustomerId().equals(customerId)){
            return Result.error("order not found");
        }
        return Result.ok(order);
    }

    @Override
    public Result findOrderList(){
        String customerId = AuthUtil.getUserInfo().getId();
        if(StringUtils.isEmpty(customerId)){
            return Result.error("Authentication exception");
        }
        QueryWrapper<Order> wrapper = new QueryWrapper();
        wrapper.eq("customer_id", customerId);
        wrapper.orderByDesc("create_time");
        List<Order> orderList = baseMapper.selectList(wrapper);
        return Result.ok(orderList);
    }

    @Override
    public Result mockPaid(String paymentId){
        String customerId = AuthUtil.getUserInfo().getId();
        if(StringUtils.isEmpty(customerId)){
            return Result.error("Authentication exception");
        }
        if(StringUtils.isBlank(paymentId)){
            return Result.error("paymentId can not be blank");
        }
        QueryWrapper<Order> wrapper = new QueryWrapper();
        wrapper.eq("customer_id", customerId);
        wrapper.eq("payment_id", paymentId);
        Order order = baseMapper.selectOne(wrapper);
        if(order == null){
            return Result.error("order not found");
        }
        order.setStatus(2);
        order.setPayTime(new Date());
        baseMapper.updateById(order);
        return Result.ok(order);
    }

    @Override
    public Result providerFindOrderList(){
        String providerId = "pid0001";

        if(StringUtils.isEmpty(providerId)){
            return Result.error("Authentication exception");
        }
        QueryWrapper<Order> wrapper = new QueryWrapper();
        wrapper.eq("provider_id", providerId);
        wrapper.orderByDesc("create_time");
        List<Order> orderList = baseMapper.selectList(wrapper);
        return Result.ok(orderList);
    }

    @Override
    public Result providerUpdateOrderStatus(String orderId, Integer status){
        String providerId = "pid0001";

        if(StringUtils.isEmpty(providerId)){
            return Result.error("Authentication exception");
        }
        // find order
        Order order = baseMapper.selectById(orderId);
        if(order == null || !order.getProviderId().equals(providerId)){
            return Result.error("order not found");
        }
        // validate status

        // update status
        order.setStatus(status);
        baseMapper.updateById(order);
        return Result.ok(order);
    }
}
