package com.bigdata.gxbuser.config;

import com.bigdata.gxbuser.domain.TUserAuthEntity;
import com.bigdata.gxbuser.repository.UserAuthRepository;
import com.bigdata.gxbuser.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * 用户认证
 *
 * @author fwj
 * @date 2019-02-12 22:11
 **/
@Component
public class UserAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private CustomUserService customUserService;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserDetails userDetails = customUserService.loadUserByUsername(authentication.getPrincipal().toString());
        if (userDetails == null) {
            throw new BadCredentialsException("Username not found!");
        }
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails.getUsername(),
                userDetails.getPassword(), userDetails.getAuthorities());
        token.setDetails(userDetails);
        return token;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
