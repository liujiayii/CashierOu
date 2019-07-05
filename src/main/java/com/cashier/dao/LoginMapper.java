package com.cashier.dao;

import java.util.List;

import com.cashier.entity.Permission;
import com.cashier.entity.Role;
import com.cashier.entity.User;

/**
 * @ClassName: LoginServiceImpl
 * @description 登陆注册相关操作
 * @author zhoujiaxin
 * @createDate 2018年11月19日
 */
public interface LoginMapper {

    /**
     * @Title: selectUserByUsername
     * @description 通过用户名查询用户信息
     * @param username
     * @return User    
     * @author zhoujiaxin
     * @createDate 2018年11月19日
     */
    User selectUserByUsername(User user);

    /**
     * @Title: listRolesByUser
     * @description 通过用户名调用业务层查询角色
     * @param user
     * @return List<Role>    
     * @author zhoujiaxin
     * @createDate 2018年11月19日
     */
    List<Role> listRolesByUser(User user);

    /**
     * @Title: listPermissionsByUser
     * @description 通过用户名调用业务层查询角色
     * @param user
     * @return List<Permission>    
     * @author zhoujiaxin
     * @createDate 2018年11月19日
     */
    List<Permission> listPermissionsByUser(User user);

}
