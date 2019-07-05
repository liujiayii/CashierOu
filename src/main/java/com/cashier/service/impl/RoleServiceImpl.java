package com.cashier.service.impl;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cashier.dao.RoleMapper;
import com.cashier.entity.Role;
import com.cashier.entity.User;
import com.cashier.entityVo.PermissionVo;
import com.cashier.entityVo.RoleVo;
import com.cashier.service.RoleService;

/**
 *
 * @ClassName: RoleServiceImpl
 * @description 角色Service实现类
 *
 * @author dujiawei
 * @createDate 2019年7月4日
 */
@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private RoleMapper roleMapper;
	
	/**
	 * @Title: listRole
	 * @description 分页查询角色列表
	 * @param @param roleVo
	 * @return List<RoleVo>    
	 * @author dujiawei
	 * @createDate 2019年7月4日
	 */
	@Override
	public List<RoleVo> listRole(RoleVo roleVo) {
		// TODO Auto-generated method stub
		return roleMapper.listRole(roleVo);
	}

	/**
	 * @Title: countRole
	 * @description 角色的数量
	 * @param @param roleVo
	 * @return RoleVo    
	 * @author dujiawei
	 * @createDate 2019年7月4日
	 */
	@Override
	public RoleVo countRole(RoleVo roleVo) {
		// TODO Auto-generated method stub
		return roleMapper.countRole(roleVo);
	}

	/**
	 * @Title: selectAllRole
	 * @description 查询所有角色（不分页）
	 * @param @param role
	 * @return List<Role>    
	 * @author dujiawei
	 * @createDate 2019年7月4日
	 */
	@Override
	public List<Role> selectAllRole(Role role) {
		
		return roleMapper.selectAllRole(role);
	}

	/**
	 * @Title: saveRole
	 * @description 增加角色
	 * @param @param role
	 * @return int    
	 * @author dujiawei
	 * @createDate 2019年7月4日
	 */
	@Override
	public int saveRole(Role role) {
	    if ("超级管理员".equals(role.getName())) {
            return 0;
        }
	    Role role2 = roleMapper.selectRoleByRoleName(role);
	    if (role2 != null) {
	        return 0;
        }
	    return roleMapper.saveRole(role);
	}

	/**
	 * @Title: selectRoleById
	 * @description 根据ID查询角色
	 * @param @param role
	 * @return Role    
	 * @author dujiawei
	 * @createDate 2019年7月4日
	 */
	@Override
	public Role selectRoleById(Role role) {
	
		return roleMapper.selectRoleById(role);
	}
	
	/**
	 * @Title: updateRole
	 * @description 修改角色
	 * @param @param role
	 * @return int    
	 * @author dujiawei
	 * @createDate 2019年7月4日
	 */
	@Override
	public int updateRole(Role role) {
		if ("超级管理员".equals(role.getName())) {
            return 0;
        }
	    Role role2 = roleMapper.selectRoleByRoleNameNotThis(role);
	    if (role2 != null) {
	        return 0;
        }
		
        return roleMapper.updateRole(role);
	}

	/**
	 * @Title: deleteRoleById
	 * @description 根据id删除角色
	 * @param @param id
	 * @return int    
	 * @author dujiawei
	 * @createDate 2019年7月4日
	 */
	@Override
	public int deleteRoleById(BigInteger id) {

		return roleMapper.deleteRoleById( id);
	}

	/**
     * @Title: getPermissionListByShopId
     * @description 根据分店ID获取当前分店的权限信息
     * @param @param user
     * @return List<PermissionVo>    
     * @author dujiawei
     * @createDate 2019年7月4日
     */
    @Override
    public List<PermissionVo> getPermissionListByShopId(User user) {
        return roleMapper.getPermissionListByShopId(user);
    }

}
