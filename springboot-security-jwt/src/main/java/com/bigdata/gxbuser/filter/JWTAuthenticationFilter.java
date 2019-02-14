package com.bigdata.gxbuser.filter;

import com.bigdata.gxbuser.domain.TUserAuthEntity;
import com.bigdata.gxbuser.repository.UserAuthRepository;
import com.bigdata.gxbuser.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 检验是否登录过滤器
 *
 * @author fwj
 * @date 2019-02-13 22:26
 **/
public class JWTAuthenticationFilter extends BasicAuthenticationFilter {
    public static final Logger logger = LoggerFactory.getLogger(JWTAuthenticationFilter.class);
    private UserAuthRepository userAuthRepository;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, UserAuthRepository userAuthRepository) {
        this(authenticationManager);
        this.userAuthRepository = userAuthRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String authentication = request.getHeader("Authorization");
        logger.info("Authorization: {}", authentication);
        if (authentication == null) {
            chain.doFilter(request, response);
            return;
        }
        String username = JwtUtil.getUsername(authentication);
        TUserAuthEntity userAuthEntity = userAuthRepository.findByIdentifier(username).orElse(null);
        if (userAuthEntity == null) {
            throw new BadCredentialsException("user not exist");
        }
        String password = userAuthEntity.getCredential();
        if (JwtUtil.verify(authentication, username, password)) {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
            Authentication authenticate = null;
            try {
                authenticate = getAuthenticationManager().authenticate(token);
                SecurityContextHolder.getContext().setAuthentication(authenticate);
            } catch (AuthenticationException e) {
                e.printStackTrace();
            }
        }
        chain.doFilter(request, response);
    }
}
