package com.gsh.web.forum.controller;

import com.gsh.domain.AddFriendApply;
import com.gsh.domain.Chat;
import com.gsh.domain.User;
import com.gsh.service.ChatService;
import com.gsh.service.UserService;
import com.gsh.web.forum.beans.ChatFormBean;
import com.gsh.web.util.KeywordReplaceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

	@RequestMapping(method = RequestMethod.GET, value = "/message/send_chat")
	public String getSendChatPage(@RequestParam("to_user_id") int toUserId, Model model)
	{
		User toUser = userService.queryUserById((long)toUserId);
		model.addAttribute("to_user", toUser);

		return "send_chat";
	}

	//Ajax POST /message/send_chat
	@RequestMapping(method = RequestMethod.POST, value = "/message/send_chat")
	@ResponseBody
	public void doSendChat(@RequestParam("to_user_id") int toUserId,
	                         @RequestParam("content") String content,
	                         HttpSession httpSession)
	{
		User user = (User)httpSession.getAttribute("user");
		if(user != null)
		{
			Long fromUserId = user.getUserId();
			ChatFormBean chatFormBean = new ChatFormBean();
			if(content.length() <= 50)
			{
				chatFormBean.setContent(KeywordReplaceUtil.HTMLTageFilter(content));
				chatService.makeChat(chatFormBean, fromUserId, (long)toUserId);
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

	//Ajax请求 发出好友申请
	// /message/make_friend_apply?to_user_id=x
	@RequestMapping(method = RequestMethod.GET, value = "/message/make_friend_apply", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String makeAddFriendApply(@RequestParam("to_user_id") int userId, HttpSession httpSession)
	{
		User user = (User)httpSession.getAttribute("user");
		if(user != null)
		{
			Long fromUserId = user.getUserId();
			if(!userService.ifApplyExist(fromUserId, (long)userId))
			{
				userService.makeAddFriendApply(fromUserId, (long)userId);
				return "添加成功";
			}
			else
			{
				return "您以发出申请";
			}
		}
		else
		{
			return "请先登陆";
		}
	}

	//Ajax请求 关注某用户
	//  /message/watch?to_user_id=x
	@RequestMapping(method = RequestMethod.GET, value = "/message/watch", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String watchUser(@RequestParam("to_user_id") int userId, HttpSession httpSession)
	{
		User user = (User)httpSession.getAttribute("user");
		if(user != null)
		{
			//我的ID-关注者
			Long fromUserId = user.getUserId();
			//获取被关注者的关注者列表
			List<User> watchers = userService.getAllWatchers((long)userId);
			//如果我在列表中-说明我已经关注过
			if(watchers.contains(user))
			{
				return "您已经关注TA啦";
			}
			//不在则关注成功
			else
			{
				userService.watchUser(fromUserId, (long)userId);
				return "关注成功";
			}
		}
		else
		{
			return "请先登陆";
		}
	}
}
