package com.gaoshuhang.service;

import com.gaoshuhang.domain.News;
import com.gaoshuhang.globals.LogVars;
import com.gaoshuhang.globals.POSTVars;
import com.gaoshuhang.utils.URLEncodeUtil;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * 向网站服务器发送数据
 * @author gaoshuhang
 */
public class Poster
{
	List<News> newsList = null;

	public Poster(List<News> newsList)
	{
		this.newsList = newsList;
	}

	/**
	 * 发起POST请求
	 * @throws IOException POST请求时网络出错
	 */
	public void doPost() throws IOException
	{
		for(News news : this.newsList)
		{
			//编码数据
			String title = URLEncodeUtil.encodeURL(news.getTitle());
			String content = URLEncodeUtil.encodeURL(news.getContent());
			String categoryName = news.getCategory();

			//组织表单
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("auid=");
			stringBuilder.append(POSTVars.AUID);
			stringBuilder.append("&pwd=");
			stringBuilder.append(POSTVars.PWD);
			stringBuilder.append("&title=");
			stringBuilder.append(title);
			stringBuilder.append("&content=");
			stringBuilder.append(content);
			stringBuilder.append("&categoryName=");
			stringBuilder.append(categoryName);
			String postData = stringBuilder.toString();

			//准备发送数据
			URL url = new URL(POSTVars.POST_URL);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.connect();

			//向输出流写入数据
			OutputStream outputStream = conn.getOutputStream();
			outputStream.write(postData.getBytes());
			outputStream.flush();
			outputStream.close();

			//读取返回状态码
			LogVars.GLOBAL_LOGGER.info("响应状态码" + conn.getResponseCode());

		}
	}
}
