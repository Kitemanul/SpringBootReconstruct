package com.graduationrecons.Config;

import com.graduationrecons.Constant.Constant;

import com.graduationrecons.Handler.LoginSuccessHandler;
import com.graduationrecons.Service.LoginService.LoginUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AndRequestMatcher;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    LoginSuccessHandler loginSuccessHandler;
    @Override
    //授权
    protected void configure(HttpSecurity http) throws Exception {

         http.formLogin()
                 .loginPage(SecurityConstant.LoginPage)
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
                 .antMatchers("/user/sendcode.do").authenticated()
                 .antMatchers("/**/*.do").authenticated()
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

    //认证/
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

     //auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("root").password("123").disabled(false);
       auth.userDetailsService(loginUserDetailService).passwordEncoder(new BCryptPasswordEncoder());







    }
}
