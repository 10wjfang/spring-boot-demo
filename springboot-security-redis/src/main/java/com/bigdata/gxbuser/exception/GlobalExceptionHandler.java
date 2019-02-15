package com.bigdata.gxbuser.exception;

import com.bigdata.dto.ResponseDTO;
import com.bigdata.enums.ErrorCodeEnum;
import com.bigdata.manger.ResponseGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理
 *
 * @author fwj
 * @date 2019-02-15 15:34
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(InvalidTokenException.class)
    public ResponseDTO invalidTokenException(InvalidTokenException e) {
        return ResponseGenerator.error(ErrorCodeEnum.UNAUTHORIZED);
    }
}
