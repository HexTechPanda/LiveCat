package com.livecat.event.req;

import com.livecat.dto.Event;
import com.livecat.util.base.BaseRequest;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class EventReq extends BaseRequest<Event> {
    private String title;
}
