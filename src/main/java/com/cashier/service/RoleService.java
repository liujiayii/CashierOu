package com.cashier.service;

import java.math.BigInteger;
import java.util.List;

import com.cashier.entity.Role;
import com.cashier.entity.User;
import com.cashier.entityVo.PermissionVo;
import com.cashier.entityVo.RoleVo;

/**
 *
 * @ClassName: RoleService
 * @description 角色Service类
 *
 * @author dujiawei
 * @createDate 2019年7月4日
 */
public interface RoleService {
	
	/**
	 * @Title: listRole
	 * @description 分页查询角色列表
	 * @param @param roleVo
	 * @return List<RoleVo>    
	 * @author dujiawei
	 * @createDate 2019年7月4日
	 */
	public List<RoleVo> listRole(RoleVo roleVo);
	
	/**
	 * @Title: countRole
	 * @description 角色的数量
	 * @param @param roleVo
	 * @return RoleVo    
	 * @author dujiawei
	 * @createDate 2019年7月4日
	 */
	public RoleVo countRole(RoleVo roleVo);
	
	/**
	 * @Title: selectAllRole
	 * @description 查询所有角色（不分页）
	 * @param @param role
	 * @return List<Role>    
	 * @author dujiawei
	 * @createDate 2019年7月4日
	 */
	public List<Role> selectAllRole(Role role);

	/**
	 * @Title: saveRole
	 * @description 增加角色
	 * @param @param role
	 * @return int    
	 * @author dujiawei
	 * @createDate 2019年7月4日
	 */
	public int saveRole(Role role);

	/**
	 * @Title: selectRoleById
	 * @description 根据ID查询角色
	 * @param @param role
	 * @return Role    
	 * @author dujiawei
	 * @createDate 2019年7月4日
	 */
	public Role selectRoleById(Role role);

	/**
	 * @Title: updateRole
	 * @description 修改角色
	 * @param @param role
	 * @return int    
	 * @author dujiawei
	 * @createDate 2019年7月4日
	 */
	public int updateRole(Role role);

	/**
	 * @Title: deleteRoleById
	 * @description 根据id删除角色
	 * @param @param id
	 * @return int    
	 * @author dujiawei
	 * @createDate 2019年7月4日
	 */
	public int deleteRoleById(BigInteger id);

    /**
     * @Title: getPermissionListByShopId
     * @description 根据分店ID获取当前分店的权限信息
     * @param @param user
     * @return List<PermissionVo>    
     * @author dujiawei
     * @createDate 2019年7月4日
     */
    public List<PermissionVo> getPermissionListByShopId(User user);

    /**
     * @Title: listShopRole
     * @description 新增修改用户信息时，角色的下拉单选项使用
     * @param @param roleVo
     * @return List<RoleVo>    
     * @author dujiawei
     * @createDate 2019年7月18日
     */
	public List<RoleVo> listShopRole(RoleVo roleVo);

}
