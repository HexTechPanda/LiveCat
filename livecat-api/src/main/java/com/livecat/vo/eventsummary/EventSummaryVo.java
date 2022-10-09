package com.livecat.vo.eventsummary;

import com.livecat.dto.Event;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
public class EventSummaryVo implements Serializable {
    private static final long serialVersionUID = 722705595458815680L;
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

    public EventSummaryVo(Event event) {
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
