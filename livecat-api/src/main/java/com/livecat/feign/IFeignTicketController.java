package com.livecat.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "ticket-server", path = "/ticket")
public interface IFeignTicketController {
}
