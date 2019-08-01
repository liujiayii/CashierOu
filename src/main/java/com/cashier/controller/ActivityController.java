/**
 *
 * @Title: ActivityController.java
 * @Package com.cashier.controller
 * @Description: TODO)
 * @author zhoujiaxin
 * @date 2019年6月18日
 */

package com.cashier.controller;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cashier.entity.ActivitiesActive;
import com.cashier.entity.ProductType;
import com.cashier.entity.Regulation;
import com.cashier.entity.SpecialOffers;
import com.cashier.entityDTO.RegulationDTO;
import com.cashier.entityDTO.SpecialOffersDTO;
import com.cashier.service.ActivityService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * @Description 优惠活动相关操作(活动管理、满减管理、活动商品管理)
 * @author zhoujiaxin  
 * @createDate 2019年6月18日  
 */
@Controller
public class ActivityController {
    
    @Resource
    private ActivityService activityService;
    
    /**
     * @Title: updateToBegin
     * @Description 00定时任务每天凌晨把当天未开始活动变更为进行时
     * @param  
     * @return int 
     * @author zhoujiaxin  
     * @createDate 2019年7月8日  
     */
    @Scheduled(cron = "0 00 00 * * ?")
    public void updateToBegin(){
        Calendar instance = Calendar.getInstance();
        long timeInMillis = instance.getTimeInMillis();
        Timestamp startTime = new Timestamp(timeInMillis);
        Timestamp endTime = new Timestamp(timeInMillis+(1000*3600*24)-1);
        SpecialOffers specialOffers = new SpecialOffers();
        specialOffers.setStartTime(startTime);
        specialOffers.setEndTime(endTime);
        activityService.UpdateSpecialOffersToBegin(specialOffers);
    }
    
    /**
     * @Title: updateToEnd
     * @Description 01定时任务每天凌晨把当天进行中活动变更为已结束
     * @param  
     * @return int 
     * @author zhoujiaxin  
     * @createDate 2019年7月8日  
     */
    @Scheduled(cron = "0 00 00 * * ?")
    public void updateToEnd(){
        Calendar instance = Calendar.getInstance();
        long timeInMillis = instance.getTimeInMillis();
        Timestamp startTime = new Timestamp(timeInMillis-(1000*3600*24));
        Timestamp endTime = new Timestamp(timeInMillis-1);
        SpecialOffers specialOffers = new SpecialOffers();
        specialOffers.setStartTime(startTime);
        specialOffers.setEndTime(endTime);
        activityService.UpdateSpecialOffersToEnd(specialOffers);
    }
    
    /**
     * @Title: checkUpdateActivityProduct
     * @Description 98修改折扣或满减活动前先检验添加的商品其他活动添加过没有
     * @param  
     * @return int 
     * @author zhoujiaxin  
     * @createDate 2019年6月26日  
     */
    @RequestMapping("/checkUpdateActivityProduct")
    public String checkUpdateActivityProduct(Model model,ActivitiesActive activitiesActive,HttpSession session,@RequestParam("productIdArray[]") List<BigInteger> productIdArray,@RequestParam("productNameArray[]") List<String> productNameArray){
        // 通过session获取shopId保存到活动表
        BigInteger shopId = (BigInteger) session.getAttribute("shopId");
        activitiesActive.setShopId(shopId);
        List<ActivitiesActive> activitiesActiveList = activityService.listActivitiesActive(activitiesActive);
        if (activitiesActiveList.size()>0) {
            List<String> productNameList = new ArrayList<>();
            for (int i = 0; i < activitiesActiveList.size(); i++) {
                ActivitiesActive activitiesActive2 = activitiesActiveList.get(i);
                if (productIdArray.indexOf(activitiesActive2.getProductId())==-1) {
                    productNameList.add(productNameArray.get(i));
                }
            }
            if (productNameList.size()>0) {
                return"商品有重复，报错返回";
            }else {
                return"正常";
            }
        }else {
            return "正常";
        }
    }
    
