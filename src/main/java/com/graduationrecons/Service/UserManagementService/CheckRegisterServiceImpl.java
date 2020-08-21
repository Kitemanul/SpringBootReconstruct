package com.graduationrecons.Service.UserManagementService;


import com.graduationrecons.Dao.User.UserMapper;
import com.graduationrecons.POJO.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CheckRegisterServiceImpl implements CheckRegisterService
{
    @Autowired
    UserMapper userMapper;

    @Override
    @Cacheable(value = "RegisterUser")
    public List<User> getAllUnRegisterUser(User user) {
        List<User> res=new ArrayList<>();
        List<User> temp=userMapper.getUsers(user);
        for(User u:temp)
        {
            if(u.getPass()==0)
            {
                res.add(u);
            }
        }
        return res;
    }

    @Override
    @Caching(evict={@CacheEvict(value = "RegisterUser",allEntries = true),@CacheEvict(value = "Users",allEntries = true)})
    public int Check(String username) {
        return userMapper.UpdatePass(username);
    }
}
