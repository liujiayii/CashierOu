package com.cashier.controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.cashier.dao.UserOperationMapper;
import com.cashier.entity.CustomException;
import com.cashier.entity.Role;
import com.cashier.entity.Shop;
import com.cashier.entity.User;
import com.cashier.entity.UserOperation;
import com.cashier.entityVo.UserShopVo;
import com.cashier.entityVo.UserVo;
import com.cashier.service.RoleService;
import com.cashier.service.ShopService;
import com.cashier.service.UserService;
import com.cashier.util.MD5Util;

/**
 *
 * @ClassName: UserController
 * 
 * @description 用户表的控制层
 *
 * @author dujiawei
 * @createDate 2018年12月3日
 */
@Controller
public class UserController {
	
	@Resource
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private ShopService shopService;
	@Autowired
	private UserOperationMapper userOperationMapper;
	
	/**
	 * @Title: ZtselectAllRole
	 * @description 跳转本店用户列表页面
	 * @param @param model
	 * @return String    
	 * @author zhou jiaxin
	 * @createDate 2019年7月4日
	 */
	@RequiresPermissions("/userListVo")
	@RequestMapping("/ZtselectAllUser")
	public String ZtselectAllRole(Model model) {
	    /** 获得各分店铺id和名称 */
        List<Shop> shopList = shopService.listShopIdAndName();
        model.addAttribute("shopList", shopList);
        model.addAttribute("shopId", 0);
        
		return "/views/entireManage/userManage/userManage";
	}
	
	/**
	 * @Title: userListVoForZ
	 * @description 总店查询分店用户信息用户列表
	 * @param @param model
	 * @param @param page
	 * @param @param limit
	 * @param @param userVo
	 * @param @param session
	 * @param @param shopID
	 * @return Object    
	 * @author zhou jiaxin
	 * @createDate 2019年7月4日
	 */
	//@RequiresPermissions("/listBsiTablesBySupper")
    @RequestMapping("/userListVoForZ")
    @ResponseBody
    public Object userListVoForZ(String shopID, Model model, Integer page, Integer limit, UserVo userVo, HttpSession session) {      
        BigInteger shopId = new BigInteger(shopID);
        userVo.setPage((page-1)*limit);
        userVo.setLimit(limit);
        userVo.setShopId(shopId);
        List<UserVo>  list = userService.listUserVo(userVo);
        
        if(list.size() > 0) {
            for(int i = 0; i<list.size(); i++) {
                list.get(i).setCount(0);
            }
        };
        
        UserVo uVo = userService.countUser(userVo);
        int count = 0;
        if(uVo.getCount() != 0) {
            count = uVo.getCount();
        };
        
        Map<String , Object> result = new HashMap<String , Object>();       
        result.put("code", 1);
        result.put("msg", "Success");
        //JSONArray array = JSONArray.fromObject(list);
        result.put("data", list);
        result.put("count", count);
        
        return result;  
    }
	
	/**
	 * @Title: userList
	 * @description 本店用户列表
	 * @param @param model
	 * @param @param page
	 * @param @param limit
	 * @param @param userVo
	 * @param @param session
	 * @return Object    
	 * @author dujiawei
	 * @createDate 2019年7月4日
	 */
	//@RequiresPermissions("/userListVo")
	@RequestMapping("/userListVo")
	@ResponseBody
	public Object userList(Model model, Integer page, Integer limit, UserVo userVo, HttpSession session) {		
		BigInteger shopId = (BigInteger)session.getAttribute("shopId");
		userVo.setPage((page-1)*limit);
		userVo.setLimit(limit);
		userVo.setShopId(shopId);
		List<UserVo>  list = userService.listUserVo(userVo);
		
		if(list.size() > 0) {
			for(int i = 0; i<list.size(); i++) {
				list.get(i).setCount(0);
			}
		};
		
		UserVo uVo = userService.countUser(userVo);
		int count = 0;
		if(uVo.getCount() != 0) {
			count = uVo.getCount();
		};
		
		Map<String , Object> result = new HashMap<String , Object>();		
		result.put("code", 1);
		result.put("msg", "Success");
		//JSONArray array = JSONArray.fromObject(list);
		result.put("data", list);
		result.put("count", count);
		
		return result;	
	}
	
