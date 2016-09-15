package com.gsh.web.forum.beans;

import org.springframework.stereotype.Component;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Component
public class UserProfileFormBean
{
	private String sex;

	@Size(max = 20)
	private String realname;

	private Integer age;

	@Size(max = 20)
	private String major;

	@Size(max = 100)
	private String address;

	@Size(max = 20)
	@Pattern(regexp = "^[0-9]+$")
	private String qq;

	@Size(min = 1, max = 30)
	@Pattern(regexp = "^[0-9]+$")
	private String phone;

	public UserProfileFormBean()
	{
	}

	public String getSex()
	{
		return sex;
	}

	public void setSex(String sex)
	{
		this.sex = sex;
	}

	public String getRealname()
	{
		return realname;
	}

	public void setRealname(String realname)
	{
		this.realname = realname;
	}

	public Integer getAge()
	{
		return age;
	}

	public void setAge(Integer age)
	{
		this.age = age;
	}

	public String getMajor()
	{
		return major;
	}

	public void setMajor(String major)
	{
		this.major = major;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getQq()
	{
		return qq;
	}

	public void setQq(String qq)
	{
		this.qq = qq;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}
}
