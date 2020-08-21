package com.graduationrecons.Config;

import com.graduationrecons.Handler.AuthenticationPublisher;
import com.graduationrecons.Handler.LoginSuccessHandler;
import com.graduationrecons.Service.LoginService.LoginUserDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@EnableWebSecurity
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    LoginSuccessHandler loginSuccessHandler;


    @Override
    //授权
    protected void configure(HttpSecurity http) throws Exception {

         http.formLogin()
                 .loginPage(SecurityConstant.LoginPage)
                 //登录请求URL
                 .loginProcessingUrl(SecurityConstant.LogingProcessingUrl)
                 .successHandler(loginSuccessHandler)
                 .failureUrl(SecurityConstant.LoginFailUrl)
                 .usernameParameter("username")
                 .passwordParameter("mm")
                 .and()
                 .authorizeRequests()
                 .antMatchers("/css/**").permitAll()
                 .antMatchers("/js/**").permitAll()
                 .antMatchers("/assets/**").permitAll()
                 .antMatchers("/dist/**").permitAll()
                 .antMatchers("/plugin/**").permitAll()
                 .antMatchers("/datatable/**").permitAll()
                 .antMatchers("/user/**").hasRole("1")
                 .antMatchers("/Temperature/**").authenticated()
                 .antMatchers("/Celler/**").authenticated()
                 .antMatchers("/index").hasAnyRole("1","2")
                 .and()
                 .logout()
                 .logoutSuccessUrl(SecurityConstant.LogoutUrl)
                 .logoutUrl("/logout.do")
                 .and()
                 .rememberMe()
                 ;

    }

    @Autowired
    private LoginUserDetailService loginUserDetailService;

    @Autowired
    private AuthenticationPublisher authenticationPublisher;

    //认证/
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        //认证成功或失败通知类

       auth.userDetailsService(loginUserDetailService).passwordEncoder(new BCryptPasswordEncoder())
       .and()
       .authenticationEventPublisher(authenticationPublisher);




    }
}
