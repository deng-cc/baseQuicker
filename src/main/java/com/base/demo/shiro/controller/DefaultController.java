package com.base.demo.shiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * DefaultController.
 *
 * @author Dulk
 * @version 20190429
 * @date 2019/4/29
 */
@Controller
public class DefaultController {

    @RequestMapping("/")
    public String index() {
        return "/login/index";
    }

}
