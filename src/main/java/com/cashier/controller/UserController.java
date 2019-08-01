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
import com.cashier.entity.ShopListDTO;
import com.cashier.entity.User;
import com.cashier.entity.UserOperation;
import com.cashier.entityVo.UserShopVo;
import com.cashier.entityVo.UserVo;
import com.cashier.service.RoleService;
import com.cashier.service.ShopService;
import com.cashier.service.UserService;
import com.cashier.util.MD5Util;

/**
 * @ClassName: UserController
 * @description 用户表的控制层
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
     * @Title: listShopIdAndName
     * @description 添加区域经理时异步加载区域下的店铺信息
     * @param User user
     * @return List<Shop>    
     * @author zhou jiaxin
     * @createDate 2019年7月26日
     */
    @RequestMapping("/listShopIdAndNameForManager")
    @ResponseBody
    public Map<String, Object> listShopIdAndNameForManager(Model model,User user,ShopListDTO shopListDTO) {
        Map<String, Object> map = new HashMap<>();
        if (user.getAgentType()== 1) {
            String userProvinceId = user.getUserProvinceId().toString().substring(0, 2);
            String beginNumber = userProvinceId+"0000";
            String endNumber = userProvinceId+"9999";
            shopListDTO.setBeginNumber(Integer.parseInt(beginNumber));
            shopListDTO.setEndNumber(Integer.parseInt(endNumber));
            // 101通过省级区域经理查询对应省里的店铺信息（ID和名称）
            List<Shop> shopList = userService.listShopMsgByProvince(shopListDTO);
            map.put("data", shopList);
            map.put("code", 1);
            map.put("msg", "查询成功");
        }else if (user.getAgentType()==2) {
            String userProvinceId = user.getUserCityId().toString().substring(0, 4);
            String beginNumber = userProvinceId+"00";
            String endNumber = userProvinceId+"99";
            shopListDTO.setBeginNumber(Integer.parseInt(beginNumber));
            shopListDTO.setEndNumber(Integer.parseInt(endNumber));
            // 102通过市级区域经理查询对应市里的店铺信息（ID和名称）
            List<Shop> shopList = userService.listShopMsgByCity(shopListDTO);
            map.put("data", shopList);
            map.put("code", 1);
            map.put("msg", "查询成功");
        }else {
            // 103通过区级区域经理查询对应区里的店铺信息（ID和名称）
            List<Shop> shopList = userService.listShopMsgByArea(user);
            map.put("data", shopList);
            map.put("code", 1);
            map.put("msg", "查询成功");
        }
        return map;
    }
	
	
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
	public Map<String , Object> saveUser(UserVo userVo, HttpSession session) throws CustomException {
		if (userVo.getPassword() != null && userVo.getPassword() != "") {
            try {
            	userVo.setPassword(MD5Util.md5Encode(userVo.getPassword()));
            } catch (Exception e) {
                throw new CustomException("用户注册时加密出现错误，请联系管理员！！！");
            }
        }
		if (userVo.getAgentType()==0) {
		    BigInteger shopId = (BigInteger)session.getAttribute("shopId");
		    userVo.setShopId(shopId);
        }else{
            //添加区域经理，区域经理的角色ID是通过分店ID获取分店超级管理员角色的ID来进行添加的
            Role role = shopService.getRoleIdByShopId(userVo);
            userVo.setRoleId(role.getId());
        }
		int num = userService.saveUser(userVo);
		
		// 添加一条操作记录
        User user = (User)session.getAttribute("user");
        UserOperation userOperation = new UserOperation();
        userOperation.setShopId(userVo.getShopId());
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
	 * 
	 * 周嘉鑫190726
	 * 1.区域经理属于区域下的一个分店用户，首次登陆时显示的是所属分店的信息
	 * 2.区域经理的增删该查由总店统一管理
	 * 
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
		User user = (User)session.getAttribute("user");
		Shop shop = (Shop)session.getAttribute("shop");
		userShopVo.setUsername(user.getUsername());
		//通过用户账号获取用户信息
		//UserShopVo us = userService.getUserByName(userShopVo); 
		List<UserShopVo> agentShops = new ArrayList<UserShopVo>();
		//判断登陆用户是总店还是分店
		if(shop.getType() == 1){
			agentShops = shopService.listAllShopIdAndName(userShopVo); //总店查看所有店铺
		}else {
			//是分店后，判断代理的类型（0.不是代理；1.省级代理；2.市级代理；3.区级代理）
			if(user.getAgentType() != null){
				if(user.getAgentType() == 1){
					userShopVo.setUserProvinceId(user.getUserProvinceId());
					agentShops = userService.listShopByProvinceAgent(userShopVo);
				}else if(user.getAgentType() == 2){
					userShopVo.setUserCityId(user.getUserCityId());
					agentShops = userService.listShopByCityAgent(userShopVo);
				}else if(user.getAgentType() == 3){
					userShopVo.setAreaId(user.getAreaId());
					agentShops = userService.listShopByAreaAgent(userShopVo);
				}
			}
		}
		model.addAttribute("shopList", agentShops);  //存储区域经理管理的店铺
		Map<String , Object> result = new HashMap<String , Object>();		
		result.put("code", 1);
		result.put("msg", "Success");
		result.put("data", agentShops);
		return result;	
	}

}
