package com.cashier.controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cashier.dao.UserOperationMapper;
import com.cashier.entity.Permission;
import com.cashier.entity.Shop;
import com.cashier.entity.ShopListDTO;
import com.cashier.entity.User;
import com.cashier.entity.UserOperation;
import com.cashier.entityDTO.PermissionDTO;
import com.cashier.entityDTO.ShopUserPermissionDTO;
import com.cashier.entityVo.PermissionVo;
import com.cashier.entityVo.ShopVo;
import com.cashier.entityVo.UserVo2;
import com.cashier.service.RoleService;
import com.cashier.service.ShopService;
import com.cashier.service.UserService;
import com.cashier.service.ex.ServiceException;

import net.sf.json.JSONArray;

/**
 *
 * @ClassName: ShopController
 * @description shop表的控制层
 *
 * @author linhongyu
 * @createDate 2018年11月8日
 */
@Controller
public class ShopController {

	@Resource
	private ShopService shopService;
	@Resource
	private UserService userService;
	@Resource
	private RoleService roleService;
	@Resource
	private UserOperationMapper userOperationMapper;
	/**
	 * @Title: getGeneralScopeById
	 * @description 通过店铺id查询店铺省市区号和会员消费范围
	 * @param model,
	 *            session, shopVo
	 * @return Object
	 * @author dujiawei
	 * @createDate 2018年12月13日
	 */
	// @RequiresPermissions("/getGeneralScopeById")
	@RequestMapping("/getGeneralScopeById")
	@ResponseBody
	public Object getGeneralScopeById(Model model, ShopVo shopVo, HttpSession session) {
		BigInteger branchId = (BigInteger) session.getAttribute("shopId");
		shopVo.setId(branchId);
		ShopVo shop = shopService.getGeneralScopeById(shopVo);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", 0);
		result.put("msg", "Success");
		JSONArray array = JSONArray.fromObject(shop);
		result.put("data", array);

		return result;
	};

	/**
	 * description 跳转列表页面
	 *
	 * @author dujiawei
	 * @createDate 2018年12月4日
	 */
	@RequiresPermissions("/listAllShopVo")
	@RequestMapping("/ZtlistAllShopVo")
	public String ZtlistAllShopVo(Model model) {

		return "/views/entireManage/storeManage/storeManage";
	}

	/**
	 * @Title: listAllShopVo
	 * @description 查询所有商铺信息
	 * @param shopVo,
	 *            model, limit
	 * @return Map
	 * @author dujiawei
	 * @createDate 2018年12月5日
	 */
	// @RequiresPermissions("/listAllShopVo")
	@RequestMapping("/listAllShopVo")
	@ResponseBody
	public Map<String, Object> listAllShopVo(Model model, Integer page, Integer limit, ShopVo shopVo) {
		shopVo.setPage((page - 1) * limit);
		shopVo.setLimit(limit);
		List<ShopVo> shopList = shopService.listAllShopVo(shopVo);
		if (shopList.size() > 0) {
			for (int i = 0; i < shopList.size(); i++) {
				shopList.get(i).setCount(0);
			}
		}
		;
		ShopVo sVo = shopService.countShop(shopVo);
		int count = 0;
		if (sVo.getCount() != 0) {
			count = sVo.getCount();
		}
		;

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", 1);
		result.put("msg", "Success");
		result.put("data", shopList);
		result.put("count", count);

		return result;
	};

