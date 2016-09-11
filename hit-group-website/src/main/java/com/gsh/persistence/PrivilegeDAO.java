package com.gsh.persistence;

import com.gsh.domain.Privilege;

public interface PrivilegeDAO
{
	/**
	 * 通过ID查找权限
	 * @param privilegeId 权限ID
	 * @return 权限名
	 */
	public Privilege getPrivilegeById(Long privilegeId);

	/**
	 * 通过权限名查找权限
	 * @param privilegeName 权限名
	 * @return 权限对象
	 */
	public Privilege getPrivilegeByName(String privilegeName);
}
