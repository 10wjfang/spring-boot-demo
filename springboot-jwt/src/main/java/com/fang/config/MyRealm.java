package com.fang.config;

import com.fang.domain.JWTToken;
import com.fang.domain.SysPermission;
import com.fang.domain.SysRole;
import com.fang.domain.UserInfo;
import com.fang.service.UserInfoService;
import com.fang.utils.JWTUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * @Author: fwj
 * @Description:
 * @Date: Created in 2018/11/7 14:26
 * @Modified by:
 */
public class MyRealm extends AuthorizingRealm {
    @Resource
    private UserInfoService userInfoService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        String username = (String)principalCollection.toString();
        UserInfo userInfo = userInfoService.findByUsername(username);
        for(SysRole role:userInfo.getRoleList()){
            System.out.println(role.getRole());
            authorizationInfo.addRole(role.getRole());
            for(SysPermission p:role.getPermissions()){
                System.out.println(p.getPermission());
                authorizationInfo.addStringPermission(p.getPermission());
            }
        }
        return authorizationInfo;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getCredentials();
        String username = JWTUtil.getUsername(token);
        if (username == null) {
            throw  new AuthenticationException("token invalid");
        }
        UserInfo userInfo = userInfoService.findByUsername(username);
        if (userInfo == null) {
            throw new AuthenticationException("user not exist");
        }
        if (!JWTUtil.verify(token, username, userInfo.getPassword())) {
            throw new AuthenticationException("username or password error");
        }
        return new SimpleAuthenticationInfo(token, token, getName());
    }
}
