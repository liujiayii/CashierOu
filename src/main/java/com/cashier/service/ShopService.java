package com.cashier.service;

import java.util.List;

import com.cashier.entity.Role;
import com.cashier.entity.Shop;
import com.cashier.entityDTO.ShopUserPermissionDTO;
import com.cashier.entityVo.ShopVo;
import com.cashier.entityVo.UserShopVo;
import com.cashier.entityVo.UserVo;

/**
 *
 * @ClassName: ShopService

 * @description 店铺管理接口
 *
 * @author linhongyu
 * @createDate 2018年11月8日
 */

public interface ShopService {
	
	/**
     * @Title: getGeneralScopeById
     * @description 通过店铺id查询店铺省市区号和会员消费范围 
     * @return ShopVo    
     * @author dujiawei
     * @createDate 2018年12月13日
     */
	public ShopVo getGeneralScopeById(ShopVo shopVo);
	
	/**
     * @Title: listShop
     * @description 查询所有店铺信息 
     * @return List<ShopVo>    
     * @author dujiawei
     * @createDate 2018年12月11日
     */
	public List<ShopVo> listShop(ShopVo shopVo);
	/**
     * @Title: listAllShopVo
     * @description 查询所有店铺信息 
     * @return List<ShopVo>    
     * @author dujiawei
     * @createDate 2018年12月5日
     */
	public List<ShopVo> listAllShopVo(ShopVo shopVo);
	/**
	 * @Title: countShop
	 * @description 所有店铺的数量
	 * @return ShopVo    
	 * @author dujiawei
	 * @createDate 2018年12月5日
	 */
	public ShopVo countShop(ShopVo shopVo);
	/**
     * @Title: updateShop
     * @description 修改店铺信息
     * @return int   
     * @author dujiawei	
	 * @param shopUserPermissionDTO 
     * @createDate 2018年12月5日
     */
	public int updateShop(ShopVo shopVo, ShopUserPermissionDTO shopUserPermissionDTO,String ids);
	/**
     * @Title: insertShop
     * @description 添加店铺
     * @return int   
     * @author dujiawei
	 * @param ids 
     * @createDate 2018年12月5日
     */
	public Integer insertShop(ShopUserPermissionDTO shopUserPermissionDTO, String ids);
	/**
     * @Title: deleteShop
     * @description 删除店铺
     * @return int   
     * @author dujiawei
     * @createDate 2018年12月5日
     */
	public int deleteShop(Shop shop);
	/**
     * @Title: listByShopNameVo
     * @description 通过名称店铺查询信息
     * @return List<ShopVo>   
     * @author dujiawei
     * @createDate 2018年12月5日
     */
	public List<ShopVo> listByShopNameVo(ShopVo shopVo);
	/**
     * @Title: countByShopName
     * @description 通过名称店铺查询数量
     * @return ShopVo   
     * @author dujiawei
     * @createDate 2018年12月5日
     */
	public ShopVo countByShopName(ShopVo shopVo);
	/**
     * @Title: getId
     * @description 通过ID查询地区ID
     * @param  
     * @return Shop    
     * @author linhongyu
     * @createDate 2018年11月20日
     */
	public Shop getId(Shop shop);

	/**
     * @Title: getname
     * @description 通过id获取name
     * @param  shop  
     * @return Shop    
     * @author liujunkai
     * @createDate 2018年12月5日
     */
    public List<Shop> listshop(Shop shop);

    /**@description 获得各分店铺id和名称
     * @return List<Shop>
     * @author zhou jiaxin
     * @createDate 2019年2月12日
     */
    public List<Shop> listShopIdAndName();

    /**
     * @Title: listShopIdButMe
     * @description 获得各分店铺id和名称除了当前店
     * @param shopId
     * @return List<Shop>    
     * @author liujunkai
     * @createDate 2019年3月1日
     */
    public List<Shop> listShopIdButMe(Integer shopId);

    /**
     * 
         * @Title: getAllCity
         * @description  获得所有的省市区
         * @param  
         * @return 省市区列表
         * @author chenshuxian	
         * @createDate 2019年7月8日
     */
    public List<ShopVo> getAllCity();
    
    
    /**
     * @Title: listAllShopIdAndName
     * @description (下拉单使用)查询所有的店铺名称和id
     * @param @param userShopVo
     * @return List<UserShopVo>    
     * @author dujiawei
     * @createDate 2019年7月19日
     */
    public List<UserShopVo> listAllShopIdAndName(UserShopVo userShopVo);

    /**
     *
     * @Title: getRoleIdByShopId
     * @description 通过分店ID获取分店超级管理员角色的ID
     * @param @param userVo
     * @param @return  
     * @return Role    
     * @author zhoujiaxin
     * @createDate 2019年7月31日
     */
    public Role getRoleIdByShopId(UserVo userVo);
}
