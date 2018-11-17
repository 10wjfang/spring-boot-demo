package com.fang.dao;

import com.fang.entity.UserInfo;
import org.springframework.data.repository.CrudRepository;

/**
 * @Author: fwj
 * @Description:
 * @Date: Created in 2018/4/26 11:39
 * @Modified by:
 */
public interface UserInfoDao extends CrudRepository<UserInfo, Long> {
    public UserInfo findByUsername(String username);
}