    /**
     * @Title: checkAddActivityProduct
     * @Description 99添加折扣或满减活动前先检验添加的商品其他活动添加过没有
     * @param  
     * @return int 
     * @author zhoujiaxin  
     * @createDate 2019年6月26日  
     */
    @RequestMapping("/checkAddActivityProduct")
    @ResponseBody
    public Map<String, Object> checkAddActivityProduct(Model model,ActivitiesActive activitiesActive,HttpSession session,String productIdArray){
        // 通过session获取shopId保存到活动表      [hello, world, java]
        Map<String, Object> map = new HashMap<>();
        if (activitiesActive.getShopId()==null) {
         // 通过session获取shopId保存到活动表
            BigInteger shopId = (BigInteger) session.getAttribute("shopId");
            if (shopId==null) {
                map.put("code", -2);
                map.put("msg", "店铺ID获取失败，请重新登录");
                return map;
            }
            activitiesActive.setShopId(shopId);
        }
        List<ActivitiesActive> activitiesActiveList = activityService.listActivitiesActive(activitiesActive);
        if (activitiesActiveList.size()>0) {
            // 排查出将要添加的活动和已经添加的活动是否冲突，如果出现冲突，查询出已经添加的活动，如果强制添加这次活动，与之冲突的活动将关闭或结束
            Map<BigInteger, String> map2 = new HashMap<>();
             for (int i = 0; i < activitiesActiveList.size(); i++) {
                ActivitiesActive activitiesActive2 = activitiesActiveList.get(i);
                String[] productIdStrArray = productIdArray.split(",");
                for (int j = 0; j < productIdStrArray.length; j++) {
                    if (activitiesActive2.getProductId().compareTo(new BigInteger(productIdStrArray[j]))==0) {
                        map2.put(activitiesActive2.getActivityId(), activitiesActive2.getActivityName());
                    }
                }
            }
            if (map2.size()>0) {
                map.put("code", -1);
                map.put("msg", "参加活动的商品重复");
                List<SpecialOffers> specialOffersList = new ArrayList<>();
                SpecialOffers specialOffers = new SpecialOffers();
                //键找值遍历
                Set<BigInteger> set1=map2.keySet();
                for(BigInteger key:set1) {
                    specialOffers.setId(key);
                    specialOffers.setName(map2.get(key));
                    specialOffersList.add(specialOffers);
                }
                map.put("data", specialOffersList);
                return map;
            }else {
                map.put("code", 1);
                map.put("msg", "参加活动的商品可以添加");
                return map;
            }
        }else {
            map.put("code", 1);
            map.put("msg", "参加活动的商品可以添加");
            return map;
        }
    }
    
    /**
     * @Title: checkFullReduce
     * @Description 100添加满减活动前先检验添加的满减规则是否合规（满金额不允许重复，满金额和减金额走势曲线成正比）
     * @param  
     * @return int 
     * @author zhoujiaxin  
     * @createDate 2019年6月20日  
     */
    @RequestMapping("/checkFullReduce")
    @ResponseBody
    public Map<String, Object> checkFullReduce(Model model,int[] fullArray,int[] reduceArray){
        Map<String, Object> map = new HashMap<>();
        if(fullArray.length>0 && reduceArray.length>0 && fullArray.length == reduceArray.length){
            List<RegulationDTO> regulationDTOList = new ArrayList<>();
            for (int i = 0; i < fullArray.length; i++) {
                RegulationDTO regulationDTO = new RegulationDTO();
                regulationDTO.setMoney(fullArray[i]);regulationDTO.setReduceMoney(reduceArray[i]);
                if(regulationDTOList.size()==0){
                    regulationDTOList.add(regulationDTO);
                }else{
                    for (int j = 0; j < regulationDTOList.size(); j++) {
                        //集合从小到大排序，
                        RegulationDTO compareParam = regulationDTOList.get(j); 
                        if (regulationDTO.getMoney().equals(compareParam.getMoney())) {
                            map.put("code", -1);
                            map.put("msg", "满金额重复");
                            return map;
                        }else if (regulationDTO.getMoney()>compareParam.getMoney()) {
                            if (regulationDTO.getReduceMoney()<compareParam.getReduceMoney()) {
                                map.put("code", -1);
                                map.put("msg", "满金额和减金额走势曲线不成正比");
                                return map;
                            }
                            //如果比到最后一个就直接添加到最后
                            if (j==regulationDTOList.size()-1) {
                                regulationDTOList.add(j+1,regulationDTO);
                                break;
                            }
                        }else {
                            if (regulationDTO.getReduceMoney()>compareParam.getReduceMoney()) {
                                map.put("code", -1);
                                map.put("msg", "满金额和减金额走势曲线不成正比");
                                return map;
                            }
                            regulationDTOList.add(j,regulationDTO);
                            break;
                        }
                    }
                }
            }
            map.put("code", 1);
            map.put("msg", "满减规则没有问题");
            return map;
        }
        map.put("code", -1);
        map.put("msg", "数据传输异常，请从新传输");
        return map;
    }
    
