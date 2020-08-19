package com.graduationrecons.Service.RegisterService;


import com.graduationrecons.Dao.User.UserMapper;
import com.graduationrecons.POJO.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RegisterServeceImpl implements RegisterService {

    @Autowired
    UserMapper userMapper;


    @Override
    public int Register(User user) {

        return userMapper.InsertUser(user);

    }




}
