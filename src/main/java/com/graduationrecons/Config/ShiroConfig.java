package com.graduationrecons.Config;

import com.graduationrecons.Handler.MyRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


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

//        Map<String, String> filterMap = new HashMap<String, String>();
//        // 登出
//        filterMap.put("/logout", "logout");
//        // swagger
//        filterMap.put("/swagger**/**", "anon");
//        filterMap.put("/webjars/**", "anon");
//        filterMap.put("/v2/**", "anon");
//        // 对所有用户认证
//        filterMap.put("/**", "authc");
//        // 登录
//        shiroFilterFactoryBean.setLoginUrl("/login");
//        // 首页
//        shiroFilterFactoryBean.setSuccessUrl("/index");
//        // 错误页面，认证不通过跳转
//        shiroFilterFactoryBean.setUnauthorizedUrl("/error");
//        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }
}