package com.cashier.dao;

import java.util.List;
import com.cashier.entity.Shop;
import com.cashier.entityDTO.ShopUserPermissionDTO;
import com.cashier.entityVo.ShopVo;
import com.cashier.entityVo.UserShopVo;

/**
 *
 * @ClassName: ShopMapper
 * @description 
 *
 * @author linhongyu
 * @createDate 2018年11月8日
 */

public interface ShopMapper {
    
	/**
     * @Title: getGeneralScopeById
     * @description 通过店铺id查询店铺省市区号和会员消费范围 
     * @return ShopVo    
     * @author dujiawei
     * @createDate 2018年12月13日
     */
	public ShopVo getGeneralScopeById(ShopVo shopVo);
	/**
	 * @Title: listAllShopId
	 * @description 查询总店id
	 * @return Integer    
	 * @author lijunkai
	 * @createDate 2019年3月5日
	 */
	Integer listAllShopId();
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
     * @createDate 2018年12月5日
     */
	public int updateShop(ShopVo shopVo);
	/**
     * @Title: insertShop
     * @description 添加店铺
     * @return int   
     * @author dujiawei
     * @createDate 2018年12月5日
     */
	public int insertShop(ShopUserPermissionDTO shopUserPermissionDTO);
	/**
     * @Title: deleteShop
     * @description 删除店铺
     * @return BigInteger   
     * @author dujiawei
     * @createDate 2018年12月5日
     */
	public int deleteShop(Shop shop);
	/**
     * @Title: listByShopNameVo
     * @description 通过名称店铺信息
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

    /**@description 在店铺添加时查询店铺列表信息
     *
     * @param shopUserPermissionDTO
     * @return
     * @author 
     * @createDate
     */
    public List<ShopVo> listShopByDTO(ShopUserPermissionDTO shopUserPermissionDTO);

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
     * 
     * @Title: getShopByCondition
     * @description 根据id或者type查询shop表数据
     * @param shop
     * @return Shop    
     * @author liujunkai
     * @createDate 2019年7月11日
     */
    public Shop getShopByCondition(Shop shop);
    
    /**
     * @Title: listAllShopIdAndName
     * @description (下拉单使用)查询所有的店铺名称和id
     * @param @param userShopVo
     * @return List<UserShopVo>    
     * @author dujiawei
     * @createDate 2019年7月19日
     */
    public List<UserShopVo> listAllShopIdAndName(UserShopVo userShopVo);
}
