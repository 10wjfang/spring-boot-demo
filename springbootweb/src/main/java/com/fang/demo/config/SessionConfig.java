package com.fang.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @Author: fwj
 * @Description:
 * @Date: Created in ${date} ${time}
 * @Modified by:
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 10)
public class SessionConfig {
}
