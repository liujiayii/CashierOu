/**
 * @Title: UserOperationServiceImpl.java
 * @Package com.cashier.service.impl
 * @Description: TODO(用一句话描述该文件做什么)
 * @author Administrator
 * @date 2019年7月8日
 */
package com.cashier.service.impl;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cashier.dao.UserOperationMapper;
import com.cashier.entityVo.UserOperationVo;
import com.cashier.service.UserOperationService;

/**
 * @ClassName: UserOperationServiceImpl
 * @description 用一句话描述这个类的作用
 * @author liujunkai
 * @createDate 2019年7月8日
 */
@Service
public class UserOperationServiceImpl implements UserOperationService{
    
    @Autowired
    private UserOperationMapper userOperationMapper;
    
    /**
     * 
     * @Title: selectAllUserOperation
     * @description 查询操作记录表
     * @param userOperationVo
     * @return List<UserOperationVo>    
     * @author liujunkai
     * @createDate 2019年7月8日
     */
    @Override
    public List<UserOperationVo> selectAllUserOperation(UserOperationVo userOperationVo) {

        return userOperationMapper.selectAllUserOperation(userOperationVo);
    }

    /**
     * 
     * @Title: findUserOperationCount
     * @description 查询操作记录表的条数
     * @param userOperationVo
     * @return int    
     * @author liujunkai
     * @createDate 2019年7月8日
     */
    @Override
    public int findUserOperationCount(UserOperationVo userOperationVo) {

        return userOperationMapper.findUserOperationCount(userOperationVo);
    }

    /**
     * 
     * @Title: deleteUserOperationById
     * @description 删除一条操作记录表
     * @param id 
     * @return void    
     * @author liujunkai
     * @createDate 2019年7月8日
     */
    @Override
    public void deleteUserOperationById(BigInteger id) {
        userOperationMapper.deleteUserOperationById(id);
    }

}
