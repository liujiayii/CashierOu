/**
 *
 * @Title: ActivityServiceImpl.java
 * @Package com.cashier.service.impl
 * @Description: TODO)
 * @author zhoujiaxin
 * @date 2019年6月18日
 */

package com.cashier.service.impl;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cashier.dao.ActivityMapper;
import com.cashier.entity.Activities;
import com.cashier.entity.ActivitiesActive;
import com.cashier.entity.ProductType;
import com.cashier.entity.Regulation;
import com.cashier.entity.SpecialOffers;
import com.cashier.entityDTO.SpecialOffersDTO;
import com.cashier.service.ActivityService;

/**
 * 
 * @Description 优惠活动相关操作(活动管理、满减管理、活动商品管理)
 * @author zhoujiaxin  
 * @createDate 2019年6月18日  
 */
@Service
public class ActivityServiceImpl implements ActivityService {
    
    @Resource
    private ActivityMapper activityMapper;

    /**
     * @Title: insertActivity
     * @Description 101新增一条优惠活动
     * @param  SpecialOffersDTO specialOffersDTO
     * @return int   
     * @author zhoujiaxin  
     * @createDate 2019年6月20日  
     */
    @Override
    public int insertActivity(SpecialOffersDTO specialOffersDTO) {
        SpecialOffers specialOffers = new SpecialOffers();
        // 先把与这次活动冲突的活动关闭
        List<BigInteger> activityIds = specialOffersDTO.getActivityIds();
        if (activityIds.size()>0) {
            for (int i = 0; i < activityIds.size(); i++) {
                specialOffers.setId(activityIds.get(i));
                specialOffers.setState(3);
                activityMapper.updateActivityById(specialOffers);
                // 201907041802根据活动ID查询出动态商品表中的商品ID集合
                List<Activities> activitiesList = activityMapper.listProductIdByActivityActive(specialOffers);
                for (int j = 0; j < activitiesList.size(); j++) {
                    Activities activities = activitiesList.get(j);
                    activities.setSpecialOffersId(specialOffers.getId());
                    // 将动态活动商品表的活动集合转移到活动商品表
                    activityMapper.insertActivityProduct(activities);
                    // 201907041816根据活动ID把动态活动商品表里的数据删除
                    activityMapper.deleteAaProductIdByActivityId(specialOffers);
                }
            }
        }
        // 10101向活动表添加一条数据
        specialOffers.setName(specialOffersDTO.getName());
        specialOffers.setScope(specialOffersDTO.getScope());
        specialOffers.setShopId(specialOffersDTO.getShopId());
        specialOffers.setType(specialOffersDTO.getType());
        if (specialOffersDTO.getType()==2) {
            specialOffers.setDiscount(specialOffersDTO.getDiscount());
        }
        specialOffers.setStartTime(new Timestamp(Integer.parseInt(specialOffersDTO.getStartTime())*1000));
        specialOffers.setEndTime(new Timestamp(Integer.parseInt(specialOffersDTO.getEndTime())*1000));
        //数据准备完毕，添加活动
        activityMapper.insertActivity(specialOffers);
        // 根据活动表返回的ID添加活动商品表数据到动态活动商品表
        if (specialOffersDTO.getIds().size()>0) {
            ActivitiesActive activitiesActive = new ActivitiesActive();
            activitiesActive.setActivityId(specialOffers.getId());
            for (int i = 0; i < specialOffersDTO.getIds().size(); i++) {
                activitiesActive.setProductId(specialOffersDTO.getIds().get(i));
                //201907041718根据活动ID添加参加活动的商品信息到动态活动商品表
                activityMapper.insertActivityProductToAa(activitiesActive);
            } 
        }
        // 如果是满减活动添加满减规则
        if (specialOffersDTO.getType()==1 && specialOffersDTO.getRegulations().size()>0) {
            for (int i = 0; i < specialOffersDTO.getRegulations().size(); i++) {
                //10103给满减活动添加满减规则
                Regulation regulation = specialOffersDTO.getRegulations().get(i);
                regulation.setSpecialOffersId(specialOffers.getId());
                activityMapper.insertRegulation(regulation);
            }
        }
        return 1;
    }
    
    /**
     * @Title: listActivity
     * @Description 102查询优惠活动列表
     * @param  SpecialOffers specialOffers,BigInteger shopId
     * @return List<SpecialOffers> specialOffersList
     * @author zhoujiaxin  
     * @createDate 2019年6月20日  
     */
    @Override
    public List<SpecialOffers> listActivity(SpecialOffers specialOffers) {
        // 根据分店ID查询该分店下的活动列表
        return activityMapper.listActivity(specialOffers);
    }

