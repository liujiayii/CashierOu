package com.cashier.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cashier.dao.JurisdictionMapper;
import com.cashier.dao.RoleMapper;
import com.cashier.dao.ShopMapper;
import com.cashier.dao.UserMapper;
import com.cashier.entity.Permission;
import com.cashier.entity.Role;
import com.cashier.entity.RolePermissionRelationship;
import com.cashier.entity.Shop;
import com.cashier.entity.User;
import com.cashier.entityDTO.ShopUserPermissionDTO;
import com.cashier.entityVo.PermissionVo;
import com.cashier.entityVo.ShopVo;
import com.cashier.entityVo.UserVo;
import com.cashier.service.ShopService;
import com.cashier.service.ex.DataNotExistsException;
import com.cashier.service.ex.ShopNameConflictException;
import com.cashier.service.ex.UserNameConflictException;
import com.cashier.util.MD5Util;
import com.shove.data.DataException;

/**
 *
 * @ClassName: ShopServiceImpl

 * @description 店铺Service实现层
 *
 * @author chenshuxian
 * @createDate 2019年7月6日
 */
@Service("ShopService")
@Transactional
public class ShopServiceImpl implements ShopService{

	@Resource
	private ShopMapper shopMapper;
	@Resource
    private RoleMapper roleMapper;
	@Resource
    private UserMapper userMapper;
	@Resource
	private JurisdictionMapper jurisdictionMapper;
	
