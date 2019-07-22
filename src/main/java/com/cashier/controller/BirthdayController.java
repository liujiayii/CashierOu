package com.cashier.controller;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.cashier.entity.Birthday;
import com.cashier.entityVo.BirthdayVo;
import com.cashier.service.BirthdayService;
import net.sf.json.JSONArray;



@Controller
@Lazy(false)
public class BirthdayController {
	
	@Resource
	private BirthdayService birthdayService;
	
	/**
	 * @Title: saveBirthday
	 * @description 定时查询当天过生日的会员并存储
	 * @return void    
	 * @author dujiawei
	 * @createDate 2019年6月20日
	 */
	//@Scheduled(cron = "0 20 15 * * ?")
	@Scheduled(cron = "0 00 1 * * ?")  //每天凌晨一点执行方法
	public void saveBirthday() {
		Birthday birthday = new Birthday();
		birthdayService.deleteAllBirthday(); //先清空整个生日表数据
		birthdayService.saveBirthday(birthday); //存储当天过生日的会员信息
	}
	
	/**
	 * @Title: ZtlistBirthday
	 * @description 跳转到生日列表页面
	 * @param @param model
	 * @return String    
	 * @author dujiawei
	 * @createDate 2019年6月20日
	 */
	@RequestMapping("/ZtlistBirthday")
	public String ZtlistBirthday(Model model){
		
		return "/views/preferentialModuleManage/levelmemberManage/memberlevelManagement";
	}
	
	/**
	 * @Title: listBirthday
	 * @description 当天过生日的列表
	 * @param @param model
	 * @param @param page
	 * @param @param limit
	 * @param @param birthdayVo
	 * @param @param session
	 * @return Object    
	 * @author dujiawei
	 * @createDate 2019年6月20日
	 */
	@RequestMapping("/listBirthday")
	@ResponseBody
	public Object listBirthday(Model model, Integer page, Integer limit, BirthdayVo birthdayVo, HttpSession session) {
		birthdayVo.setPage((page-1)*limit);
		birthdayVo.setLimit(limit);
		List<BirthdayVo>  list = birthdayService.listBirthday(birthdayVo);  //当天过生日的列表
		if(list.size() > 0) {
			for(int i = 0; i<list.size(); i++) {
				list.get(i).setCount(0);
			}
		};
		BirthdayVo wVo = birthdayService.countBirthday(birthdayVo);//当天过生日的人数
		int count = 0;
		if(wVo.getCount() != 0) {
			count = wVo.getCount();
		};
		
		Map<String , Object> result = new HashMap<String , Object>();		
		result.put("code", 1);
		result.put("msg", "Success");
		JSONArray array = JSONArray.fromObject(list);
		result.put("data", array);
		result.put("count", count);
		
		return result;	
	}
	
	/**
	 * @Title: removeBirthday
	 * @description 删除一条生日
	 * @param @param id
	 * @param @param session
	 * @return Map<String,Object>    
	 * @author dujiawei
	 * @createDate 2019年6月20日
	 */
	@RequestMapping("/removeBirthday")
	@ResponseBody
	public Map<String , Object> removeBirthday(BigInteger id,HttpSession session) {		
		int num = birthdayService.removeBirthday(id);
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

}
