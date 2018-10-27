package com.aiqiyi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegistController {
    /**
     * 进入注册页面
     * @return
     */
    @GetMapping(value = "/regist.html")
    public String toLogin(){
        return "regist";
    }
}
