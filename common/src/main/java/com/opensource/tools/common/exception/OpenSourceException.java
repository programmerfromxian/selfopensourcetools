package com.opensource.tools.common.exception;

/**
 * 自定义异常类
 *
 * @author yangzai
 * @date 2022-02-18
 */
public class OpenSourceException extends RuntimeException {

    public OpenSourceException() {
        super();
    }

    public OpenSourceException(String errMsg) {
        super(errMsg);
    }

    public OpenSourceException(String errMsg, Throwable cause) {
        super(errMsg, cause);
    }

    public OpenSourceException(Throwable cause) {
        super(cause);
    }

    public String getErrMsg() {
        return this.getMessage();
    }

}
