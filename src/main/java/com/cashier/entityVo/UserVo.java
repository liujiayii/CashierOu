package com.cashier.entityVo;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;
/**
 * 
 *
 * @ClassName: UserVo
 * @description 用户的Vo类
 *
 * @author dujiawei
 * @createDate 2018年12月3日
 */
public class UserVo implements Serializable {
	/** UID */
	private static final long serialVersionUID = 1L;
	/** 用户ID */
	private BigInteger id;
	/** 用户账号 */
	private String username;
	/** 用户密码 */
	private String password;
	/** 用户姓名 */
	private String name;
	/** 用户联系电话 */
	private String phone;
	/**
	 * 用户性别：
	 * 1.男;
	 * 2.女;
	 */
	private Integer sex;
	/** 用户年龄 */
	private Integer age;
	/** 用户入职日期 */
	private Timestamp entryTime;
	/** 用户生日 */
	private Timestamp birthday;
	/** 备注 */
	private String remarks;
	/** 所属店铺ID */
    private BigInteger shopId;
    /** 用户角色id */
	private BigInteger roleId;
    /** 角色名称 */
	private String roleName;
	
	/** 用户状态 */
    private Integer state;
    /** 区域经理类型:
     *（  0.不是经理;
     *  1.省级;
     *  2.市级;
     *  3.区级） */
    private Integer agentType;
    /** 用户省ID */
    private Integer userProvinceId;
    /** 用户市ID */
    private Integer userCityId;
    /** 区域ID */
    private Integer areaId;
	
	/** 页数 */
	private int page = 1;
	/** 分页条数 */
	private int limit = 5;
	/** 数据条数 */
	private Integer count;
	
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Timestamp getEntryTime() {
		return entryTime;
	}
	public void setEntryTime(Timestamp entryTime) {
		this.entryTime = entryTime;
	}
	public Timestamp getBirthday() {
		return birthday;
	}
	public void setBirthday(Timestamp birthday) {
		this.birthday = birthday;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public BigInteger getShopId() {
		return shopId;
	}
	public void setShopId(BigInteger shopId) {
		this.shopId = shopId;
	}
	public BigInteger getRoleId() {
		return roleId;
	}
	public void setRoleId(BigInteger roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getAgentType() {
		return agentType;
	}
	public void setAgentType(Integer agentType) {
		this.agentType = agentType;
	}
	public Integer getUserProvinceId() {
		return userProvinceId;
	}
	public void setUserProvinceId(Integer userProvinceId) {
		this.userProvinceId = userProvinceId;
	}
	public Integer getUserCityId() {
		return userCityId;
	}
	public void setUserCityId(Integer userCityId) {
		this.userCityId = userCityId;
	}
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((age == null) ? 0 : age.hashCode());
		result = prime * result + ((agentType == null) ? 0 : agentType.hashCode());
		result = prime * result + ((areaId == null) ? 0 : areaId.hashCode());
		result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
		result = prime * result + ((count == null) ? 0 : count.hashCode());
		result = prime * result + ((entryTime == null) ? 0 : entryTime.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + limit;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + page;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((remarks == null) ? 0 : remarks.hashCode());
		result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
		result = prime * result + ((roleName == null) ? 0 : roleName.hashCode());
		result = prime * result + ((sex == null) ? 0 : sex.hashCode());
		result = prime * result + ((shopId == null) ? 0 : shopId.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((userCityId == null) ? 0 : userCityId.hashCode());
		result = prime * result + ((userProvinceId == null) ? 0 : userProvinceId.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserVo other = (UserVo) obj;
		if (age == null) {
			if (other.age != null)
				return false;
		} else if (!age.equals(other.age))
			return false;
		if (agentType == null) {
			if (other.agentType != null)
				return false;
		} else if (!agentType.equals(other.agentType))
			return false;
		if (areaId == null) {
			if (other.areaId != null)
				return false;
		} else if (!areaId.equals(other.areaId))
			return false;
		if (birthday == null) {
			if (other.birthday != null)
				return false;
		} else if (!birthday.equals(other.birthday))
			return false;
		if (count == null) {
			if (other.count != null)
				return false;
		} else if (!count.equals(other.count))
			return false;
		if (entryTime == null) {
			if (other.entryTime != null)
				return false;
		} else if (!entryTime.equals(other.entryTime))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (limit != other.limit)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (page != other.page)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (remarks == null) {
			if (other.remarks != null)
				return false;
		} else if (!remarks.equals(other.remarks))
			return false;
		if (roleId == null) {
			if (other.roleId != null)
				return false;
		} else if (!roleId.equals(other.roleId))
			return false;
		if (roleName == null) {
			if (other.roleName != null)
				return false;
		} else if (!roleName.equals(other.roleName))
			return false;
		if (sex == null) {
			if (other.sex != null)
				return false;
		} else if (!sex.equals(other.sex))
			return false;
		if (shopId == null) {
			if (other.shopId != null)
				return false;
		} else if (!shopId.equals(other.shopId))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (userCityId == null) {
			if (other.userCityId != null)
				return false;
		} else if (!userCityId.equals(other.userCityId))
			return false;
		if (userProvinceId == null) {
			if (other.userProvinceId != null)
				return false;
		} else if (!userProvinceId.equals(other.userProvinceId))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "UserVo [id=" + id + ", username=" + username + ", password=" + password + ", name=" + name + ", phone="
				+ phone + ", sex=" + sex + ", age=" + age + ", entryTime=" + entryTime + ", birthday=" + birthday
				+ ", remarks=" + remarks + ", shopId=" + shopId + ", roleId=" + roleId + ", roleName=" + roleName
				+ ", state=" + state + ", agentType=" + agentType + ", userProvinceId=" + userProvinceId
				+ ", userCityId=" + userCityId + ", areaId=" + areaId + ", page=" + page + ", limit=" + limit
				+ ", count=" + count + "]";
	}
	
}
