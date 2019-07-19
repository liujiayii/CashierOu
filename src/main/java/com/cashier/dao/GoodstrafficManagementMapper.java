package com.cashier.dao;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cashier.entity.GoodstrafficManagement;
import com.cashier.entityDTO.GoodstrafficManagementDTO;
import com.cashier.entityVo.AddsubscriptionVo;
import com.cashier.entityVo.GoodstrafficManagementVo;
import com.cashier.entityVo.GoodstrafficOrdersProductVo;

public interface GoodstrafficManagementMapper {

    
    /**
     * @Title: addprocurement
     * @description 添加采购订单
     * @param goodstrafficManagement
     * @return void
     * @author chenshuxian
     * @createDate 2019年6月19日
     */
    Integer addprocurement(GoodstrafficManagement goodstrafficManagement);

    /**
     * @Title: listProcurement
     * @description 采购订单
     * @param goodstrafficManagementDTO
     *            店铺id、运输状态、店铺名称、起始结束时间
     * @return List<GoodstrafficManagementVo>
     * @author chenshuxian
     * @createDate 2019年6月19日
     */
    List<GoodstrafficManagementVo> listProcurement(GoodstrafficManagementDTO goodstrafficManagementDTO);

    /**
     * @Title: listProcurementCount
     * @description 采购订单条数
     * @param goodstrafficManagementDTO
     * @return int
     * @author liujunkai
     * @createDate 2019年7月5日
     */
    int listProcurementCount(GoodstrafficManagementDTO goodstrafficManagementDTO);

    /**
     * 
     * @Title: listProductAndProductType
     * @description 采购商品显示
     * @param id
     * @return List<AddsubscriptionVo>
     * @author liujunkai
     * @createDate 2019年7月5日
     */
    List<AddsubscriptionVo> listProductAndProductType(@Param("id") BigInteger id);

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
    void updateSubscribe(GoodstrafficManagement goodstrafficManagement);

    /**
     * @Title: updateSettlementStatus
     * @description 修改分店结算状态
     * @param @param
     *            goodstrafficManagement
     * @return void
     * @author chenshuxian
     * @createDate 2019年6月19日
     */
    void updateSettlementStatus(GoodstrafficManagement goodstrafficManagement);

    /**
     * 
     * @Title: listReceivingShopName
     * @description 送货页面
     * @param goodstrafficManagementDTO
     * @return List<GoodstrafficManagementVo>
     * @author liujunkai
     * @createDate 2019年7月5日
     */
    List<GoodstrafficManagementVo> listReceivingShopName(GoodstrafficManagementDTO goodstrafficManagementDTO);
    
    /**
     * 
     * @Title: listReceivingShopNameCount
     * @description 送货页面显示数据的条数
     * @param  goodstrafficManagementDTO
     * @return int    
     * @author liujunkai
     * @createDate 2019年7月5日
     */
    int listReceivingShopNameCount(GoodstrafficManagementDTO goodstrafficManagementDTO);
    
    /**
     * 
     * @Title: listShipmentsShopName
     * @description 发起页面
     * @param goodstrafficManagementDTO
     * @return List<GoodstrafficManagementVo>
     * @author liujunkai
     * @createDate 2019年7月5日
     */
    List<GoodstrafficManagementVo> listShipmentsShopName(GoodstrafficManagementDTO goodstrafficManagementDTO);
    
    /**
     * 
     * @Title: listShipmentsShopNameCount
     * @description 发起页面显示数据的条数
     * @param  goodstrafficManagementDTO
     * @return int    
     * @author liujunkai
     * @createDate 2019年7月5日
     */
    int listShipmentsShopNameCount(GoodstrafficManagementDTO goodstrafficManagementDTO);
    
    /**
     * 
     * @Title: getGoodstrafficManagementByApp
     * @description 查询单条货流表数据
     * @param id
     * @return GoodstrafficManagement    
     * @author liujunkai
     * @createDate 2019年7月9日
     */
    GoodstrafficManagement getGoodstrafficManagementByApp(BigInteger id);
}
