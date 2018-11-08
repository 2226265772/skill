package com.lq.skill.utils;

/**
 * @Auther: LQ
 * @Date: 2018/11/7 22:49
 * @Description:字符串转对象，对象转字符串的工具类 因为StringRedisTemplate的opsForValue()方法需要key, value都需要String类型，所以当value值存入对象的时候
 * 先转成字符串后存入。 转换失败异常抛给调用方处理
 */

import java.io.IOException;

import org.springframework.util.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    //对象转字符串
    public static <T> String obj2String(T obj) throws Exception {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("obj2String 转换错误");
        }
    }

    //字符串转对象
    public static <T> T string2Obj(String str, Class<T> clazz) throws Exception {
        if (StringUtils.isEmpty(str) || clazz == null) {
            return null;
        }
        try {
            return clazz.equals(String.class) ? (T) str : objectMapper.readValue(str, clazz);
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("string2Obj 转换错误");
        }
    }

 /*   //just for test
    public static void main(String[] args) {
        User user = new User();
        user.setName("lisi");
        user.setId(1);
        User user2 = new User();
        user2.setName("lisi22");
        user2.setId(2);
        List<User> list = new ArrayList<>();
        list.add(user);
        list.add(user2);
        String s = obj2String(list);
        System.out.println(s);
        List list1 = string2Obj(s, List.class);
        System.out.println(list1);
    }*/
}
