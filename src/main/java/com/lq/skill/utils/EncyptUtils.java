package com.lq.skill.utils;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.UUID;

/**
 * @Auther: LQ
 * @Date: 2018/11/8 21:51
 * @Description:加密 uuid等
 */
public class EncyptUtils {

    private static final String My_SALT = "liangbaikai";

    public static void main(String[] args) {
//        32c5cedebded0147044d1edcaddadcfb
        String s = md5("123456");
        System.out.println(s);
    }

    public static String md5(String str) {
        return DigestUtils.md5Hex(My_SALT.charAt(2) + str + My_SALT);
    }

    public static String UUID() {
        return UUID.randomUUID().toString().replaceAll("_", "").toUpperCase();
    }
}
