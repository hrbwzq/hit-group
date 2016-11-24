package pa1;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class webCrawl {

	String news_url = "http://today.hit.edu.cn/classList/2.html";
	String people_url = "http://today.hit.edu.cn/classList/32_55.html";
	String hot_url = "http://today.hit.edu.cn/phb/0.htm";
	// Document people_doc = Jsoup.connect(people_url).get();
	// Document news_doc = Jsoup.connect(news_url).get();
	// Document hot_doc = Jsoup.connect(hot_url).get();

	HashSet<String> news = new HashSet<String>();
	HashSet<String> people = new HashSet<String>();
	HashSet<String> hot = new HashSet<String>();

	// downloadPage1(people_url,people,1);
	// downloadPage1(news_url,news,2);
	// downloadPage1(hot_url,hot,2);

	public void connect() throws Exception {
		Document people_doc = Jsoup.connect(people_url).get();
		Document news_doc = Jsoup.connect(news_url).get();
		Document hot_doc = Jsoup.connect(hot_url).get();
		downloadPage1(people_url, people, 1);
		downloadPage1(news_url, news, 2);
		downloadPage1(hot_url, hot, 2);
	}

	public static void downloadPage1(String str, HashSet<String> hss, int a) {
		// 下载网页
		try {
			URL pageUrl = new URL(str);
			URLConnection uc = pageUrl.openConnection(); // 打开连接
			// String path = pageUrl.getPath();//获取网页的相对路径

			InputStream is = uc.getInputStream();

			BufferedReader reader = new BufferedReader(new InputStreamReader(is, "gbk"));
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}

			String result = sb.toString();
			if (a == 1) {

			}

			String people_pattern = "(/articles/[0-9]*/[0-9]*-[0-9]*/[0-9]*.htm)";
			String news_pattern = "(/news/[0-9]*/[0-9]*-[0-9]*/[0-9a-zA-Z]*.htm)";
			Pattern r = Pattern.compile(people_pattern);
			Pattern r1 = Pattern.compile(news_pattern);

			if (a == 1) {
				Matcher m = r.matcher(result);

				int i = 0;
				while (m.find()) {
					String g = m.group(1);
					g = g.replaceAll(g, "http://today.hit.edu.cn" + g);
					if (i < 5) {
						hss.add(g);
						i++;
					}
				}
			} else if (a == 2) {
				Matcher m = r1.matcher(result);

				int i = 0;
				while (m.find()) {
					String g = m.group(1);
					g = g.replaceAll(g, "http://today.hit.edu.cn" + g);
					if (i < 5) {
						hss.add(g);
						i++;
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}