package com.lq.skill.config;

import java.lang.annotation.*;

/**
 * @Auther: LQ
 * @Date: 2018/11/10 20:54
 * @Description:IgnoreCheck标识符 让ArgumentsResolver放行
 */

@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnoreCheck {
    boolean value() default false;
}
