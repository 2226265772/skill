package com.lq.skill.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: LQ
 * @Date: 2018/11/8 22:28
 * @Description:全局异常处理器 简单处理
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class HandleException {

    @ExceptionHandler(value = {Exception.class})
    public String handle(Exception e) {
        System.out.println(" enter  handler  exception ");
        log.error(" enter  handler  exception {} ", e);
        if (e instanceof SkillException) {
            return e.getMessage() + "  -错误码-" + ((SkillException) e).getCode();
        }
        return e.getMessage();
    }
}