	/**
     * @Title: getGeneralScopeById
     * @description 通过店铺id查询店铺省市区号和会员消费范围
     * @param  shopVo
     * @return ShopVo    
     * @author dujiawei
     * @createDate 2018年12月13日
     */
	@Override
	public ShopVo getGeneralScopeById(ShopVo shopVo) {
		ShopVo shop = new ShopVo();
		try {
			shop = shopMapper.getGeneralScopeById(shopVo);
			if (shop != null) {
				//System.out.println("已查询到店铺省市区号和消费范围：" + shop);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		return shop;
	}
	
	/**
     * @Title: listShop
     * @description 查询所有店铺信息（无分页，新增店铺用）
     * @param  shopVo
     * @return List<ShopVo>    
     * @author dujiawei
     * @createDate 2018年12月11日
     */
	@Override
	public List<ShopVo> listShop(ShopVo shopVo) {
		List<ShopVo> shopList = new ArrayList<ShopVo>();
		try {
			shopList = shopMapper.listShop(shopVo);
			if (shopList.size() > 0) {
				//System.out.println("已查询所有店铺：" + shopList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		return shopList;
	}
	
	/**
     * @Title: listAllShopVo
     * @description 查询所有店铺信息
     * @param  shopVo
     * @return List<ShopVo>    
     * @author dujiawei
     * @createDate 2018年12月5日
     */
	@Override
	@Transactional(readOnly=true)
	public List<ShopVo> listAllShopVo(ShopVo shopVo) {
		List<ShopVo> shopList = new ArrayList<ShopVo>();
		try {
			shopList = shopMapper.listAllShopVo(shopVo);
			if (shopList.size() > 0) {
				//System.out.println("已查询所有店铺：" + shopList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		return shopList;
	}
	/**
     * @Title: countShop
     * @description 所有店铺的数量
     * @param  shopVo
     * @return ShopVo    
     * @author dujiawei
     * @createDate 2018年12月5日
     */
	@Override
	public ShopVo countShop(ShopVo shopVo) {
		shopVo = shopMapper.countShop(shopVo);
		
		return shopVo;
	}
	/**
     * @Title: updateShop
     * @description 修改店铺信息--超级管理员信息--超级管理员权限信息
     * @param  shop
     * @return int  
     * @author dujiawei
     * @createDate 2018年12月5日
     */
	@Override
	public int updateShop(ShopVo shopVo, ShopUserPermissionDTO shopUserPermissionDTO,String ids) {
		int num = 0;
		try {
		    // 更新店铺权限
			num = shopMapper.updateShop(shopVo);
			// 更新超级管理员信息
			UserVo userVo = new UserVo();
			userVo.setId(shopUserPermissionDTO.getuId());
			User user = userMapper.getOneUserById(userVo);
            String password = shopUserPermissionDTO.getPassword();
            if (!(user.getPassword().equals(password)) && password !="") {
                shopUserPermissionDTO.setPassword(MD5Util.md5Encode(shopUserPermissionDTO.getPassword()));
            }
			userMapper.updateSupperUser(shopUserPermissionDTO);
			// 如果是总店管理员-不允许修改权限
			if(shopUserPermissionDTO.getType()==1){
			    return 1;
			}
			// 更新超级管理员权限
			List<Permission> permissionIDList = roleMapper.getPermissionIDListByShopId(shopVo);
			String[] permissionIds = ids.split(",");
			Role role = roleMapper.getRoleIdByShopId(shopVo);
			// 1先判断需要添加的内容
			
			RolePermissionRelationship rolePermissionRelationship = new RolePermissionRelationship();
			rolePermissionRelationship.setRoleId(role.getId());
			for (int i = 0; i < permissionIds.length; i++) {
			    int flag = 0;
                for (int j = 0; j < permissionIDList.size(); j++) {
                    if (new BigInteger(permissionIds[i]).compareTo(permissionIDList.get(j).getId())==0) {
                        flag = 1;
                        break;
                    }
                }
                if (flag == 0) {
                    rolePermissionRelationship.setPermissionId(new BigInteger(permissionIds[i]));
                    roleMapper.saveRolePermissionRelationship(rolePermissionRelationship);
                }
            }
			// 2判断需要删除的内容
			// 先判断当前角色是否是超级管理员
	        Role superRole = jurisdictionMapper.selectSuperRoleByRoleId(role);
	        if (superRole != null ) {
	            for (int j = 0; j < permissionIDList.size(); j++) {
	                int flag = 0;
	                for (int i = 0; i < permissionIds.length; i++) {
	                    if (new BigInteger(permissionIds[i]).compareTo(permissionIDList.get(j).getId())==0) {
	                        flag = 1;
	                        break;
	                    }
	                }
	                if (flag == 0) {
	                    rolePermissionRelationship.setPermissionId(permissionIDList.get(j).getId());
	                    // 用角色ID代替shopId传到后台
	                    rolePermissionRelationship.setRoleId(superRole.getShopId());
	                    //roleMapper.deleteRolePermissionRelationship(rolePermissionRelationship);
	                    // 删除分店超级管理员权限时需要把该分店的权限全部收回
	                    roleMapper.deleteAllRolePermissionRelationship(rolePermissionRelationship);
	                    
	                }
	            }
	        }else{
	            for (int j = 0; j < permissionIDList.size(); j++) {
	                int flag = 0;
	                for (int i = 0; i < permissionIds.length; i++) {
	                    if (new BigInteger(permissionIds[i]).compareTo(permissionIDList.get(j).getId())==0) {
	                        flag = 1;
	                        break;
	                    }
	                }
	                if (flag == 0) {
	                    rolePermissionRelationship.setPermissionId(permissionIDList.get(j).getId());
	                    roleMapper.deleteRolePermissionRelationship(rolePermissionRelationship);
	                }
	            }
	        }
            
		} catch (Exception e) {
			e.printStackTrace();
		}

		return num;
	}
	/**
     * @Title: insertShop
     * @description 添加店铺
     * @param  shopVo
     * @return int  
     * @author chenshuxian
     * @createDate 2019年7月4日
     */
	@Override
	@Transactional
	public Integer insertShop(ShopUserPermissionDTO shopUserPermissionDTO,String ids) {
		Integer rows=0;
		
			// 1.查询出所有店铺信息-shopList
			List<ShopVo> shopList = shopMapper.listShopByDTO(shopUserPermissionDTO);
			
			if (shopList.size() > 0) {
				for (int i = 0; i < shopList.size(); i++) {
					if (shopList.get(i).getName().equals(shopUserPermissionDTO.getName())) {
						
						// 这里应该抛出异常，用自定义异常处理器解决
						throw new ShopNameConflictException("店铺名称重复，请重新输入");
					}
				}
			}
			
			// 判断用户名是否重复
			UserVo userVo = new UserVo();
            userVo.setUsername(shopUserPermissionDTO.getUsername());
            UserVo userVo2 = userMapper.selectUserByUsername(userVo);
            if (userVo2 != null) {
                
                // 这里应该抛出异常，用自定义异常处理器解决
                throw new UserNameConflictException("用户名重复，请重新输入");
            }
			rows = shopMapper.insertShop(shopUserPermissionDTO);
			if(rows == 0){
				throw new DataNotExistsException("添加失败，请联系管理员");
			}
			BigInteger newshopId = shopUserPermissionDTO.getId();
			// 2.添加一个角色给这个店铺
			Role role = new Role();
			role.setName("超级管理员");
			role.setSalary(new BigDecimal(5000));
			role.setShopId(shopUserPermissionDTO.getId());
			role.setRemarks("无");
			rows = roleMapper.saveRole(role);
			if(rows == 0){
				throw new DataNotExistsException("添加失败，请联系管理员");
			}
			// 3.添加一个用户给这个店铺
			shopUserPermissionDTO.setRoleId(role.getId());
			shopUserPermissionDTO.setShopId(newshopId);
			try {
				shopUserPermissionDTO.setPassword(MD5Util.md5Encode(shopUserPermissionDTO.getPassword()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			rows = userMapper.saveUserByDTO(shopUserPermissionDTO);
			if(rows == 0){
				throw new DataNotExistsException("添加失败，请联系管理员");
			}
			// 4.添加用户角色关联表
			userVo.setId(newshopId);
			userVo.setRoleId(role.getId());
			rows = userMapper.saveUserAndRoleRelation(userVo);
			if(rows == 0){
				throw new DataNotExistsException("添加失败，请联系管理员");
			}
			// 5.添加权限给当前店铺的超级管理员--先查询总店超级管理员所有权限--根据授权信息给新分店超级管理员添加权限
	        List<Permission> permissions = userMapper.listPermissionBySuperUser();
	        String[] permissionIds = ids.split(",");
	        RolePermissionRelationship rolePermissionRelationship = new RolePermissionRelationship();
	        rolePermissionRelationship.setRoleId(role.getId());
	        for (int m = 0; m < permissionIds.length; m++) {
	            for (int n = 0; n < permissions.size(); n++) {
                    if (new BigInteger(permissionIds[m]).compareTo(permissions.get(n).getId())==0) {
                        rolePermissionRelationship.setPermissionId(permissions.get(n).getId());
                        rows=roleMapper.saveRolePermissionRelationship(rolePermissionRelationship);
                        if(rows==0){
                        	throw new DataNotExistsException("添加失败，请联系管理员");
                        }
                        break;
                    }
                }
                
            }
		
		return rows;
	}
	/**
     * @Title: deleteShop
     * @description 删除店铺+删除用户
     * @param  shop
     * @return int 
     * @author dujiawei
     * @createDate 2018年12月5日
     */
	@Override
	public int deleteShop(Shop shop) {
		int num = 0;
		try {
			num = shopMapper.deleteShop(shop);
			
			userMapper.removeUserByShopId(shop);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return num;
	}
	/**
     * @Title: listByShopNameVo
     * @description 通过店铺名称查询信息
     * @param  shopVo
     * @return List<ShopVo>
     * @author dujiawei
     * @createDate 2018年12月5日
     */
	@Override
	public List<ShopVo> listByShopNameVo(ShopVo shopVo) {
		List<ShopVo> shopList = new ArrayList<ShopVo>();
		try {
			shopList = shopMapper.listByShopNameVo(shopVo);
			if (shopList.size() > 0) {
				//System.out.println("通过店铺名已查询到：" + shopList);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return shopList;
	}
	/**
     * @Title: countByShopName
     * @description 通过店铺名称查询条数
     * @param  shopVo
     * @return ShopVo
     * @author dujiawei
     * @createDate 2018年12月5日
     */
	@Override
	public ShopVo countByShopName(ShopVo shopVo) {
		shopVo = shopMapper.countByShopName(shopVo);
		
		return shopVo;
	}
	/**
     * @Title: getId
     * @description 通过ID查询地区ID
     * @param  
     * @return Shop    
     * @author linhongyu
     * @createDate 2018年11月20日
     */
	@Override
	public Shop getId(Shop shop) {
		return shopMapper.getId(shop);
	}
   
	/**
     * @Title: getname
     * @description 通过id获取name
     * @param  Shop
     * @return Shop    
     * @author liujunkai
     * @createDate 2018年12月5日
     */
	@Override
    public List<Shop> listshop(Shop shop) {
       
	    return shopMapper.listshop(shop);
    }

	/**@description 获得各分店铺id和名称
     * @return List<Shop>
     * @author zhou jiaxin
     * @createDate 2019年2月12日
     */
    @Override
    public List<Shop> listShopIdAndName() {
        return shopMapper.listShopIdAndName();
    }
    
    /**@description 获得各分店铺id和名称除了当前店
     * @return List<Shop>
     * @return shopId   
     * @author zhou jiaxin
     * @createDate 2019年2月12日
     */
    @Override
    public List<Shop> listShopIdButMe(Integer shopId) {
        
        return shopMapper.listShopIdButMe(shopId);
    }
    /**
     * 
         * @Title: getAllCity
         * @description  获得所有的省市区
         * @param  
         * @return 省市区列表
         * @author chenshuxian	
         * @createDate 2019年7月8日
     */
	@Override
	public List<ShopVo> getAllCity() {
		
		return shopMapper.getAllCity();
	}
    
   
}
