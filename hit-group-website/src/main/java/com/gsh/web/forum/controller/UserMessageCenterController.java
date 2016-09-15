package com.gsh.web.forum.controller;

import com.gsh.domain.AddFriendApply;
import com.gsh.domain.Chat;
import com.gsh.domain.User;
import com.gsh.service.ChatService;
import com.gsh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserMessageCenterController
{

	@Autowired
	private UserService userService;

	@Autowired
	private ChatService chatService;

	@RequestMapping(method = RequestMethod.GET, value = "/message/chats")
	public String getReceiveChatsPage(Model model, HttpSession httpSession)
	{
		//登陆检测
		User user = (User)httpSession.getAttribute("user");
		if(user == null)
		{
			return "redirect:/";
		}
		else
		{
			List<Chat> chatList = userService.getAllChats(user.getUserId());

			//填充页面数据
			model.addAttribute("chat_list", chatList);

			return "message_chats";
		}
	}

	//  /message/read_chat?chat_id=x
	@RequestMapping(method = RequestMethod.GET, value = "/message/read_chat")
	public String doReadChat(@RequestParam("chat_id") int chatId, HttpSession httpSession)
	{
		User user = (User)httpSession.getAttribute("user");
		if(user == null)
		{
			return "redirect:/";
		}
		else
		{
			if(user.getUserId().equals(chatService.querySingleChat((long)chatId).getToUser().getUserId()))
			{
				chatService.readChat((long)chatId);
				return "redirect:/message/chats";
			}
			else
			{
				return "redirect:/";
			}
		}
	}

	//  /message/delete_chat?chat_id=x
	@RequestMapping(method = RequestMethod.GET, value = "/message/delete_chat")
	public String doDeleteChat(@RequestParam("chat_id") int chatId, HttpSession httpSession)
	{
		User user = (User)httpSession.getAttribute("user");
		if(user == null)
		{
			return "redirect:/";
		}
		else
		{
			if(user.getUserId().equals(chatService.querySingleChat((long)chatId).getToUser().getUserId()))
			{
				chatService.deleteChat((long)chatId);
				return "redirect:/message/chats";
			}
			else
			{
				return "redirect:/";
			}
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/message/friend_apply")
	public String getAddFriendApplyPage(Model model, HttpSession httpSession)
	{
		//登陆检测
		User user = (User)httpSession.getAttribute("user");
		if(user == null)
		{
			return "redirect:/";
		}
		else
		{
			List<AddFriendApply> addFriendApplyList = userService.getAddFriendApplies(user.getUserId());

			//填充页面数据
			model.addAttribute("apply_list", addFriendApplyList);

			//BUG补丁 由于好友申请表是后添加的,没有外键,Hibernate也就没有映射,这里得手动传入需要显示的用户名
			List<User> userList = new ArrayList<>();
			for(AddFriendApply afa : addFriendApplyList)
			{
				Long fromUserId = afa.getFromUserId();
				User fromUser = userService.queryUserById(fromUserId);
				userList.add(fromUser);
			}
			model.addAttribute("user_list", userList);

			return "message_apply";
		}
	}

	// /message/accept_friend?user_id=x
	@RequestMapping(method = RequestMethod.GET, value = "/message/accept_friend")
	public String acceptFriend(@RequestParam("user_id") int userId, HttpSession httpSession)
	{
		User user = (User)httpSession.getAttribute("user");
		if(user == null)
		{
			return "redirect:/";
		}
		else
		{
			userService.acceptAddFriendApply((long)userId, user.getUserId());
			return "redirect:/message/friend_apply";
		}
	}
	//	/message/decline_friend?user_id=x
	@RequestMapping(method = RequestMethod.GET, value = "/message/decline_friend")
	public String declineFriend(@RequestParam("user_id") int userId, HttpSession httpSession)
	{
		User user = (User)httpSession.getAttribute("user");
		if(user == null)
		{
			return "redirect:/";
		}
		else
		{
			userService.deleteAddFriendApply((long)userId, user.getUserId());
			return "redirect:/message/friend_apply";
		}
	}
}
