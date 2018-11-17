package com.fang.dao;

import com.fang.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: fwj
 * @Description:
 * @Date: Created in 2018/11/7 13:56
 * @Modified by:
 */
public interface UserInfoDao extends JpaRepository<UserInfo, Integer> {
    UserInfo findUserInfoByUsername(String username);
}