	/**
	 * description 跳转修改店铺信息页面
	 *
	 * @author chenshuxian
	 * @createDate 2019年7月5日
	 */
	// @RequiresPermissions("/updateShop")
	@RequestMapping("/showUpdateShop")
	@ResponseBody
	public Map<String, Object> ZtupdateShop(User user, HttpSession session) {
		Map<String, Object> map = new HashMap<>();
		Shop s = new Shop();
		s.setId(user.getShopId());
		Shop shop = shopService.getId(s);

		// 通过店铺ID获取该店铺的超级管理员信息
		UserVo2 userVo2 = userService.getSuperUserByShopId(user);
		//查询所有省市区
	 	List<ShopVo> listCity= shopService.getAllCity();
		// 获取当前管理者的权限信息
		String username = (String) session.getAttribute("username");
		
		if (username =="" || username == null) { 
			map.put("code", -2);
			map.put("msg", "登陆超时,请重新登陆"); 
		 	return map; //抛异常登陆超时
		 }
		 
		// 超级管理员信息
		map.put("userVo2", userVo2);
		map.put("shop", shop);
		//List<PermissionVo> currentPermissionVolist = userService.listPermissionByCurrentUser(username);
		// 根据分店ID获取当前分店的权限信息
		List<PermissionVo> PermissionVolist = roleService.getPermissionListByShopId(user);
	/*	// 数据对比，用于修改页面回显
		for (int j = 0; j < currentPermissionVolist.size(); j++) {
			PermissionVo curPermissionVo = currentPermissionVolist.get(j);
			for (int i = 0; i < PermissionVolist.size(); i++) {
				PermissionVo permissionVo = PermissionVolist.get(i);
				if (permissionVo.getParent_names().equals(curPermissionVo.getParent_names())) {
					List<Permission> permissions = permissionVo.getPermissions();
					List<Permission> curPermissions = curPermissionVo.getPermissions();
					for (int k = 0; k < permissions.size(); k++) {
						for (int m = 0; m < curPermissions.size(); m++) {
							if (permissions.get(k).getId().compareTo(curPermissions.get(m).getId()) == 0) {
								curPermissions.get(m).setState(1);
							}
						}
					}
					break;
				}
			}
		}*/
		List<PermissionDTO> listPermissionDTO = new ArrayList<>();
		for(PermissionVo p : PermissionVolist){
			PermissionDTO permissionDTO = new PermissionDTO();
			permissionDTO.setId(p.getParentIds());
			for(Permission per : p.getPermissions()){
				PermissionDTO permission = new PermissionDTO();
				permission.setId(per.getId());
				listPermissionDTO.add(permission);
			}
			listPermissionDTO.add(permissionDTO);
		}
		
		// 权限信息
		map.put("PermissionVolist", listPermissionDTO);
		//map.put("listCity", listCity);
		map.put("code", 1);
		map.put("msg", "成功");
		// model.addAttribute("PermissionVolist", currentPermissionVolist);
		return map;
	}

	/**
	 * @Title: updateShop
	 * @description 修改店铺信息
	 * @param model,
	 *            shop
	 * @return Object
	 * @author dujiawei
	 * @createDate 2018年12月5日
	 */
	@RequestMapping("/updateShop")
	@RequiresPermissions("/updateShop")
	@ResponseBody
	public Object updateShop(Model model, ShopVo shopVo, ShopUserPermissionDTO shopUserPermissionDTO, String ids,HttpSession session) {
		int num = shopService.updateShop(shopVo, shopUserPermissionDTO, ids);

		
		Map<String, Object> map = new HashMap<String, Object>();
		if (num == 1) {
			 // 添加一条操作记录
	        User u = (User)session.getAttribute("user");
	        UserOperation userOperation = new UserOperation();
	        userOperation.setShopId(new BigInteger(session.getAttribute("shopId")+""));
	        userOperation.setUserName(u.getUsername());
	        userOperation.setName(u.getName());
	        userOperation.setOperatingContent("修改店铺信息");
	        userOperationMapper.saveUserOperation(userOperation);
			map.put("code", 1);
			map.put("msg", "Success");
		} else {
			map.put("code", 0);
			map.put("msg", "修改失败了");
		}

		return map;
	};

	/**
	 * description 跳转新增店铺信息页面
	 *
	 * @author chenshuxian
	 * @createDate 2019年7月4日
	 */
	// @RequiresPermissions("/insertShop")
	@RequestMapping("/showInsertShop")
	@ResponseBody
	public Map<String, Object> ZtinsertShop(Model model, HttpSession session) {
		Map<String, Object> map = new HashMap<>();
		String username = (String) session.getAttribute("username");
		if (username == "" || username == null) {
			map.put("code", -1);
			map.put("msg", "登陆超时，请重新登陆");
		}
		//查询所有省市区
	 	List<ShopVo> listCity= shopService.getAllCity();
		List<PermissionVo> PermissionVolist = userService.listPermissionByCurrentUser(username);
		// model.addAttribute("PermissionVolist", PermissionVolist);
		map.put("PermissionVolist", PermissionVolist);
		map.put("listCity", listCity);
		return map;
	}

