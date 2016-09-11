package com.gsh.persistence;

import com.gsh.domain.AddFriendApply;

import java.util.List;

public interface FriendApplyDAO
{
	/**
	 * 得到发向某一用户所有的好友申请
	 * @param toUserId 用户ID
	 * @return 好汉查询结果的列表,未查询到结果返回空列表
	 */
	public List<AddFriendApply> getToUserApply(Long toUserId);

	/**
	 * 添加一条好友申请
	 * @param fromUserId 申请来源用户
	 * @param toUserId 申请接收用户
	 */
	public void addFriendApply(Long fromUserId, Long toUserId);

	/**
	 * 删除一条好友申请
	 * @param fromUserId 申请来源用户
	 * @param toUserId 申请接收用户
	 */
	public void deleteFriendApply(Long fromUserId, Long toUserId);
}
