package com.livecat.vo.orderStatus;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class UpdateOrderStatusRequest implements Serializable {
    private static final long serialVersionUID = 8388911872642087249L;
    @NotNull
    private String orderId;
    @NotNull
    private Integer status;
}
