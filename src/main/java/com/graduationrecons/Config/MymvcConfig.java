package com.graduationrecons.Config;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Api("页面跳转控制类")
public class MymvcConfig implements WebMvcConfigurer {

    @Override
    @ApiOperation("页面跳转")
    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/").setViewName("login");
        registry.addViewController("/toRegister").setViewName("register");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/Celler/CellerManagement").setViewName("page/Celler/CellerManagement");
        registry.addViewController("/User/UserManagement").setViewName("page/User/UserManagement");
        registry.addViewController("/User/RegisterUserManagement").setViewName("page/User/RegisterUserManagement");
        registry.addViewController("/Temperature/TemperatureManagement").setViewName("page/Temperature/TemperatureManagement");
        registry.addViewController("/Temperature/ErrorTemperature").setViewName("page/Temperature/ErrorTemperature");
        registry.addViewController("/Temperature/TemperatureCompare").setViewName("page/Temperature/TemperatureCompare");

    }


}
