package com.graduationrecons.Service.UserManagementService;

import com.graduationrecons.POJO.User;

import java.util.List;

public interface CheckRegisterService {

    public int Check(String username);

    public List<User> getAllUnRegisterUser(User user);

   // public User getUnRegisterUser(String username);
}
