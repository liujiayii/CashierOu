package com.cashier.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cashier.dao.GoodstrafficManagementMapper;
import com.cashier.dao.GoodstrafficOrdersProductMapper;
import com.cashier.dao.ProductMapper;
import com.cashier.dao.ShopMapper;
import com.cashier.entity.GoodstrafficManagement;
import com.cashier.entity.GoodstrafficOrdersProduct;
import com.cashier.entity.Product;
import com.cashier.entity.Shop;
import com.cashier.entityDTO.GoodstrafficManagementDTO;
import com.cashier.entityVo.AddsubscriptionVo;
import com.cashier.entityVo.GoodstrafficManagementVo;
import com.cashier.entityVo.GoodstrafficManagementVo2;
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
    @Autowired
    private ShopMapper shopMapper;
    @Autowired
    private ProductMapper productMapper;
    
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
        JSONArray myJsonArray = JSONArray.fromObject(g);
        BigDecimal bigDecimal = new BigDecimal("0");
        Shop shop = new Shop();
        shop.setId(goodstrafficManagement.getShopId());
        Shop shop2 = shopMapper.getShopByCondition(shop);
        if(shop2.getType()==2 && goodstrafficManagement.getGoodstrafficState()==2){
            Shop shop3 = new Shop();
            shop3.setType(1);
            Shop s = shopMapper.getShopByCondition(shop3);
            goodstrafficManagement.setReceivingShopId(s.getId());
        }
        for (int i = 0; i < myJsonArray.size(); i++) {
            JSONObject obj = JSONObject.fromObject(myJsonArray.get(i));
            Product product = 
                    productMapper.getProductByGoodstraffic(new BigInteger(obj.getString("productId")));
            BigDecimal decimal = product.getPleased().multiply(new BigDecimal(obj.getString("quantity")));
            bigDecimal = bigDecimal.add(decimal);
            
        }
        //流水号
        goodstrafficManagement.setSerialnumber(this.SerialNumber());
        goodstrafficManagement.setTotalMoney(bigDecimal);
        goodstrafficManagementMapper.addprocurement(goodstrafficManagement);
        GoodstrafficOrdersProduct goodstrafficOrdersProduct = new GoodstrafficOrdersProduct();
        for (int i = 0; i < myJsonArray.size(); i++) {
            JSONObject obj = JSONObject.fromObject(myJsonArray.get(i));
            Product product = 
                    productMapper.getProductByGoodstraffic(new BigInteger(obj.getString("productId")));
            BigDecimal decimal = product.getPleased().multiply(new BigDecimal(obj.getString("quantity")));
            goodstrafficOrdersProduct.setGoodstrafficManagementId(goodstrafficManagement.getId());
            goodstrafficOrdersProduct.setMoney(decimal);
            goodstrafficOrdersProduct.setProductId(new BigInteger(obj.getString("productId")));
            goodstrafficOrdersProduct.setQuantity(new BigInteger(obj.getString("quantity")));
            goodstrafficOrdersProductMapper.insertSubscribePurchase(goodstrafficOrdersProduct);

        }

    }

    /**
     * @Title: SerialNumber
     * @description 流水订单号
     * @return String
     * @author liujunkai
     * @createDate 2019年2月18日
     */
    @Override
    public String SerialNumber(){
        //生成16位唯一性的订单号
        int random = (int) (Math.random()*9+1); //随机生成一位整数
        String valueOf = String.valueOf(random);
        int hashCode = UUID.randomUUID().toString().hashCode(); //生成uuid的hashCode值
        if(hashCode<0){ //可能为负数
            hashCode = -hashCode;
        }
        String value = valueOf + String.format("%011d", hashCode);
        String id = "YXO"+value ; 
        return id ; 
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
    public List<AddsubscriptionVo> listProductAndProductType(BigInteger shopId) {

        return goodstrafficManagementMapper.listProductAndProductType(shopId);
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

  
    @Override
    public Map<String, Object> ParticularsByApp(BigInteger id) {
        Map<String, Object> map = new HashMap<>();
        //订单
        GoodstrafficManagement goodstrafficManagement = 
                goodstrafficManagementMapper.getGoodstrafficManagementByApp(id);
        //详情
        List<GoodstrafficOrdersProduct> goodstrafficOrdersProduct = 
                goodstrafficOrdersProductMapper.listGoodstrafficOrdersProduct(id);
        GoodstrafficManagementVo2 goodstrafficManagementVo = new GoodstrafficManagementVo2();
        if(goodstrafficManagement.getGoodstrafficState()==2){
            Shop shop = new Shop();
            shop.setId(goodstrafficManagement.getShipmentsShopId());
            Shop shop2 = shopMapper.getId(shop);
            goodstrafficManagementVo.setShipmentsShopName(shop2.getName());    
            shop.setId(goodstrafficManagement.getReceivingShopId());  
            Shop shop3 = shopMapper.getId(shop);
            goodstrafficManagementVo.setReceivingShopName(shop3.getName());    
        }
        //备注
        goodstrafficManagementVo.setRemark(goodstrafficManagement.getRemark());
        //金额
        goodstrafficManagementVo.setTotalMoney(goodstrafficManagement.getTotalMoney());
        //流水号
        goodstrafficManagementVo.setSerialNumber(goodstrafficManagement.getSerialnumber());
        //发货时间
        goodstrafficManagementVo.setDeliveryDate(goodstrafficManagement.getDeliveryDate());
        //订单时间
        goodstrafficManagementVo.setOrderDate(goodstrafficManagement.getOrderDate());
        //货流订单表ID
        goodstrafficManagementVo.setId(goodstrafficManagement.getId());
        map.put("code", 1);
        map.put("msg", "显示成功");
        map.put("data", goodstrafficManagementVo);
        map.put("data2", goodstrafficOrdersProduct);
        
        return map;
    }

}
