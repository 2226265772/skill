package com.lq.skill.controller;

import com.lq.skill.entity.User;
import com.lq.skill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @Auther: LQ
 * @Date: 2018/11/6 21:50
 * @Description:测试环境搭建
 */

@Controller
@RequestMapping("/test")
public class SampleController {

    @Autowired
    private UserService userService;

    /**
     * 测试前后端连通性
     *
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("name", "liangbaikai");
        return "hello";
    }

    /**
     * 测试数据库
     *
     * @return
     */
    @RequestMapping("/get")
    @ResponseBody
    public User getUserById() {
        return userService.getUserById(1);
    }

    /**
     * 测试事务
     *
     * @return
     */
    @RequestMapping("/tx")
    @ResponseBody
    public String tX() {
        return userService.tx();
    }
}
