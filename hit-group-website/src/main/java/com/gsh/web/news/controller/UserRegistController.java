package com.gsh.web.news.controller;

import com.gsh.service.UserService;
import com.gsh.web.news.beans.UserRegistFormBean;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserRegistController
{

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET, value = "/regist")
	public String getRegistPage(Model model)
	{
		model.addAttribute(new UserRegistFormBean());
		return "regist";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/regist")
	public String doRegist(Model model, @Validated UserRegistFormBean userRegistFormBean, BindingResult bindingResult)
	{
		//表单验证出错
		if(bindingResult.hasErrors())
		{
			model.addAttribute(userRegistFormBean);
			return "regist";
		}
		//表单验证成功
		else
		{
			//用户名已存在
			if(userService.queryUserByUsername(userRegistFormBean.getUsername()) != null)
			{
				model.addAttribute(userRegistFormBean);
				model.addAttribute("registError", "用户名已存在");
				return "regist";
			}
			//注册成功
			else
			{
				model.addAttribute("username", userRegistFormBean.getUsername());
				userRegistFormBean.setPassword(new String(DigestUtils.md5(userRegistFormBean.getPassword())));
				userService.regist(userRegistFormBean);
				return "regist_success";
			}
		}
	}
}
