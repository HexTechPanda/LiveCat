package com.livecat.order.feign;

import com.livecat.dto.Order;
import com.livecat.feign.IFeignOrderController;
import com.livecat.order.service.IOrderService;
import com.livecat.util.base.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Feign Order Controller")
@RestController
public class FeignOrderController implements IFeignOrderController {

    @Autowired
    IOrderService orderService;

    @Override
    public Result createOrder(Order order) {
        return orderService.createOrder(order);
    }

    @Override
    public Result findOrderById(String orderId) {
        return Result.ok(orderService.getById(orderId));
    }
}
