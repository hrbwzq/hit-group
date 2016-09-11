package com.gsh.test.service;

import com.gsh.domain.Chat;
import com.gsh.domain.User;
import com.gsh.service.ChatService;
import com.gsh.service.UserService;
import com.gsh.test.util.SpringContextUtil;
import com.gsh.web.forum.beans.ChatFormBean;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TestChatService
{

	public void makeTestUser()
	{
		TestUserService t = new TestUserService();
		t.testRegistUser();
	}

	@Test
	public void testMakeChat()
	{
		this.makeTestUser();
		ChatService chatService = (ChatService) SpringContextUtil.getContext().getBean("chatService");
		ChatFormBean chatFormBean = new ChatFormBean();
		chatFormBean.setContent("测试私信内容");
		chatService.makeChat(chatFormBean, 1L, 2L);
	}

	@Test
	public void testGetAllChats()
	{
		this.testMakeChat();
		UserService userService = (UserService)SpringContextUtil.getContext().getBean("userService");
		List<Chat> chatList1 = userService.getAllChats(1L);
		List<Chat> chatList2 = userService.getAllChats(2L);
		Assert.assertEquals(1, chatList2.size());
		Assert.assertEquals(0, chatList1.size());
	}

	@Test
	public void testReadAndDeleteChat()
	{
		this.testMakeChat();
		ChatService chatService = (ChatService) SpringContextUtil.getContext().getBean("chatService");
		List<Chat> chatList = chatService.getRecievedChats(2L);
		Assert.assertEquals(1, chatList.size());
		Chat chat = chatList.get(0);
		Long chatId = chat.getChatId();
		chatService.readChat(chatId);
		chatService.deleteChat(chatId);
	}
}
