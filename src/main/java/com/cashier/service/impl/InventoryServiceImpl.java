package com.cashier.service.impl;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cashier.dao.InventoryMapper;
import com.cashier.entity.Inventory;
import com.cashier.entityDTO.InventoryDTO;
import com.cashier.entityVo.InventoryVo;
import com.cashier.service.InventoryService;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryMapper inventoryMapper;

    @Override
    public List<InventoryVo> listInventory(InventoryDTO inventoryDTO) {

        return inventoryMapper.listInventory(inventoryDTO);
    }

    @Override
    public int listInventoryCount(InventoryDTO inventoryDTO) {

        return inventoryMapper.listInventoryCount(inventoryDTO);
    }

    @Override
    public Integer insertInventory(Inventory inventory) {

        return inventoryMapper.insertInventory(inventory);
    }

    @Override
    public void updateInventory(Inventory inventory) {

        inventoryMapper.updateInventory(inventory);

    }

    @Override
    public Map<String, Object> updateQuantity(Inventory inventory, Integer judge) {
        Map<String, Object> map = new HashMap<>();
        try {
            // judege为1则是出库，其他则是入库
            Inventory inventory2 = inventoryMapper.getInventory(inventory);
            if (judge == 1) {
                // 如果这个出库值大于库存值则无法出库
                if (inventory2.getQuantity().compareTo(inventory.getQuantity()) == 1) {
                    inventory.setQuantity(inventory2.getQuantity().subtract(inventory.getQuantity()));
                    inventoryMapper.updateInventory(inventory);
                } else {
                    map.put("msg", "库存不足，无法出库");
                    map.put("code", -1);
                    
                    return map;
                }

            } else {
                // 如果库存值为0
                if (inventory2.getQuantity().compareTo(new BigInteger("0")) == 0) {
                    inventoryMapper.updateInventory(inventory);
                } else {
                    inventory.setQuantity(inventory.getQuantity().add(inventory2.getQuantity()));
                    inventoryMapper.updateInventory(inventory);
                }
            }
            map.put("msg", "修改成功");
            map.put("code", 1);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "方法错误");
            map.put("code", 0);
        }
            
        return map;

    }

    /**
     * 
     * @Title: getInventoryByShopId
     * @description 查看本店低于库存预警的数据
     * @param  inventory
     * @return List<Inventory>
     * @author liujunkai
     * @createDate 2019年7月8日
     */
    @Override
    public List<Inventory> getInventoryByShopId(InventoryDTO inventoryDTO) {
        
        return inventoryMapper.getInventoryByShopId(inventoryDTO);
    }

    /**
     * 
     * @Title: getInventoryByShopIdCount
     * @description 查看本店低于库存预警的数据的条数
     * @param inventoryDTO
     * @return int    
     * @author liujunkai
     * @createDate 2019年7月8日
     */
    @Override
    public int getInventoryByShopIdCount(InventoryDTO inventoryDTO) {

        return inventoryMapper.getInventoryByShopIdCount(inventoryDTO);
    }

    
}
