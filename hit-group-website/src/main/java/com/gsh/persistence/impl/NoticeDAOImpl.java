package com.gsh.persistence.impl;

import com.gsh.domain.Notice;
import com.gsh.persistence.NoticeDAO;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "noticeDAO")
public class NoticeDAOImpl extends CommonDAO implements NoticeDAO
{
	@Override
	public void addNotice(Notice notice)
	{
		this.getSession().save(notice);
	}

	@Override
	public Notice getActiveNotice()
	{
		String hql = "from Notice notice where actived=1";
		Query query = this.getSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Notice> noticeList = query.list();
		if(noticeList.size() == 0)
		{
			return null;
		}
		else if(noticeList.size() == 1)
		{
			return noticeList.get(0);
		}
		else
		{
			throw new RuntimeException("多于一条的公告被激活");
		}
	}
}
