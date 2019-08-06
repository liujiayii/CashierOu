/**
 *
 * @Title: ActivityService.java
 * @Package com.cashier.service
 * @Description: TODO)
 * @author zhoujiaxin
 * @date 2019年6月18日
 */

package com.cashier.service;

import java.util.List;
import java.util.Map;

import com.cashier.entity.ActivitiesActive;
import com.cashier.entity.ProductType;
import com.cashier.entity.Regulation;
import com.cashier.entity.SpecialOffers;
import com.cashier.entityDTO.SpecialOffersDTO;

/**
 * 
 * @Description 优惠活动相关操作(活动管理、满减管理、活动商品管理)
 * @author zhoujiaxin  
 * @createDate 2019年6月18日  
 */
public interface ActivityService {

    /**
     * @Title: insertActivity
     * @Description 101新增一条优惠活动
     * @param  SpecialOffersDTO specialOffersDTO
     * @return int   
     * @author zhoujiaxin  
     * @createDate 2019年6月20日  
     */
    int insertActivity(SpecialOffersDTO specialOffersDTO);

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
     * @Title: selectActivityInfoById
     * @Description 103根据活动ID查询参加活动的商品或满减规则
     * @param  SpecialOffers specialOffers
     * @return 
     * @author zhoujiaxin  
     * @createDate 2019年6月20日  
     */
    Map<String, Object> selectActivityInfoById(SpecialOffers specialOffers);
    
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
     * @Title: deleteActivityById
     * @Description 105根据活动ID删除某条活动和活动商品和满减规则
     * @param  SpecialOffers specialOffers
     * @return 
     * @author zhoujiaxin  
     * @createDate 2019年6月21日  
     */
    int deleteActivityById(SpecialOffers specialOffers);

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
     * @Title: insertOneRegulation
     * @Description 107添加一条满减规则
     * @param  Regulation regulation
     * @return 
     * @author zhoujiaxin  
     * @createDate 2019年6月21日  
     */
    int insertOneRegulation(Regulation regulation);

    /**
     * @Title: updateOneRegulation
     * @Description 108修改一条满减规则
     * @param  Regulation regulation
     * @return Map<String, Object> map 
     * @author zhoujiaxin  
     * @createDate 2019年6月21日  
     */
    Map<String, Object> updateOneRegulation(Regulation regulation);

    /**
     * @Title: selectPtAndPBySpecialOffersId
     * @Description 109根据活动ID查询活动商品到活动商品修改页
     * @param  SpecialOffers specialOffers
     * @return 
     * @author zhoujiaxin  
     * @createDate 2019年6月24日  
     */
    Map<String, Object> selectPtAndPBySpecialOffersId(SpecialOffers specialOffers);

    /**
     * @Title: updateProductBySpecialOffersId
     * @Description 1010根据活动ID更新活动商品表
     * @param  SpecialOffers specialOffers
     * @return 
     * @author zhoujiaxin  
     * @createDate 2019年6月24日  
     */
    Map<String, Object> updateProductBySpecialOffersId(SpecialOffersDTO specialOffersDTO);

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
     * @Title: beginSpecialOffersById
     * @description 1732手动开始活动
     * @param @param specialOffers
     * @param @return  
     * @return Map<String,Object>    
     * @author zhoujiaxin
     * @createDate 2019年7月5日
     */
    Map<String, Object> beginSpecialOffersById(SpecialOffers specialOffers);

    /**
     * @Title: endSpecialOffersById
     * @Description 1742手动结束活动
     * @param  SpecialOffers specialOffers
     * @return 
     * @author zhoujiaxin  
     * @createDate 2019年07月05日  
     */
    Map<String, Object> endSpecialOffersById(SpecialOffers specialOffers);

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

    /**
     * @Title: listActivitiesActiveNoAid
     * @description 修改折扣或满减活动前先检验添加的商品其他活动添加过没有
     * @param @param activitiesActive
     * @param @return  
     * @return List<ActivitiesActive>    
     * @author zhoujiaxin
     * @createDate 2019年8月2日
     */
    List<ActivitiesActive> listActivitiesActiveNoAid(ActivitiesActive activitiesActive);

    
   

    
    
}
