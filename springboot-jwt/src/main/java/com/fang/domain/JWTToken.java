package com.fang.domain;

import com.fang.service.UserInfoService;
import com.fang.utils.JWTUtil;
import org.apache.shiro.authc.AuthenticationToken;

import javax.annotation.Resource;

/**
 * @Author: fwj
 * @Description:
 * @Date: Created in 2018/11/7 15:08
 * @Modified by:
 */
public class JWTToken implements AuthenticationToken {
    private String token;

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
