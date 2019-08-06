package com.cashier.dao;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cashier.entity.Permission;
import com.cashier.entity.Role;
import com.cashier.entity.RolePermission;
import com.cashier.entityVo.PermissionVo;



/**
 *
 * @ClassName: JurisdictionMapper

 * @description 
 *
 * @author pangchong
 * @createDate 2019年1月9日
 */

public interface JurisdictionMapper {
	/**
	 * 根据角色查询相应权限
	 *
	 * @author pangchong
	 * @createDate 2018年11月30日 下午2:00
	 */
	public List<PermissionVo> selectAllPermission(PermissionVo permissionVo);
	/**
	 * 查询用户数量
	 *
	 * @author pangchong
	 * @createDate 2018年11月30日 下午2:00
	 */
	public PermissionVo selectUsersCount(PermissionVo permissionVo);

	/**
	 * 修改用户状态（解锁/锁定）
	 *
	 * @author pangchong
	 * @createDate 2018年11月30日 下午2:00
	 */
	public int updateUserState(PermissionVo permissionVo);

	/**@description 根据角色id修改权限
	 *
	 * @param permissionVo
	 * @return
	 * @author pangchong
	 * @createDate  2019年1月18日 下午2:00
	 */
	public int addPermissionByRoleId(PermissionVo permission);

	/**@description 根据角色id删除权限
	 *
	 * @param permissionVo
	 * @return
	 * @author pangchong
	 * @createDate 2019年1月18日 下午2:00
	 */
	public int deletePermissionByRoleId(Integer id);
	

	
	/**@description 获取当前分店中角色的权限信息
     * @param role
     * @return List<PermissionVo>
     * @author zhou jiaxin
     * @createDate 2018年1月30日 
     */
    public List<PermissionVo> listPermissionByRoleIdAndShopId(Role role);
   
    /**@description 修改店铺中角色权限信息
     * @param id
     * @param ids
     * @return int 
     * @author zhou jiaxin
     * @createDate 2018年1月30日 
     */
    public List<Permission> listPermissionIDByRole(Role role);
    /**@description 先判断当前角色是否是超级管理员
     * @param role
     * @return Role
     * @author zhou jiaxin
     * @createDate 20190221
     */
    public Role selectSuperRoleByRoleId(Role role);
	public List<PermissionVo> listAllPermissions();
	
	
	public List<RolePermission> listRolePermissions(RolePermission rolePermission);
	/**
	 * 
	     * @Title: getRolePermission
	     * @description 获得当前登陆角色所有权限（包含权限名称）
	     * @param  
	     * @return    
	     * @author 
	     * @createDate
	 */
	public List<PermissionVo> getRolePermission(@Param("shopId")BigInteger shopId,@Param("roleId")BigInteger roleId);

}
