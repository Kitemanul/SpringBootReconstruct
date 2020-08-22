package com.graduationrecons.Config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.graduationrecons.Handler.MyRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


@Configuration
public class ShiroConfig{

    @Bean
    public  MyRealm myRealm()
    {
        return new MyRealm();
    }



    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myRealm());
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());

        Map<String, String> filterMap = new LinkedHashMap<String, String>();

        // 登出
        filterMap.put("/css/**", "anon");
        filterMap.put("/js/**", "anon");
        filterMap.put("/img/**", "anon");
        filterMap.put("/datapicker/**", "anon");
        filterMap.put("/fontawesome-free/**", "anon");
        filterMap.put("/logout.do","logout");
        filterMap.put("/Temperature/**","authc");
        filterMap.put("/Celler/**","authc");
        filterMap.put("/index/**","authc");
        filterMap.put("/User/**","perms[user:admin]");


        //shiroFilterFactoryBean.setLoginUrl("/user/login.do");

        //没权限跳转到的页面
        //shiroFilterFactoryBean.setUnauthorizedUrl("login");

        //没登陆的人跳转的页面
        shiroFilterFactoryBean.setLoginUrl("/login");


        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);


        return shiroFilterFactoryBean;
    }

    @Bean
    public ShiroDialect getShiroDialect()
    {
        return new ShiroDialect();
    }
}