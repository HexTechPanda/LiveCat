package com.livecat.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.livecat.dto.Order;
import com.livecat.util.base.Result;

public interface IOrderService extends IService<Order> {
    Result createOrder(Order order);
    Result findByOrderId(String orderId);
    Result findOrderList();
    Result mockPaid(String paymentId);

    // provider api
    Result providerFindOrderList();
    Result providerUpdateOrderStatus(String orderId, Integer status);
}
