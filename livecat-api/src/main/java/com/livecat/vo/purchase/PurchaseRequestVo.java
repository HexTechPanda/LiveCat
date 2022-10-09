package com.livecat.vo.purchase;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Accessors(chain = true)
public class PurchaseRequestVo implements Serializable {
    private static final long serialVersionUID = 50420473562487393L;

    @NotNull
    private String ticketId;

    @NotNull
    @Digits(integer = 2, fraction = 0)
    private Integer quantity;

    @Email
    @NotNull
    private String deliveryEmail;

    @NotNull
    private String deliveryAddress;

    @NotNull
    private String phone;
}
