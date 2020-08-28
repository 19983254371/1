package com.hqyj.cyj.moudls.account.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.hqyj.cyj.moudls.account.entity.Role;
import com.hqyj.cyj.moudls.common.vo.Result;
import com.hqyj.cyj.moudls.common.vo.SearchVo;


public interface RoleService {

	List<Role> getRoles();
	
	Result<Role> editRole(Role role);
	
	Result<Role> deleteRole(int roleId);
	
	PageInfo<Role> getRoles(SearchVo searchVo);
	
	List<Role> getRolesByUserId(int userId);
	
	List<Role> getRolesByResourceId(int resourceId);
	
	Role getRoleById(int roleId);
}
