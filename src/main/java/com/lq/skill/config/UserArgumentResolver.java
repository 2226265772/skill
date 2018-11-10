package com.lq.skill.config;

import com.lq.skill.controller.SampleController;
import com.lq.skill.entity.User;
import com.lq.skill.exception.ErrorCode;
import com.lq.skill.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: LQ
 * @Date: 2018/11/10 19:40
 * @Description:
 */
@Component
public class UserArgumentResolver implements HandlerMethodArgumentResolver {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        //登录放行
        IgnoreCheck methodAnnotation = methodParameter.getMethodAnnotation(IgnoreCheck.class);
        if (methodAnnotation != null && methodAnnotation.value() == false) {
            return false;
        }

        Class<?> parameterType = methodParameter.getParameterType();
        return User.class == parameterType;
    }


    /**
     * 此种方法可以代替aop或者拦截器 取得user的值  注意对登录放行
     *
     * @param methodParameter
     * @param modelAndViewContainer
     * @param nativeWebRequest
     * @param webDataBinderFactory
     * @return
     * @throws Exception
     */
    @Nullable
    @Override
    public Object resolveArgument(MethodParameter methodParameter, @Nullable ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest, @Nullable WebDataBinderFactory webDataBinderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) nativeWebRequest.getNativeRequest();
        //HttpServletResponse response = (HttpServletResponse) nativeWebRequest.getNativeResponse();
        String paramToken = request.getParameter(SampleController.COOKIE_TOKEN);
        String cookieToken = "";
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (SampleController.COOKIE_TOKEN.equals(cookie.getName())) {
                    cookieToken = cookie.getValue();
                    break;
                }
            }
        }
        if (StringUtils.isBlank(paramToken) && StringUtils.isBlank(cookieToken)) {
            //大部分情况是token过期了 可以直接抛出异常 或者 重新刷新token
            ErrorCode.throwSkillException(ErrorCode.LOGIN_EXPIRED_ERROR);
        }
        String realToken = (StringUtils.isBlank(paramToken) ? cookieToken : paramToken);
        String value = stringRedisTemplate.opsForValue().get(SampleController.SYSTEM_PREFIX + realToken);
        return JsonUtils.string2Obj(value, User.class);
    }
}
