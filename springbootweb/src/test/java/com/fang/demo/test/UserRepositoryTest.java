package com.fang.demo.test;

import com.fang.demo.domain.UserEntity;
import com.fang.demo.enums.UserSexEnum;
import com.fang.demo.service.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Author: fwj
 * @Description:
 * @Date: Created in 2018/4/25 14:51
 * @Modified by:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void TestSave() {
        UserEntity user = new UserEntity();
        user.setId(3L);
        user.setUserName("yang");
        user.setNickName("nick");
        user.setPassWord("123");
        user.setUserSex(UserSexEnum.WOMAN);
        userRepository.save(user);
    }

    @Test
    public void GetOne() {
        UserEntity userEntity = userRepository.findById(3L).get();
        System.out.println(userEntity);
    }
}