	/**
	 * @Title: listByNameForZ
	 * @description 总店通过用户名查询分店用户信息
	 * @param @param shopID
	 * @param @param model
	 * @param @param page
	 * @param @param limit
	 * @param @param userVo
	 * @param @param session
	 * @return Object    
	 * @author zhou jiaxin
	 * @createDate 2019年7月4日
	 */
	//@RequiresPermissions("/listBsiTablesBySupper")
    @RequestMapping("/listByNameForZ")
    @ResponseBody
    public Object listByNameForZ(String shopID, Model model, Integer page, Integer limit, UserVo userVo, HttpSession session) {
        BigInteger shopId =  new BigInteger(shopID);
        userVo.setShopId(shopId);
        userVo.setPage((page-1)*limit);
        userVo.setLimit(limit);
        List<UserVo>  list = userService.listByName(userVo);
        
        if(list.size() > 0) {
            for(int i = 0; i<list.size(); i++) {
                list.get(i).setCount(0);
            }
        };
        UserVo uVo = userService.countByName(userVo);
        int count = 0;
        if(uVo.getCount() != 0) {
            count = uVo.getCount();
        };

        Map<String , Object> result = new HashMap<String , Object>();       
        result.put("code", 1);
        result.put("msg", "Success");
        //JSONArray array = JSONArray.fromObject(list);
        result.put("data", list);
        result.put("count", count);
        
        return result;  
    }
	
	/**
	 * @Title: listByName
	 * @description 通过用户名查询用户信息
	 * @param @param model
	 * @param @param page
	 * @param @param limit
	 * @param @param userVo
	 * @param @param session
	 * @return Object    
	 * @author dujiawei
	 * @createDate 2019年7月4日
	 */
	//@RequiresPermissions("/userListVo")
	@RequestMapping("/listByName")
	@ResponseBody
	public Object listByName(Model model, Integer page, Integer limit, UserVo userVo, HttpSession session) {
		BigInteger shopId =  (BigInteger)session.getAttribute("shopId");
		userVo.setShopId(shopId);
		userVo.setPage((page-1)*limit);
		userVo.setLimit(limit);
		List<UserVo>  list = userService.listByName(userVo);
		
		if(list.size() > 0) {
			for(int i = 0; i<list.size(); i++) {
				list.get(i).setCount(0);
			}
		};
		UserVo uVo = userService.countByName(userVo);
		int count = 0;
		if(uVo.getCount() != 0) {
			count = uVo.getCount();
		};

		Map<String , Object> result = new HashMap<String , Object>();		
		result.put("code", 1);
		result.put("msg", "Success");
		//JSONArray array = JSONArray.fromObject(list);
		result.put("data", list);
		result.put("count", count);
		
		return result;	
	}
	
	/**
	 * @Title: ZtSaveRole
	 * @description 跳转新增用户页面
	 * @param @param role
	 * @param @param model
	 * @param @param session
	 * @return String    
	 * @author dujiawei
	 * @createDate 2019年7月4日
	 */
	@RequiresPermissions("/saveUser")
	@RequestMapping("/ZtSaveUser")
	public String ZtSaveRole(Role role, Model model, HttpSession session) {
		BigInteger shopId = (BigInteger)session.getAttribute("shopId");
		role.setShopId(shopId);
		List<Role> AllRole = roleService.selectAllRole(role);
		model.addAttribute("AllRole",AllRole);
		
		return "views/entireManage/userManage/adduser";
	}
	
