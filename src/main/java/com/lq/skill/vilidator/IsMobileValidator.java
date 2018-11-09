package com.lq.skill.vilidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


/**
 * @Auther: LQ
 * @Date: 2018/11/9 20:03
 * @Description:自定义手机号码校验器
 */
public class IsMobileValidator implements ConstraintValidator<Mobile, String> {

    private boolean required = false;

    @Override
    public void initialize(Mobile constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return required == true ? ValidatiorUtils.isMobile(value) : true;
    }
}
