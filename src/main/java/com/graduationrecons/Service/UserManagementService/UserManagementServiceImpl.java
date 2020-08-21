package com.graduationrecons.Service.UserManagementService;

import com.graduationrecons.Dao.User.UserMapper;
import com.graduationrecons.POJO.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManagementServiceImpl implements UserManagementService {

    @Autowired
    UserMapper userMapper;

    @Override
    @Cacheable(value="Users")
    public List<User> SearchaUsers(User user) {
        return userMapper.getUsers(user);
    }

    @Override
    @Caching(evict={@CacheEvict(value = "RegisterUser",allEntries = true),@CacheEvict(value = "Users",allEntries = true)})
    public int UpdateUser(User user,String username) {

        return userMapper.UpdateUser(user,username);
    }

    @Override
    @Caching(evict={@CacheEvict(value = "RegisterUser",allEntries = true),@CacheEvict(value = "Users",allEntries = true)})
    public int DeleteUser(String username) {

        return userMapper.DeleteUserbyUsername(username);
    }

    @Override
    @Caching(evict={@CacheEvict(value = "RegisterUser",allEntries = true),@CacheEvict(value = "Users",allEntries = true)})
    public int InsertUser(User user) {
        if(user.getPermission()==0) {
            user.setPermission(2);
        }
       //user.setMm(new BCryptPasswordEncoder().encode(user.getMm()));
       return userMapper.InsertUser(user);

    }
}
