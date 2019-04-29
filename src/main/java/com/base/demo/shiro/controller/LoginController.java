package com.base.demo.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * LoginController.
 *
 * @author Dulk
 * @version 20190425
 * @date 2019/4/25
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping("/index")
    public String index() {
        return "/login/index";
    }

    @RequestMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, ModelMap data) {

        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            data.put("message", e.getMessage());
            return "/error";
        }

        return "/login/login";
    }

    @RequestMapping("/unauthorized")
    public String unauthorized() {
        return "/login/unauthorized";
    }

}
