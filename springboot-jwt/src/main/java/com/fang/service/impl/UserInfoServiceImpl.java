package com.fang.service.impl;

import com.fang.dao.UserInfoDao;
import com.fang.domain.UserInfo;
import com.fang.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: fwj
 * @Description:
 * @Date: Created in 2018/11/7 13:59
 * @Modified by:
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Resource
    private UserInfoDao userInfoDao;
    @Override
    public UserInfo findByUsername(String username) {
        return userInfoDao.findUserInfoByUsername(username);
    }
}
