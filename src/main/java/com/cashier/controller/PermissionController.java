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
import com.cashier.entity.Permission;
import com.cashier.entity.User;
import com.cashier.entity.UserOperation;
import com.cashier.entityVo.PermissionVo;
import com.cashier.service.PermissionService;
import com.cashier.service.ex.ServiceException;

@Controller
public class PermissionController {
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private UserOperationMapper userOperationMapper;
	/**
	 * 
	     * @Title: getAllPermission
	     * @description 获得所有权限（每个父类下子权限）
	     * @param  
	     * @return    
	     * @author chenshuxian
	     * @createDate 2019年7月29日
	 */
	@RequestMapping("/getAllPermission")
	@ResponseBody
	public Map<String,Object> getAllPermission(){
		Map<String,Object> map = new HashMap<>();
	  	List<PermissionVo> permissionVo = permissionService.getAllPermission();
		map.put("code", 1);
		map.put("msg", "成功");
		map.put("permissionVo", permissionVo);
	  	return map;
	}
	/**
	 * 
	     * @Title: showSavePermission
	     * @description 添加页面展示数据（父权限下拉选）
	     * @param  
	     * @return    
	     * @author chenshuxian
	     * @createDate 2019年7月29日
	 */
	@RequestMapping("/showSavePermission")
	@RequiresPermissions("/savePermission")
	@ResponseBody
	public Map<String,Object> showSavePermission(){
		Map<String,Object> map = new HashMap<String, Object>();
		List<Permission> permissions= permissionService.getAllParentPermission();
		map.put("code", 1);
		map.put("msg", "成功");
		map.put("permissions", permissions);
		return map;
	}
	/**
	 * 
	     * @Title: savePermission
	     * @description 添加权限方法
	     * @param  权限名称，父权限id，名称，访问url地址，映射地址
	     * @return  
	     * @author chenshuxian
	     * @createDate 2019年7月29日
	 */
	@RequestMapping("/savePermission")
	@RequiresPermissions("/savePermission")
	@ResponseBody
	public Map<String,Object> savePermission(Permission permission,HttpSession session){
		Map<String,Object> map = new HashMap<String, Object>();
		Integer row = permissionService.insertPermission(permission);
		if( row == 0 ) {
			map.put("code", 0);
			map.put("msg", "添加失败");
		} else {
			 // 添加一条操作记录
            User user = (User)session.getAttribute("user");
            UserOperation userOperation = new UserOperation();
            userOperation.setShopId(new BigInteger(session.getAttribute("shopId")+""));
            userOperation.setUserName(user.getUsername());
            userOperation.setName(user.getName());
            userOperation.setOperatingContent("添加权限");
            userOperationMapper.saveUserOperation(userOperation);
			map.put("code", 1);
			map.put("msg", "添加成功");
		}
		return map;
	}
	/**
	 * 
	     * @Title: 返回修改页面所需数据
	     * @description showUpdatePermission
	     * @param  权限id
	     * @return    
	     * @author chenshuxian
	     * @createDate 2019年7月29日
	 */
	@RequestMapping("/showUpdatePermission")
	@RequiresPermissions("/savePermission")
	@ResponseBody
	public Map<String,Object> showUpdatePermission(BigInteger permissionId){
		Map<String,Object> map = new HashMap<>();
		Permission p = permissionService.getPermissionById(permissionId);
		//查询所有父权限
		List<Permission> listParentPermission = permissionService.getAllParentPermission();
		map.put("code", 1);
		map.put("msg", "成功");
		map.put("permission", p);
		map.put("listParentPermission", listParentPermission);
		return map;
	}
	/**
	 * 
	     * @Title: updatePermission
	     * @description 修改权限相关
	     * @param  
	     * @return    
	     * @author chenshuxian
	     * @createDate 2019年7月29日
	 */
	@RequestMapping("/updatePermission")
	@RequiresPermissions("/savePermission")
	@ResponseBody
	public Map<String,Object> updatePermission(Permission permission,HttpSession session){
		Map<String,Object> map = new HashMap<>();
		try {
			permissionService.updatePermissionById(permission);
			 // 添加一条操作记录
            User user = (User)session.getAttribute("user");
            UserOperation userOperation = new UserOperation();
            userOperation.setShopId(new BigInteger(session.getAttribute("shopId")+""));
            userOperation.setUserName(user.getUsername());
            userOperation.setName(user.getName());
            userOperation.setOperatingContent("修改权限");
            userOperationMapper.saveUserOperation(userOperation);
			map.put("code", 1);
			map.put("msg", "修改成功");
		} catch (ServiceException e) {
			map.put("code", 0);
			map.put("msg", e.getMessage());
		}
		return map;
	}
	/**
	 * 
	     * @Title: delPerssionById
	     * @description 根据权限id删除相对应权限（如果是父权限的话，会相对应删除子权限）
	     * @param  权限id
	     * @return    
	     * @author chenshuxian
	     * @createDate 2019年7月29日
	 */
	@RequestMapping("/delPerssionById")
	@RequiresPermissions("/savePermission")
	@ResponseBody
	public Map<String,Object> delPerssionById(BigInteger permissionId,HttpSession session){
		Map<String,Object> map = new HashMap<>();
		try {
			Integer row = permissionService.delPerssionById(permissionId);
			 // 添加一条操作记录
            User user = (User)session.getAttribute("user");
            UserOperation userOperation = new UserOperation();
            userOperation.setShopId(new BigInteger(session.getAttribute("shopId")+""));
            userOperation.setUserName(user.getUsername());
            userOperation.setName(user.getName());
            userOperation.setOperatingContent("删除权限");
            userOperationMapper.saveUserOperation(userOperation);
			map.put("code", 1);
			map.put("msg", "删除成功");
		} catch (ServiceException e) {
			map.put("code", 0);
			map.put("msg", e.getMessage());
		}
		return map;
	}
}
