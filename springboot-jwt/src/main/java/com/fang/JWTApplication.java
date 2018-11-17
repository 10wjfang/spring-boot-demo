package com.fang;

import com.auth0.jwt.JWT;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: fwj
 * @Description:
 * @Date: Created in 2018/11/7 13:48
 * @Modified by:
 */
@SpringBootApplication
public class JWTApplication {
    public static void main(String[] args) {
        SpringApplication.run(JWTApplication.class, args);
    }
}
