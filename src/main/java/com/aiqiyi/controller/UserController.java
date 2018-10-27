package com.aiqiyi.controller;

import com.aiqiyi.entity.User;
import com.aiqiyi.service.UserService;
import com.aiqiyi.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 用户注册
     * @param user 用户对象
     * @param surePassword 确认密码
     * @return
     */
    @PostMapping(value = "v1/users/regist")
    @ResponseBody
    public Result regist(User user,String surePassword){
        return userService.regist(user,surePassword);
    }
}
