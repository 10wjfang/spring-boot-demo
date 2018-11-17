package com.fang.service;

import com.fang.domain.UserInfo;
import com.fang.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @Author: fwj
 * @Description:
 * @Date: Created in 2018/11/16 10:22
 * @Modified by:
 */
@Component
public class CustomUserService {
    @Autowired
    UserInfoRepository repository;

    public UserInfo loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println("s = [" + s + "]");
        UserInfo userInfo = repository.findUserInfoByUsername(s);
        if (userInfo == null) {
            System.out.println("用户名不存在");
            throw new UsernameNotFoundException("用户名不存在");
        }
        return userInfo;
    }
}
