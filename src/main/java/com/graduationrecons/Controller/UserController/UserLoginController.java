package com.graduationrecons.Controller.UserController;


import com.graduationrecons.Config.SecurityConstant;
import com.graduationrecons.POJO.User;
import com.graduationrecons.Service.EmailService.EmailService;
import com.graduationrecons.Service.LoginService.UserService;
import com.graduationrecons.Service.UserManagementService.UserManagementService;
import com.graduationrecons.Util.MD5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

@Controller
@RequestMapping("/user")
@Api(value = "用户登录控制类")
@Slf4j
public class UserLoginController {
    @Autowired
    UserService userService;
    @Autowired
    UserManagementService userManagementService;
    @Autowired
    EmailService emailService;


    @PostMapping("/login.do")
    @ApiOperation("登录请求")
    public String UserLogin(User user)
    {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUsername(), MD5Utils.stringToMD5(user.getMm()));
        try {
            subject.login(usernamePasswordToken);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return SecurityConstant.LoginFailUrl;
        }

        return SecurityConstant.HomePage;
    }


    @PostMapping("/register.do")
    @ApiOperation("注册请求")
    public String UserRegister(User user,Model model,HttpServletRequest request)
    {
        String code= (String) request.getSession().getAttribute("code");
        if(code==null)
        {
            model.addAttribute("msg","请验证邮箱");
            return "register";

        }
        else if (!code.equals(user.getCode()))
        {
            model.addAttribute("msg", "验证码错误");
            return "register";
        }

        User oldUser=userService.LoginUser(user.getUsername());
        if(oldUser!=null)
        {
            model.addAttribute("msg","用户名已经被注册");
            return "register";
        }
        else
        {
            if(userManagementService.InsertUser(user)==1)
            {
                return "login";
            }
            else
            {
                model.addAttribute("msg","注册失败");
                return "register";
            }
        }

    }

    @PostMapping("/sendcode.do")
    @ResponseBody
    @ApiOperation(value = "使用邮箱发送验证码")
    public String SendCode(@RequestParam("email") String email,HttpServletRequest request)
    {
        log.info("-------开始生成生成验证码-----------");
        Random r = new Random();
        StringBuilder sb=new StringBuilder();
        sb.append(r.nextInt(10));
        sb.append(r.nextInt(10));
        sb.append(r.nextInt(10));
        sb.append(r.nextInt(10));
        request.getSession().setAttribute("code",sb.toString());
        log.info("-------生成验证码完毕-----------");
        log.info("-------开始发送验证码-----------");
        emailService.SendEmail(email,sb.toString());
        log.info("-------发送验证码完毕-----------");
        return "";

    }



}
