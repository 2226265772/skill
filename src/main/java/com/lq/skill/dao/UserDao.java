package com.lq.skill.dao;

import com.lq.skill.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @Auther: LQ
 * @Date: 2018/11/6 23:03
 * @Description:交互数据库
 */
@Repository
@Mapper
public interface UserDao {

    @Select("select * from user where id =#{id}")
    User getById(@Param("id") int id);

    @Insert("insert into user(id,name) values(#{id},#{name})")
    int insert(User user);

    @Select("select * from user where username=#{username} and password=#{password}")
    User login(User user);
}
