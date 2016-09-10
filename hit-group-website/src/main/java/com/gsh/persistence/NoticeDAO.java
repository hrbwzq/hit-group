package com.gsh.persistence;

import com.gsh.domain.Notice;

public interface NoticeDAO
{
	/**
	 * 保存一条公告
	 * @param notice 公告对象
	 */
	public void addNotice(Notice notice);

	/**
	 * 查询当前唯一被激活的公告
	 * @return 公告对象,如果无结果返回null
	 */
	public Notice getActiveNotice();
}
