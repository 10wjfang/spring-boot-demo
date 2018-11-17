package com.fang.exception;

/**
 * @Author: fwj
 * @Description:
 * @Date: Created in 2018/11/8 14:50
 * @Modified by:
 */
public class ParamErrorException extends RuntimeException {
    public ParamErrorException() {
    }

    public ParamErrorException(String message) {
        super(message);
    }
}
