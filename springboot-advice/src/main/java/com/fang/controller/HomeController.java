package com.fang.controller;

import com.fang.domain.result.ExceptionMsg;
import com.fang.domain.result.ResponseBean;
import com.fang.exception.ParamErrorException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: fwj
 * @Description:
 * @Date: Created in 2018/11/8 14:41
 * @Modified by:
 */
@RestController
public class HomeController {
    @RequestMapping("/home")
    public ResponseBean index(String name) throws Exception {
        if (StringUtils.isEmpty(name)) {
            throw new ParamErrorException();
        }
        return new ResponseBean(ExceptionMsg.SUCCESS);
    }
}
