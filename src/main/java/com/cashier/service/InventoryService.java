package com.cashier.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cashier.entity.Inventory;
import com.cashier.entityDTO.InventoryDTO;
import com.cashier.entityVo.InventoryVo;

public interface InventoryService {

    /**
     * @Title: listInventory
     * @description 显示库存页面数据
     * @param inventoryDTO
     * @return List<InventoryVo>
     * @author liujunkai
     * @createDate 2019年7月3日
     */

    List<InventoryVo> listInventory(InventoryDTO inventoryDTO);

    /**
     * @Title: listInventoryCount
     * @description 显示库存页面数据的条数
     * @param inventoryDTO
     * @return int
     * @author chenshuxian
     * @createDate 2019年7月3日
     */
    int listInventoryCount(InventoryDTO inventoryDTO);
    
    /**
     * 
     * @Title: insertInventory
     * @description 添加库存商品
     * @param inventory
     * @return Integer
     * @author chenshuxian
     * @createDate 2019年6月19日
     */
    Integer insertInventory(Inventory inventory);

    
    /**
     * @Title: updateInventory
     * @description 修改库存数量,修改库存预警数量
     * @param inventory
     * @return void    
     * @author liujunkai
     * @createDate 2019年7月3日
     */
    void updateInventory(Inventory inventory);
    
    
    /**
     * @Title: updateQuantity
     * @description 出库入库
     * @param inventory
     * @return Map<String,Object>  
     * @author liujunkai
     * @createDate 2019年7月3日
     */
    Map<String,Object> updateQuantity(Inventory inventory,Integer judge);
}
