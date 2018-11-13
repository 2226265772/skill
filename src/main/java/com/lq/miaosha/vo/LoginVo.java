package com.lq.miaosha.vo;

import com.lq.miaosha.validator.IsMobile;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class LoginVo {



	@NotNull
	@IsMobile
	private String telephone;
	@NotNull
	@Length(min=32)
	private String password;

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Override
	public String toString() {
		return "LoginVo{" +
				"telephone='" + telephone + '\'' +
				", password='" + password + '\'' +
				'}';
	}
}
