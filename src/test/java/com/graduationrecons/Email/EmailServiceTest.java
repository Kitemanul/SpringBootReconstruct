package com.graduationrecons.Email;

import com.graduationrecons.Service.EmailService.EmailService;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@SpringBootTest
public class EmailServiceTest {

    @Autowired
    private EmailService emailService;


    @Test
    public void test()
    {
//        Random r = new Random();
//        StringBuilder sb=new StringBuilder();
//        sb.append(r.nextInt(10));
//        sb.append(r.nextInt(10));
//        sb.append(r.nextInt(10));
//        sb.append(r.nextInt(10));
//
//        emailService.SendEmail("1525378562@qq.com",sb.toString());
        String s="445365163@qq.com";

        String a=s.split("@")[1];
        System.out.println(a);
}

}
