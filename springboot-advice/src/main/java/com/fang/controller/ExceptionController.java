package com.fang.controller;

import com.fang.domain.result.ExceptionMsg;
import com.fang.domain.result.ResponseBean;
import com.fang.exception.ParamErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: fwj
 * @Description:
 * @Date: Created in 2018/11/8 14:32
 * @Modified by:
 */
@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseBean globalException(HttpServletRequest request, Throwable ex) {
        return new ResponseBean("999999",ex.getMessage());
    }

    @ExceptionHandler(ParamErrorException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseBean paramErrorException(HttpServletRequest request, Throwable ex) {
        return new ResponseBean(ExceptionMsg.ParamError);
    }
}
