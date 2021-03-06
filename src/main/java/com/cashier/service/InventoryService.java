package com.cashier.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

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
    Map<String,Object> updateQuantity(BigInteger id, String inventory, Integer judge, HttpSession session);
    
    /**
     * 
     * @Title: getInventoryByShopId
     * @description 查看本店低于库存预警的数据
     * @param  inventoryDTO
     * @return List<Inventory>
     * @author liujunkai
     * @createDate 2019年7月8日
     */
    List<InventoryVo> getInventoryByShopId(InventoryDTO inventoryDTO);
    
    /**
     * 
     * @Title: getInventoryByShopIdCount
     * @description 查看本店低于库存预警的数据的条数
     * @param inventoryDTO
     * @return int    
     * @author liujunkai
     * @createDate 2019年7月8日
     */
    int getInventoryByShopIdCount(InventoryDTO inventoryDTO);
}
