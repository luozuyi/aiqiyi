package com.aiqiyi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping(value = "hello")
    public String toIndex(Model model){
        model.addAttribute("msg","helloworld");
        return "index";
    }
    @GetMapping(value = "aiqiyi")
    public String toAiqiyi(Model model){
        model.addAttribute("msg","helloworld");
        return "aiqiyi";
    }
}
