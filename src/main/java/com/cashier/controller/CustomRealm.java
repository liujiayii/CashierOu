package com.cashier.controller;

import javax.annotation.Resource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import com.cashier.entity.User;
import com.cashier.service.LoginService;
import redis.clients.jedis.JedisPool;


/**
 * 
 * @Description 
 *    
 * @author zhoujiaxin  
 * @createDate 2018年11月14日  
 */
public class CustomRealm extends AuthorizingRealm {

    @Resource
    private LoginService loginService;
    @Resource
    JedisPool jedisPool;
    /** 验证功能 */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)token;
        // 通过用户名查询用户信息
        String username = usernamePasswordToken.getUsername();
        User user = loginService.selectUserByUsername(username);
        if (user != null) {
            return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
        }
        return null;
    }
    
    /** 授权功能 */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection token) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        
        // 根据当前登陆用户查询对应角色和权限
        Subject subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();
        
        // 通过用户名调用业务层查询角色
        /*List<Role> roleList = loginService.listRolesByUser(user);
        if (roleList.size()>0) {
            for (Role role :roleList) {
                simpleAuthorizationInfo.addRole(role.getName());
            }
        }*/
        // 通过用户名调用业务层查询角色
        /*List<Permission> permissionList = loginService.listPermissionsByUser(user);
        if (permissionList.size()>0) {
            for (Permission permission :permissionList) {
                simpleAuthorizationInfo.addStringPermission(permission.getPercode());
            }
        }*/
        return simpleAuthorizationInfo;
    }
    

}
