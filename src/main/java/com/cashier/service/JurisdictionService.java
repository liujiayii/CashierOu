package com.cashier.service;

import java.util.List;

import com.cashier.entity.Role;
import com.cashier.entity.RolePermission;
import com.cashier.entityVo.PermissionVo;
import com.cashier.entityVo.ShopVo;

/**
 *
 * @ClassName: JurisdictionService

 * @description 
 *
 * @author 
 * @createDate 2019年1月9日
 */

public interface JurisdictionService {

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
    public int updateOneRolePermission(Role role, String ids );

    //所有的权限列表
	public List<PermissionVo> listAllPermissions();
	
	
	public List<RolePermission> listRolePermissions(RolePermission rolePermission);
}