	/**
	 * @Title: insertShop
	 * @description 添加商铺
	 * @param shopVo
	 * @return Object
	 * @author chenshuxian
	 * @createDate 2019年7月4日
	 */
	@RequestMapping("/insertShop")
	@RequiresPermissions("/insertShop")
	@ResponseBody
	public Map<String, Object> insertShop(ShopUserPermissionDTO shopUserPermissionDTO, String ids,HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			int num = shopService.insertShop(shopUserPermissionDTO, ids);
			if (num != 0) {
				 // 添加一条操作记录
		        User u = (User)session.getAttribute("user");
		        UserOperation userOperation = new UserOperation();
		        userOperation.setShopId(new BigInteger(session.getAttribute("shopId")+""));
		        userOperation.setUserName(u.getUsername());
		        userOperation.setName(u.getName());
		        userOperation.setOperatingContent("添加店铺信息");
		        userOperationMapper.saveUserOperation(userOperation);
				map.put("code", 1);
				map.put("msg", "添加成功");
			}

		} catch (ServiceException e) {
			map.put("code", 0);
			map.put("msg", e.getMessage());
		}

		return map;
	};

	/**
	 * @Title: deleteShop
	 * @description 删除商铺信息
	 * @param shop
	 * @return Object
	 * @author dujiawei
	 * @createDate 2018年12月5日
	 */
	@RequiresPermissions("/deleteShop")
	@RequestMapping("/deleteShop")
	@ResponseBody
	public Object deleteShop(Shop shop,HttpSession session) {
		int num = shopService.deleteShop(shop);

		Map<String, Object> map = new HashMap<String, Object>();
		if (num == 1) {
			 // 添加一条操作记录
	        User u = (User)session.getAttribute("user");
	        UserOperation userOperation = new UserOperation();
	        userOperation.setShopId(new BigInteger(session.getAttribute("shopId")+""));
	        userOperation.setUserName(u.getUsername());
	        userOperation.setName(u.getName());
	        userOperation.setOperatingContent("删除店铺");
	        userOperationMapper.saveUserOperation(userOperation);
			map.put("code", 1);
			map.put("msg", "Success");
		} else {
			map.put("code", 0);
			map.put("msg", "删除失败了");
		}

		return map;
	};

	/**
	 * @Title: listByShopNameVo
	 * @description 通过店铺名称查询信息
	 * @param model,
	 *            page, limit, shopVo
	 * @return Object
	 * @author chenshuxian
	 * @createDate 2019年7月6日
	 */
	@RequestMapping("/listByShopNameVo")
	@RequiresPermissions("/listByShopNameVo")
	@ResponseBody
	public Object listByShopNameVo(Model model, Integer page, Integer limit, ShopVo shopVo) {
		shopVo.setPage((page - 1) * limit);
		shopVo.setLimit(limit);
		List<ShopVo> shopList = shopService.listByShopNameVo(shopVo);
		System.out.println(shopList);
		if (shopList.size() > 0) {
			for (int i = 0; i < shopList.size(); i++) {
				shopList.get(i).setCount(0);
			}
		}
		;
		ShopVo sVo = shopService.countByShopName(shopVo);
		int count = 0;
		if (sVo.getCount() != 0) {
			count = sVo.getCount();
		}
		;

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", 1);
		result.put("msg", "Success");
		JSONArray array = JSONArray.fromObject(shopList);
		result.put("data", array);
		result.put("count", count);

		return result;
	};

	/**
	 * @Title: shopID
	 * @description 通过ID查询地区ID
	 * @param
	 * @return Shop
	 * @author linhongyu
	 * @createDate 2018年11月20日
	 */
	// @RequiresPermissions("/shopID")
	@RequestMapping("/shopID")
	@ResponseBody
	public Map<String, Object> getId(Shop shop) {
		shop = shopService.getId(shop);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", "1");
		map.put("msg", "ok");
		map.put("data", shop);
		return map;

	}

	/**
	 * 
	 * @Title: 获得所有店铺名称和id 供条件查询下拉选使用
	 * @description listShopIdAndName
	 * @param
	 * @return
	 * @author chenshuxian
	 * @createDate 2019年7月6日
	 */
	@RequestMapping("/listShopIdAndName")
	@RequiresPermissions("/listShopIdAndName")
	@ResponseBody
	public Map<String, Object> listShopIdAndName(HttpSession session,ShopListDTO shopListDTO) {
	    User user = (User)session.getAttribute("user");
	    Map<String, Object> map = new HashMap<String, Object>();
	    if(user.getAgentType()==1 ||user.getAgentType()==2 || user.getAgentType()==3){
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
	    }else{
	        List<Shop> listShop = shopService.listShopIdAndName();
	        map.put("code", 1);
	        map.put("msg", "ok");
	        map.put("data", listShop); 
	    }
		return map;
	}
	
}
