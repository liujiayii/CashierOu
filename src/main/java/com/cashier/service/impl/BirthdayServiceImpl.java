package com.cashier.service.impl;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cashier.dao.BirthdayMapper;
import com.cashier.dao.MemberMapper;
import com.cashier.entity.Birthday;
import com.cashier.entityVo.BirthdayVo;
import com.cashier.entityVo.MemberVo;
import com.cashier.service.BirthdayService;


/**
 *
 * @ClassName: BirthdayServiceImpl
 * @description 生日表的Service实现层
 *
 * @author dujiawei
 * @createDate 2019年6月20日
 */
@Service
public class BirthdayServiceImpl implements BirthdayService {
	
	@Resource
	private BirthdayMapper birthdayMapper;
	@Autowired
	private MemberMapper memberMapper;

	/**
	 * @Title: listBirthday
	 * @description 当天过生日的列表
	 * @param @param birthdayVo
	 * @return List<BirthdayVo>    
	 * @author dujiawei
	 * @createDate 2019年6月20日
	 */
	@Override
	public List<BirthdayVo> listBirthday(BirthdayVo birthdayVo) {
		// TODO Auto-generated method stub
		//获取当前日期字符串
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
		String time = sdf.format(date).toString(); //当前日期字符串 
		birthdayVo.setTime(time);
		
		return birthdayMapper.listBirthday(birthdayVo);
	}

	/**
	 * @Title: countBirthday
	 * @description 当天过生日人数
	 * @param @param birthdayVo
	 * @return BirthdayVo    
	 * @author dujiawei
	 * @createDate 2019年6月20日
	 */
	@Override
	public BirthdayVo countBirthday(BirthdayVo birthdayVo) {
		// TODO Auto-generated method stub
		return birthdayMapper.countBirthday(birthdayVo);
	}

	/**
	 * @Title: saveBirthday
	 * @description 新增一条生日
	 * @param @param birthday
	 * @return int    
	 * @author dujiawei
	 * @createDate 2019年6月20日
	 */
	@Override
	public int saveBirthday(Birthday birthday) {
		// TODO Auto-generated method stub
		int num = 0;
		/** 01获取当前时间信息 */
		Date dNow = new Date();
		Date dBefore = new Date();
		Calendar calendar = Calendar.getInstance(); //得到日历
		
		calendar.setTime(dNow);//把当前时间赋给日历
		calendar.add(Calendar.MONTH, 0);//当月的时间    
		dBefore = calendar.getTime();   //得到当月的时间
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd"); //设置时间格式
		String month = sdf.format(dBefore);    //格式化
		
		MemberVo memberVo = new MemberVo();
		//memberVo.setBirthday(sdf.parse(month));
		memberVo.setDayTime(month);
		List<MemberVo>  memberList = memberMapper.listMemberByBirthday(memberVo);
		//System.out.println("启动定时任务"+memberList);
		//System.out.println("启动定时任务2"+month);
		
		if(memberList.size() > 0){
			for(int i =0 ; i < memberList.size() ; i++){
				MemberVo members = memberList.get(i);
				
				birthday.setMemberId(members.getId());
				try {
					birthday.setTime(sdf.parse(month));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				num = birthdayMapper.saveBirthday(birthday);
			}
		}
		return num;
	}

	/**
	 * @Title: removeBirthday
	 * @description 删除一条生日
	 * @param @param id
	 * @return int    
	 * @author dujiawei
	 * @createDate 2019年6月20日
	 */
	@Override
	public int removeBirthday(BigInteger id) {
		// TODO Auto-generated method stub
		return birthdayMapper.removeBirthday(id);
	}

}