    /**
     * @Title: selectActivityInfoById
     * @Description 103根据活动ID查询参加活动的商品或满减规则
     * @param  SpecialOffers specialOffers
     * @return 
     * @author zhoujiaxin  
     * @createDate 2019年6月20日  
     */
    @Override
    public Map<String, Object> selectActivityInfoById(SpecialOffers specialOffers) {
        // 根据商品活动级别查询对应的商品列表 1.通用级别 2.分类级别3.商品级别
        //10302查询商品列表根据活动ID
        Map<String, Object> map = new HashMap<>();
        List<ProductType> productTypes = activityMapper.listProductTypeAndProductByActivityId(specialOffers);
        map.put("productList", productTypes);
        if (specialOffers.getType()==1) {
            //10303根据满减活动ID查询该活动的满减规则
            List<Regulation> regulationList = activityMapper.listRegulation(specialOffers);
            map.put("regulationList", regulationList);
        }
        map.put("code", 1);
        map.put("message", "查询成功");
        return map;
    }
    
    /**
     * @Title: updateActivityById
     * @Description 104根据活动ID修改某条活动
     * @param  SpecialOffers specialOffers
     * @return 
     * @author zhoujiaxin  
     * @createDate 2019年6月21日  
     */
    @Override
    public int updateActivityById(SpecialOffers specialOffers) {
        int result = activityMapper.updateActivityById(specialOffers);
        return 0;
    }

    /**
     * @Title: deleteActivityById
     * @Description 105根据活动ID删除某条活动和活动商品和满减规则
     * @param  SpecialOffers specialOffers
     * @return 
     * @author zhoujiaxin  
     * @createDate 2019年6月21日  
     */
    @Override
    public int deleteActivityById(SpecialOffers specialOffers) {
        // 10501根据活动ID删除活动表一条信息和活动商品表对应信息
        int result = activityMapper.deleteActivityAndProductById(specialOffers);
        if (specialOffers.getType()==1) {
            // 10502根据活动ID删除满减规则信息
            int resultRegulation = activityMapper.deleteRegulationByActivityId(specialOffers);
        }
        
        return 0;
    }

    /**
     * @Title: deleteRegulationById
     * @Description 106根据满减表ID删除一条满减规则
     * @param  Regulation regulation
     * @return 
     * @author zhoujiaxin  
     * @createDate 2019年6月21日  
     */
    @Override
    public int deleteRegulationById(Regulation regulation) {
        int result = activityMapper.deleteRegulationById(regulation);
        return 0;
    }

    /**
     * @Title: insertOneRegulation
     * @Description 107添加一条满减规则
     * @param  Regulation regulation
     * @return 
     * @author zhoujiaxin  
     * @createDate 2019年6月21日  
     */
    @Override
    public int insertOneRegulation(Regulation regulation) {
        activityMapper.insertRegulation(regulation);
        return 0;
    }

    /**
     * @Title: updateOneRegulation
     * @Description 108修改一条满减规则
     * @param  Regulation regulation
     * @return Map<String, Object> map 
     * @author zhoujiaxin  
     * @createDate 2019年6月21日  
     */
    @Override
    public Map<String, Object> updateOneRegulation(Regulation regulation) {
        /*// 通过活动ID查询出该活动下的满减规则
        SpecialOffers specialOffers = new SpecialOffers();
        specialOffers.setId(regulation.getSpecialOffersId());
        List<Regulation> regulations = activityMapper.listRegulation(specialOffers);
        //检验插入的新数据是否符合已有数据中的规则
        for (int j = 0; j < regulations.size(); j++) {
            //集合从小到大排序，
            Regulation compareParam = regulations.get(j); 
            if (regulation.getMoney().compareTo(compareParam.getMoney())==0 && regulation.getId().compareTo(regulations.get(j).getId()) != 0 ) {
                return null;//错误：满金额重复
            }else if (regulation.getMoney().compareTo(compareParam.getMoney())==1) {
                if (regulation.getReduceMoney().compareTo(compareParam.getReduceMoney())==-1) {
                    return null;//错误：满金额和减金额走势曲线不成正比
                }
            }else {
                if (regulation.getReduceMoney().compareTo(compareParam.getReduceMoney())==1) {
                    return null;//错误：满金额和减金额走势曲线不成正比
                }
                break;
            }
        }*/
        // 通过满减规则ID修改一条满减规则
        Map<String, Object> map = new HashMap<>();
        int result = activityMapper.updateOneRegulation(regulation);
        if (result==1) {
            map.put("code", 1);
            map.put("message", "修改成功");
            return map;    
        }else{
            map.put("code", -1);
            map.put("message", "修改失败");
            return map;  
        }
    }

