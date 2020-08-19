package com.graduationrecons.Service.LoginService;


import com.graduationrecons.Dao.User.UserMapper;
import com.graduationrecons.POJO.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;


    @Override
    public User LoginUser(String username) {

        return userMapper.getUserbyName(username);
    }


}
