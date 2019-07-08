package com.cashier.dao;

import java.math.BigInteger;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.cashier.entity.Permission;
import com.cashier.entity.Shop;
import com.cashier.entity.User;
import com.cashier.entity.UserRoleRelationship;
import com.cashier.entityDTO.ShopUserPermissionDTO;
import com.cashier.entityVo.PermissionVo;
import com.cashier.entityVo.UserShopVo;
import com.cashier.entityVo.UserVo;
import com.cashier.entityVo.UserVo2;

/**
 *
 * @ClassName: UserMapper

 * @description 用户表Dao层的接口类，查询、新增、修改、删除等方法
 *
 * @author dujiawei
 * @createDate 2019年7月4日
 */

public interface UserMapper {
	
	/**
	 * @Title: listUser
	 * @description 查询本店所有的用户（无分页，新增用户使用）
	 * @param @param userVo
	 * @return List<UserVo>    
	 * @author dujiawei
	 * @createDate 2019年7月4日
	 */
	public List<UserVo> listUser(UserVo userVo);
	
	/**
	 * @Title: listUserVo
	 * @description 查询本店所有的用户
	 * @param @param userVo
	 * @return List<UserVo>    
	 * @author dujiawei
	 * @createDate 2019年7月4日
	 */
	public List<UserVo> listUserVo(UserVo userVo);
	
	/**
	 * @Title: countUser
	 * @description 查询本店用户的数据条数
	 * @param @param userVo
	 * @return UserVo    
	 * @author dujiawei
	 * @createDate 2019年7月4日
	 */
	public UserVo countUser(UserVo userVo);
	
	/**
	 * @Title: listByName
	 * @description 通过员工姓名查询员工信息
	 * @param @param userVo
	 * @return List<UserVo>    
	 * @author dujiawei
	 * @createDate 2019年7月4日
	 */
	public List<UserVo> listByName(UserVo userVo);
	
	/**
	 * @Title: countByName
	 * @description 通过员工姓名查询员工信息的条数
	 * @param @param userVo
	 * @return UserVo    
	 * @author dujiawei
	 * @createDate 2019年7月4日
	 */
	public UserVo countByName(UserVo userVo);
	
	/**
	 * @Title: saveUser
	 * @description 新增用户
	 * @param @param userVo
	 * @return int    
	 * @author dujiawei
	 * @createDate 2019年7月4日
	 */
	public int saveUser(UserVo userVo);
	
	/**
	 * @Title: updateUser
	 * @description 修改用户
	 * @param @param userVo
	 * @return int    
	 * @author dujiawei
	 * @createDate 2019年7月4日
	 */
	public int updateUser(UserVo userVo);
	
	/**
	 * @Title: removeUser
	 * @description 删除用户（硬删除）
	 * @param @param id
	 * @return int    
	 * @author dujiawei
	 * @createDate 2019年7月4日
	 */
	public int removeUser(@Param("id") BigInteger id);
	
	/**
	 * @Title: listUserName
	 * @description (下拉单用)查询本店所有员工id和员工姓名
	 * @param @param userVo
	 * @return List<UserVo>    
	 * @author dujiawei
	 * @createDate 2019年7月4日
	 */
	public List<UserVo> listUserName(UserVo userVo);

    /**
     * @Title: saveUserByDTO
     * @description 
     * @param @param shopUserPermissionDTO   
     * @return void    
     * @author 
     * @createDate 2019年7月4日
     */
    public Integer saveUserByDTO(ShopUserPermissionDTO shopUserPermissionDTO);

    /**
     * @Title: getSuperUserByShopId
     * @description 通过店铺ID获取该店铺的超级管理员信息
     * @param @param user
     * @return UserVo2    
     * @author dujiawei
     * @createDate 2019年7月4日
     */
    public UserVo2 getSuperUserByShopId(User user);

    /**
     * @Title: saveUserAndRoleRelation
     * @description 添加用户时需要把用户个角色的关联表也加上
     * @param @param userVo   
     * @return void    
     * @author dujiawei
     * @createDate 2019年7月4日
     */
    public Integer saveUserAndRoleRelation(UserVo userVo);

