package com.livecat.purchase.service;

import com.livecat.util.base.Result;
import com.livecat.vo.purchase.PurchaseRequestVo;

public interface IPurchaseService{
    Result doPurchase(PurchaseRequestVo purchaseRequestVo, String customerId);
}
