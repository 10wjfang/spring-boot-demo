package com.fang.config;

import com.fang.domain.UserInfo;
import com.fang.service.CustomUserService;
import com.fang.utils.MD5PasswordEncoder;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @Author: fwj
 * @Description:
 * @Date: Created in 2018/11/16 14:03
 * @Modified by:
 */
public class MyAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    CustomUserService userService;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        UserInfo userInfo = userService.loadUserByUsername(username);
        String credential = password+userInfo.getSalt();
        String encodePassword = new MD5PasswordEncoder("").encode(new MD5PasswordEncoder("").encode(credential));
        if (!userInfo.getPassword().equals(encodePassword)) {
            System.out.println("MyAuthenticationProvider.authenticate 密码不正确: " + encodePassword);
            throw new DisabledException("密码不正确");
        }
        return new UsernamePasswordAuthenticationToken(username, password, null);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
