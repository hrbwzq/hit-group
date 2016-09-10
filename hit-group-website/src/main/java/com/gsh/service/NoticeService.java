package com.gsh.service;

import com.gsh.domain.Notice;
import com.gsh.web.backend.beans.NoticeFormBean;

public interface NoticeService
{
	/**
	 * 发布新的公告
	 * @param noticeFormBean 包含公告信息的FormBean
	 * @param userId 用户ID
	 */
	public void createNewNotice(NoticeFormBean noticeFormBean, Long userId);

	/**
	 * 读取当前公告
	 * @return 当前公告
	 */
	public Notice getCurrentNotice();
}
