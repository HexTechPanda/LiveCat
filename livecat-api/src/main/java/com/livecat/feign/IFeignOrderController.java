package com.livecat.feign;

import com.livecat.dto.Order;
import com.livecat.util.base.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "order-server", path = "/order")
public interface IFeignOrderController {
    @ApiOperation("Feign Interface - create order.")
    @PostMapping("/feign/orders")
    Result createOrder(@RequestBody Order order);

    @ApiImplicitParam(name = "order-id", value = "order-id", required = true)
    @ApiOperation("Feign Interface - query order by orderId.")
    @GetMapping("/feign/orders/{order-id}")
    Result findOrderById(@PathVariable("order-id") String orderId);
}

