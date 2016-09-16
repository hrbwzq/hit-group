package com.gsh.web.news.controller;

import com.gsh.domain.User;
import com.gsh.service.UserService;
import com.gsh.web.news.beans.UserLoginFormBean;
import com.gsh.web.util.RandVerCodeValueGenerator;
import com.gsh.web.util.VerCodeGenerator;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

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
	public String doLogin(Model model, @Validated UserLoginFormBean userLoginFormBean, @RequestParam("vercode") String vercode, BindingResult bindingResult, HttpSession httpSession)
	{
		//表单验证出错
		if(bindingResult.hasErrors())
		{
			model.addAttribute(userLoginFormBean);
			return "login";
		}
		else
		{
			userLoginFormBean.setPassword(new String(DigestUtils.md5(userLoginFormBean.getPassword())));
			User user = userService.login(userLoginFormBean);
			//验证码认证失败
			if(!vercode.equals(httpSession.getAttribute("vercodeValue")))
			{
				model.addAttribute(userLoginFormBean);
				model.addAttribute("loginError", "验证码错误");
				return "login";
			}
			else
			{
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
					if(user.getBanned() == 1)
					{
						model.addAttribute(userLoginFormBean);
						model.addAttribute("loginError", "您的账户已被封禁");
						return "login";
					}
					else
					{
						httpSession.setAttribute("user", user);
						return "redirect:/";
					}

				}
			}
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/logout")
	public String doLogout(HttpSession httpSession)
	{
		httpSession.removeAttribute("user");
		return "redirect:/";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/vercode")
	@ResponseBody
	public void getVerCode(HttpServletResponse httpServletResponse, HttpSession httpSession)
	{

		String randString = RandVerCodeValueGenerator.getRandString(4);

		httpSession.setAttribute("vercodeValue", randString);

		VerCodeGenerator verCodeGenerator = new VerCodeGenerator(100, 50, randString, 20);
		BufferedImage bufferedImage = verCodeGenerator.generate();

		try
		{
			OutputStream outputStream = httpServletResponse.getOutputStream();
			ImageIO.write(bufferedImage, "png", outputStream);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

	}
}
