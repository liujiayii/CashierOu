package com.cashier.dao;

import java.math.BigInteger;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.cashier.entity.Birthday;
import com.cashier.entityVo.BirthdayVo;


/**
 *
 * @ClassName: BirthdayMapper
 * @description 生日表的方法
 *
 * @author dujiawei
 * @createDate 2019年6月20日
 */
public interface BirthdayMapper {

	/**
	 * @Title: listBirthday
	 * @description 当天过生日的列表
	 * @param @param birthdayVo
	 * @return List<BirthdayVo>    
	 * @author dujiawei
	 * @createDate 2019年6月20日
	 */
	public List<BirthdayVo> listBirthday(BirthdayVo birthdayVo);
	
	/**
	 * @Title: countBirthday
	 * @description 当天过生日人数
	 * @param @param birthday
	 * @return BirthdayVo    
	 * @author dujiawei
	 * @createDate 2019年6月20日
	 */
	public BirthdayVo countBirthday(BirthdayVo birthdayVo);
	
	/**
	 * @Title: saveBirthday
	 * @description 新增一条生日
	 * @param @param birthday
	 * @return int    
	 * @author dujiawei
	 * @createDate 2019年6月20日
	 */
	public int saveBirthday(Birthday birthday);
	
	/**
	 * @Title: removeBirthday
	 * @description 删除一条生日
	 * @param @param id
	 * @return int    
	 * @author dujiawei
	 * @createDate 2019年6月20日
	 */
	public int removeBirthday(@Param("id") BigInteger id);
	
	/**
	 * @Title: deleteAllBirthday
	 * @description 删除生日表的所有数据
	 * @return int    
	 * @author dujiawei
	 * @createDate 2019年7月19日
	 */
	public int deleteAllBirthday();

}
