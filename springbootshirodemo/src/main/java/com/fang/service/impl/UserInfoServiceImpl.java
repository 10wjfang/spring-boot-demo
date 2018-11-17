package com.fang.service.impl;

import com.fang.dao.UserInfoDao;
import com.fang.entity.UserInfo;
import com.fang.service.UserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: fwj
 * @Description:
 * @Date: Created in 2018/4/26 11:42
 * @Modified by:
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Resource
    private UserInfoDao userInfoDao;
    @Override
    public UserInfo findByUsername(String username) {
        System.out.println("UserInfoServiceImpl.findByUsername");
        return userInfoDao.findByUsername(username);
    }

    @Override
    public boolean save(UserInfo userInfo) {
        UserInfo result = userInfoDao.save(userInfo);
        System.out.println(result);
        if (result != null)
            return true;
        return false;
    }
}
