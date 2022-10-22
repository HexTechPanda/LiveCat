package com.livecat.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@TableName("livecat_ticket")
@Data
public class Ticket implements Serializable {
    private static final long serialVersionUID = 5889078986762681935L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;
    private String title;
    private String type;
    private BigDecimal price;
    private Integer stockCount;
    private String detail;
    private Date startSellingDate;
    private Date endSellingDate;

    private String eventId;
    private String providerId;
}
