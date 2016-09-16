package com.gsh.persistence;

import com.gsh.domain.User;

import java.util.List;

public interface AdvancedSearch
{
	/**
	 * 用户名模糊查询
	 * @param pattern 匹配字符串
	 * @return 包含查询结果的列表
	 */
	public List<User> userFuzzyQuery(String pattern);

}
