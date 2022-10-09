package com.livecat.purchase.controller;

import com.livecat.purchase.service.IPurchaseService;
import com.livecat.util.base.Result;
import com.livecat.vo.purchase.PurchaseRequestVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class PurchaseController {

    @Autowired
    IPurchaseService purchaseService;

    @PostMapping("/purchase")
    public Result purchaseTickets(@Valid @RequestBody PurchaseRequestVo purchaseRequestVo){
        return purchaseService.doPurchase(purchaseRequestVo);
    }
}
