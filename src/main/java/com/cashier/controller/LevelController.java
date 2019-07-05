package com.cashier.controller;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.cashier.entity.Level;
import com.cashier.service.LevelService;
import net.sf.json.JSONArray;


/**
 *
 * @ClassName: LevelController
 * @description 会员等级表的控制层
 *
 * @author dujiawei
 * @createDate 2019年6月20日
 */
@Controller
public class LevelController {
	
	@Resource
	private LevelService levelService;
	
	
	/**
	 * @Title: ZtlistLevel
	 * @description 跳转会员等级列表页面
	 * @param @param model
	 * @return String    
	 * @author dujiawei
	 * @createDate 2019年6月20日
	 */
	@RequestMapping("/ZtlistLevel")
	public String ZtlistLevel(Model model){
		
		return "/views/preferentialModuleManage/levelmemberManage/memberlevelManagement";
	}
	
	/**
	 * @Title: listLevel
	 * @description 会员等级详情列表
	 * @param @param model
	 * @param @param page
	 * @param @param limit
	 * @param @param level
	 * @param @param session
	 * @return Object    
	 * @author dujiawei
	 * @createDate 2019年6月20日
	 */
	@RequestMapping("/listLevel")
	@ResponseBody
	public Object listLevel(Model model, Integer page, Integer limit, Level level, HttpSession session) {
		level.setPage((page-1)*limit);
		level.setLimit(limit);
		List<Level>  list = levelService.listLevel(level);
		if(list.size() > 0) {
			for(int i = 0; i<list.size(); i++) {
				list.get(i).setCount(0);
			}
		};
		Level levelCount = levelService.countLevel();
		int count = 0;
		if(levelCount.getCount() != 0) {
			count = levelCount.getCount();
		};
		
		Map<String , Object> result = new HashMap<String , Object>();		
		result.put("code", 0);
		result.put("msg", "Success");
		JSONArray array = JSONArray.fromObject(list);
		result.put("data", array);
		result.put("count", count);
		
		return result;	
	}
	
	/**
	 * @Title: ZtsaveLevel
	 * @description 跳转新增会员等级页面
	 * @param @param model
	 * @return String    
	 * @author dujiawei
	 * @createDate 2019年6月20日
	 */
	@RequestMapping("/ZtsaveLevel")
	public String ZtsaveLevel(Model model){
		
		return "/views/preferentialModuleManage/levelmemberManage/addlevel";
	}
	
	/**
	 * @Title: saveLevel
	 * @description 新增会员等级
	 * @param @param level
	 * @param @param session
	 * @return Map<String,Object>    
	 * @author dujiawei
	 * @createDate 2019年6月20日
	 */
	@RequestMapping("/saveLevel")
	@ResponseBody
	public Map<String , Object> saveLevel(Level level, HttpSession session) {
		int num = levelService.saveLevel(level);
		Map<String , Object> map = new HashMap<String , Object>();
		if(num == 1){
			map.put("code", 0);
			map.put("msg", "Success");
		} else {
			map.put("code", 1);
			map.put("msg", "金额区间有冲突，新增失败");
		}
		
		return map;
	}
	
	/**
	 * @Title: ZtupdateLevel
	 * @description 跳转修改会员等级页面
	 * @param @param model
	 * @return String    
	 * @author dujiawei
	 * @createDate 2019年6月20日
	 */
	@RequestMapping("/ZtupdateLevel")
	public String ZtupdateLevel(Model model){
		
		return "/views/preferentialModuleManage/levelmemberManage/updatelevel";
	}
	
	/**
	 * @Title: updateLevel
	 * @description 修改会员等级
	 * @param @param level
	 * @param @param session
	 * @return Map<String,Object>    
	 * @author dujiawei
	 * @createDate 2019年6月20日
	 */
	@RequestMapping("/updateLevel")
	@ResponseBody
	public Map<String , Object> updateLevel(Level level, HttpSession session) {
		int num = levelService.updateLevel(level);
		Map<String , Object> map = new HashMap<String , Object>();
		if(num == 1){
			map.put("code", 0);
			map.put("msg", "Success");
		}else if(num == -1) {
			map.put("code", -1);
			map.put("msg", "该等级已存在！");
		} else {
			map.put("code", 1);
			map.put("msg", "修改失败了");
		}
		
		return map;
	}
	
	/**
	 * @Title: removeLevel
	 * @description 删除会员等级
	 * @param @param id
	 * @param @param session
	 * @return Map<String,Object>    
	 * @author dujiawei
	 * @createDate 2019年6月20日
	 */
	@RequestMapping("/removeLevel")
	@ResponseBody
	public Map<String , Object> removeLevel(BigInteger id,HttpSession session) {		
		int num = levelService.removeLevel(id);
		Map<String , Object> map = new HashMap<String , Object>();
		if(num == 1){
			map.put("code", 0);
			map.put("msg", "Success");
		} else {
			map.put("code", 1);
			map.put("msg", "删除失败了");
		}
		
		return map;
	}

}
