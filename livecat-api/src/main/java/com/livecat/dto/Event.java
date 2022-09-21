package com.livecat.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@TableName("livecat_event")
@Data
public class Event implements Serializable {
    private static final long serialVersionUID = 7394731425810874311L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;
    private String title;
    private String summary;
    private String imageUrl;
    private String htmlContent;
    private Date eventStartTime;
    private Date eventEndTime;
    private String venue;
    private String nation;
    private String tag;
    private Integer status;
    private Date createTime;
    private String providerId;
}
