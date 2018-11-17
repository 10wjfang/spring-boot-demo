package com.fang.service;

import com.fang.domain.UserInfo;

/**
 * @Author: fwj
 * @Description:
 * @Date: Created in 2018/11/6 14:06
 * @Modified by:
 */
public interface IUserService {
    UserInfo findByUsername(String username);
    boolean save(UserInfo userInfo);
}
