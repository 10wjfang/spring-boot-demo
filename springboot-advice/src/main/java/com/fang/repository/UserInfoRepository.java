package com.fang.repository;

import com.fang.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: fwj
 * @Description:
 * @Date: Created in 2018/11/16 10:20
 * @Modified by:
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
    UserInfo findUserInfoByUsername(String username);
}
