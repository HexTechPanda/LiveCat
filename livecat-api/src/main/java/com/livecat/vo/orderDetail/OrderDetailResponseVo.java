package com.livecat.vo.orderDetail;

import com.livecat.dto.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailResponseVo {
    private String id;
    private String ticketId;
    private String ticketType;
    private Integer ticketCount;
    private String eventId;
    private String eventTitle;
    private String customerId;
    private BigDecimal totalPrice;
    // 0:canceled, 1:not paid, 2:have paid, 3:delivered, 4:expired, 5:refunded
    private Integer status;
    private Date createTime;
    private Date payExpireTime;
    private Date payTime;
    private String paymentLink;
    private String paymentId;
    private String deliveryEmail;
    private String deliveryAddress;
    private String phone;
}
