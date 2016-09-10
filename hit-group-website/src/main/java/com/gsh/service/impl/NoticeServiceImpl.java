package com.gsh.service.impl;

import com.gsh.domain.Notice;
import com.gsh.domain.User;
import com.gsh.service.NoticeService;
import com.gsh.web.backend.beans.NoticeFormBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class NoticeServiceImpl extends CommonService implements NoticeService
{
	@Override
	public void createNewNotice(NoticeFormBean noticeFormBean, Long userId)
	{
		//根据用户ID查询用户
		User user = getUserDAO().getUserById(userId);

		//创建新的Notice对象
		Notice notice = new Notice();
		notice.setActived(1);
		notice.setContent(noticeFormBean.getContent());
		notice.setCreateTime(new Date());
		notice.setUser(user);

		//取消激活原有的Notice对象
		Notice activedNotice = getNoticeDAO().getActiveNotice();
		if(activedNotice != null)
		{
			activedNotice.setActived(0);
		}

		//保存新的Notice对象
		getNoticeDAO().addNotice(notice);
	}

	@Override
	public Notice getCurrentNotice()
	{
		return getNoticeDAO().getActiveNotice();
	}
}
