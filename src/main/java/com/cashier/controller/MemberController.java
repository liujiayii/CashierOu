package com.cashier.controller;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.cashier.entity.Member;
import com.cashier.entityVo.MemberVo;
import com.cashier.service.MemberService;

/**
 *
 * @ClassName: MemberController
 * @description 会员表的Controller层
 *
 * @author dujiawei
 * @createDate 2019年6月18日
 */
@Controller
public class MemberController {
	
	@Resource
	private MemberService memberService;
	
	/**
	 * @Title: ZtmembersManagement
	 * @description 跳转会员列表页面
	 * @param @param model
	 * @return String    
	 * @author dujiawei
	 * @createDate 2019年6月18日
	 */
	@RequestMapping("/ZtmembersManagement")
	public String ZtmembersManagement(Model model){
		
		return "/views/entireManage/memberManage/membersManagement";
	}
	
	/**
	 * @Title: listMemberVo
	 * @description 分页显示会员列表
	 * @param @param model
	 * @param @param page
	 * @param @param limit
	 * @param @param memberVo
	 * @param @param session
	 * @return Map<String,Object>    
	 * @author dujiawei
	 * @createDate 2019年6月18日
	 */
	@RequestMapping("/listMemberVo")
	@ResponseBody
	public Map<String,Object> listMemberVo(Model model, Integer page, Integer limit, MemberVo memberVo, HttpSession session) {
		 
		//BigInteger branchId = (BigInteger)session.getAttribute("shopId");
		memberVo.setPage((page-1)*limit);
		memberVo.setLimit(limit);
		Map<String , Object> result = new HashMap<String , Object>();
		List<MemberVo> list = memberService.listMemberVo(memberVo);  //会员列表
		
		if(list.size() > 0) {
			for(int i = 0; i<list.size(); i++) {
				list.get(i).setCount(0);
			}
		};
		MemberVo mVo = memberService.countMember();//会员数量
		int count = 0;
		if(mVo.getCount() != 0) {
			count = mVo.getCount();
		};
		result.put("code", 1);
		result.put("msg", "Success");
		result.put("data", list);
		result.put("count", count);
		
		return result;	
	}
	
	/**
	 * @Title: getMemberById
	 * @description 通过会员id查询会员信息
	 * @param @param id
	 * @return Object    
	 * @author dujiawei
	 * @createDate 2019年6月18日
	 */
	@RequestMapping(value="/getMemberById")
	@ResponseBody
	public Object getMemberById(@Param("id") BigInteger id) {
		Member member = new Member();
		member = memberService.getMemberById(id);
		
		Map<String , Object> result = new HashMap<String , Object>();		
		result.put("code", 0);
		result.put("msg", "Success");
		//JSONArray array = (JSONArray) JSONArray.toJSON(member);
		result.put("data", member);
		
		return result;	
	}
	
	
	/**
	 * @Title: ZtsaveMember
	 * @description 跳转到新增会员界面
	 * @param @param model
	 * @return String    
	 * @author dujiawei
	 * @createDate 2019年6月18日
	 */
	@RequestMapping("/ZtsaveMember")
	public String ZtsaveMember(Model model){
		
		return "/views/entireManage/memberManage/addMember";
	}
	
	/**
	 * @Title: saveMember
	 * @description 新增会员
	 * @param @param memberVo
	 * @param @param session
	 * @param @param model
	 * @return Map<String,Object>    
	 * @author dujiawei
	 * @createDate 2019年6月18日
	 */
	@RequestMapping("/saveMember")
	@ResponseBody
	public Map<String, Object> saveMember(MemberVo memberVo, HttpSession session ,Model model) {
		Map<String , Object> map = new HashMap<String , Object>();
		int num = memberService.saveMember(memberVo);
		if(num == 1){
			map.put("code", 1);
			map.put("msg", "Success");
			
		} else if(num == -1) {
			map.put("code", 0);
			map.put("msg", "该会员已存在！");
		} else{
			map.put("code", 0);
			map.put("msg", "新增会员失败！");
		}
		return map;
	}
		
	
	/**
	 * @Title: ZtupdateMember
	 * @description 跳转修改会员信息页面
	 * @param @param model
	 * @param @param member
	 * @return String    
	 * @author dujiawei
	 * @createDate 2019年7月3日
	 */
	@RequiresPermissions("/updateMember")
	@RequestMapping("/ZtupdateMember")
	public String ZtupdateMember(Model model, Member member){
		//回显的数据
		Member m = memberService.getMemberById(member.getId());
		model.addAttribute("member",m);
		
		return "/views/entireManage/memberManage/updateMember";
	}
	