	/**
	 * @Title: saveUser
	 * @description 新增用户
	 * @param @param userVo
	 * @param @param session
	 * @param @throws CustomException   
	 * @return Map<String,Object>    
	 * @author dujiawei
	 * @createDate 2019年7月4日
	 */
	//@RequiresPermissions("/saveUser")
	@RequestMapping("/saveUser")
	@ResponseBody
	public Map<String , Object> saveUser(String shopID, UserVo userVo, HttpSession session) throws CustomException {
		if (userVo.getPassword() != null && userVo.getPassword() != "") {
            try {
            	userVo.setPassword(MD5Util.md5Encode(userVo.getPassword()));
            } catch (Exception e) {
                throw new CustomException("用户注册时加密出现错误，请联系管理员！！！");
            }
        }
		//如果未传店铺id，则是当前登陆用户添加用户；否则，是区域经理添加各自店铺的用户
		BigInteger shopId;
		if(shopID == null || shopID == ""){
			shopId = (BigInteger)session.getAttribute("shopId");
			//shopId = new BigInteger("1");
			userVo.setShopId(shopId);
		}else{
			shopId =  new BigInteger(shopID);
	        userVo.setShopId(shopId);
		}
		
		int num = userService.saveUser(userVo);
		
		// 添加一条操作记录
        User user = (User)session.getAttribute("user");
        UserOperation userOperation = new UserOperation();
        userOperation.setShopId(shopId);
        userOperation.setUserName(user.getUsername());
        userOperation.setName(user.getName());
        userOperation.setOperatingContent("新增用户");
        userOperationMapper.saveUserOperation(userOperation);
		
		Map<String , Object> map = new HashMap<String , Object>();
		if(num == 1) {
			map.put("code", 1);
			map.put("msg", "Success");
		} else if(num == -1) {
			map.put("code", 0);
			map.put("msg", "该用户已存在！");
		} else {
			map.put("code", 0);
			map.put("msg", "新增失败了");
		}
		
		return map;
	}
	
	/**
	 * @Title: ZtupdateAllRole
	 * @description 跳转修改用户页面
	 * @param @param role
	 * @param @param model
	 * @param @param session
	 * @return String    
	 * @author dujiawei
	 * @createDate 2019年7月4日
	 */
	@RequiresPermissions("/updateUser")
	@RequestMapping("/ZtupdateUser")
	public String ZtupdateAllRole(Role role, Model model,HttpSession session) {
	    BigInteger shopId = (BigInteger)session.getAttribute("shopId");
	    role.setShopId(shopId);
		List<Role> AllRole = roleService.selectAllRole(role);
		model.addAttribute("AllRole",AllRole);
		
		return "views/entireManage/userManage/updateuser";
	}
	
	/**
	 * @Title: updateUser
	 * @description 修改用户
	 * @param @param userVo
	 * @param @param session
	 * @return Map<String,Object>    
	 * @author dujiawei
	 * @createDate 2019年7月4日
	 */
	//@RequiresPermissions("/updateUser")
	@RequestMapping("/updateUser")
	@ResponseBody
	public Map<String , Object> updateUser(String shopID, UserVo userVo, HttpSession session) {
		
		//如果未传店铺id，则是当前登陆用户添加用户；否则，是区域经理添加各自店铺的用户
		BigInteger shopId;
		if(shopID == null || shopID == ""){
			shopId = (BigInteger)session.getAttribute("shopId");
			//shopId = new BigInteger("1");
			userVo.setShopId(shopId);
		}else{
			shopId =  new BigInteger(shopID);
	        userVo.setShopId(shopId);
		}
		int num = userService.updateUser(userVo);
		
		// 添加一条操作记录
        User user = (User)session.getAttribute("user");
        UserOperation userOperation = new UserOperation();
        userOperation.setShopId(shopId);
        userOperation.setUserName(user.getUsername());
        userOperation.setName(user.getName());
        userOperation.setOperatingContent("修改用户");
        userOperationMapper.saveUserOperation(userOperation);
		
		Map<String , Object> map = new HashMap<String , Object>();
		if(num == 1){
			map.put("code", 1);
			map.put("msg", "Success");
		} else {
			map.put("code", 0);
			map.put("msg", "修改失败了");
		}
		
		return map;
	}
	
