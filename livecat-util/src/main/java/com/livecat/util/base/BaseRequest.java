package com.livecat.util.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Request base class, with paging parameters.
 * @param <T>
 */
@Accessors(chain = true)
@Data
public class BaseRequest<T> implements Serializable {

    @ApiModelProperty(value = "page number", required = true)
    private long current;

    @ApiModelProperty(value = "items size per page", required = true)
    private long size;

    /**
     * Encapsulate the paging object
     * @return
     */
    @ApiModelProperty(hidden = true) // not show in swagger
    public IPage<T> getPage() {
        return new Page<T>().setCurrent(this.current).setSize(this.size);
    }
}
