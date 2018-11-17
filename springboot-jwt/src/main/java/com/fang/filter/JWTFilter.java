package com.fang.filter;

import com.fang.domain.JWTToken;
import com.fang.utils.JWTUtil;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author: fwj
 * @Description:
 * @Date: Created in 2018/11/7 14:48
 * @Modified by:
 */
public class JWTFilter extends BasicHttpAuthenticationFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (isLoginAttempt(request, response)) {
            try {
                executeLogin(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;//返回true，通过subject.isAuthenticated判断
    }

    /**
     * 检测header里面是否含有Authorization字段
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String authorization = req.getHeader("Authorization");
        return authorization != null;
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String auth = httpServletRequest.getHeader("Authorization");
        JWTToken token = new JWTToken(auth);
        getSubject(request, response).login(token);//提交给realm处理
        return true;
    }
}
