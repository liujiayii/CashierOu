package com.cashier.controller;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cashier.dao.UserOperationMapper;
import com.cashier.entity.Inventory;
import com.cashier.entity.User;
import com.cashier.entity.UserOperation;
import com.cashier.entityDTO.InventoryDTO;
import com.cashier.entityVo.InventoryVo;
import com.cashier.service.InventoryService;

@Controller
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private UserOperationMapper userOperationMapper;
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
    @RequiresPermissions("/listInventory")
    @ResponseBody
    public Map<String, Object> listInventory(InventoryDTO inventoryDTO,HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        try {
            if(inventoryDTO.getShopId()==null){
                BigInteger shopId = (BigInteger) session.getAttribute("shopId");
                inventoryDTO.setShopId(shopId);
            }
            inventoryDTO.setPage((inventoryDTO.getPage() - 1) * inventoryDTO.getLimit());
            List<InventoryVo> listInventory = inventoryService.listInventory(inventoryDTO);
            int count = inventoryService.listInventoryCount(inventoryDTO);

            map.put("msg", "查询成功");
            map.put("code", 1);
            map.put("data", listInventory);
            map.put("count", count);
        } catch (Exception e) {
            e.printStackTrace();
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
    @RequiresPermissions("/updateInventory")
    @ResponseBody
    public Map<String, Object> updateInventory(Inventory inventory,HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        try {
            inventoryService.updateInventory(inventory);
            // 添加一条操作记录
            User user = (User)session.getAttribute("user");
            UserOperation userOperation = new UserOperation();
            userOperation.setShopId(new BigInteger(session.getAttribute("shopId")+""));
            userOperation.setUserName(user.getUsername());
            userOperation.setName(user.getName());
            userOperation.setOperatingContent("修改库存");
            userOperationMapper.saveUserOperation(userOperation);
            map.put("msg", "修改成功");
            map.put("code", 1);
        } catch (Exception e) {
            e.printStackTrace();
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
    public Map<String, Object> updateQuantity(BigInteger id, String inventory, Integer judge, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        map = inventoryService.updateQuantity(id, inventory, judge, session);
        // 添加一条操作记录
        User user = (User)session.getAttribute("user");
        UserOperation userOperation = new UserOperation();
        userOperation.setShopId(new BigInteger(session.getAttribute("shopId")+""));
        userOperation.setUserName(user.getUsername());
        userOperation.setName(user.getName());
        userOperation.setOperatingContent("出库入库操作");
        userOperationMapper.saveUserOperation(userOperation);
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
    public Map<String,Object> getInventoryByShopId(InventoryDTO inventoryDTO,HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        try {
            inventoryDTO.setPage((inventoryDTO.getPage() - 1) * inventoryDTO.getLimit());
            BigInteger shopId = (BigInteger) session.getAttribute("shopId");
            inventoryDTO.setId(shopId);
            List<InventoryVo> getInventoryByShopId = inventoryService.getInventoryByShopId(inventoryDTO);
            int count = inventoryService.getInventoryByShopIdCount(inventoryDTO);
            map.put("msg", "查询成功");
            map.put("code", 1);
            map.put("data", getInventoryByShopId);
            map.put("count", count);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "查询失败，方法错误");
            map.put("code", 0);
        }
        
        return map;
    }

}