	/**
	 * @Title: updateMember
	 * @description 修改会员信息
	 * @param @param member
	 * @param @param memberVo
	 * @return Map<String,Object>    
	 * @author dujiawei
	 * @createDate 2019年6月18日
	 */
	@RequestMapping("/updateMember")
	@ResponseBody
	public Map<String , Object> updateMember(Member member) {
		Map<String , Object> map = new HashMap<String , Object>();
        int num = memberService.updateMember(member);
		if(num == 1){
			map.put("code", 1);
			map.put("msg", "Success");
		}else if(num == -1){
			map.put("code", 0);
			map.put("msg", "修改失败，手机号已存在！");
		}else {
			map.put("code", 0);
			map.put("msg", "修改失败了");
		}
		
		return map;
	}
	
	/**
	 * @Title: updateMemberState
	 * @description 修改会员状态（挂失）
	 * @param @param member
	 * @return Map<String,Object>    
	 * @author dujiawei
	 * @createDate 2019年6月18日
	 */
	@RequestMapping("/updateMemberState")
	@ResponseBody
	public Map<String , Object> updateMemberState(Member member) {
		int num = memberService.updateMemberState(member);
		
		Map<String , Object> map = new HashMap<String , Object>();
		if(num == 1){
			map.put("code", 1);
			map.put("msg", "Success");
		} else {
			map.put("code", 0);
			map.put("msg", "修改状态失败了");
		}
		
		return map;
	}
	
	/**
	 * @Title: removeMember
	 * @description 删除会员（软删除）
	 * @param @param member
	 * @return Map<String,Object>    
	 * @author dujiawei
	 * @createDate 2019年7月3日
	 */
	@RequestMapping("/removeMember")
	@ResponseBody
	public Map<String , Object> removeMember(Member member) {
		int num = memberService.removeMember(member);
		
		Map<String , Object> map = new HashMap<String , Object>();
		if(num == 1){
			map.put("code", 1);
			map.put("msg", "Success");
		} else {
			map.put("code", 0);
			map.put("msg", "删除会员失败了");
		}
		
		return map;
	}

	/**
	 * @Title: listMemberByOption
	 * @description 条件查询会员
	 * @param @param model
	 * @param @param page
	 * @param @param limit
	 * @param @param memberVo
	 * @param @param session
	 * @return Map<String,Object>    
	 * @author dujiawei
	 * @createDate 2019年6月18日
	 */
	@RequestMapping("/listMemberByOption")
	@ResponseBody
	public Map<String,Object> listMemberByOption(Model model, Integer page, Integer limit, MemberVo memberVo, HttpSession session) {
		//BigInteger branchId = (BigInteger)session.getAttribute("shopId");
		memberVo.setPage((page-1)*limit);
		memberVo.setLimit(limit);
		List<MemberVo> list;
		Map<String , Object> result = new HashMap<String , Object>();
		list = memberService.listMemberByOption(memberVo);
		//会员列表
		if(list.size() > 0) {
			for(int i = 0; i<list.size(); i++) {
				list.get(i).setCount(0);
			}
		};
		MemberVo mVo = memberService.countMemberByOption(memberVo);//会员数量
		int count = 0;
		if(mVo.getCount() != 0) {
			count = mVo.getCount();
		};
		
		result.put("code", 1);
		result.put("msg", "Success");
		result.put("data", list);
		result.put("count", count);
		
		return result;	
	}
	
	/**
	 * @Title: updateMemberMoneyAndLevel
	 * @description 修改会员累计消费金额和等级
	 * @param @param member
	 * @return Map<String,Object>    
	 * @author dujiawei
	 * @createDate 2019年6月21日
	 */
	@RequestMapping("/updateMemberMoneyAndLevel")
	@ResponseBody
	public Map<String , Object> updateMemberMoneyAndLevel(Member member) {
		
		Map<String , Object> map = new HashMap<String , Object>();
        int num = memberService.updateMemberMoneyAndLevel(member);
		if(num == 1){
			map.put("code", 1);
			map.put("msg", "Success");
		}else {
			map.put("code", 0);
			map.put("msg", "修改失败了");
		}
		
		return map;
	}
	
}
