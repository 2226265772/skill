package com.lq.skill.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Auther: LQ
 * @Date: 2018/11/6 23:02
 * @Description:user实体
 */
@Data
public class User {
    private Integer id;

    private String username;

    private String password;

    private String telephone;

    private String mail;

    private Integer deptId;

    private Integer status;

    private String remark;

    private String operater;

    private Date operateTime;

    private String operateIp;
}
