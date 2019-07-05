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
}
