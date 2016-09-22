package com.gsh.web.forum.controller;

import com.gsh.domain.Topic;
import com.gsh.domain.User;
import com.gsh.service.UserService;
import com.gsh.web.forum.beans.UserProfileFormBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
public class UserPageController
{
	@Autowired
	private UserService userService;

	//访问用户个人主页
	@RequestMapping(method = RequestMethod.GET, value = "/user_page")
	public String getUserPage(@RequestParam("user_id") int userId, Model model)
	{
		User user = userService.queryUserById((long) userId);
		model.addAttribute("selected_user", user);

		List<Topic> topicList = userService.getUserRecentTopic((long)userId);
		model.addAttribute("topic_list", topicList);

		return "user_page";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/edit_user_profile")
	public String getEditUserProfilePage(@RequestParam("user_id") int userId, Model model, HttpSession httpSession)
	{
		//登陆验证
		User user = (User)httpSession.getAttribute("user");
		if(user == null || userId != user.getUserId().intValue())
		{
			return "redirect:/";
		}
		else
		{

			User selectedUser = userService.queryUserById((long) userId);
			model.addAttribute("selected_user", selectedUser);

			UserProfileFormBean userProfileFormBean = new UserProfileFormBean();
			model.addAttribute("user_profile_form_bean", userProfileFormBean);

			return "edit_user_profile";
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/edit_user_profile")
	public String doEditUserProfile(@Validated UserProfileFormBean userProfileFormBean, Model model, HttpSession httpSession)
	{
		//登陆校验
		User user = (User)httpSession.getAttribute("user");
		if(user != null)
		{
			userService.updateUserOptionalInfo(userProfileFormBean, user.getUserId());
			return "redirect:/user_page?user_id=" + user.getUserId();
		}
		else
		{
			return "redirect:/";
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/edit_user_thumbnail")
	public String getEditThumbnailPage(@RequestParam("user_id") int userId, Model model, HttpSession httpSession)
	{
		//登陆验证
		User user = (User)httpSession.getAttribute("user");
		if(user == null || userId != user.getUserId().intValue())
		{
			return "redirect:/";
		}
		else
		{

			User selectedUser = userService.queryUserById((long) userId);
			model.addAttribute("selected_user", selectedUser);

			return "edit_user_thumbnail";
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/edit_user_thumbnail")
	public String doEditThumbnail(@RequestParam(value = "file", required = false) MultipartFile multipartFile, Model model, HttpServletRequest httpServletRequest, HttpSession httpSession)
	{
		//登陆校验
		User user = (User)httpSession.getAttribute("user");
		if(user != null)
		{
			if(!multipartFile.getContentType().equalsIgnoreCase("image/png") || multipartFile.getSize() > 2097152)
			{
				model.addAttribute("edit_user_thumbnail_error", "上传的文件不符合要求");

				model.addAttribute("selected_user", user);

				return "edit_user_thumbnail";
			}
			String basePath = httpServletRequest.getSession().getServletContext().getRealPath("/resources/thumbnails");
			String uuid = UUID.randomUUID().toString();
			File targetFile = new File(basePath, uuid + ".png");
			try
			{
				if(!targetFile.exists())
				{

					boolean createResult = targetFile.mkdirs();
					if(!createResult)
					{
						throw new RuntimeException("创建目标文件失败,请检察文件系统权限");
					}
				}
				multipartFile.transferTo(targetFile);
			}
			catch (IOException e)
			{
				e.printStackTrace();
				throw new RuntimeException("上传thumbnail文件IO出错");
			}
			String baseURL = "/resources/thumbnails/";
			userService.updateUserThumbnail(baseURL + targetFile.getName(), user.getUserId());
			return "redirect:/edit_user_thumbnail?user_id=" + user.getUserId();
		}
		else
		{
			return "redirect:/";
		}
	}
}
