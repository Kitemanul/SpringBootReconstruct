package com.graduationrecons.Handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthenticationPublisher implements AuthenticationEventPublisher {
    @Override
    public void publishAuthenticationSuccess(Authentication authentication) {
        log.info(authentication.getName().trim()+"认证成功");
    }

    @Override
    public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
        log.info(authentication.getName().trim()+"认证失败");
    }
}