	/**
	 * @Title: removeUser
	 * @description 删除用户
	 * @param @param id
	 * @param @param session
	 * @return Map<String,Object>    
	 * @author dujiawei
	 * @createDate 2019年7月4日
	 */
	//@RequiresPermissions("/removeUser")
	@RequestMapping("/removeUser")
	@ResponseBody
	public Map<String , Object> removeUser(BigInteger id ,HttpSession session) {	
		int num = userService.removeUser(id);
		
		// 添加一条操作记录
		BigInteger shopId = (BigInteger)session.getAttribute("shopId");
        User user = (User)session.getAttribute("user");
        UserOperation userOperation = new UserOperation();
        userOperation.setShopId(shopId);
        userOperation.setUserName(user.getUsername());
        userOperation.setName(user.getName());
        userOperation.setOperatingContent("删除用户");
        userOperationMapper.saveUserOperation(userOperation);
		
		Map<String , Object> map = new HashMap<String , Object>();
		if(num == 1){
			map.put("code", 1);
			map.put("msg", "Success");
		} else {
			map.put("code", 0);
			map.put("msg", "删除失败了");
		}
		
		return map;
	}
	
	/**
	 * @Title: listUserName
	 * @description (下拉单用)查询本店所有员工id和员工姓名
	 * @param @param model
	 * @param @param userVo
	 * @param @param session
	 * @return Object    
	 * @author dujiawei
	 * @createDate 2019年7月4日
	 */
	//@RequiresPermissions("/listUserName")
	@RequestMapping("/listUserName")
	@ResponseBody
	public Object listUserName(Model model, UserVo userVo, HttpSession session) {
		BigInteger shopId =  (BigInteger)session.getAttribute("shopId");
		userVo.setShopId(shopId);
		List<UserVo>  list = userService.listUserName(userVo);
		
		Map<String , Object> result = new HashMap<String , Object>();		
		result.put("code", 1);
		result.put("msg", "Success");
		//JSONArray array = JSONArray.fromObject(list);
		result.put("data", list);
		
		return result;	
	}
	
	/**
	 * @Title: listAgentShop
	 * @description 区域经理管理的店铺（下拉单）
	 * @param @param model
	 * @param @param userShopVo
	 * @param @param session
	 * @return Object    
	 * @author dujiawei
	 * @createDate 2019年7月5日
	 */
	@RequestMapping("/listAgentShop")
	@ResponseBody
	public Object listAgentShop(Model model, UserShopVo userShopVo, HttpSession session) {
		//String userName =  (String) session.getAttribute("username");
		//String userName = "admin";
		User user = (User)session.getAttribute("user");
		userShopVo.setUsername(user.getUsername());
		//通过用户账号获取用户信息
		UserShopVo us = userService.getUserByName(userShopVo); 
		
		List<UserShopVo> agentShops = new ArrayList<UserShopVo>();
		
		//判断代理的类型（0.不是代理；1.省级代理；2.市级代理；3.区级代理）
		if(us.getAgentType() != null){
			if(us.getAgentType() == 0){
				agentShops = null;
			}else if(us.getAgentType() == 1){
				userShopVo.setProvinceId(us.getProvinceId());
				agentShops = userService.listShopByProvinceAgent(userShopVo);
				
			}else if(us.getAgentType() == 2){
				userShopVo.setAreaId(us.getCityId());
				agentShops = userService.listShopByCityAgent(userShopVo);
				
			}else if(us.getAgentType() == 3){
				userShopVo.setArea(us.getArea());
				agentShops = userService.listShopByAreaAgent(userShopVo);
				
			}
		}
		
		
		Map<String , Object> result = new HashMap<String , Object>();		
		result.put("code", 1);
		result.put("msg", "Success");
		//JSONArray array = JSONArray.fromObject(agentShops);
		result.put("data", agentShops);
		
		return result;	
	}

}