    /**
     * @Title: selectPtAndPBySpecialOffersId
     * @Description 109根据活动ID查询活动商品到活动商品修改页
     * @param  SpecialOffers specialOffers
     * @return List<ProductType> productTypeList
     * @author zhoujiaxin  
     * @createDate 2019年6月24日  
     */
    @Override
    public Map<String, Object> selectPtAndPBySpecialOffersId(SpecialOffers specialOffers) {
        Map<String, Object> map = new HashMap<>();
        List<ProductType> productTypeList = activityMapper.selectPtAndPBySpecialOffersId(specialOffers);
        map.put("code", 1);
        map.put("message", "查询成功");
        map.put("data", productTypeList);
        return map; 
    }

    /**
     * @Title: updateProductBySpecialOffersId
     * @Description 1010根据活动ID更新活动商品表
     * @param  SpecialOffers specialOffers
     * @return 
     * @author zhoujiaxin  
     * @createDate 2019年6月24日  
     */
    @Override
    public Map<String, Object> updateProductBySpecialOffersId(SpecialOffersDTO specialOffersDTO) {
        Map<String, Object> map = new HashMap<>();
        // 根据活动表返回的ID添加动态活动商品表数据
        if (specialOffersDTO.getIds().size()>0) {
         // 101001根据活动ID删除动态活动商品表信息
            SpecialOffers specialOffers = new SpecialOffers();specialOffers.setId(specialOffersDTO.getId());
            activityMapper.deleteAaProductIdByActivityId(specialOffers);
            
            ActivitiesActive activitiesActive = new ActivitiesActive();
            activitiesActive.setActivityId(specialOffersDTO.getId());
            for (int i = 0; i < specialOffersDTO.getIds().size(); i++) {
                activitiesActive.setProductId(specialOffersDTO.getIds().get(i));
                //10102根据活动ID添加参加动态活动的商品信息
                activityMapper.insertActivityProductToAa(activitiesActive);
            } 
            map.put("code", 1);
            map.put("message", "更新成功");
            return map; 
        }
        map.put("code", -1);
        map.put("message", "更新失败");
        return map; 
    }

    /**
     * @Title: listActivitiesActive
     * @Description 99店铺ID查询出动态活动商品表中的商品ID
     * @param  ActivitiesActive activitiesActive
     * @return 
     * @author zhoujiaxin  
     * @createDate 2019年6月26日  
     */
    @Override
    public List<ActivitiesActive> listActivitiesActive(ActivitiesActive activitiesActive) {
        return activityMapper.listActivitiesActive(activitiesActive);
    }

    /**
     * @Title: listProductAndTypeBefore
     * @description 1141新增一条优惠活动前返回商品分类及商品列表页
     * @param @param productType
     * @return List<ProductType>    
     * @author zhoujiaxin
     * @createDate 2019年7月3日
     */
    @Override
    public List<ProductType> listProductAndTypeBefore(ProductType productType) {
        return activityMapper.listProductAndTypeBefore(productType);
    }

    /**
     * @Title: beginSpecialOffersById
     * @description 1732手动开始活动
     * @param @param specialOffers
     * @param @return  
     * @return Map<String,Object>    
     * @author zhoujiaxin
     * @createDate 2019年7月5日
     */
    @Override
    public Map<String, Object> beginSpecialOffersById(SpecialOffers specialOffers) {
        Map<String, Object> map = new HashMap<>();
        specialOffers.setState(2);
        int result = activityMapper.updateActivityById(specialOffers);
        if (result==1) {
            map.put("code", 1);
            map.put("message", "更改成功");
            return map; 
        }else{
            map.put("code", -1);
            map.put("message", "更改失败");
            return map; 
        }
    }

    /**
     * @Title: endSpecialOffersById
     * @Description 1742手动结束活动
     * @param  SpecialOffers specialOffers
     * @return 
     * @author zhoujiaxin  
     * @createDate 2019年07月05日  
     */
    @Override
    public Map<String, Object> endSpecialOffersById(SpecialOffers specialOffers) {
        Map<String, Object> map = new HashMap<>();
        specialOffers.setState(3);
        activityMapper.updateActivityById(specialOffers);
        // 201907041802根据活动ID查询出动态商品表中的商品ID集合
        List<Activities> activitiesList = activityMapper.listProductIdByActivityActive(specialOffers);
        for (int j = 0; j < activitiesList.size(); j++) {
            Activities activities = activitiesList.get(j);
            activities.setSpecialOffersId(specialOffers.getId());
            // 将动态活动商品表的活动集合转移到活动商品表
            activityMapper.insertActivityProduct(activities);
            // 201907041816根据活动ID把动态活动商品表里的数据删除
            activityMapper.deleteAaProductIdByActivityId(specialOffers);
        }
        map.put("code", 1);
        map.put("message", "更改成功");
        return map; 
    }

    
}
