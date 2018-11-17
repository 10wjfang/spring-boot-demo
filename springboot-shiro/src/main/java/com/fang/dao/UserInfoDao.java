package com.fang.dao;

import com.fang.domain.UserInfo;
import org.springframework.data.repository.CrudRepository;

/**
 * @Author: fwj
 * @Description:
 * @Date: Created in 2018/11/6 14:09
 * @Modified by:
 */
public interface UserInfoDao extends CrudRepository<UserInfo, Long> {
    UserInfo findByUsername(String username);
}
