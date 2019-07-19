package com.cashier.service.impl;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cashier.dao.GoodstrafficManagementMapper;
import com.cashier.dao.InventoryMapper;
import com.cashier.entity.GoodstrafficManagement;
import com.cashier.entity.Inventory;
import com.cashier.entityDTO.InventoryDTO;
import com.cashier.entityVo.InventoryVo;
import com.cashier.service.InventoryService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryMapper inventoryMapper;
    @Autowired
    private GoodstrafficManagementMapper goodstrafficManagementMapper;

    
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
    public Map<String, Object> updateQuantity(BigInteger id, String inventory, Integer judge, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        try {
            Inventory inventory2 = new Inventory();
            Inventory inventory4 = new Inventory();
            JSONArray myJsonArray = JSONArray.fromObject(inventory);
            for(int i=0;i<myJsonArray.size();i++){
                JSONObject obj = JSONObject.fromObject(myJsonArray.get(i));
                //设置商品ID和店铺ID
                BigInteger shopId = (BigInteger) session.getAttribute("shopId");
                inventory2.setShopId(shopId);
                inventory2.setProductId(new BigInteger(obj.getString("productId")));
                // judege为1则是出库，其他则是入库
                Inventory inventory3 = inventoryMapper.getInventory(inventory2);
                if (judge == 1) {
                    // 如果这个出库值大于库存值则无法出库
                    if(inventory3==null){
                        inventory4.setQuantity(new BigInteger(obj.getString("quantity")));
                        inventory4.setShopId(shopId);
                        inventory4.setProductId(new BigInteger(obj.getString("productId")));
                        inventoryMapper.insertInventory(inventory4);
                    } else if(inventory3.getQuantity()==null && inventory3.getQuantity().equals("")){
                        map.put("msg", "没有库存，无法出库");
                        map.put("code", -1);
                        
                        return map;
                    } else if (inventory3.getQuantity().compareTo(new BigInteger(obj.getString("quantity"))) == 1) {
                        inventory4.setQuantity(inventory3.getQuantity().subtract(new BigInteger(obj.getString("quantity"))));
                        inventory4.setId(inventory3.getId());
                        inventoryMapper.updateInventory(inventory4);
                    } else {
                        map.put("msg", "库存不足，无法出库");
                        map.put("code", -1);
                        
                        return map;
                    }

                } else {
                    // 如果库存值为0
                    if(inventory3==null){
                        inventory4.setQuantity(new BigInteger(obj.getString("quantity")));
                        inventory4.setShopId(shopId);
                        inventory4.setProductId(new BigInteger(obj.getString("productId")));
                        inventoryMapper.insertInventory(inventory4);
                    }else if (inventory3.getQuantity().compareTo(new BigInteger("0")) == 0) {
                        inventory4.setQuantity(new BigInteger(obj.getString("quantity")));
                        inventory4.setId(inventory3.getId());
                        inventoryMapper.updateInventory(inventory4);
                    } else {
                        inventory4.setQuantity(new BigInteger(obj.getString("quantity")).add(inventory3.getQuantity()));
                        inventory4.setId(inventory3.getId());
                        inventoryMapper.updateInventory(inventory4);
                    }
                }
            }
            GoodstrafficManagement goodstrafficManagement = new GoodstrafficManagement();
            goodstrafficManagement.setId(id);
            if(judge == 1){
                goodstrafficManagement.setTransportationState(3);
            }else{
                goodstrafficManagement.setTransportationState(5);
            }
           
            goodstrafficManagementMapper.updateSubscribe(goodstrafficManagement);
            
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
    public List<InventoryVo> getInventoryByShopId(InventoryDTO inventoryDTO) {
        
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
