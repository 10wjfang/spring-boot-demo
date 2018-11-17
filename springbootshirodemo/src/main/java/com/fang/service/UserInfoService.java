package com.fang.service;

import com.fang.entity.UserInfo;

/**
 * @Author: fwj
 * @Description:
 * @Date: Created in 2018/4/26 11:41
 * @Modified by:
 */
public interface UserInfoService {
    public UserInfo findByUsername(String username);
    public boolean save(UserInfo userInfo);
}
