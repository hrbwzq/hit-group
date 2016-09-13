package com.gsh.web.news.controller;

import com.gsh.domain.News;
import com.gsh.domain.NewsReply;
import com.gsh.domain.User;
import com.gsh.service.NewsService;
import com.gsh.service.UserService;
import com.gsh.web.news.beans.NewsReplyFormBean;
import com.gsh.web.util.KeywordReplaceUtil;
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
public class NewsPageController
{

	@Autowired
	private NewsService newsService;

	@Autowired
	private UserService userService;

	//填充页面需要的信息
	private void fillNewsPageContent(Model model, int newsId)
	{
		//根据ID查询新闻
		News news = newsService.getNewsById((long)newsId);
		model.addAttribute("news", news);

		//根据新闻ID查询所有新闻评论
		List<NewsReply> newsReplyList = newsService.getNewsReplyByNewsId((long)newsId);
		model.addAttribute("newsReplyList", newsReplyList);

		//放入NewsReplyFormBean
		model.addAttribute(new NewsReplyFormBean());
	}

	//获得特定新闻页 /news?id=
	@RequestMapping(value = "/news", method = RequestMethod.GET)
	public String getNewsPage(@RequestParam("id") int queryId, Model model)
	{
		this.fillNewsPageContent(model, queryId);
		return "news";
	}

	//处理新闻评论表单提交
	@RequestMapping(value = "/news", method = RequestMethod.POST)
	public String doReplyNews(@Validated NewsReplyFormBean newsReplyFormBean, @RequestParam("newsId") int newsId, BindingResult bindingResult, Model model, HttpSession httpSession)
	{
		//表单校验出错
		if(bindingResult.hasErrors())
		{
			this.fillNewsPageContent(model, newsId);
			return "news";
		}
		//表单校验成功
		else
		{
			User user = (User)httpSession.getAttribute("user");
			//Session登陆校验失败
			if(user == null)
			{
				this.fillNewsPageContent(model, newsId);
				model.addAttribute("replyError", "请先登陆!");
				return "news";
			}
			//登陆校验成功
			else
			{
				newsReplyFormBean.setContent(KeywordReplaceUtil.HTMLTageFilter(newsReplyFormBean.getContent()));

				//将评论信息插入数据库
				userService.addNewsReply(newsReplyFormBean, (long)newsId, user.getUserId());

				//组装重定向URL
				return  "redirect:news?id=" + newsId;
			}
		}
	}
}
