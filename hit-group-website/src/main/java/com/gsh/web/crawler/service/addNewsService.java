package com.gsh.web.crawler.service;

import com.gsh.service.CrawlerService;
import com.gsh.web.crawler.beans.NewsCrawlerBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Controller
public class addNewsService
{
	@Autowired
	private CrawlerService crawlerService;

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/crawler/add_news")
	public void addNews(@RequestParam("auid") String auid,
	                    @RequestParam("pwd") String pwd,
	                    @RequestParam("title") String title,
	                    @RequestParam("content") String content,
	                    @RequestParam("categoryName") String categoryName,
	                    HttpServletRequest httpServletRequest)
	{
		String confPath = httpServletRequest.getServletContext().getRealPath("/WEB-INF/crawler.properties");
		String realAuid = "";
		String realPwd = "";
		try
		{
			InputStream inputStream = new FileInputStream(confPath);
			Properties properties = new Properties();
			properties.load(inputStream);
			realAuid = properties.getProperty("auid");
			realPwd = properties.getProperty("pwd");
		}
		catch (IOException e)
		{
			throw new RuntimeException("载入爬虫API权限配置文件出错,请检查crawler.properties路径");
		}
		if(realAuid.equals(auid) && realPwd.equals(pwd))
		{
			NewsCrawlerBean newsCrawlerBean = new NewsCrawlerBean(title, content, categoryName);
			crawlerService.addNews(newsCrawlerBean);
		}
	}
}
