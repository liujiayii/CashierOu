package com.cashier.controller;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cashier.dao.InventoryMapper;
import com.cashier.dao.ProductMapper;
import com.cashier.entity.GoodstrafficManagement;
import com.cashier.entity.Inventory;
import com.cashier.entityDTO.GoodstrafficManagementDTO;
import com.cashier.entityVo.AddsubscriptionVo;
import com.cashier.entityVo.GoodstrafficManagementVo;
import com.cashier.service.GoodstrafficManagementService;
import com.cashier.service.ShopService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class GoodstrafficManagementController {
    @Autowired
    private GoodstrafficManagementService goodstrafficManagementService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private InventoryMapper inventoryMapper;
    @Autowired
    private ProductMapper productMapper;
    /**
     * 
     * @Title: listProcurement
     * @description 查看采购订单
     * @param goodstrafficManagementDTO
     * @return BigInteger
     * @author chenshuxian
     * @createDate 2019年6月19
     */
    @RequestMapping("/listProcurement")
    @ResponseBody
    public Map<String, Object> listProcurement(GoodstrafficManagementDTO goodstrafficManagementDTO,
            HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        try {
            BigInteger shopId = (BigInteger) session.getAttribute("shopId");
            goodstrafficManagementDTO.setShopId(shopId);
            goodstrafficManagementDTO
                    .setPage((goodstrafficManagementDTO.getPage() - 1) * goodstrafficManagementDTO.getLimit());
            List<GoodstrafficManagementVo> listProcurement = goodstrafficManagementService
                    .listProcurement(goodstrafficManagementDTO);
            int count = goodstrafficManagementService.listProcurementCount(goodstrafficManagementDTO);
            map.put("code", 1);
            map.put("msg", "显示成功");
            map.put("data", listProcurement);
            map.put("count", count);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", 0);
            map.put("msg", "方法错误");
        }

        return map;
    }

    /**
     * @Title: addprocurement
     * @description 添加采购订单、调拨订单（分店先去找总店调拨，总店可以选择给或者转发给其他分店）zhoujiaixin 20190730
     * @param goodstrafficManagement
     * @return Map<String,Object>
     * @author chenshuxian  
     * @createDate 2019年6月19日
     */
    @RequestMapping("/addprocurement")
    @ResponseBody
    public Map<String, Object> addprocurement(GoodstrafficManagement goodstrafficManagement, HttpSession session,String g,String time) {
        Map<String, Object> map = new HashMap<>();
        Timestamp ts = new Timestamp(System.currentTimeMillis());  
        try {
            ts = Timestamp.valueOf(time);  
            goodstrafficManagement.setDeliveryDate(ts);
            BigInteger shopId = (BigInteger) session.getAttribute("shopId");
            // 判断他是调拨还是采购
            if (goodstrafficManagement.getGoodstrafficState()==1) {
                // 采购
                if (goodstrafficManagement.getTransportationState()==null) {
                    goodstrafficManagement.setTransportationState(5);
                }
                goodstrafficManagementService.addprocurement(goodstrafficManagement,g,shopId);
            }else{
                // 调拨
                if (goodstrafficManagement.getTransportationState()==null) {
                    goodstrafficManagement.setTransportationState(1);
                }
                goodstrafficManagementService.addprocurement(goodstrafficManagement,g,shopId);
                //Integer id = goodstrafficManagementService.selectSubscribe(goodstrafficManagement.getId());
                /*if(id != goodstrafficManagement.getTransportationState()){
                    goodstrafficManagementService.addprocurement(goodstrafficManagement,g,shopId);
                }else{
                    map.put("code", 0);
                    map.put("msg", "状态已变更，请刷新");
                    return map;
                }*/
            }
            map.put("code", 1);
            map.put("msg", "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", 0);
            map.put("msg", "方法错误");
        }

        return map;
    }

    /**
     * 
     * @Title: listProductAndProductType
     * @description 采购商品显示
     * @param id
     * @return List<AddsubscriptionVo>
     * @author liujunkai
     * @createDate 2019年7月5日
     */
    @RequestMapping("/listProductAndProductType")
    @ResponseBody
    public Map<String, Object> listProductAndProductType() {
        Map<String, Object> map = new HashMap<>();
        try {
            List<AddsubscriptionVo> listProductAndProductType = 
                    goodstrafficManagementService.listProductAndProductType();
            map.put("code", 1);
            map.put("data", listProductAndProductType);
            map.put("msg", "显示成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", 0);
            map.put("msg", "方法错误");
        }

        return map;
    }

    /**
     * 
     * @Title: listReceivingShopName
     * @description 送货页面
     * @param goodstrafficManagementDTO
     * @return List<GoodstrafficManagementVo>
     * @author liujunkai
     * @createDate 2019年7月5日
     */
    @RequestMapping("/listReceivingShopName")
    @ResponseBody
    public Map<String, Object> listReceivingShopName(GoodstrafficManagementDTO goodstrafficManagementDTO,
            HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        try {
            BigInteger shopId = (BigInteger) session.getAttribute("shopId");
            goodstrafficManagementDTO.setShopId(shopId);
            goodstrafficManagementDTO
                    .setPage((goodstrafficManagementDTO.getPage() - 1) * goodstrafficManagementDTO.getLimit());
            List<GoodstrafficManagementVo> listReceivingShopName = goodstrafficManagementService
                    .listReceivingShopName(goodstrafficManagementDTO);
            int count = goodstrafficManagementService.listReceivingShopNameCount(goodstrafficManagementDTO);
            map.put("code", 1);
            map.put("msg", "显示成功");
            map.put("data", listReceivingShopName);
            map.put("count", count);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", 0);
            map.put("msg", "方法错误");
        }

        return map;
    }

    /**
     * 
     * @Title: listShipmentsShopName
     * @description 发起页面
     * @param goodstrafficManagementDTO
     * @return List<GoodstrafficManagementVo>
     * @author liujunkai
     * @createDate 2019年7月5日
     */
    @RequestMapping("/listShipmentsShopName")
    @ResponseBody
    public Map<String, Object> listShipmentsShopName(GoodstrafficManagementDTO goodstrafficManagementDTO,
            HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        try {
            BigInteger shopId = (BigInteger) session.getAttribute("shopId");
            goodstrafficManagementDTO.setShopId(shopId);
            goodstrafficManagementDTO
                    .setPage((goodstrafficManagementDTO.getPage() - 1) * goodstrafficManagementDTO.getLimit());
            List<GoodstrafficManagementVo> listShipmentsShopName = goodstrafficManagementService
                    .listShipmentsShopName(goodstrafficManagementDTO);
            int count = goodstrafficManagementService.listShipmentsShopNameCount(goodstrafficManagementDTO);
            map.put("code", 1);
            map.put("msg", "显示成功");
            map.put("data", listShipmentsShopName);
            map.put("count", count);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", 0);
            map.put("msg", "方法错误");
        }

        return map;
    }

    /**
     * 
     * @Title: updateSubscribe
     * @description 修改运输状态
     * @param goodstrafficManagement
     *            状态码 id
     * @return void
     * @author chenshuxian
     * @createDate 2019年6月19日
     */
    @RequestMapping("/updateSubscribe")
    @ResponseBody
    public Map<String, Object> updateSubscribe(GoodstrafficManagement goodstrafficManagement) {
        Map<String, Object> map = new HashMap<>();
        try {
            if(goodstrafficManagement!=null){
                //判断修改状态还是送货店
                if(goodstrafficManagement.getTransportationState()!=null){
                    Integer id = 
                            goodstrafficManagementService.selectSubscribe(goodstrafficManagement.getId());
                    if(id != goodstrafficManagement.getTransportationState()){
                        goodstrafficManagementService.updateSubscribe(goodstrafficManagement);  
                    }else{
                        map.put("code", 0);
                        map.put("msg", "状态已变更，请刷新");
                        
                        return map;
                    }
                }else if(goodstrafficManagement.getReceivingShopId()!=null){
                    goodstrafficManagementService.updateSubscribe(goodstrafficManagement);  
                }
               
               
            }
            map.put("code", 1);
            map.put("msg", "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", 0);
            map.put("msg", "方法错误");
        }

        return map;
    }

    /**
     * @Title: updateSettlementStatus
     * @description 修改分店结算状态
     * @param @param
     *            goodstrafficManagement
     * @return void
     * @author chenshuxian
     * @createDate 2019年6月19日
     */
    @RequestMapping("/updateSettlementStatus")
    @ResponseBody
    public Map<String, Object> updateSettlementStatus(GoodstrafficManagement goodstrafficManagement) {
        Map<String, Object> map = new HashMap<>();
        try {
            
            goodstrafficManagementService.updateSettlementStatus(goodstrafficManagement);
            map.put("code", 1);
            map.put("msg", "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", 0);
            map.put("msg", "方法错误");
        }

        return map;
    }
    
    @RequestMapping("/deleteGoodstrafficManagement")
    @ResponseBody
    public Map<String, Object> deleteGoodstrafficManagement(GoodstrafficManagement goodstrafficManagement) {
        Map<String, Object> map = new HashMap<>();
        try {
            if(goodstrafficManagement.getTransportationState()!=null){
                Integer id = 
                        goodstrafficManagementService.selectSubscribe(goodstrafficManagement.getId());
                if(id != goodstrafficManagement.getTransportationState()){
                    goodstrafficManagementService.deleteGoodstrafficManagement(goodstrafficManagement.getId());
                }else{
                    map.put("code", 0);
                    map.put("msg", "状态已变更，请刷新");
                    
                    return map;
                }
            }
           
            map.put("code", 1);
            map.put("msg", "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", 0);
            map.put("msg", "方法错误");
        }

        return map;
    }
    
    /**
     * 
     * @Title: updateSubscribeForPurchasing
     * @description 取消采购订单
     * @param goodstrafficManagement
     *            状态码 id
     * @return void
     * @author zhoujiaxin
     * @createDate 20190730
     */
    @RequestMapping("/updateSubscribeForPurchasing")
    @ResponseBody
    public Map<String, Object> updateSubscribeForPurchasing(GoodstrafficManagement goodstrafficManagement,String g,HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        try {
            BigInteger shopId = (BigInteger) session.getAttribute("shopId");
            JSONArray myJsonArray = JSONArray.fromObject(g);
            Inventory inventory = new Inventory();
            for (int i = 0; i < myJsonArray.size(); i++) {
                JSONObject obj = JSONObject.fromObject(myJsonArray.get(i));
                    inventory.setProductId(new BigInteger(obj.getString("productId")));
                    inventory.setQuantity(new BigInteger(obj.getString("quantity")));
                    inventory.setShopId(shopId);
                    // 取消采购订单是减库存
                    inventoryMapper.updateSubscribeForPurchasing(inventory);
            }
            goodstrafficManagementService.updateSubscribe(goodstrafficManagement);  
            map.put("code", 1);
            map.put("msg", "修改成功");
        } catch (Exception e) {
            map.put("code", 0);
            map.put("msg", "修改失败");
        }
        return map;
    }
    
    /**
     * 
     * @Title: redirectToBranchShop
     * @description 总店把调拨请求转发给分店
     * @param goodstrafficManagement
     *            状态码 id
     * @return void
     * @author zhoujiaxin
     * @createDate 20190731
     */
    @RequestMapping("/redirectToBranchShop")
    @ResponseBody
    public Map<String, Object> redirectToBranchShop(GoodstrafficManagement goodstrafficManagement,HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        try {
            goodstrafficManagementService.redirectToBranchShop(goodstrafficManagement);  
            map.put("code", 1);
            map.put("msg", "转发成功");
        } catch (Exception e) {
            map.put("code", 0);
            map.put("msg", "转发失败");
        }
        return map;
    }
   
}
