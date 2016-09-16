package com.gsh.web.forum.controller;

import com.gsh.domain.User;
import com.gsh.persistence.AdvancedSearch;
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
public class FriendsCircleController
{

	@Autowired
	private UserService userService;

	@Autowired
	private AdvancedSearch advancedSearch;

	@RequestMapping(method = RequestMethod.GET, value = "/circle/friend_list")
	public String getFriendListPage(Model model, HttpSession httpSession)
	{
		User user = (User)httpSession.getAttribute("user");
		if(user != null)
		{
			List<User> userList = userService.getAllFriends(user.getUserId());
			model.addAttribute("friend_list", userList);

			return "circle_friend_list";
		}
		else
		{
			return "redirect:/login";
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/circle/delete_friend")
	public String deleteFriend(@RequestParam("user_id") int userId, HttpSession httpSession)
	{
		User user = (User)httpSession.getAttribute("user");
		if(user != null)
		{
			userService.deleteFriend(user.getUserId(), (long)userId);

			return "circle_friend_list";
		}
		else
		{
			return "redirect:/login";
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/circle/search_friend")
	public String getSearchFriendPage(HttpSession httpSession)
	{
		User user = (User)httpSession.getAttribute("user");
		if(user != null)
		{
			return "circle_search_friend";
		}
		else
		{
			return "redirect:/login";
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/circle/search_friend")
	public String doSearchFriend(@RequestParam("pattern") String pattern, Model model, HttpSession httpSession)
	{
		User user = (User)httpSession.getAttribute("user");
		if(user != null)
		{

			List<User> userList = advancedSearch.userFuzzyQuery(pattern);
			model.addAttribute("user_list", userList);

			return "circle_search_friend";
		}
		else
		{
			return "redirect:/login";
		}
	}
}
