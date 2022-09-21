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
    private String ticketCount;
    private String eventId;
    private BigDecimal totalPrice;
    private Integer status;
    private Date createTime;
    private Date payExpireTime;
    private Date payTime;
    private String deliveryEmail;
    private String deliveryAddress;
    private String phone;
}
