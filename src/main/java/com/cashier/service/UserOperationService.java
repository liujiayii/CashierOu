/**
 * @Title: UserOperationService.java
 * @Package com.cashier.service
 * @Description: TODO(用一句话描述该文件做什么)
 * @author Administrator
 * @date 2019年7月8日
 */
package com.cashier.service;

import java.math.BigInteger;
import java.util.List;

import com.cashier.entityVo.UserOperationVo;

/**
 * @ClassName: UserOperationService
 * @description 用一句话描述这个类的作用
 * @author liujunkai
 * @createDate 2019年7月8日
 */
public interface UserOperationService {

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
     * @Title: deleteUserOperationById
     * @description 删除一条操作记录表
     * @param id 
     * @return void    
     * @author liujunkai
     * @createDate 2019年7月8日
     */
    public void deleteUserOperationById(BigInteger id);
}
