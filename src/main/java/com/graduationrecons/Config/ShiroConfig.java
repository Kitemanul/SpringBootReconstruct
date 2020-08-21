package com.graduationrecons.Config;

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


//     .loginPage(SecurityConstant.LoginPage)
//    //登录请求URL
//                 .loginProcessingUrl(SecurityConstant.LogingProcessingUrl)
//                 .successHandler(loginSuccessHandler)
//                 .failureUrl(SecurityConstant.LoginFailUrl)
//                 .usernameParameter("username")
//                 .passwordParameter("mm")
//                 .and()
//                 .authorizeRequests()
//                 .antMatchers("/css/**").permitAll()
//                 .antMatchers("/js/**").permitAll()
//                 .antMatchers("/assets/**").permitAll()
//                 .antMatchers("/dist/**").permitAll()
//                 .antMatchers("/plugin/**").permitAll()
//                 .antMatchers("/datatable/**").permitAll()
//                 .antMatchers("/user/**").hasRole("1")
//                 .antMatchers("/Temperature/**").authenticated()
//                 .antMatchers("/Celler/**").authenticated()
//                 .antMatchers("/index").hasAnyRole("1","2")
//                 .and()
//                 .logout()
//                 .logoutSuccessUrl(SecurityConstant.LogoutUrl)
//                 .logoutUrl("/logout.do")
//                 .and()
//                 .rememberMe()
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
        filterMap.put("/assets/**", "anon");
        filterMap.put("/dist/**", "anon");
        filterMap.put("/plugin/**", "anon");
        filterMap.put("/datatable/**", "anon");
        filterMap.put("/Temperature/**","authc");
        filterMap.put("/Celler/**","authc");
        filterMap.put("/index/**","authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }
}