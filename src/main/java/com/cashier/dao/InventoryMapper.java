package com.cashier.dao;

import java.util.List;
import com.cashier.entity.Inventory;
import com.cashier.entityDTO.InventoryDTO;
import com.cashier.entityVo.InventoryVo;

public interface InventoryMapper {

    /**
     * @Title: listInventory
     * @description 显示库存页面数据
     * @param goodstrafficManagementDTO
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
     * @Title: getInventory
     * @description 得到单条数据
     * @param  inventory
     * @return Inventory    
     * @author liujunkai
     * @createDate 2019年7月4日
     */
    Inventory getInventory(Inventory inventory);

    /**
     * 
     * @Title: insertInventory
     * @description 添加库存商品
     * @param inventory
     * @return Integer
     * @author liujunkai
     * @createDate 2019年7月3日
     */
    Integer insertInventory(Inventory inventory);

    /**
     * @Title: updateInventory
     * @description 修改单条数据
     * @param inventory
     * @return Integer    
     * @author liujunkai
     * @createDate 2019年7月3日
     */
    void updateInventory(Inventory inventory);
    
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

    /**
     * @Title: updateInventoryForAdd
     * @description 采购时添加库存操作
     * @param @param inventory  
     * @return void    
     * @author zhoujiaxin
     * @createDate 2019年7月30日
     */
    void updateInventoryForAdd(Inventory inventory);

    /**
     * @Title: updateSubscribeForPurchasing
     * @description 取消采购订单是减库存
     * @param @param inventory  
     * @return void    
     * @author zhoujiaxin
     * @createDate 2019年7月30日
     */
    void updateSubscribeForPurchasing(Inventory inventory);
}
