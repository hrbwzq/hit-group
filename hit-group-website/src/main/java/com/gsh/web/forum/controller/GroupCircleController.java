package com.gsh.web.forum.controller;

import com.gsh.domain.Group;
import com.gsh.domain.User;
import com.gsh.persistence.AdvancedSearch;
import com.gsh.service.GroupService;
import com.gsh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class GroupCircleController
{
	@Autowired
	private UserService userService;

	@Autowired
	private GroupService groupService;

	@Autowired
	private AdvancedSearch advancedSearch;

	@RequestMapping(method = RequestMethod.GET, value = "/circle/group_list")
	public String getGroupListPage(Model model, HttpSession httpSession)
	{
		User user = (User)httpSession.getAttribute("user");
		if(user != null)
		{
			List<Group> groupList = userService.getJoinedGroups(user.getUserId());
			model.addAttribute("group_list", groupList);

			return "circle_group_list";
		}
		else
		{
			return "redirect:/login";
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/circle/join_group")
	public String joinGroup(@RequestParam("group_id") int groupId, HttpSession httpSession)
	{
		User user = (User)httpSession.getAttribute("user");
		if(user != null)
		{
			userService.joinGroup(user.getUserId(), (long)groupId);
			String resultStr = "redirect:/forum?page=1&group_id=" + groupId;
			return resultStr;
		}
		else
		{
			return "redirect:/login";
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/circle/exit_group")
	public String exitGroup(@RequestParam("group_id") int groupId, HttpSession httpSession)
	{
		User user = (User)httpSession.getAttribute("user");
		if(user != null)
		{
			userService.exitGroup(user.getUserId(), (long)groupId);

			return "redirect:/circle/group_list";
		}
		else
		{
			return "redirect:/login";
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/circle/search_group")
	public String getSearchGroupPage(HttpSession httpSession)
	{
		User user = (User)httpSession.getAttribute("user");
		if(user != null)
		{
			return "circle_search_group";
		}
		else
		{
			return "redirect:/login";
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/circle/search_group")
	public String doSearchGroup(@RequestParam("pattern") String pattern, Model model, HttpSession httpSession)
	{
		User user = (User)httpSession.getAttribute("user");
		if(user != null)
		{

			List<Group> groupList = advancedSearch.groupFuzzyQuery(pattern);
			model.addAttribute("group_list", groupList);

			return "circle_search_group";
		}
		else
		{
			return "redirect:/login";
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/circle/create_group")
	public String getCreateCirclePage(HttpSession httpSession)
	{
		User user = (User)httpSession.getAttribute("user");
		if(user != null)
		{
			return "circle_create_group";
		}
		else
		{
			return "redirect:/login";
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/circle/create_group")
	public String doCreateCircle(@RequestParam("group_name") String groupName, HttpSession httpSession)
	{
		User user = (User)httpSession.getAttribute("user");
		if(user != null)
		{
			this.groupService.createGroup(groupName);
			Group group = this.groupService.getGroupByName(groupName);
			this.userService.joinGroup(user.getUserId(), group.getGroupId());
			String resultStr = "redirect:/forum?page=1&group_id=" + group.getGroupId();
			return resultStr;
		}
		else
		{
			return "redirect:/login";
		}
	}
}
