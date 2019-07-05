package com.cashier.service.impl;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cashier.dao.JurisdictionMapper;
import com.cashier.dao.RoleMapper;
import com.cashier.entity.Permission;
import com.cashier.entity.Role;
import com.cashier.entity.RolePermissionRelationship;
import com.cashier.entityVo.PermissionVo;
import com.cashier.service.JurisdictionService;

/**
 * 
 *
 * @ClassName: JurisdictionServiceImpl
 * @description 
 *
 * @author dujiawei
 * @createDate 2019年7月5日
 */
@Service
public class JurisdictionServiceImpl implements JurisdictionService{
	
	@Autowired  
	private JurisdictionMapper jurisdictionMapper;
	@Autowired  
    private RoleMapper roleMapper;
	
	/**
	 * 根据角色查询相应权限
	 *
	 * @author pangchong
	 * @createDate 2018年11月30日 下午2:00
	 */
	@Override
	public List<PermissionVo> selectAllPermission(PermissionVo permissionVo) {
		
		return jurisdictionMapper.selectAllPermission(permissionVo);
	}
	/**
	 * 查询用户数量
	 *
	 * @author pangchong
	 * @createDate 2018年11月30日 下午2:00
	 */
	@Override
	public PermissionVo selectUsersCount(PermissionVo permissionVo) {
		
		return jurisdictionMapper.selectUsersCount(permissionVo);
	}
	/**
	 * 修改用户状态（解锁/锁定）
	 *
	 * @author pangchong
	 * @createDate 2018年11月30日 下午2:00
	 */
	@Override
	public int updateUserState(PermissionVo permissionVo) {
		
		return jurisdictionMapper.updateUserState(permissionVo);
	}
	
	/**@description 获取当前分店中角色的权限信息
     * @param role
     * @return List<PermissionVo>
     * @author zhou jiaxin
     * @createDate 2018年1月30日 
     */
    @Override
    public List<PermissionVo> listPermissionByRoleIdAndShopId(Role role) {
        return jurisdictionMapper.listPermissionByRoleIdAndShopId(role);
    }
    
    /**@description 修改店铺中角色权限信息
     * @param id
     * @param ids
     * @return int 
     * @author zhou jiaxin
     * @createDate 2018年1月30日 
     */
    @Override
    public int updateOneRolePermission(Role role , String ids) {
        List<Permission> permissionIDList = jurisdictionMapper.listPermissionIDByRole(role);
        String[] permissionIds = ids.split(",");
        // 1先判断需要添加的内容
        RolePermissionRelationship rolePermissionRelationship = new RolePermissionRelationship();
        rolePermissionRelationship.setRoleId(role.getId());
        for (int i = 0; i < permissionIds.length; i++) {
            int flag = 0;
            for (int j = 0; j < permissionIDList.size(); j++) {
                if (new BigInteger(permissionIds[i]).compareTo(permissionIDList.get(j).getId())==0) {
                    flag = 1;
                    break;
                }
            }
            if (flag == 0) {
                rolePermissionRelationship.setPermissionId(new BigInteger(permissionIds[i]));
                roleMapper.saveRolePermissionRelationship(rolePermissionRelationship);
            }
        }
        // 2判断需要删除的内容
        for (int j = 0; j < permissionIDList.size(); j++) {
            int flag = 0;
            for (int i = 0; i < permissionIds.length; i++) {
                if (new BigInteger(permissionIds[i]).compareTo(permissionIDList.get(j).getId())==0) {
                    flag = 1;
                    break;
                }
            }
            if (flag == 0) {
                rolePermissionRelationship.setPermissionId(permissionIDList.get(j).getId());
                roleMapper.deleteRolePermissionRelationship(rolePermissionRelationship);
            }
        }
        
        return 1;
    }

}
