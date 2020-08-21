package com.graduationrecons.Handler;

import com.graduationrecons.POJO.User;
import com.graduationrecons.Service.LoginService.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Slf4j
public class MyRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;
    @Autowired
    RedisTemplate redisTemplate;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("开始授权");
        return null;
    }


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        if (authenticationToken.getPrincipal() == null) {
            return null;
        }
        String name=authenticationToken.getPrincipal().toString();
        log.info(name+"开始认证");
        if(redisTemplate.hasKey(name))
        {

        }
        else
        {
            User user=userService.LoginUser(name);
            if(user!=null)
            {
                if(user.getPass()==0)
                {
                    throw new AuthenticationException("该账号已被禁用");
                }
                log.info("name:"+name+"开始认证"+"realmname"+getName());
                log.info(user.getMm());
                user.setUsername(user.getUsername().trim());
                return new SimpleAuthenticationInfo(user,user.getMm(),getName());

            }
        }
        return null;
    }
}
