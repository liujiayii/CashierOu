/**
 *
 * @Title: ActivityMapper.java
 * @Package com.cashier.dao
 * @Description: TODO)
 * @author zhoujiaxin
 * @date 2019年6月18日
 */

package com.cashier.dao;

import java.util.List;
import com.cashier.entity.Activities;
import com.cashier.entity.ActivitiesActive;
import com.cashier.entity.ProductType;
import com.cashier.entity.Regulation;
import com.cashier.entity.SpecialOffers;
import com.cashier.entityDTO.SpecialOffersDTO;

/**
 * 
 * @Description 
 * @author zhoujiaxin  
 * @createDate 2019年6月18日  
 */
public interface ActivityMapper {

    /**
     * @Title: insertActivity
     * @Description 10101新增一条优惠活动
     * @param  SpecialOffers specialOffers
     * @return  SpecialOffers specialOffers
     * @author zhoujiaxin  
     * @createDate 2019年6月20日  
     */
    void insertActivity(SpecialOffers specialOffers);

    /**
     * @Title: insertActivityProduct
     * @Description 10102根据活动ID添加参加活动的商品信息
     * @param  Activities activities
     * @return  null
     * @author zhoujiaxin  
     * @createDate 2019年6月20日  
     */
    void insertActivityProduct(Activities activities);

    /**
     * @Title: insertRegulation
     * @Description 10103给满减活动添加满减规则
     * @param  Regulation regulation
     * @return  null
     * @author zhoujiaxin  
     * @createDate 2019年6月20日  
     */
    void insertRegulation(Regulation regulation);

    /**
     * @Title: listActivity
     * @Description 102查询优惠活动列表
     * @param  SpecialOffers specialOffers,BigInteger shopId
     * @return List<SpecialOffers> specialOffersList
     * @author zhoujiaxin  
     * @param limit 
     * @param beginNum 
     * @createDate 2019年6月20日  
     */
    List<SpecialOffers> listActivity(SpecialOffers specialOffers);

    /**
     * @Title: listProductTypeByActivityId
     * @Description 10301查询商品分类列表根据活动ID
     * @param  SpecialOffers specialOffers
     * @return List<ProductType>
     * @author zhoujiaxin  
     * @createDate 2019年6月20日  
     */
    List<ProductType> listProductTypeByActivityId(SpecialOffers specialOffers);
    
    /**
     * @Title: listProductTypeAndProductByActivityId
     * @Description 10302查询商品列表根据活动ID
     * @param  SpecialOffers specialOffers
     * @return List<ProductType>
     * @author zhoujiaxin  
     * @createDate 2019年6月20日  
     */
    List<ProductType> listProductTypeAndProductByActivityId(SpecialOffers specialOffers);
    
    /**
     * @Title: listRegulation
     * @Description 10303根据满减活动ID查询该活动的满减规则
     * @param  SpecialOffers specialOffers
     * @return List<Regulation>
     * @author zhoujiaxin  
     * @createDate 2019年6月20日  
     */
    List<Regulation> listRegulation(SpecialOffers specialOffers);
    
    /**
     * @Title: updateActivityById
     * @Description 104根据活动ID修改某条活动
     * @param  SpecialOffers specialOffers
     * @return 
     * @author zhoujiaxin  
     * @createDate 2019年6月21日  
     */
    int updateActivityById(SpecialOffers specialOffers);

    /**
     * @Title: deleteActivityAndProductById
     * @Description 10501根据活动ID删除活动表一条信息和活动商品表对应信息
     * @param  SpecialOffers specialOffers
     * @return 
     * @author zhoujiaxin  
     * @createDate 2019年6月21日  
     */
    int deleteActivityAndProductById(SpecialOffers specialOffers);

    /**
     * @Title: deleteRegulationByActivityId
     * @Description 10502根据活动ID删除满减规则信息
     * @param  SpecialOffers specialOffers
     * @return 
     * @author zhoujiaxin  
     * @createDate 2019年6月21日  
     */
    int deleteRegulationByActivityId(SpecialOffers specialOffers);

