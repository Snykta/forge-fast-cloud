package com.fast.start.basic.exception;


import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误提示
     */
    private String message;


    public ServiceException() {

    }

    public ServiceException(String message, Throwable e) {
        super(message, e);
        this.message = message;
    }

    public ServiceException(String message)
    {
        super(message);
        this.message = message;
    }

    public ServiceException(String message, Integer code)
    {
        super(message);
        this.message = message;
        this.code = code;
    }
}
