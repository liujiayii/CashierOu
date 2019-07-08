package com.cashier.dao;

import java.math.BigInteger;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.cashier.entity.UserOperation;
import com.cashier.entityVo.UserOperationVo;

/**
 *
 * @ClassName: UserOperation

 * @description 员工日常操作Dao层接口类，查询、新增、修改、删除等方法
 *
 * @author 
 * @createDate 2019年2月11日
 */

public interface UserOperationMapper {

	/**
	 * 
	 * @Title: selectAllUserOperation
	 * @description 查询操作记录表
	 * @param userOperationVo
	 * @return List<UserOperationVo>    
	 * @author liujunkai
	 * @createDate 2019年7月8日
	 */
	public List<UserOperationVo> selectAllUserOperation(UserOperationVo userOperationVo);
	
	/**
	 * 
	 * @Title: findUserOperationCount
	 * @description 查询操作记录表的条数
	 * @param userOperationVo
	 * @return int    
	 * @author liujunkai
	 * @createDate 2019年7月8日
	 */
	public int findUserOperationCount(UserOperationVo userOperationVo);
	
	/**
	 * 
	 * @Title: saveUserOperation
	 * @description 增加一条操作记录表数据
	 * @param UserOperation 
	 * @return void    
	 * @author liujunkai
	 * @createDate 2019年7月8日
	 */
	public void saveUserOperation(UserOperation UserOperation);
	
	/**
	 * 
	 * @Title: deleteUserOperationById
	 * @description 删除一条操作记录表
	 * @param id 
	 * @return void    
	 * @author liujunkai
	 * @createDate 2019年7月8日
	 */
	public void deleteUserOperationById(BigInteger id);
	
	/**
	 * description   按时间查询操作记录
	 *
	 * @author pangchong
	 * @createDate 2019年2月11日 下午2:00
	 */
	public List<UserOperationVo> findUserOperationByTime(UserOperationVo userOperationVo);
	
	/**
	 * 按时间查询操作记录条数
	 * @author pangchong
	 * @createDate 2019年2月11日 下午2:00
	 */
	public UserOperationVo findUserOperationCountByTime(UserOperationVo userOperationVo);

	/**
	 * description   按操作人查询操作记录
	 *
	 * @author pangchong
	 * @createDate 2019年2月19日 下午2:00
	 */
	public List<UserOperationVo> findUserOperationByName(UserOperationVo userOperationVo);
	/**
	 * description   按操作内容查询操作记录
	 *
	 * @author pangchong
	 * @createDate 2019年2月19日 下午2:00
	 */
	public List<UserOperationVo> findUserOperationByContent(UserOperationVo userOperationVo);
	/**
	 * 按操作人查询操作记录条数
	 * @author pangchong
	 * @createDate 2019年2月11日 下午2:00
	 */
	public UserOperationVo findUserOperationCountByName(UserOperationVo userOperationVo);
	/**
	 * 按操作内容查询操作记录条数
	 * @author pangchong
	 * @createDate 2019年2月11日 下午2:00
	 */
	public UserOperationVo findUserOperationCountByContent(UserOperationVo userOperationVo);
}

