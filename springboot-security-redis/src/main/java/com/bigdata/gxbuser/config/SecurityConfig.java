package com.bigdata.gxbuser.config;

import com.bigdata.gxbuser.filter.TokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import java.io.Serializable;

/**
 * 安全配置
 *
 * @author fwj
 * @date 2019-02-12 15:52
 **/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AccessDeniedHandlerImpl accessDeniedHandler;
    @Autowired
    private RestAuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //禁用CSRF
        http.csrf().disable()
                //权限不足异常
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler)
                //没有登录异常
                .authenticationEntryPoint(authenticationEntryPoint);
        http.authorizeRequests().antMatchers("/login").permitAll().anyRequest().authenticated().and()
                //禁用session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER);
        http.addFilter(new TokenFilter(authenticationManager(), redisTemplate));
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        // 解决authenticationManager 无法注入
        return super.authenticationManagerBean();
    }
}
