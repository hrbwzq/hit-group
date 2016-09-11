package com.gsh.web.news.controller;

import com.gsh.domain.News;
import com.gsh.service.NewsService;
import com.gsh.web.consts.PageSize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController
{
	@Autowired
	private NewsService newsService;

	@RequestMapping(method = RequestMethod.GET, value = "/")
	public String getIndexPage(Model model)
	{
		//获得新闻列表
		//由于暂时没有添加新闻分类的需求,这里暂时写死(此处代码暂时无效,视图模板暂时写死)
		List<News> hotNews = newsService.getLimitNewsByCategoryName("热点新闻", PageSize.INDEX_NEWS_PAGE_SIZE);
		List<News> hotPeople = newsService.getLimitNewsByCategoryName("热点人物", PageSize.INDEX_NEWS_PAGE_SIZE);
		List<News> hotTopic = newsService.getLimitNewsByCategoryName("热点话题", PageSize.INDEX_NEWS_PAGE_SIZE);

		//暂时写死的分类(此处代码暂时无效,视图模板暂时写死)
		Map<String, String> categories = new HashMap<>();
		categories.put("hotNews", "热点新闻");
		categories.put("hotPeople", "热点人物");
		categories.put("hotTopic", "热点话题");

		//对视图模型添加需要渲染的对象
		model.addAttribute("hotNews", hotNews);
		model.addAttribute("hotPeople", hotPeople);
		model.addAttribute("hotTopic", hotTopic);

		model.addAttribute("categories", categories);

		return "index";
	}
}
