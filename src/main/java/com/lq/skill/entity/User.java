package com.lq.skill.entity;

import com.lq.skill.vilidator.Mobile;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    @NotBlank
    private String password;
    @Mobile
    private String telephone;
    private String mail;

    private Integer deptId;

    private Integer status;

    private String remark;

    private String operater;

    private Date operateTime;

    private String operateIp;
}
