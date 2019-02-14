package com.bigdata.gxbuser.controller;

import com.bigdata.dto.ResponseDataDTO;
import com.bigdata.enums.ErrorCodeEnum;
import com.bigdata.gxbuser.config.UserAuthenticationProvider;
import com.bigdata.gxbuser.domain.TUserAuthEntity;
import com.bigdata.gxbuser.service.IUserAuthService;
import com.bigdata.gxbuser.util.JwtUtil;
import com.bigdata.gxbuser.view.LoginInfoVo;
import com.bigdata.manger.ResponseGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Properties;

/**
 * 用户登录
 *
 * @author fwj
 * @date 2019-02-12 19:09
 **/
@RestController
public class UserLoginController {
    @Autowired
    private IUserAuthService userAuthService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping(value = "/login")
    public ResponseDataDTO<LoginInfoVo> UserLogin(@RequestParam String username, @RequestParam String password) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authenticate = null;
        try {
            authenticate = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authenticate);
            TUserAuthEntity entity = userAuthService.getUserAuth(username);
            LoginInfoVo loginInfoVo = new LoginInfoVo();
            BeanUtils.copyProperties(entity, loginInfoVo);
            loginInfoVo.setToken(JwtUtil.sign(username, entity.getCredential()));
            return ResponseGenerator.success(loginInfoVo);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        return ResponseGenerator.errors(ErrorCodeEnum.USERNAME_PASSWORD_ERROR);
    }
}
