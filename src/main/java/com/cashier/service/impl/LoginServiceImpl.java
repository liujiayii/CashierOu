package com.cashier.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cashier.dao.LoginMapper;
import com.cashier.entity.Permission;
import com.cashier.entity.Role;
import com.cashier.entity.User;
import com.cashier.service.LoginService;

/**
 * @ClassName: LoginServiceImpl
 * @description 登陆注册相关操作
 * @author zhoujiaxin
 * @createDate 2018年11月19日
 */
@Service
public class LoginServiceImpl implements LoginService {
    
    @Autowired
    private LoginMapper loginMapper;
    
    /**
     * @Title: selectUserByUsername
     * @description 通过用户名查询用户信息
     * @param username
     * @return User    
     * @author zhoujiaxin
     * @createDate 2018年11月19日
     */
    @Override
    public User selectUserByUsername(String username) {
        User user = new User();
        user.setUsername(username);
        return loginMapper.selectUserByUsername(user);
    }

    /**
     * @Title: listRolesByUser
     * @description 通过用户名调用业务层查询角色
     * @param user
     * @return List<Role>    
     * @author zhoujiaxin
     * @createDate 2018年11月19日
     */
    @Override
    public List<Role> listRolesByUser(User user) {
        return loginMapper.listRolesByUser(user);
    }

    /**
     * @Title: listPermissionsByUser
     * @description 通过用户名调用业务层查询角色
     * @param user
     * @return List<Permission>    
     * @author zhoujiaxin
     * @createDate 2018年11月19日
     */
    @Override
    public List<Permission> listPermissionsByUser(User user) {
        return loginMapper.listPermissionsByUser(user);
    }

}
