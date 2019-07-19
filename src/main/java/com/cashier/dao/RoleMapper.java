package com.cashier.dao;

import java.math.BigInteger;
import java.util.List;
import com.cashier.entity.Permission;
import com.cashier.entity.Role;
import com.cashier.entity.RolePermissionRelationship;
import com.cashier.entity.User;
import com.cashier.entityVo.PermissionVo;
import com.cashier.entityVo.RoleVo;
import com.cashier.entityVo.ShopVo;

/**
 *
 * @ClassName: RoleMapper
 * @description 角色dao类
 *
 * @author dujiawei
 * @createDate 2019年7月4日
 */
public interface RoleMapper {
	
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
     * @Title: selectRoleByRoleName
     * @description 通过角色名称查询数据库中数据是否存在
     * @param @param role
     * @return Role    
     * @author dujiawei
     * @createDate 2019年7月4日
     */
    public Role selectRoleByRoleName(Role role);
    
    
    /**
     * @Title: selectRoleByRoleNameNotThis
     * @description (修改角色用)通过角色名称查询除此id的数据库中数据是否存在
     * @param @param role
     * @return Role    
     * @author dujiawei
     * @createDate 2019年7月4日
     */
    public Role selectRoleByRoleNameNotThis(Role role);


    /**
     * @Title: saveRolePermissionRelationship
     * @description 保存角色权限关联表
     * @param @param rolePermissionRelationship   
     * @return void    
     * @author dujiawei
     * @createDate 2019年7月4日
     */
    public Integer saveRolePermissionRelationship(RolePermissionRelationship rolePermissionRelationship);


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
     * @Title: getPermissionIDListByShopId
     * @description 根据分店ID获取当前分店的权限ID信息
     * @param @param shopVo
     * @return List<Permission>    
     * @author dujiawei
     * @createDate 2019年7月4日
     */
    public List<Permission> getPermissionIDListByShopId(ShopVo shopVo);


    /**
     * @Title: getRoleIdByShopId
     * @description 通过分店ID获取超级管理员角色ID
     * @param @param shopVo
     * @return Role    
     * @author dujiawei
     * @createDate 2019年7月4日
     */
    public Role getRoleIdByShopId(ShopVo shopVo);


    /**
     * @Title: deleteRolePermissionRelationship
     * @description 删除角色和权限关联表中的一条数据
     * @param @param rolePermissionRelationship   
     * @return void    
     * @author dujiawei
     * @createDate 2019年7月4日
     */
    public void deleteRolePermissionRelationship(RolePermissionRelationship rolePermissionRelationship);


    /**
     * @Title: deleteAllRolePermissionRelationship
     * @description 删除分店超级管理员权限时需要把该分店的权限全部收回
     * @param @param rolePermissionRelationship   
     * @return void    
     * @author dujiawei
     * @createDate 2019年7月4日
     */
    public void deleteAllRolePermissionRelationship(RolePermissionRelationship rolePermissionRelationship);

    
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
