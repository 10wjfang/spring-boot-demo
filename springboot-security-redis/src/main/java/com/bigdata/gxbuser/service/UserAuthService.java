package com.bigdata.gxbuser.service;

import com.bigdata.gxbuser.domain.TUserAuthEntity;
import com.bigdata.gxbuser.repository.UserAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户认证服务
 *
 * @author fwj
 * @date 2019-02-12 16:23
 **/
@Service
public class UserAuthService implements IUserAuthService {
    @Autowired
    private UserAuthRepository userAuthRepository;

    @Override
    public TUserAuthEntity getUserAuth(String identifier) {
        return userAuthRepository.findByIdentifier(identifier).orElse(null);
    }
}
