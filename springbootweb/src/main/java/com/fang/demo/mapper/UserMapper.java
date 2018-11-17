package com.fang.demo.mapper;

import com.fang.demo.domain.User;
import com.fang.demo.domain.UserEntity;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: fwj
 * @Description:
 * @Date: Created in 2018/4/24 16:35
 * @Modified by:
 */
public interface UserMapper {
    List<UserEntity> getAll();

    UserEntity getOne(Long id);

    void insert(UserEntity user);

    void update(UserEntity user);

    void delete(Long id);
}