    /**
     * @Title: deleteRegulationById
     * @Description 106根据满减表ID删除一条满减规则
     * @param  Regulation regulation
     * @return 
     * @author zhoujiaxin  
     * @createDate 2019年6月21日  
     */
    int deleteRegulationById(Regulation regulation);
    
    /**
     * @Title: updateOneRegulation
     * @Description 108修改一条满减规则
     * @param  Regulation regulation
     * @return Map<String, Object> map 
     * @author zhoujiaxin  
     * @createDate 2019年6月21日  
     */
    int updateOneRegulation(Regulation regulation);

    /**
     * @Title: selectPtAndPBySpecialOffersId
     * @Description 109根据活动ID查询活动商品到活动商品修改页
     * @param  SpecialOffers specialOffers
     * @return List<ProductType> productTypeList
     * @author zhoujiaxin  
     * @createDate 2019年6月24日  
     */
    List<ProductType> selectPtAndPBySpecialOffersId(SpecialOffers specialOffers);

    /**
     * @Title: deleteActivityProductById
     * @Description  101001根据活动ID删除活动商品表信息
     * @param  SpecialOffersDTO specialOffersDTO
     * @return 
     * @author zhoujiaxin  
     * @createDate 2019年6月24日  
     */
    void deleteActivityProductById(SpecialOffersDTO specialOffersDTO);

    /**
     * @Title: listActivitiesActive
     * @Description 99店铺ID查询出动态活动商品表中的商品ID
     * @param  ActivitiesActive activitiesActive
     * @return 
     * @author zhoujiaxin  
     * @createDate 2019年6月26日  
     */
    List<ActivitiesActive> listActivitiesActive(ActivitiesActive activitiesActive);

    /**
     * @Title: listProductAndTypeBefore
     * @description 1141新增一条优惠活动前返回商品分类及商品列表页
     * @param @param productType
     * @return List<ProductType>    
     * @author zhoujiaxin
     * @createDate 2019年7月3日
     */
    List<ProductType> listProductAndTypeBefore(ProductType productType);

    /**
     * @Title: insertActivityProductToAa
     * @description 201907041718根据活动ID添加参加活动的商品信息到动态活动商品表
     * @param activitiesActive  
     * @return void    
     * @author zhoujiaxin
     * @createDate 2019年7月4日
     */
    void insertActivityProductToAa(ActivitiesActive activitiesActive);

    /**
     * @Title: listProductIdByActivityActive
     * @description 201907041802根据活动ID查询出动态商品表中的商品ID集合
     * @param @param specialOffers
     * @return List<Activities>    
     * @author zhoujiaxin
     * @createDate 2019年7月4日
     */
    List<Activities> listProductIdByActivityActive(SpecialOffers specialOffers);

    /**
     * @Title: deleteAaProductIdByActivityId
     * @description 201907041816根据活动ID把动态活动商品表里的数据删除
     * @param @param specialOffers  
     * @return void    
     * @author zhoujiaxin
     * @createDate 2019年7月4日
     */
    void deleteAaProductIdByActivityId(SpecialOffers specialOffers);
    
    /**
     * @Title: updateToBegin
     * @Description 00定时任务每天凌晨把当天未开始活动变更为进行时
     * @param  
     * @return int 
     * @author zhoujiaxin  
     * @createDate 2019年7月8日  
     */
    void UpdateSpecialOffersToBegin(SpecialOffers specialOffers);
    
    /**
     * @Title: updateToEnd
     * @Description 01定时任务每天凌晨把当天进行中活动变更为已结束
     * @param  
     * @return int 
     * @author zhoujiaxin  
     * @createDate 2019年7月8日  
     */
    void UpdateSpecialOffersToEnd(SpecialOffers specialOffers);
    
    /**
     * @Title: listActivityCount
     * @description 活动列表页查询数量总数
     * @param @param specialOffers
     * @param  
     * @return int    
     * @author zhoujiaxin
     * @createDate 2019年7月8日
     */
    int listActivityCount(SpecialOffers specialOffers);

}
