package com.fang.service;

import com.fang.domain.UserInfo;

/**
 * @Author: fwj
 * @Description:
 * @Date: Created in 2018/11/7 13:58
 * @Modified by:
 */
public interface UserInfoService {
    UserInfo findByUsername(String username);
}
