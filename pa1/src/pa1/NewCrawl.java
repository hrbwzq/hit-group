package pa1;

//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
import java.io.*;
import java.util.Properties; 
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

public class NewCrawl {
//  public LinkedList ImgUrls=new LinkedList();//用于存放图片URL
//  public LinkedList linkurls=new LinkedList();//用于存放url链接

  public static void main(String[] args) throws Exception{  
	
	System.setProperty("http.proxyHost", "127.0.0.1");
	System.setProperty("http.proxyPort", "8087");
    NewCrawl newCl = new NewCrawl(); 
    
	webCrawl crawl=new webCrawl();
	crawl.connect();
    Properties prop = new Properties(); 
    
    FileInputStream fis = new FileInputStream("crawl.properties");//属性文件流
    prop.load(fis);
    String author="";
    String pwd="";
    String URL;
    

    author=prop.getProperty("auid");
    pwd=prop.getProperty("pwd");
    URL=prop.getProperty("url");
    

  
    Iterator iterator = crawl.news.iterator();
    int a=0;
	while(iterator.hasNext()){
		String name = (String)iterator.next();
		a=1;
		Document doc = Jsoup.connect(name).get();
		String content=newCl.downloadPage(name,a);
		String title=newCl.getnewTitle(doc);//获取新闻标题
		String db="热点新闻";
		
	    NewCrawl.doPost(author,pwd,title,content,db,URL);
		System.out.println(title);
	}
	
	iterator = crawl.people.iterator();
	while(iterator.hasNext()){
		String name = (String)iterator.next();
		a=2;
		Document doc = Jsoup.connect(name).get();
		String content=newCl.downloadPage(name,a);
		String title=newCl.getnewTitle(doc);//获取新闻标题
		String db="热点人物";
		
	    NewCrawl.doPost(author,pwd,title,content,db,URL);
		System.out.println(title);
	}
	
	iterator = crawl.hot.iterator();
	while(iterator.hasNext()){
		a=3;
		String name = (String)iterator.next();
		Document doc = Jsoup.connect(name).get();
		String content=newCl.downloadPage(name,a);
		String title=newCl.getnewTitle(doc);//获取新闻标题
		String db="热点话题";
		
	    NewCrawl.doPost(author,pwd,title,content,db,URL);
		System.out.println(title);
	}
	


   }  
   
   public String getnewTitle(Document doc){
    //获取网页的标题
    String title=doc.title();
    return title;
   }
   
   public String getNewtext(Document doc){
    //获取新闻内容
            String text = doc.select("div.text").text(); 
            text=text.replace(Jsoup.parse("   ").text(), "");
            return text;
   }
   
   public String getTime(Document doc){
    //获取新闻发布的时间
    String time=null;
    Elements element=doc.select("strong.timeSummary");//此处是根据百度新闻的网页形式解析的新闻时间
           String rex="^(((20\\d{2})-(\\d{2})-(\\d{2}))) ((\\d{2}):(\\d{2}):(\\d{2}))$";//正则表达式用于匹配时间
           Pattern pattern=Pattern.compile(rex);
           for(Element el: element){
             String content = el.text();
             Matcher matcher=pattern.matcher(content);
             if(matcher.matches()){
             time=content;
             //System.out.println("新闻发布时间为："+content);
             }
           }
           return time;
   }
   
   public static String downloadPage(String str ,int a){
    //下载网页
    String filestr="f:/test";//设置网页的保存地址
    try {
            URL pageUrl = new URL(str);
            URLConnection uc =pageUrl.openConnection(); // 打开连接     
            String path = pageUrl.getPath();//获取网页的相对路径

     InputStream is = uc.getInputStream();

     BufferedReader reader = new BufferedReader(new InputStreamReader(is, "gbk"));      
     StringBuilder sb = new StringBuilder();  
     String line=null;
     while ((line = reader.readLine()) != null) {      
         sb.append(line + "\n");      
     }      
 
     String result = sb.toString();


     // 创建 Pattern 对象
     String pattern="<img.*src=\"(.*\\.(png|gif|jpg))\"";
     Pattern r = Pattern.compile(pattern);

     // 现在创建 matcher 对象
     Matcher m = r.matcher(result);
     while (m.find( )) {

    	 String g = m.group(1);

        result=result.replaceAll(g,"http://today.hit.edu.cn"+g);

     }

     if(a==1 || a==3)
    	 result=result.substring(result.indexOf("<!--标题结束-->"),result.indexOf("<div id=\"from\">"));
     else 
    	 result=result.substring(result.indexOf("<!--内容开始-->"),result.indexOf("<!--内容结束-->"));
     System.out.println(result);
     return result;

        } catch (Exception ex) {
        	ex.printStackTrace();
        	return null;
        }      
   }
   
   public static void doPost(String author,String pwd,String title,String content,String db,String URL)throws Exception
	{
		String postData="auid="+author+"&"+"pwd="+pwd+"&"+"title="+title+"&"+"content="+content+"&"+"categoryName="+db;
		//String reqUrl = "http://localhost:8080/crawler/add_news";
		String reqUrl = URL;
		URL url = new URL(reqUrl);
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();

		conn.setDoInput(true);//**
		conn.setDoOutput(true);//***
		//conn.setUseCaches(false);
		conn.setRequestMethod("POST");//***
		conn.connect();
		OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
		//System.out.println(this.postData);
		osw.write(postData);
		osw.flush();
		osw.close();
		//System.out.println("success");
	}
}