    /**
     * @Title: listProductAndTypeBefore
     * @Description 1141新增一条优惠活动前返回商品分类及商品列表页
     * @param  BigInteger shopId
     * @return map
     * @author zhoujiaxin  
     * @createDate 2019年7月03日  
     */
    @PostMapping("/listProductAndTypeBefore")
    @ResponseBody
    public Map<String, Object> listProductAndTypeBefore(Model model,ProductType productType,HttpSession session,BigInteger shopId){
        Map<String, Object> map = new HashMap<>();
        if (shopId==null) {
         // 通过session获取shopId保存到活动表
            shopId = (BigInteger) session.getAttribute("shopId");
            if (shopId==null) {
                map.put("code", -2);
                map.put("msg", "店铺ID获取失败，请重新登录");
                return map;
            }
        }
        productType.setShopId(shopId);
        List<ProductType> productTypes = activityService.listProductAndTypeBefore(productType);
        map.put("data", productTypes);
        map.put("code", 1);
        map.put("msg", "查询成功");
        return map;
    }
    /**
     * @Title: insertActivity
     * @Description 101新增一条优惠活动
     * @param  SpecialOffersDTO specialOffersDTO,BigInteger shopId
     * @return int   
     * @author zhoujiaxin  
     * @createDate 2019年6月20日  
     */
    @RequestMapping("/insertActivity")
    @ResponseBody
    public Map<String, Object> insertActivity(Model model,HttpSession session,SpecialOffersDTO specialOffersDTO,String jsonstr,String myRegulations){
        JSONObject jsonObject=JSONObject.fromObject(jsonstr);
        specialOffersDTO = (SpecialOffersDTO)JSONObject.toBean(jsonObject, SpecialOffersDTO.class);
        JSONArray jsonArray = JSONArray.fromObject(myRegulations);
        List<Regulation> list = JSONArray.toList(jsonArray,new Regulation(),new JsonConfig());
        specialOffersDTO.setRegulations(list);
        Map<String, Object> map = new HashMap<>();
        if (specialOffersDTO.getShopId()==null) {
            // 通过session获取shopId保存到活动表
            BigInteger shopId = (BigInteger) session.getAttribute("shopId");
            if (shopId==null) {
                map.put("code", -2);
                map.put("msg", "店铺ID获取失败，请重新登陆");
                return map;
            }
            specialOffersDTO.setShopId(shopId);
        }
        // 添加一条活动和活动商品，如果是满减活动会添加满减规则
        int result = activityService.insertActivity(specialOffersDTO);
        if (result==1) {
            map.put("code", 1);
            map.put("msg", "活动添加成功");
            return map;
        }else{
            map.put("code", -1);
            map.put("msg", "活动添加失败");
            return map;
        }
    }
    
