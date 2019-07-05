package com.cashier.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cashier.dao.GoodstrafficManagementMapper;
import com.cashier.dao.GoodstrafficOrdersProductMapper;
import com.cashier.entity.GoodstrafficManagement;
import com.cashier.entity.GoodstrafficOrdersProduct;
import com.cashier.entityDTO.GoodstrafficManagementDTO;
import com.cashier.entityVo.AddsubscriptionVo;
import com.cashier.entityVo.GoodstrafficManagementVo;
import com.cashier.entityVo.GoodstrafficOrdersProductVo;
import com.cashier.service.GoodstrafficManagementService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class GoodstrafficManagementServiceImpl implements GoodstrafficManagementService {
    @Autowired
    private GoodstrafficManagementMapper goodstrafficManagementMapper;
    @Autowired
    private GoodstrafficOrdersProductMapper goodstrafficOrdersProductMapper;

    /**
     * @Title: addprocurement
     * @description 添加采购订单
     * @param goodstrafficManagement
     * @return void
     * @author chenshuxian
     * @createDate 2019年6月19日
     */
    @Override
    public void addprocurement(GoodstrafficManagement goodstrafficManagement, String g) {

        Integer id = goodstrafficManagementMapper.addprocurement(goodstrafficManagement);
        GoodstrafficOrdersProduct goodstrafficOrdersProduct = new GoodstrafficOrdersProduct();
        JSONArray myJsonArray = JSONArray.fromObject(g);
        for (int i = 0; i < myJsonArray.size(); i++) {
            JSONObject obj = JSONObject.fromObject(myJsonArray.get(i));
            goodstrafficOrdersProduct.setId(new BigInteger(id.toString()));
            goodstrafficOrdersProduct.setMoney(new BigDecimal(obj.getString("money")));
            goodstrafficOrdersProduct.setProductId(new BigInteger(obj.getString("productId")));
            goodstrafficOrdersProduct.setQuantity(new BigInteger(obj.getString("quantity")));
            goodstrafficOrdersProductMapper.insertSubscribePurchase(goodstrafficOrdersProduct);

        }

    }

    /**
     * @Title: listProcurement
     * @description 采购订单
     * @param goodstrafficManagementDTO
     *            店铺id、运输状态、店铺名称、起始结束时间
     * @return List<GoodstrafficManagementVo>
     * @author chenshuxian
     * @createDate 2019年6月19日
     */
    @Override
    public List<GoodstrafficManagementVo> listProcurement(GoodstrafficManagementDTO goodstrafficManagementDTO) {

        return goodstrafficManagementMapper.listProcurement(goodstrafficManagementDTO);
    }

    /**
     * @Title: listProcurementCount
     * @description 采购订单条数
     * @param goodstrafficManagementDTO
     * @return int
     * @author liujunkai
     * @createDate 2019年7月5日
     */
    @Override
    public int listProcurementCount(GoodstrafficManagementDTO goodstrafficManagementDTO) {

        return goodstrafficManagementMapper.listProcurementCount(goodstrafficManagementDTO);
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
    @Override
    public List<AddsubscriptionVo> listProductAndProductType(BigInteger id) {

        return goodstrafficManagementMapper.listProductAndProductType(id);
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
    @Override
    public void updateSubscribe(GoodstrafficManagement goodstrafficManagement) {
        goodstrafficManagementMapper.updateSubscribe(goodstrafficManagement);
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
    @Override
    public void updateSettlementStatus(GoodstrafficManagement goodstrafficManagement) {
        goodstrafficManagementMapper.updateSettlementStatus(goodstrafficManagement);

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
    @Override
    public List<GoodstrafficManagementVo> listReceivingShopName(GoodstrafficManagementDTO goodstrafficManagementDTO) {

        return goodstrafficManagementMapper.listReceivingShopName(goodstrafficManagementDTO);
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
    @Override
    public List<GoodstrafficManagementVo> listShipmentsShopName(GoodstrafficManagementDTO goodstrafficManagementDTO) {

        return goodstrafficManagementMapper.listShipmentsShopName(goodstrafficManagementDTO);
    }

    /**
     * 
     * @Title: listReceivingShopNameCount
     * @description 送货页面显示数据的条数
     * @param goodstrafficManagementDTO
     * @return int
     * @author liujunkai
     * @createDate 2019年7月5日
     */
    @Override
    public int listReceivingShopNameCount(GoodstrafficManagementDTO goodstrafficManagementDTO) {

        return goodstrafficManagementMapper.listReceivingShopNameCount(goodstrafficManagementDTO);
    }

    /**
     * 
     * @Title: listShipmentsShopNameCount
     * @description 发起页面显示数据的条数
     * @param goodstrafficManagementDTO
     * @return int
     * @author liujunkai
     * @createDate 2019年7月5日
     */
    @Override
    public int listShipmentsShopNameCount(GoodstrafficManagementDTO goodstrafficManagementDTO) {

        return goodstrafficManagementMapper.listShipmentsShopNameCount(goodstrafficManagementDTO);
    }

}
