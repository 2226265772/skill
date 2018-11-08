package com.lq.skill.utils;

/**
 * @Auther: LQ
 * @Date: 2018/11/8 22:07
 * @Description:自定义业务异常
 */
public class SkillException extends RuntimeException {

    private Integer code;

    SkillException(ErrorCode errorCode) {
        super(errorCode.getMsg());
        this.code = errorCode.getCode();
    }

    SkillException() {
    }

    public SkillException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
}
