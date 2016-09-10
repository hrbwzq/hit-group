package com.gsh.persistence;

import com.gsh.domain.News;
import com.gsh.domain.NewsCategory;

import java.util.List;

public interface NewsDAO
{
	/**
	 * 通过新闻分类名插入一条新闻,News类newsCategory字段可设置为null,其值将根据参数重新指定
	 * @param news 新闻bean
	 * @param newsCategoryName 新闻所属分类名
	 */
	public void insertNewsByCategoryName(News news, String newsCategoryName);

	/**
	 * 通过新闻分类ID插入一条新闻,News类newsCategory字段可设置为null,其值将根据参数重新指定
	 * @param news 新闻bean
	 * @param newsCategoryId 新闻所属分类ID
	 */
	public void insertNewsByCategoryId(News news, Long newsCategoryId);

	/**
	 * 根据时间和新闻类名查询指定条数的新闻
	 * @param categoryName 新闻类名
	 * @param size 待查条数
	 * @return 包含查询结果信息的列表,无结果返回空列表
	 */
	public List<News> queryLimitNewsByCategoryName(String categoryName, int size);

	/**
	 * 根据时间和新闻类名分页查询所有新闻
	 * @param categoryName 新闻类名
	 * @param startPage 其实页码
	 * @param pageSize 分页大小
	 * @return 包含查询结果信息的列表,无结果返回空列表
	 */
	public List<News> queryNewsByPage(String categoryName, int startPage, int pageSize);

	/**
	 * 通过新闻ID查询新闻
	 * @param newdId 新闻ID
	 * @return 新闻对象,如果查询结果为空,返回null
	 */
	public News queryNewsById(Long newdId);

	/**
	 * 根据新闻分类名查询该类新闻条数
	 * @param categoryName 新闻分类名
	 * @return 新闻条数
	 */
	public int queryNewsCountByCategoryName(String categoryName);

	/**
	 * 插入一个新闻分类
	 * @param newsCategoryName 新闻分类名
	 */
	public void addNewsCategory(String newsCategoryName);

	/**
	 * 获取所有新闻分类
	 * @return 包含所有新闻分类的集合,结果集可能为空集
	 */
	public List<NewsCategory> getAllNewsCategory();

	/**
	 * 根据新闻分类名查找新闻分类
	 * @param newsCategoryName 新闻分类名
	 * @return 新闻分类bean,如果无结果,返回null
	 */
	public NewsCategory getNewsCategoryByCategoryName(String newsCategoryName);
}