    /**
     * @Title: listActivity
     * @Description 102查询优惠活动列表+条件查询
     * @param  SpecialOffers specialOffers,BigInteger shopId
     * @return List<SpecialOffers> specialOffersList
     * @author zhoujiaxin  
     * @createDate 2019年6月20日  
     */
    @RequestMapping("/listActivity")
    @ResponseBody
    public Map<String, Object> listActivity(Model model,HttpSession session,SpecialOffers specialOffers,
            @RequestParam(name = "page", defaultValue = "1") int page,String timeStr){
            if (timeStr!="" && timeStr!=null) {
                specialOffers.setName(timeStr);
            }
        Map<String, Object> map = new HashMap<>();
        if (specialOffers.getShopId()==null) {
            // 通过session获取shopId保存到活动表
            BigInteger shopId = (BigInteger) session.getAttribute("shopId");
            if (shopId==null) {
                map.put("code", -2);
                map.put("msg", "店铺ID获取失败，请重新登陆");
                return map;
            }
            specialOffers.setShopId(shopId);
        }
        // 分页时起始值
        specialOffers.setBeginNum((page-1)*specialOffers.getLimit());
        List<SpecialOffers> specialOffersList = activityService.listActivity(specialOffers);
        int count = activityService.listActivityCount(specialOffers);
        map.put("count", count);
        map.put("code", 1);
        map.put("msg", "查询成功");
        map.put("data", specialOffersList);
        return map;
    }
    
    /**
     * @Title: selectActivityInfoById
     * @Description 103根据活动ID查询参加活动的商品或满减规则
     * @param  SpecialOffers specialOffers
     * @return  productList   RegulationList
     * @author zhoujiaxin  
     * @createDate 2019年6月20日  
     */
    @RequestMapping("/selectActivityInfoById")
    @ResponseBody
    public Map<String, Object> selectActivityInfoById(Model model,SpecialOffers specialOffers){
        return activityService.selectActivityInfoById(specialOffers);
    }
    
    /**
     * @Title: updateActivityById
     * @Description 104根据活动ID修改某条活动
     * @param  SpecialOffers specialOffers
     * @return 
     * @author zhoujiaxin  
     * @createDate 2019年6月21日  
     */
    @RequestMapping("/updateActivityById")
    @ResponseBody
    public Map<String, Object> updateActivityById(Model model,HttpSession session,SpecialOffers specialOffers){
        Map<String, Object> map = new HashMap<>();
        if (specialOffers.getShopId()==null) {
            // 通过session获取shopId保存到活动表
            BigInteger shopId = (BigInteger) session.getAttribute("shopId");
            if (shopId==null) {
                map.put("code", -2);
                map.put("msg", "店铺ID获取失败，请重新登录");
                return map;
            }
            specialOffers.setShopId(shopId);
        }
        int result = activityService.updateActivityById(specialOffers);
        if (result==1) {
            map.put("code", 1);
            map.put("msg", "修改成功");
            return map;
        }else {
            map.put("code", -1);
            map.put("msg", "修改失败");
            return map;
        }
        
    }
    /**
     * @Title: deleteActivityById
     * @Description 105根据活动ID删除某条活动和活动商品和满减规则
     * @param  SpecialOffers specialOffers
     * @return 
     * @author zhoujiaxin  
     * @createDate 2019年6月21日  
     */
    @RequestMapping("/deleteActivityById")
    public String deleteActivityById(Model model,SpecialOffers specialOffers){
        //int result = activityService.deleteActivityById(specialOffers);
        return "";
    }
    /**
     * @Title: deleteRegulationById
     * @Description 106根据满减表ID删除一条满减规则
     * @param  Regulation regulation
     * @return int
     * @author zhoujiaxin  
     * @createDate 2019年6月21日  
     */
    @RequestMapping("/deleteRegulationById")
    @ResponseBody
    public Map<String, Object> deleteRegulationById(Model model,Regulation regulation){
        Map<String, Object> map = new HashMap<>();
        int result = activityService.deleteRegulationById(regulation);
        if (result==1) {
            map.put("code", 1);
            map.put("msg", "删除成功");
            return map;    
        }else{
            map.put("code", -1);
            map.put("msg", "删除失败");
            return map;  
        }
    }
    /**
     * @Title: insertOneRegulation
     * @Description 107添加一条满减规则
     * @param  Regulation regulation
     * @return int
     * @author zhoujiaxin  
     * @createDate 2019年6月21日  
     */
    @RequestMapping("/insertOneRegulation")
    @ResponseBody
    public Map<String, Object> insertOneRegulation(Model model,Regulation regulation){
        Map<String, Object> map = new HashMap<>();
        int result = activityService.insertOneRegulation(regulation);
        if (result==1) {
            map.put("code", 1);
            map.put("msg", "添加成功");
            return map;    
        }else{
            map.put("code", -1);
            map.put("msg", "添加失败");
            return map;  
        }
    }
    /**
     * @Title: updateOneRegulation
     * @Description 108修改一条满减规则
     * @param  Regulation regulation
     * @return Map<String, Object> map 
     * @author zhoujiaxin  
     * @createDate 2019年6月21日  
     */
    @RequestMapping("/updateOneRegulation")
    @ResponseBody
    public Map<String, Object> updateOneRegulation(Model model,Regulation regulation){
        return activityService.updateOneRegulation(regulation);
    }
    
