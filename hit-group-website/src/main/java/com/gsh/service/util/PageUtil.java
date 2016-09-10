package com.gsh.service.util;

public class PageUtil
{
	/**
	 * 根据查询得到的记录条数生成分页总数
	 * @param queryCount 查询得到的记录条数
	 * @param pageSize 分页大小
	 * @return 分页数量
	 */
	public static int getPageCount(int queryCount, int pageSize)
	{
		int pageCount = 0;
		if(queryCount % pageSize == 0)
		{
			pageCount = queryCount / pageSize;
		}
		else
		{
			pageCount = queryCount / pageSize + 1;
		}
		return pageCount;
	}
}
