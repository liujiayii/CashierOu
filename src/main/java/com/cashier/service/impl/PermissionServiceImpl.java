package com.cashier.service.impl;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cashier.dao.PermissionMapper;
import com.cashier.entity.Permission;
import com.cashier.entityVo.PermissionVo;
import com.cashier.service.PermissionService;
import com.cashier.service.ex.DataNotExistsException;
@Service
public class PermissionServiceImpl implements PermissionService {
	@Autowired
	private PermissionMapper permissionMapper;
	/**
	 * 
	     * @Title: insertPermissionMapper
	     * @description 添加权限相关信息
	     * @param  权限对象
	     * @return    成功执行的条数
	     * @author chenshuxian
	     * @createDate 2019年7月29日
	 */
	@Override
	public Integer insertPermission(Permission permission) {
		return permissionMapper.insertPermission(permission);
	}
	/**
	 * 
	     * @Title: updatePermissionById
	     * @description 通过id修改权限相关信息
	     * @param  权限对象
	     * @return    成功执行的条数
	     * @author chenshuxian
	     * @createDate 2019年7月29日
	 */
	@Override
	@Transactional
	public Integer updatePermissionById(Permission permission) {
		//查询该权限是否是父权限
		Permission permissions = permissionMapper.getPermissionById(permission.getId());
		Integer row;
		if(permissions.getState()==1){
			//该权限为父权限，查询所有对应子权限，修改权限名称
			Permission p = new Permission();
			p.setParent_name(permission.getName());
			p.setParentId(permission.getId());
			row = permissionMapper.updateParentNameById(p);
			if(row <= 0){
				throw new DataNotExistsException("修改失败，请联系管理员");
			}
			
		}
		row = permissionMapper.updatePermissionById(permission);
		if(row <= 0){
			throw new DataNotExistsException("修改失败，请联系管理员");
		}
		return row;
	}
	/**
	 * 
	     * @Title: delPerssionById
	     * @description 根据id删除相对应权限
	     * @param  权限id
	     * @return  成功执行的条数
	     * @author chenshuxian
	     * @createDate 2019年7月29日
	 */
	@Override
	@Transactional
	public Integer delPerssionById(BigInteger permissionId) {
		//查询该权限是否为父权限
		Permission p = permissionMapper.getPermissionById(permissionId);
		Integer row;
		if(p.getState()==1){
			//该权限为父权限,将该权限下的子权限都删除
			row = permissionMapper.delPermissionByParentId(permissionId);
			if(row<=0){
				throw new DataNotExistsException("删除失败，请联系管理员");
			}
		}
		//执行删除操作
		row = permissionMapper.delPerssionById(permissionId);
		if(row<=0){
			throw new DataNotExistsException("删除失败，请联系管理员");
		}
		return row;
	}
	/**
	 * 
	     * @Title: getPerssionsByParentId
	     * @description 根据父类id查询所有子类权限
	     * @param  父类id
	     * @return   子类权限集合
	     * @author chenshuxian
	     * @createDate 2019年7月29日
	 */
	@Override
	public List<Permission> getPermissionsByParentId(BigInteger parentId) {
		
		return permissionMapper.getPermissionsByParentId(parentId);
	}
	/**
	 * 
	     * @Title: getPermissionById
	     * @description 根据id查询权限
	     * @param  权限id
	     * @return   权限相关信息
	     * @author chenshuxian
	     * @createDate 2019年7月29日
	 */
	@Override
	public Permission getPermissionById(BigInteger permissionId) {
		
		return permissionMapper.getPermissionById(permissionId);
	}
	
	/**
	 * 
	     * @Title: getAllParentPermission
	     * @description 获得所有父类权限，供下拉选使用
	     * @param  
	     * @return    
	     * @author chenshuxian
	     * @createDate 2019年7月29日
	 */
	@Override
	public List<Permission> getAllParentPermission() {
		
		return permissionMapper.getAllParentPermission();
	}
	/**
	 * 
	     * @Title: getAllPermission
	     * @description 获得所有权限（每个父类下子权限）
	     * @param  
	     * @return    
	     * @author chenshuxian
	     * @createDate 2019年7月29日
	 */
	@Override
	public List<PermissionVo> getAllPermission() {
		
		return permissionMapper.getAllPermission();
	}

}
