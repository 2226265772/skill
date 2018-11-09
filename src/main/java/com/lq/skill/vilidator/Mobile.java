package com.lq.skill.vilidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.lang.annotation.*;

/**
 * @Auther: LQ
 * @Date: 2018/11/9 19:51
 * @Description:验证注解
 */
@Documented
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {IsMobileValidator.class})
public @interface Mobile {
    String message() default "{手机号格式错误}";

    boolean required() default true;


    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}
