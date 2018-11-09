package com.lq.skill;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动入口
 */
@SpringBootApplication
//@MapperScan("com.lq.skill.dao")
public class SkillApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkillApplication.class, args);
	}
}
