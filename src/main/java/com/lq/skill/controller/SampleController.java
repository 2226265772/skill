package com.lq.skill.controller;

import com.lq.skill.config.IgnoreCheck;
import com.lq.skill.entity.User;
import com.lq.skill.service.UserService;
import com.lq.skill.utils.EncyptUtils;
import com.lq.skill.exception.ErrorCode;
import com.lq.skill.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
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

    //可提取到一个常数类
    //项目名字字母作为前缀key
    public static final String SYSTEM_PREFIX = "SKILL";
    //cookie名
    public static final String COOKIE_TOKEN = "token";
    //30分钟过期
    private static final int MAX_TIME = 3600;
    private static final long REDIS_AGE = 30;

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

    @RequestMapping("/to_list")
    public String list(Model model, User user) {
        model.addAttribute("user", user);
        return "index";
    }

    /**
     * 简单登录
     *
     * @param user
     * @return
     */
    @IgnoreCheck
    @PostMapping("/doLogin")
    public String login(User user, HttpServletResponse response) throws Exception {
        log.info(user + "");
        user = userService.login(user);
        if (user == null) {
            //待放到服务层抛
            ErrorCode.throwSkillException(ErrorCode.LOGIN_ERROR);
        }
        //成功后生成 token  写入redis,设置过期时间30分钟 存储用户信息 并写回客户端
        String token = EncyptUtils.UUID();
        stringRedisTemplate.opsForValue().set(SYSTEM_PREFIX + token, JsonUtils.obj2String(user), REDIS_AGE, TimeUnit.MINUTES);
        Cookie cookie = new Cookie(COOKIE_TOKEN, token);
        cookie.setPath("/");
        cookie.setMaxAge(MAX_TIME);
        response.addCookie(cookie);
        //跳转页面
        return "index";
    }

    /**
     * 测试数据库 和redis
     *
     * @return
     */
    @RequestMapping("/get")
    @ResponseBody
    public String getUserById() {
        stringRedisTemplate.opsForValue().set("key", "v", 111, TimeUnit.SECONDS);
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

    /**
     * 测试验证参数
     *
     * @param user
     * @return
     */
    @GetMapping("/check")
    @ResponseBody
    public String check(@Valid User user) {
        log.info(user + "");
        return user != null ? "success!" : "error!";
    }

}
