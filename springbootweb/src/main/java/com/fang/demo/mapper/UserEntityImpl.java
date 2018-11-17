package com.fang.demo.mapper;

import com.fang.demo.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: fwj
 * @Description:
 * @Date: Created in 2018/4/25 10:47
 * @Modified by:
 */
@Component
public class UserEntityImpl implements UserMapper {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Override
    public List<UserEntity> getAll() {
        return null;
    }

    public UserEntity getOne(Long id) {
        Query query = new Query(Criteria.where("id").is(id));
        UserEntity user = mongoTemplate.findOne(query, UserEntity.class);
        return user;
    }

    @Override
    public void insert(UserEntity user) {
        mongoTemplate.save(user);
    }

    @Override
    public void update(UserEntity user) {

    }

    @Override
    public void delete(Long id) {

    }
}
