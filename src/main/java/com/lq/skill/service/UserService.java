package com.lq.skill.service;

import com.lq.skill.dao.UserDao;
import com.lq.skill.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Auther: LQ
 * @Date: 2018/11/6 23:04
 * @Description:
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User getUserById(int id) {
        return userDao.getById(id);
    }

    @Transactional
    public String tx() {
        User user = new User();
        user.setId(2);
        user.setName("222");
        int insert = userDao.insert(user);

        User user1 = new User();
        user1.setId(1);
        user1.setName("11");
        insert = userDao.insert(user1);
        return String.valueOf(insert);
    }
}
