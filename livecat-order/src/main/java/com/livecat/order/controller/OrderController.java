package com.livecat.order.controller;

import com.livecat.order.service.IOrderService;
import com.livecat.util.base.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Order API")
@RestController
public class OrderController {

    @Autowired
    IOrderService orderService;

    @ApiOperation("Customer get history order list")
    @GetMapping("/orders")
    public Result findOrderList(){
        return orderService.findOrderList();
    }

    @GetMapping("/orders/{order-id}")
    public Result findById(@PathVariable("order-id") String orderId){
        return orderService.findByOrderId(orderId);
    }

    @PostMapping("/mock-payment/{payment-id}")
    public Result mockPayment(@PathVariable("payment-id") String paymentId){
        return orderService.mockPaid(paymentId);
    }
}