    /**
     * @Title: selectPtAndPBySpecialOffersId
     * @Description 109根据活动ID查询活动商品到活动商品修改页
     * @param  SpecialOffers specialOffers
     * @return map
     * @author zhoujiaxin  
     * @createDate 2019年6月24日  
     */
    @RequestMapping("/selectPtAndPBySpecialOffersId")
    @ResponseBody
    public Map<String, Object> selectPtAndPBySpecialOffersId(Model model,SpecialOffers specialOffers,HttpSession session){
        if (specialOffers.getShopId()==null) {
            // 通过session获取shopId保存到活动表
            Map<String, Object> map = new HashMap<>();
            BigInteger shopId = (BigInteger) session.getAttribute("shopId");
            if (shopId==null) {
                map.put("code", -2);
                map.put("msg", "店铺ID获取失败，请重新登录");
                return map;
            }
            specialOffers.setShopId(shopId);
        }
        return activityService.selectPtAndPBySpecialOffersId(specialOffers);
    }
    
    /**
     * @Title: updateProductBySpecialOffersId
     * @Description 1010根据活动ID更新活动商品表
     * @param  SpecialOffers specialOffers
     * @return 
     * @author zhoujiaxin  
     * @createDate 2019年6月24日  
     */
    @RequestMapping("/updateProductBySpecialOffersId")
    @ResponseBody
    public Map<String, Object> updateProductBySpecialOffersId(Model model,SpecialOffersDTO specialOffersDTO){
        return activityService.updateProductBySpecialOffersId(specialOffersDTO);
    }
    
    /**
     * @Title: beginSpecialOffersById
     * @Description 1732手动开始活动
     * @param  SpecialOffers specialOffers
     * @return 
     * @author zhoujiaxin  
     * @createDate 2019年07月05日  
     */
    @RequestMapping("/beginSpecialOffersById")
    @ResponseBody
    public Map<String, Object> beginSpecialOffersById(Model model,SpecialOffers specialOffers){
        return activityService.beginSpecialOffersById(specialOffers);
    }
    
    /**
     * @Title: endSpecialOffersById
     * @Description 1742手动结束活动
     * @param  SpecialOffers specialOffers
     * @return 
     * @author zhoujiaxin  
     * @createDate 2019年07月05日  
     */
    @RequestMapping("/endSpecialOffersById")
    @ResponseBody
    public Map<String, Object> endSpecialOffersById(Model model,SpecialOffers specialOffers){
        return activityService.endSpecialOffersById(specialOffers);
    }
}
