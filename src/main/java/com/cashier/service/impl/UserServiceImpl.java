package com.cashier.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.cashier.dao.UserMapper;
import com.cashier.entity.User;
import com.cashier.entity.UserRoleRelationship;
import com.cashier.entityVo.PermissionVo;
import com.cashier.entityVo.UserShopVo;
import com.cashier.entityVo.UserVo;
import com.cashier.entityVo.UserVo2;
import com.cashier.service.UserService;
import com.cashier.util.MD5Util;

/**
 *
 * @ClassName: UserServiceImpl
 *
 * @description 用户表的Service实现层
 *
 * @author dujiawei
 * @createDate 2018年11月8日
 */
@Service
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserMapper userMapper;
	
	/**
	 * @Description 查询本店所有用户信息
	 * @param userVo
	 * @return List<UserVo>
	 * @author dujiawei
	 * @createDate 2018年12月3日
	 */
	@Override
	public List<UserVo> listUser(UserVo userVo) {
		List<UserVo> userList = new ArrayList<UserVo>();
		try {
			userList = userMapper.listUser(userVo);
			if (userList.size() > 0) {
				//System.out.println("已查询所有用户：" + userList);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return userList;
	}

	/**
	 * @Description 查询本店所有用户信息
	 * @param userVo
	 * @return List<UserVo>
	 * @author dujiawei
	 * @createDate 2018年12月3日
	 */
	@Override
	public List<UserVo> listUserVo(UserVo userVo) {
		List<UserVo> userList = new ArrayList<UserVo>();
		try {
			userList = userMapper.listUserVo(userVo);
			if (userList.size() > 0) {
				//System.out.println("已查询所有用户：" + userList);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return userList;
	}
	
	/**
	 * @Description 查询本店所有用户的条数
	 * @param userVo
	 * @return UserVo
	 * @author dujiawei
	 * @createDate 2018年12月3日
	 */
	@Override
	public UserVo countUser(UserVo userVo) {
		userVo = userMapper.countUser(userVo);
		
		return userVo;
	}
	
	/**
	 * @Description 通过员工名查询本店员工信息
	 * @param userVo
	 * @return List<UserVo>
	 * @author dujiawei
	 * @createDate 2018年12月5日
	 */
	@Override
	public List<UserVo> listByName(UserVo userVo) {
		List<UserVo> userList = new ArrayList<UserVo>();
		try {
			userList = userMapper.listByName(userVo);
			if (userList.size() > 0) {
				//System.out.println("通过员工名已查询到用户：" + userList);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return userList;
	}

	/**
	 * @Description 通过员工姓名查询本店用户的条数
	 * @param userVo
	 * @return UserVo
	 * @author dujiawei
	 * @createDate 2018年12月5日
	 */
	@Override
	public UserVo countByName(UserVo userVo) {
		userVo = userMapper.countByName(userVo);
		
		return userVo;
	}

	/**
	 * @Description 新增用户
	 * @param userVo
	 * @return int
	 * @author dujiawei
	 * @createDate 2018年12月3日
	 */
	@Override
	public int saveUser(UserVo userVo) {
		try {
			UserVo userVo2 = userMapper.selectUserByUsername(userVo);
			if (userVo2 != null) {
                return -1;
            }
			userMapper.saveUser(userVo);
			userMapper.saveUserAndRoleRelation(userVo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 1;
	}

	/**
	 * @Description 修改用户信息
	 * @param user
	 * @return int
	 * @author dujiawei
	 * @createDate 2018年11月8日
	 */
	@Override
	public int updateUser(UserVo userVo) {
		
		try {
		    User user = userMapper.getOneUserById(userVo);
		    String password = userVo.getPassword();
		    if (!user.getPassword().equals(password) && password !="") {
		        userVo.setPassword(MD5Util.md5Encode(userVo.getPassword()));
            }
			userMapper.updateUser(userVo);
			/** 修改员工的同时，修改对应的员工角色关系表的数据 */
			UserRoleRelationship urrs = new UserRoleRelationship();
			urrs.setUserId(userVo.getId());
			urrs.setRoleId(userVo.getRoleId());
			userMapper.updateUserRoleRelationship(urrs);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}
	
	/**
	 * @Description 删除用户
	 * @param id
	 * @return int
	 * @author dujiawei
	 * @createDate 2018年11月8日
	 */
	@Override
	public int removeUser(BigInteger id) {
		try {
			userMapper.removeUser(id);
			userMapper.removeUserRoleRelation(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 1;
	}

	/**
	 * @Description (下拉单用)查询本店所有员工id和员工姓名
	 * @param userVo
	 * @return List<UserVo>
	 * @author dujiawei
	 * @createDate 2019年1月10日
	 */
	@Override
	public List<UserVo> listUserName(UserVo userVo) {
		List<UserVo> userList = new ArrayList<UserVo>();
		try {
			userList = userMapper.listUserName(userVo);
			if (userList.size() > 0) {
				//System.out.println("已查询所有用户id和姓名：" + userList);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return userList;
	}

	/**@description  通过店铺ID获取该店铺的超级管理员信息
     * @param user
     * @return user
     * @author zhou jiaxin
     * @createDate 2019-01-21
     */
    @Override
    public UserVo2 getSuperUserByShopId(User user) {
        return userMapper.getSuperUserByShopId(user);
    }

    /**@description 根据当前总店管理员姓名查询出她的权限范围
     * @param username
     * @author zhou jiaxin
     * @createDate 2019-01-24
     */
    @Override
    public List<PermissionVo> listPermissionByCurrentUser(String username) {
        User user = new User();
        user.setUsername(username);
        return userMapper.listPermissionByCurrentUser(user);
    }
    

	/**@description 修改一条‘员工角色关系’数据
     * @param urrs
     * @author dujiawei
     * @createDate 2019-01-24
     */
	@Override
	public int updateUserRoleRelationship(UserRoleRelationship urrs) {
		int num = 0;
		try {
			num = userMapper.updateUserRoleRelationship(urrs);
			
			if (num != 0) {
				//System.out.println("修改一条‘员工角色关系’数据成功！");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return num;
	}

	/**
	 * @Title: getUserByName
	 * @description 登录后通过用户名查询用户信息
	 * @param @param userShopVo
	 * @return UserShopVo    
	 * @author dujiawei
	 * @createDate 2019年7月5日
	 */
	@Override
	public UserShopVo getUserByName(UserShopVo userShopVo) {
		// TODO Auto-generated method stub
		return userMapper.getUserByName(userShopVo);
	}
	
	/**
	 * @Title: listAgentShop
	 * @description 区域经理管理的店铺（下拉单）
	 * @param @param userShopVo
	 * @param @return   
	 * @return List<UserShopVo>    
	 * @author dujiawei
	 * @createDate 2019年7月5日
	 */
	@Override
	public List<UserShopVo> listAgentShop(UserShopVo userShopVo) {
		// TODO Auto-generated method stub
		return userMapper.listAgentShop(userShopVo);
	}

	/**
	 * @Title: listShopByProvinceAgent
	 * @description 省级经理管理的店铺
	 * @param @param userShopVo
	 * @return List<UserShopVo>    
	 * @author dujiawei
	 * @createDate 2019年7月5日
	 */
	@Override
	public List<UserShopVo> listShopByProvinceAgent(UserShopVo userShopVo) {
		// TODO Auto-generated method stub
		return userMapper.listShopByProvinceAgent(userShopVo);
	}

	/**
	 * @Title: listShopByCityAgent
	 * @description 市级经理管理的店铺
	 * @param @param userShopVo
	 * @return List<UserShopVo>    
	 * @author dujiawei
	 * @createDate 2019年7月5日
	 */
	@Override
	public List<UserShopVo> listShopByCityAgent(UserShopVo userShopVo) {
		// TODO Auto-generated method stub
		return userMapper.listShopByCityAgent(userShopVo);
	}

	/**
	 * @Title: listShopByAreaAgent
	 * @description 区级经理管理的店铺
	 * @param @param userShopVo
	 * @return List<UserShopVo>    
	 * @author dujiawei
	 * @createDate 2019年7月5日
	 */
	@Override
	public List<UserShopVo> listShopByAreaAgent(UserShopVo userShopVo) {
		// TODO Auto-generated method stub
		return userMapper.listShopByAreaAgent(userShopVo);
	}

}
