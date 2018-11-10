package com.lq.skill.exception;

import lombok.Data;
import org.apache.commons.lang3.ArrayUtils;

/**
 * @Auther: LQ
 * @Date: 2018/11/8 22:01
 * @Description:错误码统一存放
 */
@Data
public class ErrorCode {
    private Integer code;
    private String msg;

    public static final ErrorCode LOGIN_ERROR = new ErrorCode(10001, "账号或密码错误");
    public static final ErrorCode LOGIN_EXPIRED_ERROR = new ErrorCode(10002, "登录过期 请重新登录");

    public ErrorCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static void throwSkillException(ErrorCode errorCode, Object... values) {
        String message = errorCode.msg;
        if (ArrayUtils.isNotEmpty(values)) {
            for (int i = 0; i < values.length; i++) {
                message = message.replaceFirst("\\{" + i + "\\}", values[i].toString());
            }
        }
        throw new SkillException(errorCode.code, errorCode.msg);
    }

    @Override
    public String toString() {
        return "ErrorCode{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
