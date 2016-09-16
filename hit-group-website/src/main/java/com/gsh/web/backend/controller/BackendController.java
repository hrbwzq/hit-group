package com.gsh.web.backend.controller;

import com.gsh.domain.Privilege;
import com.gsh.domain.User;
import com.gsh.service.NoticeService;
import com.gsh.service.UserService;
import com.gsh.web.backend.beans.NoticeFormBean;
import com.gsh.web.backend.beans.UserPageBean;
import com.gsh.web.consts.PageSize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class BackendController
{

	@Autowired
	private UserService userService;

	@Autowired
	private NoticeService noticeService;

	@RequestMapping(method = RequestMethod.GET, value = "/backend/user_manage")
	public String getUserListPage(@RequestParam(value = "page", required = false) Integer page, Model model, HttpSession httpSession)
	{
		User user = (User)httpSession.getAttribute("user");
		if(user != null)
		{
			List<Privilege> privilegeList = userService.getAllPrivileges(user.getUserId());
			for(Privilege p : privilegeList)
			{
				if(p.getPrivilege().equals("administrator"))
				{
					if(page == null)
					{
						page = 1;
					}
					UserPageBean userPageBean = userService.queryUsersByPage(page, PageSize.BACKEND_USER_PAGE_SIZE);
					model.addAttribute("user_page_bean", userPageBean);
					return "backend_userlist";
				}
			}
			return "redirect:/";
		}
		else
		{
			return "redirect:/";
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/backend/notice")
	public String getPublishNoticePage(Model model, HttpSession httpSession)
	{
		User user = (User)httpSession.getAttribute("user");
		if(user != null)
		{
			List<Privilege> privilegeList = userService.getAllPrivileges(user.getUserId());
			for(Privilege p : privilegeList)
			{
				if(p.getPrivilege().equals("administrator"))
				{
					NoticeFormBean noticeFormBean = new NoticeFormBean();
					model.addAttribute("noticeFormBean", noticeFormBean);
					return "backend_notice";
				}
			}
			return "redirect:/";
		}
		else
		{
			return "redirect:/";
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/backend/notice")
	public String doPublishNotice(@Validated NoticeFormBean noticeFormBean,
	                              BindingResult bindingResult,
	                              Model model,
	                              HttpSession httpSession)
	{
		User user = (User)httpSession.getAttribute("user");
		if(user != null)
		{
			noticeService.createNewNotice(noticeFormBean, user.getUserId());
			NoticeFormBean newNoticeFormBean = new NoticeFormBean();
			model.addAttribute("noticeFormBean", newNoticeFormBean);

			if(!bindingResult.hasErrors())
			{
				model.addAttribute("message", "公告发布成功");
				return "backend_notice";
			}
			else
			{
				model.addAttribute("message", "公告发布失败");
				return "backend_notice";
			}
		}
		else
		{
			return "redirect:/";
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/backend/ban")
	public String doBanUser(@RequestParam("user_id") int userId, HttpSession httpSession)
	{
		User user = (User)httpSession.getAttribute("user");
		if(user != null)
		{
			List<Privilege> privilegeList = userService.getAllPrivileges(user.getUserId());
			for(Privilege p : privilegeList)
			{
				if(p.getPrivilege().equals("administrator"))
				{
					userService.banUser((long)userId);
					return "redirect:/backend/user_manage";
				}
			}
			return "redirect:/";
		}
		else
		{
			return "redirect:/";
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/backend/release")
	public String doReleaseUser(@RequestParam("user_id") int userId, HttpSession httpSession)
	{
		User user = (User)httpSession.getAttribute("user");
		if(user != null)
		{
			List<Privilege> privilegeList = userService.getAllPrivileges(user.getUserId());
			for(Privilege p : privilegeList)
			{
				if(p.getPrivilege().equals("administrator"))
				{
					userService.releaseUser((long)userId);
					return "redirect:/backend/user_manage";
				}
			}
			return "redirect:/";
		}
		else
		{
			return "redirect:/";
		}
	}
}
