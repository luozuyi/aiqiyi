package com.aiqiyi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    /**
     * 进入登陆页面
     * @return
     */
    @GetMapping(value = "/login.html")
    public String toLogin(){
        return "login";
    }
}
