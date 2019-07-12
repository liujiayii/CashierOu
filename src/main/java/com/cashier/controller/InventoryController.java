package com.cashier.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cashier.entity.Inventory;
import com.cashier.entityDTO.InventoryDTO;
import com.cashier.entityVo.InventoryVo;
import com.cashier.service.InventoryService;

@Controller
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    /**
     * 
     * @Title: listInventory
     * @description 查询数据
     * @param  inventoryDTO
     * @return Map<String,Object>    
     * @author liujunkai
     * @createDate 2019年7月5日
     */
    @RequestMapping("/listInventory")
    @ResponseBody
    public Map<String, Object> listInventory(InventoryDTO inventoryDTO) {
        Map<String, Object> map = new HashMap<>();
        try {
            inventoryDTO.setPage((inventoryDTO.getPage() - 1) * inventoryDTO.getLimit());
            List<InventoryVo> listInventory = inventoryService.listInventory(inventoryDTO);
            int count = inventoryService.listInventoryCount(inventoryDTO);

            map.put("msg", "查询成功");
            map.put("code", 1);
            map.put("data", listInventory);
            map.put("count", count);
        } catch (Exception e) {
            map.put("msg", "方法错误");
            map.put("code", 0);
        }
        return map;
    }

    /**
     * 
     * @Title: updateInventory
     * @description 修正数据库数据
     * @param inventory
     * @return Map<String,Object>
     * @author liujunkai
     * @createDate 2019年7月5日
     */
    @RequestMapping("/updateInventory")
    @ResponseBody
    public Map<String, Object> updateInventory(Inventory inventory) {
        Map<String, Object> map = new HashMap<>();
        try {
            inventoryService.updateInventory(inventory);

            map.put("msg", "修改成功");
            map.put("code", 1);
        } catch (Exception e) {
            map.put("code", 0);
            map.put("msg", "方法错误");
        }

        return map;
    }
    
    /**
     * @Title: updateQuantity
     * @description 出库入库
     * @param  inventory
     * @param  judge
     * @return Map<String,Object>    
     * @author liujunkai
     * @createDate 2019年7月5日
     */
    @RequestMapping("/updateQuantity")
    @ResponseBody
    public Map<String, Object> updateQuantity(Inventory inventory, Integer judge) {
        Map<String, Object> map = new HashMap<>();
        map = inventoryService.updateQuantity(inventory, judge);

        return map;
    }
    
    /**
     * 
     * @Title: getInventoryByShopId
     * @description 查看本店低于库存预警的数据
     * @param  inventory
     * @return Inventory    
     * @author liujunkai
     * @createDate 2019年7月8日
     */
    @RequestMapping("/getInventoryByShopId")
    @ResponseBody
    public Map<String,Object> getInventoryByShopId(InventoryDTO inventoryDTO) {
        Map<String, Object> map = new HashMap<>();
        try {
            List<Inventory> getInventoryByShopId = inventoryService.getInventoryByShopId(inventoryDTO);
            int count = inventoryService.getInventoryByShopIdCount(inventoryDTO);
            map.put("msg", "查询成功");
            map.put("code", 1);
            map.put("data", getInventoryByShopId);
            map.put("count", count);
        } catch (Exception e) {
            map.put("msg", "查询失败，方法错误");
            map.put("code", 0);
        }
        
        return map;
    }

}
