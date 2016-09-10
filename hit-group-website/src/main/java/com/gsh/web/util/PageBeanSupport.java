package com.gsh.web.util;

public class PageBeanSupport
{
	private int totalPage;
	private int currentPage;

	public int getTotalPage()
	{
		return totalPage;
	}

	public void setTotalPage(int totalPage)
	{
		this.totalPage = totalPage;
	}

	public int getCurrentPage()
	{
		return currentPage;
	}

	public void setCurrentPage(int currentPage)
	{
		this.currentPage = currentPage;
	}

	public int getNextPage()
	{
		int nextPage;
		if(this.currentPage < this.totalPage)
		{
			nextPage = this.currentPage + 1;
		}
		else if(this.currentPage == this.totalPage)
		{
			nextPage = this.currentPage;
		}
		else
		{
			throw new RuntimeException("页码超过分页上限");
		}
		return nextPage;
	}

	public int getPreviousPage()
	{
		int previousPage;
		if(this.currentPage > 1)
		{
			previousPage = this.currentPage - 1;
		}
		else if(this.currentPage == 1)
		{
			previousPage = 1;
		}
		else
		{
			throw new RuntimeException("页码低于1");
		}
		return previousPage;
	}
}