    /**
     * @Title: removeUserRoleRelation
     * @description 删除用户时删除和角色关联的关系表
     * @param @param id   
     * @return void    
     * @author dujiawei
     * @createDate 2019年7月4日
     */
    public void removeUserRoleRelation(BigInteger id);

    /**
     * @Title: getOneUserById
     * @description 通过用户ID查询用户信息
     * @param @param userVo
     * @return User    
     * @author zhou jiaxin
     * @createDate 2019年7月4日
     */
    public User getOneUserById(UserVo userVo);

    /**
     * @Title: selectUserByUsername
     * @description 通过用户名查询整张表查看用户名是否重复
     * @param @param userVo
     * @return UserVo    
     * @author zhou jiaxin
     * @createDate 2019年7月4日
     */
    public UserVo selectUserByUsername(UserVo userVo);

   /**
    * @Title: removeUserByShopId
    * @description 店铺删除时删除店铺里的会员
    * @param @param shop   
    * @return void    
    * @author zhou jiaxin
    * @createDate 2019年7月4日
    */
    public void removeUserByShopId(Shop shop);

    /**
     * @Title: updateSupperUser
     * @description 更新超级管理员
     * @param @param shopUserPermissionDTO   
     * @return void    
     * @author zhou jiaxin
     * @createDate 2019年7月4日
     */
    public void updateSupperUser(ShopUserPermissionDTO shopUserPermissionDTO);

    /**
     * @Title: listPermissionByCurrentUser
     * @description 根据当前总店管理员姓名查询出她的权限范围
     * @param @param user
     * @return List<PermissionVo>    
     * @author zhou jiaxin
     * @createDate 2019年7月4日
     */
    public List<PermissionVo> listPermissionByCurrentUser(User user);

    /**
     * @Title: listPermissionBySuperUser
     * @description 先查询总店超级管理员所有权限
     * @return List<Permission>    
     * @author zhou jiaxin
     * @createDate 2019年7月4日
     */
    public List<Permission> listPermissionBySuperUser();
    
	/**
	 * @Title: updateUserRoleRelationship
	 * @description 修改一条‘员工角色关系’数据
	 * @param urrs
	 * @return int    
	 * @author dujiawei
	 * @createDate 2019年1月31日
	 */
	public int updateUserRoleRelationship(UserRoleRelationship urrs);
	
	/**
	 * @Title: getUserByName
	 * @description 登录后通过用户名查询用户信息
	 * @param @param userShopVo
	 * @return UserShopVo    
	 * @author dujiawei
	 * @createDate 2019年7月5日
	 */
	public UserShopVo getUserByName(UserShopVo userShopVo);
	
	/**
	 * @Title: listAgentShop
	 * @description 区域经理管理的店铺（下拉单）
	 * @param @param userShopVo
	 * @param @return   
	 * @return List<UserShopVo>    
	 * @author dujiawei
	 * @createDate 2019年7月5日
	 */
	public List<UserShopVo> listAgentShop(UserShopVo userShopVo);

	/**
	 * @Title: listShopByProvinceAgent
	 * @description 省级经理管理的店铺
	 * @param @param userShopVo
	 * @return List<UserShopVo>    
	 * @author dujiawei
	 * @createDate 2019年7月5日
	 */
	public List<UserShopVo> listShopByProvinceAgent(UserShopVo userShopVo);
	
	/**
	 * @Title: listShopByCityAgent
	 * @description 市级经理管理的店铺
	 * @param @param userShopVo
	 * @return List<UserShopVo>    
	 * @author dujiawei
	 * @createDate 2019年7月5日
	 */
	public List<UserShopVo> listShopByCityAgent(UserShopVo userShopVo);

	/**
	 * @Title: listShopByAreaAgent
	 * @description 区级经理管理的店铺
	 * @param @param userShopVo
	 * @return List<UserShopVo>    
	 * @author dujiawei
	 * @createDate 2019年7月5日
	 */
	public List<UserShopVo> listShopByAreaAgent(UserShopVo userShopVo);

}
