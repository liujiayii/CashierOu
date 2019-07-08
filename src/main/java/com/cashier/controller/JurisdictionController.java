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
import com.cashier.entity.Permission;
import com.cashier.entity.Role;
import com.cashier.entity.RolePermission;
import com.cashier.entity.User;
import com.cashier.entity.UserOperation;
import com.cashier.entityVo.PermissionVo;
import com.cashier.entityVo.UserOperationVo;
import com.cashier.service.JurisdictionService;
import com.cashier.service.RoleService;
import com.cashier.service.UserService;
import net.sf.json.JSONArray;

/**
 *
 * @ClassName: JurisdictionController

 * @description 权限的相关操作
 *
 * @author 
 * @createDate 2019年1月6日
 */
@Controller
public class JurisdictionController {
	@Autowired
	private RoleService roleService;
	@Autowired
	private JurisdictionService jurisdictionService;
	@Autowired
    private UserService userService;
	@Autowired
	private UserOperationMapper userOperationMapper;
	
	
	/**
	 * 用于跳转权限管理列表的action
	 *
	 * @author pangchong
	 * @createDate 2019年1月10日 下午2:00
	 */
	//@RequiresPermissions("/ztPermissions")
	@RequestMapping("/ztPermissions")
	public String selectAllRole(HttpSession session, Model model, Role role) {
	    BigInteger shopId = (BigInteger)session.getAttribute("shopId");
        if (shopId != null) {
            role.setShopId(shopId);
        }
        List<Role> AllRole = roleService.selectAllRole(role);
        JSONArray jsonArray = JSONArray.fromObject(AllRole);
        model.addAttribute("AllRole",jsonArray);
	    /*
		// 获得当前店铺id
		BigInteger shopId = new BigInteger(session.getAttribute("shopId") + "");
		role.setShopId(shopId);
		List<Role> AllRole = roleService.selectAllRole(role);
		model.addAttribute("AllRole", AllRole);
			*/
		return "views/entireManage/permissionsManage/permissionsManage";
	}

	
	/**
	 * 用于跳转展示具体权限列表的action
	 *
	 * @author pangchong
	 * @createDate 2018年9月12日 下午2:00
	 */
	//@RequiresPermissions("/updateOneRolePermission")
	@RequestMapping("/ztShowPermissions")
	@ResponseBody
	public Map<String,Object> ztShowPermissions(Model model,HttpSession session,Role role) {
		Map<String , Object> result = new HashMap<String , Object>();
		
	    String username = (String)session.getAttribute("username");
	    Object shopId = session.getAttribute("shopId");
	    role.setShopId(new BigInteger(""+shopId));
        if (username =="" || username == null) {
        	result.put("code", 0);
    		result.put("msg", "登陆超时");
            return result; // 抛异常登陆超时
        }
        // 获取当前分店中角色的权限信息
        List<PermissionVo> PermissionVolist = jurisdictionService.listPermissionByRoleIdAndShopId(role);
        // 获取当前登陆用户的权限信息
        List<PermissionVo> currentPermissionVolist = userService.listPermissionByCurrentUser(username);
        // 数据对比，用于修改页面回显
        for (int j = 0; j < currentPermissionVolist.size(); j++) {
            PermissionVo curPermissionVo = currentPermissionVolist.get(j);
            for (int i = 0; i < PermissionVolist.size(); i++) {
                PermissionVo permissionVo = PermissionVolist.get(i);
                if (permissionVo.getParent_names().equals(curPermissionVo.getParent_names())) {
                    List<Permission> permissions = permissionVo.getPermissions();
                    List<Permission> curPermissions = curPermissionVo.getPermissions();
                    for (int k = 0; k < permissions.size(); k++) {
                        for (int m = 0; m < curPermissions.size(); m++) {
                            if (permissions.get(k).getId().compareTo(curPermissions.get(m).getId())==0) {
                                curPermissions.get(m).setState(1);
                            }
                        }
                    }
                    break;
                }
            }
        }
        model.addAttribute("PermissionVolist", currentPermissionVolist);
        model.addAttribute("role", role);
		
        //return "views/entireManage/permissionsManage/showpermissionsManage";
        result.put("code", 1);
		result.put("msg", "Success");
		result.put("data", currentPermissionVolist);
		
		return result;
	}
	
