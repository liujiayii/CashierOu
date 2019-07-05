package com.cashier.service.impl;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cashier.dao.LevelMapper;
import com.cashier.dao.MemberMapper;
import com.cashier.entity.Level;
import com.cashier.entity.Member;
import com.cashier.entityVo.MemberVo;
import com.cashier.service.MemberService;

/**
 *
 * @ClassName: MemberServiceImpl
 * @description 会员表的Service实现层
 *
 * @author dujiawei
 * @createDate 2019年6月18日
 */
@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberMapper memberMapper;
	@Resource
	private LevelMapper levelMapper;

	/**
	 * @Title: listAllMember
	 * @description 所有的会员（无分页，增加会员查重使用）
	 * @param    
	 * @return List<Member>    
	 * @author dujiawei
	 * @createDate 2019年6月18日
	 */
	@Override
	public List<Member> listAllMember() {
		// TODO Auto-generated method stub
		return memberMapper.listAllMember();
	}

	/**
	 * @Title: listMemberVo
	 * @description 分页查询所有的会员
	 * @param @param memberVo
	 * @return List<MemberVo>    
	 * @author dujiawei
	 * @createDate 2019年6月18日
	 */
	@Override
	public List<MemberVo> listMemberVo(MemberVo memberVo) {
		// TODO Auto-generated method stub
		return memberMapper.listMemberVo(memberVo);
	}

	/**
	 * @Title: countMember
	 * @description 所有会员的数量
	 * @return MemberVo    
	 * @author dujiawei
	 * @createDate 2019年6月18日
	 */
	@Override
	public MemberVo countMember() {
		// TODO Auto-generated method stub
		return memberMapper.countMember();
	}

	/**
	 * @Title: saveMember
	 * @description 新增一位会员
	 * @param @param memberVo
	 * @return int    
	 * @author dujiawei
	 * @createDate 2019年6月18日
	 */
	@Override
	public int saveMember(MemberVo memberVo) {
		// TODO Auto-generated method stub
		int num = 0;
		try {
			// 查询出所有会员信息-memberList
			List<Member> memberList = memberMapper.listAllMember();
			/*生成随机数会员卡号*/
			//随机生成后两位数字
			Random random = new Random();
			int ends = random.nextInt(99);
			String twoEnds = String.format("%02d",ends);//后两位随机数，如果不足两位，前面补0
			
			//获取当前日期字符串
			Date date = new Date();
			SimpleDateFormat dateFormat= new SimpleDateFormat("yyyyMMddHHmmss");
			String timeNow = dateFormat.format(date).toString(); //当前日期字符串 
			
			//最终的会员卡号
			String cardNum = timeNow.concat(twoEnds);
			memberVo.setNumber(cardNum);//存值
			
			if (memberList.size() > 0) {
				for (int i = 0; i < memberList.size(); i++) {
					if (memberList.get(i).getNumber().equals(memberVo.getNumber()) || memberList.get(i).getPhone().equals(memberVo.getPhone())) {
						//System.out.println("该会员已存在！");
						
						return -1;
					}
				}
			}
			Level level = new Level();
			BigInteger ldLevelId = levelMapper.getMinLevelId(level).getId();  //查询最低级的会员等级id
			//memberVo.setPassword(MD5Util.md5Encode(memberVo.getPassword()));
			memberVo.setLdLevelId(ldLevelId);  //存会员等级id值
			/* 解决中文名字乱码问题 */
			String name = memberVo.getName();
			name = new String(name.getBytes("ISO-8859-1"), "UTF-8");
			memberVo.setName(name);
		
			num = memberMapper.saveMember(memberVo); //执行新增方法
			if (num != 0) {
				//System.out.println("新增用户成功！");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return num;
	}

	/**
	 * @Title: updateMember
	 * @description 修改会员信息
	 * @param @param member
	 * @return int    
	 * @author dujiawei
	 * @createDate 2019年6月18日
	 */
	@Override
	public int updateMember(Member member) {
		// TODO Auto-generated method stub
		int num = 0;
		try {
			//String password = member.getPassword();
			//BigInteger id = member.getId();
			//String memberPass = memberMapper.getMemberById(id).getPassword(); //查询原密码
			List<Member> memberList = memberMapper.listMemberForUpdate(member);
			//判断修改的信息是否已存在
			if (memberList.size() > 0) {
				for (int i = 0; i < memberList.size(); i++) {
					if (memberList.get(i).getPhone().equals(member.getPhone())) {
						
						return -1;
					}
				}
			}
			//判断密码是否有变化，若有，加密
			/*if(password!=null && !password.equals("")){
				if (!memberPass.equals(password) && !password.equals("")) {
					member.setPassword(MD5Util.md5Encode(password));
	            }
			}*/
			/* 解决中文名字乱码问题 */
			String name = member.getName();
			name = new String(name.getBytes("ISO-8859-1"), "UTF-8");
			member.setName(name);
			//执行修改会员信息方法
			num = memberMapper.updateMember(member);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return num;
	}

	/**
	 * @Title: listMemberForUpdate
	 * @description 查重用（修改会员信息查重使用，电话信息等不能重复）
	 * @param @param member
	 * @return List<Member>    
	 * @author dujiawei
	 * @createDate 2019年6月18日
	 */
	@Override
	public List<Member> listMemberForUpdate(Member member) {
		// TODO Auto-generated method stub
		return memberMapper.listMemberForUpdate(member);
	}

	/**
	 * @Title: updateMemberState
	 * @description 修改会员状态（挂失）
	 * @param @param member
	 * @return int    
	 * @author dujiawei
	 * @createDate 2019年6月18日
	 */
	@Override
	public int updateMemberState(Member member) {
		// TODO Auto-generated method stub
		return memberMapper.updateMemberState(member);
	}
	
	/**
	 * @Title: removeMember
	 * @description 删除会员（软删除）
	 * @param @param member
	 * @return int    
	 * @author dujiawei
	 * @createDate 2019年7月3日
	 */
	@Override
	public int removeMember(Member member) {
		// TODO Auto-generated method stub
		return memberMapper.removeMember(member);
	}

	/**
	 * @Title: getMemberById
	 * @description 通过会员id查询会员信息
	 * @param @param id
	 * @return Member    
	 * @author dujiawei
	 * @createDate 2019年6月18日
	 */
	@Override
	public Member getMemberById(BigInteger id) {
		// TODO Auto-generated method stub
		return memberMapper.getMemberById(id);
	}

	/**
	 * @Title: listMemberByOption
	 * @description 条件筛选会员
	 * @param @param memberVo
	 * @return List<MemberVo>    
	 * @author dujiawei
	 * @createDate 2019年6月18日
	 */
	@Override
	public List<MemberVo> listMemberByOption(MemberVo memberVo) {
		// TODO Auto-generated method stub
		return memberMapper.listMemberByOption(memberVo);
	}

	/**
	 * @Title: countMemberByOption
	 * @description 条件筛选会员的数量
	 * @param @param memberVo
	 * @return MemberVo    
	 * @author dujiawei
	 * @createDate 2019年6月18日
	 */
	@Override
	public MemberVo countMemberByOption(MemberVo memberVo) {
		// TODO Auto-generated method stub
		return memberMapper.countMemberByOption(memberVo);
	}

	/**
	 * @Title: listMemberByBirthday
	 * @description 根据生日查询会员
	 * @param @param memberVo
	 * @return List<MemberVo>    
	 * @author dujiawei
	 * @createDate 2019年6月20日
	 */
	@Override
	public List<MemberVo> listMemberByBirthday(MemberVo memberVo) {
		// TODO Auto-generated method stub
		return memberMapper.listMemberByBirthday(memberVo);
	}

	/**
	 * @Title: updateMemberMoneyAndLevel
	 * @description 修改会员累计消费金额和等级
	 * @param @param member
	 * @return int    
	 * @author dujiawei
	 * @createDate 2019年6月21日
	 */
	@Override
	public int updateMemberMoneyAndLevel(Member member) {
		// TODO Auto-generated method stub
		return memberMapper.updateMemberMoneyAndLevel(member);
	}

}
