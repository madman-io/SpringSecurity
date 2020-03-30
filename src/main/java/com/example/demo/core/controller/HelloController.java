package com.example.demo.core.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author chaofeng.zhang
 * @email wb689178@alibaba-inc.com
 * @date 2020/3/27
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/index")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @RequestMapping(value = {"/checkpreload.htm"})
    public ModelAndView error() {
        return new ModelAndView("checkpreload");
    }

}