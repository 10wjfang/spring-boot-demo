package com.fang.demo.mapper;

import com.fang.demo.domain.UserEntity;
import com.fang.demo.enums.UserSexEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Author: fwj
 * @Description:
 * @Date: Created in 2018/4/25 10:53
 * @Modified by:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserEntityImplTest {
    @Autowired
    private UserEntityImpl userEntityImpl;
    @Test
    public void getOne() throws Exception {
        UserEntity user = userEntityImpl.getOne(2L);
        System.out.println(user.toString());
    }

    @Test
    public void insert() throws Exception {
        UserEntity user = new UserEntity();
        user.setId(2L);
        user.setUserName("fang");
        user.setNickName("nick");
        user.setPassWord("123");
        user.setUserSex(UserSexEnum.MAN);
        userEntityImpl.insert(user);
    }

}