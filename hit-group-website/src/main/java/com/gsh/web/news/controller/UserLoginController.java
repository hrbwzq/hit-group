package com.gsh.web.news.controller;

import com.gsh.domain.User;
import com.gsh.service.UserService;
import com.gsh.web.news.beans.UserLoginFormBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class UserLoginController
{
	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET, value = "/login")
	public String getLoginPage(Model model)
	{
		model.addAttribute(new UserLoginFormBean());
		return "login";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/login")
	public String doLogin(Model model, @Validated UserLoginFormBean userLoginFormBean, BindingResult bindingResult, HttpSession httpSession)
	{
		//表单验证出错
		if(bindingResult.hasErrors())
		{
			model.addAttribute(userLoginFormBean);
			return "login";
		}
		else
		{
			User user = userService.login(userLoginFormBean);
			//登录失败
			if(user == null)
			{
				model.addAttribute(userLoginFormBean);
				model.addAttribute("loginError", "用户名或密码错误");
				return "login";
			}
			//登陆成功
			else
			{
				httpSession.setAttribute("user", user);
				return "redirect:/";
			}
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/logout")
	public String doLogout(HttpSession httpSession)
	{
		httpSession.removeAttribute("user");
		return "redirect:/";
	}
}
