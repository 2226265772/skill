package com.lq.skill.service;

import com.lq.skill.dao.UserDao;
import com.lq.skill.entity.User;
import com.lq.skill.utils.EncyptUtils;
import com.lq.skill.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Auther: LQ
 * @Date: 2018/11/6 23:04
 * @Description:
 */
@Service
@Slf4j
public class UserService {

    @Autowired
    private UserDao userDao;

    public User getUserById(int id) {
        return userDao.getById(id);
    }

    public User login(User user) {
        if (user == null || StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())) {
            ErrorCode.throwSkillException(ErrorCode.LOGIN_ERROR);
        }
        //加密
        String passwd = EncyptUtils.md5(user.getPassword());
        user.setPassword(passwd);
        log.info(user + "");
        user = userDao.login(user);
        return user;
    }

    @Transactional
    public String tx() {
/*        User user = new User();
        user.setId(2L);
        user.setName("222");
        int insert = userDao.insert(user);
        User user1 = new User();
        user1.setId(1L);
        user1.setName("11");
        insert = userDao.insert(user1);*/
        return String.valueOf(1);
    }
}
