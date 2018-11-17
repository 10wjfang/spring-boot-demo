package com.fang.service.impl;

import com.fang.dao.UserInfoDao;
import com.fang.domain.UserInfo;
import com.fang.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: fwj
 * @Description:
 * @Date: Created in 2018/11/6 14:10
 * @Modified by:
 */
@Service
public class UserInfoServiceImpl implements IUserService {
    @Resource
    private UserInfoDao userInfoDao;
    @Override
    public UserInfo findByUsername(String username) {
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
