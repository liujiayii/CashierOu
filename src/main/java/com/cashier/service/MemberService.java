package com.cashier.service;

import java.math.BigInteger;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.cashier.entity.Member;
import com.cashier.entityVo.MemberVo;

/**
 *
 * @ClassName: MemberService
 * @description 会员表的方法
 *
 * @author dujiawei
 * @createDate 2019年6月18日
 */
public interface MemberService {
	
	/**
	 * @Title: listAllMember
	 * @description 所有的会员（无分页，增加会员查重使用）
	 * @param    
	 * @return List<Member>    
	 * @author dujiawei
	 * @createDate 2019年6月18日
	 */
	public List<Member> listAllMember();
	
	/**
	 * @Title: listMemberVo
	 * @description 分页查询所有的会员
	 * @param @param memberVo
	 * @return List<MemberVo>    
	 * @author dujiawei
	 * @createDate 2019年6月18日
	 */
	public List<MemberVo> listMemberVo(MemberVo memberVo);
	
	/**
	 * @Title: countMember
	 * @description 所有会员的数量
	 * @return MemberVo    
	 * @author dujiawei
	 * @createDate 2019年6月18日
	 */
	public MemberVo countMember();
	
	/**
	 * @Title: saveMember
	 * @description 新增一位会员
	 * @param @param memberVo
	 * @return int    
	 * @author dujiawei
	 * @createDate 2019年6月18日
	 */
	public int saveMember(MemberVo memberVo);
	
	/**
	 * @Title: updateMember
	 * @description 修改会员信息
	 * @param @param member
	 * @return int    
	 * @author dujiawei
	 * @createDate 2019年6月18日
	 */
	public int updateMember(Member member);
	
	/**
	 * @Title: listMemberForUpdate
	 * @description 查重用（修改会员信息查重使用，电话信息等不能重复）
	 * @param @param member
	 * @return List<Member>    
	 * @author dujiawei
	 * @createDate 2019年6月18日
	 */
	public List<Member> listMemberForUpdate(Member member);
	
	/**
	 * @Title: updateMemberState
	 * @description 修改会员状态（挂失）
	 * @param @param member
	 * @return int    
	 * @author dujiawei
	 * @createDate 2019年6月18日
	 */
	public int updateMemberState(Member member);
	
	/**
	 * @Title: removeMember
	 * @description 删除会员（软删除）
	 * @param @param member
	 * @return int    
	 * @author dujiawei
	 * @createDate 2019年7月3日
	 */
	public int removeMember(Member member);
	
	/**
	 * @Title: getMemberById
	 * @description 通过会员id查询会员信息
	 * @param @param id
	 * @return Member    
	 * @author dujiawei
	 * @createDate 2019年6月18日
	 */
	public Member getMemberById(@Param("id") BigInteger id);
	
	/**
	 * @Title: listMemberByOption
	 * @description 条件筛选会员
	 * @param @param memberVo
	 * @return List<MemberVo>    
	 * @author dujiawei
	 * @createDate 2019年6月18日
	 */
	public List<MemberVo> listMemberByOption(MemberVo memberVo);
	
	/**
	 * @Title: countMemberByOption
	 * @description 条件筛选会员的数量
	 * @param @param memberVo
	 * @return MemberVo    
	 * @author dujiawei
	 * @createDate 2019年6月18日
	 */
	public MemberVo countMemberByOption(MemberVo memberVo);
	
	/**
	 * @Title: listMemberByBirthday
	 * @description 根据生日查询会员
	 * @param @param memberVo
	 * @return List<MemberVo>    
	 * @author dujiawei
	 * @createDate 2019年6月20日
	 */
	public List<MemberVo> listMemberByBirthday(MemberVo memberVo);
	
	/**
	 * @Title: updateMemberMoneyAndLevel
	 * @description 修改会员累计消费金额和等级
	 * @param @param member
	 * @return int    
	 * @author dujiawei
	 * @createDate 2019年6月21日
	 */
	public int updateMemberMoneyAndLevel(Member member);
	
	/**
	 * @Title: listMemberByPhone
	 * @description APP接口----通过电话模糊查询会员信息
	 * @param @param memberVo
	 * @return List<MemberVo>    
	 * @author dujiawei
	 * @createDate 2019年7月8日
	 */
	public List<MemberVo> listMemberByPhone(MemberVo memberVo);
	
	/**
	 * @Title: countMemberByPhone
	 * @description APP接口----通过电话模糊查询会员的数量
	 * @param @param memberVo
	 * @return MemberVo    
	 * @author dujiawei
	 * @createDate 2019年7月8日
	 */
	public MemberVo countMemberByPhone(MemberVo memberVo);

}
