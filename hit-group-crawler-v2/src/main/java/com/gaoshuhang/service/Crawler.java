package com.gaoshuhang.service;

import com.gaoshuhang.domain.News;
import com.gaoshuhang.globals.LogVars;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

/**
 * 爬虫核心抓取类
 *
 * @author gaoshuhang
 */
public class Crawler
{
	private List<News> newsList = new ArrayList<>();
	private String baseURL;
	private String category;

	/**
	 * 构造函数
	 *
	 * @param baseURL  列表文档URL
	 * @param category 分类名
	 */
	public Crawler(String baseURL, String category)
	{
		this.baseURL = baseURL;
		this.category = category;
	}

	/**
	 * 抓取并解析,结果保存在ArrayList中
	 *
	 * @throws IOException IO错误返回上级处理
	 */
	public void runFetch() throws IOException
	{
		String userAgent = "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:50.0) Gecko/20100101 Firefox/50.0";


		//抓取列表文档
		Connection listConnection = Jsoup.connect(baseURL);
		Document document = listConnection
				.userAgent(userAgent)
				.get();
		Element listDiv = document.getElementsByClass("sidelist7").get(0);
		Element ul = listDiv.child(0);
		Elements lis = ul.children();
		for (Element li : lis)
		{

			//抓取内层新闻文档
			Element a2 = li.child(1);
			String link = "http://today.hit.edu.cn" + a2.attr("href");
			try
			{
				Connection innerConnection = Jsoup.connect(link);
				Document newsDoc = innerConnection
						.userAgent(userAgent)
						.get();

				//组织数据建立领域对象
				String title = newsDoc.title();
				String articleId;
				if (this.category.equals("热点新闻") || this.category.equals("热点话题"))
				{
					articleId = "text";
				}
				else
				{
					articleId = "articleText";
				}
				Element textDiv = newsDoc.getElementById(articleId);

				//链接修复
				Elements imgs = textDiv.select("img");
				for (Element img : imgs)
				{
					String originSrc = img.attr("src");
					img.attr("src", "http://today.hit.edu.cn" + originSrc);
				}

				String content = textDiv.html();//正文

				News news = new News(title, content, this.category);
				this.newsList.add(news);
				LogVars.GLOBAL_LOGGER.info("抓取成功 URL=" + link);
			}
			catch (NullPointerException e)
			{
				//抓取失败
				LogVars.GLOBAL_LOGGER.info("抓取失败,解析错误 URL=" + link);
			}
			catch (SocketTimeoutException e)
			{
				//内层连接超时
				LogVars.GLOBAL_LOGGER.info("抓取失败,内层连接超时 URL=" + link);
			}
			catch (IOException e)
			{
				//内层IO错误
				LogVars.GLOBAL_LOGGER.info("抓取失败,内层IO错误 URL=" + link);
			}
		}

	}

	/**
	 * 获得抓取到的新闻列表
	 *
	 * @return 新闻列表
	 */
	public List<News> getNewsList()
	{
		return this.newsList;
	}
}
