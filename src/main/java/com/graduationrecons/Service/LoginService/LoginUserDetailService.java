package com.graduationrecons.Service.LoginService;

import com.graduationrecons.POJO.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoginUserDetailService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user=userService.LoginUser(s);
        if (user == null){
            throw new UsernameNotFoundException("用户名不存在!");
        }
        List<GrantedAuthority> list=new ArrayList<>();
        list.add(new SimpleGrantedAuthority("ROLE_"+Integer.toString(user.getPermission())));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getMm(), true, true, true, user.getPass()==1,list);
    }
}
