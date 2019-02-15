package com.bigdata.gxbuser.filter;

import com.bigdata.config.Constants;
import com.bigdata.gxbuser.domain.TUserAuthEntity;
import com.bigdata.gxbuser.exception.InvalidTokenException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * Token过滤
 *
 * @author fwj
 * @date 2019-02-15 17:15
 **/
public class TokenFilter extends BasicAuthenticationFilter {
    public static final Logger logger = LoggerFactory.getLogger(TokenFilter.class);

    private RedisTemplate<String, Serializable> redisTemplate;

    public TokenFilter(AuthenticationManager authenticationManager, RedisTemplate redisTemplate) {
        super(authenticationManager);
        this.redisTemplate = redisTemplate;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String authentication = request.getHeader(Constants.HEADER_TOKEN);
        logger.debug("Token: {}", authentication);
        TUserAuthEntity user = (TUserAuthEntity) redisTemplate.opsForHash().get(authentication, Constants.KEY_USER_INFO);
        if (user == null) {
            throw new InvalidTokenException();
        }
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user.getIdentifier(), user.getCredential());
        SecurityContextHolder.getContext().setAuthentication(getAuthenticationManager().authenticate(auth));
        chain.doFilter(request, response);
    }
}
