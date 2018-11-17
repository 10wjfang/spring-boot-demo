package com.fang.demo.test;

import com.fang.demo.Application;
import com.fang.demo.domain.Message;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RedisTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test() throws Exception {
        stringRedisTemplate.opsForValue().set("aaa","112");
        Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
    }

    @Test
    public void testObj() throws Exception {
        Message msg = new Message();
        msg.setText("text");
        msg.setSummary("summary");
        msg.setId(1L);
        msg.setCreated(Calendar.getInstance());
        ValueOperations<String, Message> valueOperations = redisTemplate.opsForValue();
        valueOperations.set("com.fang.msg",msg);
        boolean exists = redisTemplate.hasKey("com.fang.msg");
        if(exists) {
            System.out.println("exists");
        }
        else {
            System.out.println("not exists");
        }
    }
}
