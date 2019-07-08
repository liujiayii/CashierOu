/**
 * @Title: UserOperationController.java
 * @Package com.cashier.controller
 * @Description: TODO(用一句话描述该文件做什么)
 * @author Administrator
 * @date 2019年7月8日
 */
package com.cashier.controller;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cashier.entityDTO.InventoryDTO;
import com.cashier.entityVo.InventoryVo;
import com.cashier.entityVo.UserOperationVo;
import com.cashier.service.UserOperationService;

/**
 * @ClassName: UserOperationController
 * @description 用一句话描述这个类的作用
 * @author liujunkai
 * @createDate 2019年7月8日
 */

@Controller
public class UserOperationController {
    
    @Autowired
    private UserOperationService userOperationService;
    
    
    
    /**
     * 
     * @Title: selectAllUserOperation
     * @description 查询操作记录表
     * @param userOperationVo
     * @return Map<String, Object>
     * @author liujunkai
     * @createDate 2019年7月8日
     */
    @RequestMapping("/selectAllUserOperation")
    @ResponseBody
    public Map<String, Object> selectAllUserOperation(UserOperationVo userOperationVo) {
        Map<String, Object> map = new HashMap<>();
        try {
            userOperationVo.setPage((userOperationVo.getPage() - 1) * userOperationVo.getLimit());
            List<UserOperationVo> selectAllUserOperation = 
                    userOperationService.selectAllUserOperation(userOperationVo);
            int count = userOperationService.findUserOperationCount(userOperationVo);

            map.put("msg", "查询成功");
            map.put("code", 1);
            map.put("data", selectAllUserOperation);
            map.put("count", count);
        } catch (Exception e) {
            map.put("msg", "查询失败，方法错误");
            map.put("code", 0);
        }
        return map;
    }
   
   
 
    /**
     * 
     * @Title: deleteUserOperationById
     * @description 删除一条操作记录表
     * @param id 
     * @return Map<String, Object>  
     * @author liujunkai
     * @createDate 2019年7月8日
     */
    @RequestMapping("/deleteUserOperationById")
    @ResponseBody
    public Map<String, Object> deleteUserOperationById(BigInteger id) {
        Map<String, Object> map = new HashMap<>();
        try {
            userOperationService.deleteUserOperationById(id);;
            map.put("msg", "删除成功");
            map.put("code", 1);
        } catch (Exception e) {
            map.put("msg", "查询失败，方法错误");
            map.put("code", 0);
        }
        
        return map;
    }
    
}
