package com.lq.skill.vilidator;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Auther: LQ
 * @Date: 2018/11/9 20:08
 * @Description:工具类 校验手机号码 13054542874
 */
public class ValidatiorUtils {

    private static Pattern pattern = null;
    private static Matcher matcher = null;

    public static boolean isMobile(String value) {
        if (StringUtils.isBlank(value)) {
            return false;
        }
        if (value.trim().length() != 11) {
            return false;
        }
        pattern = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$");
        matcher = pattern.matcher(value);
        return matcher.matches();
    }

    public static boolean isTelephone(final String str) {
        Pattern p1 = null, p2 = null;
        Matcher m = null;
        boolean b = false;
        p1 = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$");  // 验证带区号的021-88889999
        p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$");         // 验证没有区号的88889999
        if (str.length() > 9) {
            m = p1.matcher(str);
            b = m.matches();
        } else {
            m = p2.matcher(str);
            b = m.matches();
        }
        return b;
    }

  /*  public  static  void main(String[]args){
        boolean mobile = isMobile("19054290269");
        System.out.println(mobile);
    }*/
}
