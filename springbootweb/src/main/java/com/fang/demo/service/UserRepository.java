package com.fang.demo.service;

import com.fang.demo.domain.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Author: fwj
 * @Description:
 * @Date: Created in 2018/4/25 14:49
 * @Modified by:
 */
public interface UserRepository extends MongoRepository<UserEntity, Long> {
}
