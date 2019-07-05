package com.cashier.dao;

import java.math.BigInteger;
import java.util.List;
import org.apache.ibatis.annotations.Param;
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
	 * description   查询所有操作记录
	 *
	 * @author pangchong
	 * @createDate 2019年2月11日 下午2:00
	 */
	public List<UserOperationVo> selectAllUserOperation(UserOperationVo userOperationVo);
	
	/**
	 * 查询操作记录条数
	 * @author pangchong
	 * @createDate 2019年2月11日 下午2:00
	 */
	public UserOperationVo findUserOperationCount(UserOperationVo userOperationVo);
	
	/**
	 * 添加操作记录
	 * @author pangchong
	 * @createDate 2019年2月11日 下午2:00
	 */
	public int saveUserOperation(UserOperationVo userOperationVo);
	
	 /**
     * @Title: deleteUserOperationById
     * @description 通过操作记录ID删除一条操作记录信息
     * @param UserOperationVo
     * @return     
     * @author pangchong
     * @createDate 2019年2月11日 下午2:00
     */
	public  int deleteUserOperationById(@Param("id") BigInteger id);
	
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

