package com.graduationrecons.Controller.UserController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.graduationrecons.POJO.User;
import com.graduationrecons.Service.LoginService.UserService;
import com.graduationrecons.Service.UserManagementService.CheckRegisterService;
import com.graduationrecons.Service.UserManagementService.UserManagementService;
import com.graduationrecons.Util.MD5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

@Controller
@RequestMapping("/user")
@Api(value = "用户登录控制类")
@Slf4j
public class UserManagementController {

    @Autowired
    UserManagementService userManagementService;

    @Autowired
    CheckRegisterService checkRegisterService;

    @Autowired
    UserService userService;

    @RequestMapping("/AddUser")
    @ResponseBody
    @ApiOperation("添加用户")
    public String AddUser(User user)
    {

        User searchUser=userService.LoginUser(user.getUsername());

        if(searchUser!=null)
        {
            return "用户名已被注册";
        }
        else
        {
            user.setPass(1);
            user.setMm(MD5Utils.stringToMD5(user.getMm()));
            int row=userManagementService.InsertUser(user);
            if(row==1)
            {
                return  "添加成功";
            }
            else
            {
                return "添加失败";
            }
        }

    }

    @RequestMapping("/RemoveUser")
    @ResponseBody
    @ApiOperation("删除用户")
    public String DeleteUser(User user)
    {
        int row=userManagementService.DeleteUser(user.getUsername());

        if(row==1)
        {
            return "删除成功";
        }
        else
        {
            return "删除失败";
        }
    }

    @RequestMapping("/EditUser")
    @ResponseBody
    @ApiOperation("修改用户")
    public String EditUser(User newuser,String _username)
    {
        User _user= userService.LoginUser(newuser.getUsername());
        if(_user!=null&&!_user.getUsername().equals(_username))
        {
            return "该用户名已被注册";
        }
        else
        {
            int row=userManagementService.UpdateUser(newuser,_username);

            if(row==1)
            {
                return "修改成功";
            }
            else
            {
                return "修改失败";
            }
        }
    }

    @RequestMapping("/registeringUserdata")
    @ResponseBody
    public String SearchUnregisterUser(User user)
    {
        List<User> list=null;
        list=checkRegisterService.getAllUnRegisterUser(user);
        JSONArray res=new JSONArray();
        for(int i=0;i<list.size();i++)
        {
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("用户名",list.get(i).getUsername());
            jsonObject.put("权限", list.get(i).getPermission());
            jsonObject.put("审核状态", "待审核");
            res.add(jsonObject);
        }

        JSONObject jsonObject=new JSONObject();
        jsonObject.put("data",res);
        return  jsonObject.toString();


    }

    @RequestMapping("/userpass")
    @ResponseBody
    @ApiOperation("审核用户")
    public String CheckUser(String username, HttpServletResponse response)
    {
        response.setCharacterEncoding("utf-8");
        int row=0;
        row=checkRegisterService.Check(username);

        if(row==1)
        {
            return "审核通过";
        }
        else {
            return "审核失败";
        }
    }

    @RequestMapping("/Userdata")
    @ResponseBody
    @ApiOperation("查找用户")
    public String Searchuserdata(User userr)
    {

        List<User> list=userManagementService.SearchaUsers(userr);
        JSONArray jsonArray=new JSONArray();
        if(list.size()!=0)
        {
            for(User u:list)
            {
                JSONObject jsonObject=new JSONObject();
                jsonObject.put("用户名",u.getUsername());
                jsonObject.put("权限",u.getPermission());
                if(u.getPass()==1)
                {
                    jsonObject.put("审核状态", "通过");
                }
                else {
                    jsonObject.put("审核状态", "待审核");
                }
                jsonArray.add(jsonObject);
            }
        }
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("data",jsonArray);
        return  jsonObject.toString();

    }





}
