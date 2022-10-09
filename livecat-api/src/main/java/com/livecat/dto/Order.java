package com.livecat.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@TableName("livecat_order")
@Data
public class Order implements Serializable {
    private static final long serialVersionUID = -4212704918615369922L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;
    private String ticketId;
    private Integer ticketCount;
    private String eventId;
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
