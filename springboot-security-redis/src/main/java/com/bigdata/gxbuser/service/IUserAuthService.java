package com.bigdata.gxbuser.service;

import com.bigdata.gxbuser.domain.TUserAuthEntity;

/**
 * 用户认证接口
 *
 * @author fwj
 * @date 2019-02-12 22:06
 **/
public interface IUserAuthService {
    TUserAuthEntity getUserAuth(String identifier);
}
