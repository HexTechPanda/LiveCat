package com.livecat.order.controller;

import com.livecat.order.service.IOrderService;
import com.livecat.util.base.Result;
import com.livecat.vo.orderStatus.UpdateOrderStatusRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = "Order API")
@RestController
public class OrderController {

    @Autowired
    IOrderService orderService;

    @GetMapping("/api")
    public String healthCheck(){
        return "Health check OK!";
    }

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

    // the following are provider APIs
//    @PreAuthorize("hasAuthority('provider')")
    @GetMapping("/provider/orders")
    public Result providerFindOrderList(){
        return orderService.providerFindOrderList();
    }

//    @PreAuthorize("hasAuthority('provider')")
    @PutMapping("/provider/order-status")
    public Result providerUpdateOrderStatus(@Valid @RequestBody UpdateOrderStatusRequest request){
        return orderService.providerUpdateOrderStatus(request.getOrderId(), request.getStatus());
    }
}
