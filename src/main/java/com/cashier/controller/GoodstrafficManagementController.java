package com.cashier.controller;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cashier.entity.GoodstrafficManagement;
import com.cashier.entityDTO.GoodstrafficManagementDTO;
import com.cashier.entityVo.AddsubscriptionVo;
import com.cashier.entityVo.GoodstrafficManagementVo;
import com.cashier.entityVo.GoodstrafficOrdersProductVo;
import com.cashier.service.GoodstrafficManagementService;

@Controller
public class GoodstrafficManagementController {
    @Autowired
    private GoodstrafficManagementService goodstrafficManagementService;

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
     * @description 添加采购订单
     * @param goodstrafficManagement
     * @return Map<String,Object>
     * @author chenshuxian
     * @createDate 2019年6月19日
     */
    @RequestMapping("/addprocurement")
    @ResponseBody
    public Map<String, Object> addprocurement(GoodstrafficManagement goodstrafficManagement, HttpSession session,String g) {
        Map<String, Object> map = new HashMap<>();
        try {
            BigInteger shopId = (BigInteger) session.getAttribute("shopId");
            goodstrafficManagement.setShopId(shopId);
            goodstrafficManagementService.addprocurement(goodstrafficManagement,g);
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
    public Map<String, Object> listProductAndProductType(HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        try {
            BigInteger shopId = (BigInteger) session.getAttribute("shopId");
            List<AddsubscriptionVo> listProductAndProductType = 
                    goodstrafficManagementService.listProductAndProductType(shopId);
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
            goodstrafficManagementService.updateSubscribe(goodstrafficManagement);
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
}
