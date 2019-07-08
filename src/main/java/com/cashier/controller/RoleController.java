package com.cashier.controller;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.cashier.dao.UserOperationMapper;
import com.cashier.entity.Role;
import com.cashier.entity.Shop;
import com.cashier.entity.User;
import com.cashier.entity.UserOperation;
import com.cashier.entityVo.RoleVo;
import com.cashier.entityVo.UserOperationVo;
import com.cashier.service.RoleService;
import com.cashier.service.ShopService;
import net.sf.json.JSONArray;

/**
 *
 * @ClassName: RoleController
 * @description 角色Controller控制层
 *
 * @author dujiawei
 * @createDate 2019年7月4日
 */
@Controller
public class RoleController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private ShopService shopService;
	@Autowired
	private UserOperationMapper userOperationMapper;
	
	/**
	 * @Title: selectAllRoleForZ
     * @description  查询所有角色
     * @author 
     * @createDate 2019年7月4日
     */
	//@RequiresPermissions("/listBsiTablesBySupper")
    @RequestMapping("/selectAllRoleForZ")
    public String selectAllRoleForZ(String shopID ,Model model,Role role,HttpSession session) {
        /** 获得各分店铺id和名称 */
        List<Shop> shopList = shopService.listShopIdAndName();
        model.addAttribute("shopList", shopList);
        BigInteger shopId = new BigInteger(shopID);
        if (shopId != null) {
            role.setShopId(shopId);
        }
        List<Role> AllRole = roleService.selectAllRole(role);
        JSONArray jsonArray = JSONArray.fromObject(AllRole);
        model.addAttribute("AllRole",jsonArray);
        model.addAttribute("shopId",shopId);
        
        return "/views/entireManage/roleManage/roleManage";
    }
	/**
	 * @Title: selectAllRole
     * @description 查询所有角色（不分页）
	 * @author dujiawei
	 * @createDate 2019年7月4日
	 */
    //@RequiresPermissions("/selectAllRole")
	@RequestMapping("/selectAllRole")
	public String selectAllRole(Model model,Role role,HttpSession session) {
	    /** 获得各分店铺id和名称 */
        List<Shop> shopList = shopService.listShopIdAndName();
        model.addAttribute("shopList", shopList);
	    BigInteger shopId = (BigInteger)session.getAttribute("shopId");
	    if (shopId != null) {
            role.setShopId(shopId);
        }
		List<Role> AllRole = roleService.selectAllRole(role);
		JSONArray jsonArray = JSONArray.fromObject(AllRole);
		model.addAttribute("AllRole",jsonArray);
		model.addAttribute("shopId",shopId);
		return "/views/entireManage/roleManage/roleManage";
	}
	
    /**
     * @Title: selectAllRoleByNameForZ
     * @description 条件查询角色
     * @param @param shopID
     * @param @param model
     * @param @param role
     * @param @param session
     * @return Map<String,Object>    
     * @author 
     * @createDate 2019年7月4日
     */
    //@RequiresPermissions("/listBsiTablesBySupper")
	@RequestMapping("/selectAllRoleByNameForZ")
    @ResponseBody
    public Map<String, Object> selectAllRoleByNameForZ(String shopID ,Model model,Role role,HttpSession session) {
        BigInteger shopId = new BigInteger(shopID);
        if (shopId != null) {
            role.setShopId(shopId);
        }
        List<Role> AllRole = roleService.selectAllRole(role);
        model.addAttribute("AllRole",AllRole);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", 0);
        result.put("msg", "Success");
        //JSONArray array = JSONArray.fromObject(AllRole);
        result.put("data", AllRole);
        
        return result;
    }

	/**
	 * @Title: selectAllRoleByName
	 * @description 条件查询角色
	 * @param @param model
	 * @param @param role
	 * @param @param session
	 * @return Map<String,Object>    
	 * @author dujiawei
	 * @createDate 2019年7月4日
	 */
	//@RequiresPermissions("/selectAllRole")
	@RequestMapping("/selectAllRoleByName")
    @ResponseBody
    public Map<String, Object> selectAllRoleByName(Model model,Role role,HttpSession session) {
	    BigInteger shopId = (BigInteger)session.getAttribute("shopId");
	    if (shopId != null) {
	        role.setShopId(shopId);
	    }
        List<Role> AllRole = roleService.selectAllRole(role);
        model.addAttribute("AllRole",AllRole);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", 0);
        result.put("msg", "Success");
        //JSONArray array = JSONArray.fromObject(AllRole);
        result.put("data", AllRole);
        
        return result;
    }
	
	/**
	 * @Title: listRole
	 * @description 分页显示本店角色列表
	 * @param @param model
	 * @param @param page
	 * @param @param limit
	 * @param @param roleVo
	 * @param @param session
	 * @return Map<String,Object>    
	 * @author dujiawei
	 * @createDate 2019年7月4日
	 */
	@RequestMapping("/listRole")
	@ResponseBody
	public Map<String,Object> listRole(Model model, Integer page, Integer limit, RoleVo roleVo, HttpSession session) {
		Map<String , Object> result = new HashMap<String , Object>();
		BigInteger shopId = (BigInteger)session.getAttribute("shopId");
		//BigInteger shopId = new BigInteger("1");
		roleVo.setShopId(shopId);
		roleVo.setPage((page-1)*limit);
		roleVo.setLimit(limit);
		List<RoleVo> list = roleService.listRole(roleVo);  //角色列表
		if(list.size() > 0) {
			for(int i = 0; i<list.size(); i++) {
				list.get(i).setCount(0);
			}
		};
		RoleVo rVo = roleService.countRole(roleVo);//角色的数量
		int count = 0;
		if(rVo.getCount() != 0) {
			count = rVo.getCount();
		};
		result.put("code", 1);
		result.put("msg", "Success");
		result.put("data", list);
		result.put("count", count);
		
		return result;	
	}
	
	/**
     * @Title: ZTsaveRole
     * @description 转跳角色添加界面
     * @author dujiawei
     * @createDate 2019年7月4日
     */
	//@RequiresPermissions("/saveRole")
    @RequestMapping("/ZTsaveRole")
    public String ZTsaveRole(Role role, Model model,HttpSession session) {
        return "/views/entireManage/roleManage/addrole";
    }
	/**
	 * @Title: saveRole
     * @description  添加角色
	 * @author dujiawei
	 * @createDate 2019年7月4日
	 */
	//@RequiresPermissions("/saveRole")
	@RequestMapping("/saveRole")
	@ResponseBody
	public Map<String , Object> saveRole(String shopID, Role role, HttpSession session) {
		
		//如果未传店铺id，则是当前登陆用户添加角色；否则，是区域经理添加各自店铺的角色
		BigInteger shopId;
		if(shopID == null || shopID == ""){
			shopId = (BigInteger)session.getAttribute("shopId");
			//shopId = new BigInteger("1");
			role.setShopId(shopId);
		}else{
			shopId =  new BigInteger(shopID);
			role.setShopId(shopId);
		}
        /*BigInteger shopId = (BigInteger)session.getAttribute("shopId");
        if (shopId != null) {
            role.setShopId(shopId);
        }*/	
        // 执行新增方法
        int num = roleService.saveRole(role); 
        // 添加一条操作记录
        User user = (User)session.getAttribute("user");
        UserOperation userOperation = new UserOperation();
        userOperation.setShopId(shopId);
        userOperation.setUserName(user.getUsername());
        userOperation.setName(user.getName());
        userOperation.setOperatingContent("添加角色");
        userOperationMapper.saveUserOperation(userOperation);
        
        Map<String , Object> map = new HashMap<String , Object>();
        if(num == 1){
            map.put("code", 1);
            map.put("msg", "Success");
        } else {
            map.put("code", 0);
            map.put("msg", "添加角色名称重复");
        }
        
        return map;
	}

	/**
	 * @Title: selectRoleById
     * @description 转跳角色修改界面
	 * @author zjx
	 * @createDate 2019年7月4日
	 */
	//@RequiresPermissions("/updateRole")
	@RequestMapping("/selectRoleById")
	public String selectRoleById(Role role, Model model,HttpSession session) {
        return "/views/entireManage/roleManage/updaterole";
	}
	
	
	
	
	/**
	 * @Title: updateRole
     * @description 修改角色
	 * @author dujiawei
	 * @createDate 2019年7月4日
	 */
	//@RequiresPermissions("/updateRole")
	@RequestMapping("/updateRole")
	@ResponseBody
	public Map<String , Object> updateRole(String shopID, Model model, Role role, HttpSession session) {
		
		//如果未传店铺id，则是当前登陆用户修改角色；否则，是区域经理修改各自店铺的角色
		BigInteger shopId;
		if(shopID == null || shopID == ""){
			shopId = (BigInteger)session.getAttribute("shopId");
			//shopId = new BigInteger("1");
			role.setShopId(shopId);
		}else{
			shopId =  new BigInteger(shopID);
			role.setShopId(shopId);
		}
		/*BigInteger shopId = (BigInteger)session.getAttribute("shopId");
	    if (shopId != null) {
	        role.setShopId(shopId);
	    }*/
	    int num = roleService.updateRole(role);
	    
	    // 添加一条操作记录
        User user = (User)session.getAttribute("user");
        UserOperation userOperation = new UserOperation();
        userOperation.setShopId(shopId);
        userOperation.setUserName(user.getUsername());
        userOperation.setName(user.getName());
        userOperation.setOperatingContent("修改角色");
        userOperationMapper.saveUserOperation(userOperation);
        
	    Map<String , Object> map = new HashMap<String , Object>();
        if(num == 1){
            map.put("code", 1);
            map.put("msg", "Success");
        } else {
            map.put("code", 0);
            map.put("msg", "角色名称重复");
        }
        
        return map;
	}
	
	/**
	 * @Title: deleteRoleById
     * @description 根据id删除角色
	 * @author dujiawei
	 * @createDate 2019年7月4日
	 */
	//@RequiresPermissions("/deleteRoleById")
	@RequestMapping("/deleteRoleById")
	@ResponseBody
	public Map<String , Object> deleteRoleById(Model model, BigInteger id,HttpSession session) {
		int  ss = roleService.deleteRoleById(id);
		// 添加一条操作记录
        User user = (User)session.getAttribute("user");
        BigInteger shopId = (BigInteger)session.getAttribute("shopId");
        UserOperation userOperation = new UserOperation();
        userOperation.setShopId(shopId);
        userOperation.setUserName(user.getUsername());
        userOperation.setName(user.getName());
        userOperation.setOperatingContent("添加角色");
        userOperationMapper.saveUserOperation(userOperation);
		
		Map<String, Object> map = new HashMap<String, Object>();
		if(ss ==1){
			map.put( "code",1);
			map.put( "msg","Success");
		}else{
			map.put( "code",0);
			map.put( "msg","失败");
		}
		
		return map;
	}
	
}
