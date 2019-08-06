package com.cashier.service;

import java.math.BigInteger;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.cashier.entity.Shop;
import com.cashier.entity.ShopListDTO;
import com.cashier.entity.User;
import com.cashier.entity.UserRoleRelationship;
import com.cashier.entityVo.PermissionVo;
import com.cashier.entityVo.UserShopVo;
import com.cashier.entityVo.UserVo;
import com.cashier.entityVo.UserVo2;

/**
 *
 * @ClassName: UserService
 * 
 * @description 用户表的Service层的接口类，查询、新增、修改、删除等方法
 *
 * @author dujiawei
 * @createDate 2018年11月8日
 */
public interface UserService {
	
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
     * @Title: getSuperUserByShopId
     * @description  通过店铺ID获取该店铺的超级管理员信息
     * @param user
     * @return user
     * @author zhou jiaxin
     * @createDate 2019-01-21
     */
    public UserVo2 getSuperUserByShopId(User user);

    /**
     * @Title: listPermissionByCurrentUser
     * @description 根据当前总店管理员姓名查询出她的权限范围
     * @param username
     * @author zhou jiaxin
     * @createDate 2019-01-24
     */
    public List<PermissionVo> listPermissionByCurrentUser(String username);
    
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

    /**
     *
     * @Title: listShopMsgByProvince
     * @description 101通过省级区域经理查询对应省里的店铺信息（ID和名称）
     * @param @param shopListDTO
     * @param @return  
     * @return List<Shop>    
     * @author zhoujiaxin
     * @createDate 2019年7月26日
     */
    public List<Shop> listShopMsgByProvince(ShopListDTO shopListDTO);

    /**
     * @Title: listShopMsgByCity
     * @description 102通过市级区域经理查询对应市里的店铺信息（ID和名称）
     * @param @param shopListDTO
     * @return List<Shop>    
     * @author zhoujiaxin
     * @createDate 2019年7月29日
     */
    public List<Shop> listShopMsgByCity(ShopListDTO shopListDTO);

    /**
     * @Title: listShopMsgByArea
     * @description 103通过区级区域经理查询对应区里的店铺信息（ID和名称）
     * @param @param user
     * @return List<Shop>    
     * @author zhoujiaxin
     * @createDate 2019年7月29日
     */
    public List<Shop> listShopMsgByArea(User user);

    /**
     * @Title: listManagerUserVo
     * @description 列表查询区域经理列表
     * @param @param userVo
     * @param @return  
     * @return List<UserVo>    
     * @author zhoujiaxin
     * @createDate 2019年7月31日
     */
    public List<UserVo> listManagerUserVo(UserVo userVo);

    /**
     * @Title: countManagerUser
     * @description 查询区域经理列表数量
     * @param @param userVo
     * @param @return  
     * @return int    
     * @author zhoujiaxin
     * @createDate 2019年7月31日
     */
    public int countManagerUser(UserVo userVo);


}
