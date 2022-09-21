package com.livecat.vo;

import com.livecat.dto.Event;
import lombok.Data;

import java.util.Date;

@Data
public class EventSummaryVO {
    private String id;
    private String title;
    private String summary;
    private String imageUrl;
//    private String htmlContent;
    private Date eventStartTime;
    private Date eventEndTime;
    private String venue;
    private String nation;
    private String tag;
    private Integer status;
    private Date createTime;
    private String providerId;

    public EventSummaryVO(Event event) {
        this.id = event.getId();
        this.title = event.getTitle();
        this.summary = event.getSummary();
        this.imageUrl = event.getImageUrl();
//        this.htmlContent = event.getHtmlContent();
        this.eventStartTime = event.getEventStartTime();
        this.eventEndTime = event.getEventEndTime();
        this.venue = event.getVenue();
        this.nation = event.getNation();
        this.tag = event.getTag();
        this.status = event.getStatus();
        this.createTime = event.getCreateTime();
        this.providerId = event.getProviderId();
    }
}
