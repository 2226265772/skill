package com.lq.skill.controller;

import com.lq.skill.entity.User;
import com.lq.skill.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.TimeUnit;


/**
 * @Auther: LQ
 * @Date: 2018/11/6 21:50
 * @Description:测试环境搭建
 */

@Controller
@RequestMapping("/test")
@Slf4j
public class SampleController {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

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
     * 简单登录
     * @param user
     * @return
     */
    @PostMapping("/doLogin")
    @ResponseBody
    public String login(User user) {
        log.info(user + "");
        user = userService.login(user);
        return user != null ? "success!" : "error!";
    }

    /**
     * 测试数据库 和redis
     *
     * @return
     */
    @RequestMapping("/get")
    @ResponseBody
    public String getUserById() {
        stringRedisTemplate.opsForValue().set("key", "v", 1, TimeUnit.HOURS);
        String value = stringRedisTemplate.opsForValue().get("key");
        return userService.getUserById(1) + "redis:" + value;
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
