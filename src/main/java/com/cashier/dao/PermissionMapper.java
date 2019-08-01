package com.cashier.dao;

import java.math.BigInteger;
import java.util.List;

import com.cashier.entity.Permission;
import com.cashier.entityVo.PermissionVo;

public interface PermissionMapper {
	/**
	 * 
	     * @Title: insertPermissionMapper
	     * @description 添加权限相关信息
	     * @param  权限对象
	     * @return    成功执行的条数
	     * @author chenshuxian
	     * @createDate 2019年7月29日
	 */
	Integer insertPermission(Permission permission);
	/**
	 * 
	     * @Title: updatePermissionById
	     * @description 通过id修改权限相关信息
	     * @param  权限对象
	     * @return    成功执行的条数
	     * @author chenshuxian
	     * @createDate 2019年7月29日
	 */
	Integer updatePermissionById(Permission permission);
	/**
	 * 
	     * @Title: delPerssionById
	     * @description 根据id删除相对应权限
	     * @param  权限id
	     * @return  成功执行的条数
	     * @author chenshuxian
	     * @createDate 2019年7月29日
	 */
	Integer delPerssionById(BigInteger permissionId);
	/**
	 * 
	     * @Title: getPerssionsByParentId
	     * @description 根据父类id查询所有子类权限
	     * @param  父类id
	     * @return   子类权限集合
	     * @author chenshuxian
	     * @createDate 2019年7月29日
	 */
	List<Permission> getPermissionsByParentId(BigInteger parentId);
	/**
	 * 
	     * @Title: getPermissionById
	     * @description 根据id查询权限
	     * @param  权限id
	     * @return   权限相关信息
	     * @author chenshuxian
	     * @createDate 2019年7月29日
	 */
	Permission getPermissionById(BigInteger permissionId);
	/**
	 * 
	     * @Title: delPermissionByParentId
	     * @description 根据父类id删除相对应所有子类权限
	     * @param  父类id
	     * @return    成功执行的条数
	     * @author chenshuxian
	     * @createDate 2019年7月29日
	 */
	Integer delPermissionByParentId(BigInteger parentId);
	/**
	 * 
	     * @Title: getAllParentPermission
	     * @description 获得所有父类权限，供下拉选使用
	     * @param  
	     * @return    
	     * @author chenshuxian
	     * @createDate 2019年7月29日
	 */
	List<Permission> getAllParentPermission();
	/**
	 * 
	     * @Title: updateParentNameById
	     * @description 根据父类id修改父类权限名称
	     * @param  父类id
	     * @return  成功执行条数
	     * @author chenshuxian
	     * @createDate 2019年7月29日
	 */
	Integer updateParentNameById(Permission p);
	/**
	 * 
	     * @Title: getAllPermission
	     * @description 获得所有权限（每个父类下子权限）
	     * @param  
	     * @return    
	     * @author chenshuxian
	     * @createDate 2019年7月29日
	 */
	List<PermissionVo> getAllPermission();
}