	/**
     * @Title: updateOneRolePermission
     * @description 修改店铺中角色权限信息
     * @param  String IDS
     * @return Object    
     * @author zhou jiaxin
     * @createDate 2019年1月30日
     */
    //@RequiresPermissions("/updateOneRolePermission")
    @RequestMapping("/updateOneRolePermission")
    @ResponseBody
    public Map<String , Object> updateOneRolePermission(String shopID, Model model,Role role,String ids,HttpSession session){
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
        int num = jurisdictionService.updateOneRolePermission(role,ids);
        
        // 添加一条操作记录
        User user = (User)session.getAttribute("user");
        UserOperation userOperation = new UserOperation();
        userOperation.setShopId(shopId);
        userOperation.setUserName(user.getUsername());
        userOperation.setName(user.getName());
        userOperation.setOperatingContent("修改权限信息");
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
    };
	
	/**
	 * description 查询所有角色
	 * @author pangchong
	 * @createDate 2018年11月12日 上午10:00
	 */
    //@RequiresPermissions("/ztPermissions")
	@RequestMapping("/selectAllRoleList")
	@ResponseBody
	public Map<String, Object> selectAllProduct(HttpSession session, Model model, Role role) {
		// 获得当前店铺id
		BigInteger shopId = new BigInteger(session.getAttribute("shopId") + "");
		role.setShopId(shopId);
		List<Role> AllRole = roleService.selectAllRole(role);
		model.addAttribute("AllRole", AllRole);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", 1);
		result.put("msg", "成功");

		JSONArray array = JSONArray.fromObject(AllRole);
		result.put("data", array);
		return result;

	}
	
	/**
	 * @Title: listRolePermissions
	 * @description 角色已拥有的权限
	 * @param @param shopID
	 * @param @param model
	 * @param @param session
	 * @param @param role
	 * @return Map<String,Object>    
	 * @author dujiawei
	 * @createDate 2019年7月8日
	 */
	@RequestMapping("/listRolePermissions")
	@ResponseBody
	public Map<String,Object> listRolePermissions(String shopID, Model model,HttpSession session,RolePermission rolePermission) {
		Map<String , Object> result = new HashMap<String , Object>();
		
	    String username = (String)session.getAttribute("username");
	  //如果未传店铺id，则是当前登陆用户修改角色；否则，是区域经理修改各自店铺的角色
  		BigInteger shopId;
  		if(shopID == null || shopID == ""){
  			shopId = (BigInteger)session.getAttribute("shopId");
  			//shopId = new BigInteger("1");
  			rolePermission.setShopId(shopId);
  		}else{
  			shopId =  new BigInteger(shopID);
  			rolePermission.setShopId(shopId);
  		}
  		rolePermission.setShopId(new BigInteger(""+shopId));
        if (username =="" || username == null) {
        	result.put("code", 0);
    		result.put("msg", "登陆超时");
            return result; // 抛异常登陆超时
        }
        // 获取当前店中角色拥有的权限信息
        List<RolePermission> permissionVolist = jurisdictionService.listRolePermissions(rolePermission);
		BigInteger[] arr = new BigInteger[permissionVolist.size()];
		for(int i=0;i<permissionVolist.size();i++){
			arr[i] = permissionVolist.get(i).getId();
		}
        
        result.put("code", 1);
		result.put("msg", "Success");
		result.put("data", arr);
		
		return result;
	}
	
	
		/**
		 * @Title: listAllPermissions
		 * @description 所有的权限列表
		 * @param @param shopID
		 * @param @param model
		 * @param @param session
		 * @return Map<String,Object>    
		 * @author dujiawei
		 * @createDate 2019年7月8日
		 */
		@RequestMapping("/listAllPermissions")
		@ResponseBody
		public Map<String,Object> listAllPermissions(String shopID, Model model,HttpSession session) {
			Map<String , Object> result = new HashMap<String , Object>();
			
		    String username = (String)session.getAttribute("username");
	        if (username =="" || username == null) {
	        	result.put("code", 0);
	    		result.put("msg", "登陆超时");
	            return result; // 抛异常登陆超时
	        }
	        // 获取当前店中角色拥有的权限信息
	        List<PermissionVo> allPermission = jurisdictionService.listAllPermissions();
			
	        result.put("code", 1);
			result.put("msg", "Success");
			result.put("data", allPermission);
			
			return result;
		}
	
	
}
