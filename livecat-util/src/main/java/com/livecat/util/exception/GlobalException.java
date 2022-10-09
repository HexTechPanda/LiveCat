package com.livecat.util.exception;

import com.livecat.util.enums.ResultEnum;

public class GlobalException extends RuntimeException{

    private static final long serialVersionUID = 1277184993503077998L;

    private final ResultEnum resultEnum;

    public GlobalException(ResultEnum resultEnum){
        super(resultEnum.toString());
        this.resultEnum = resultEnum;
    }

    public ResultEnum getResultEnum() {
        return resultEnum;
    }
}
