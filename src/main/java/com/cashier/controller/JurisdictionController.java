package com.cashier.controller;



import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.cashier.dao.UserOperationMapper;
import com.cashier.entity.Permission;
import com.cashier.entity.Role;
import com.cashier.entity.User;
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
    public Map<String , Object> updateOneRolePermission(Model model,Role role,String ids,HttpSession session){
        BigInteger shopId = (BigInteger)session.getAttribute("shopId");
        role.setShopId(shopId);
        int num = jurisdictionService.updateOneRolePermission(role,ids);
        
        // 添加一条操作记录
        User user = (User)session.getAttribute("user");
        UserOperationVo userOperationVo = new UserOperationVo();
        userOperationVo.setShopId(shopId);
        userOperationVo.setUserUname(user.getUsername());
        userOperationVo.setName(user.getName());
        userOperationVo.setOperatingContent("修改权限信息");
        userOperationMapper.saveUserOperation(userOperationVo);
        
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
	
}
