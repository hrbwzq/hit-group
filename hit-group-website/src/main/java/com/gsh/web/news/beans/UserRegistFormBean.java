package com.gsh.web.news.beans;

import com.gsh.web.util.RegexUtil;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Component
public class UserRegistFormBean
{
	@Size(min = 5, max = 15, message = "您的用户名长度不符合要求")
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "用户名只能包含数字和字母")
	private String username;

	@Size(min = 15, max = 20, message = "密码长度应在10-20位")
	private String password;

	@Pattern(regexp = RegexUtil.EMAIL_REGEX, message = "您输入的邮箱格式不符合规范")
	private String email;

	public UserRegistFormBean()
	{
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}
}
