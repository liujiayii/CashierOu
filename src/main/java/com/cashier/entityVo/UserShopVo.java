package com.cashier.entityVo;

import java.io.Serializable;
import java.math.BigInteger;

public class UserShopVo implements Serializable {

	/** UID  */
	private static final long serialVersionUID = 1L;
	/** 用户ID */
	private BigInteger id;
	/** 用户账号 */
	private String username;
	/** 用户密码 */
	private String password;
	/** 用户姓名 */
	private String name;
    
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
    /** 用户区域ID */
    private Integer areaId;
    
    //  店铺表字段
    
    /** 店铺ID */
	private BigInteger shopId;
	/** 店铺名称 */
	private String shopName;
	/**
     * 店铺类型：
     * 1.总店;
     * 2.分店;
     */
	private Integer type;
	/** 店铺所属地区id */
	private Integer area;
	/** 店铺所属省份id */
	private Integer provinceId;
	/** 店铺所属城市id */
	private Integer cityId;
	
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
	public BigInteger getShopId() {
		return shopId;
	}
	public void setShopId(BigInteger shopId) {
		this.shopId = shopId;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getArea() {
		return area;
	}
	public void setArea(Integer area) {
		this.area = area;
	}
	public Integer getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((agentType == null) ? 0 : agentType.hashCode());
		result = prime * result + ((area == null) ? 0 : area.hashCode());
		result = prime * result + ((areaId == null) ? 0 : areaId.hashCode());
		result = prime * result + ((cityId == null) ? 0 : cityId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((provinceId == null) ? 0 : provinceId.hashCode());
		result = prime * result + ((shopId == null) ? 0 : shopId.hashCode());
		result = prime * result + ((shopName == null) ? 0 : shopName.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		UserShopVo other = (UserShopVo) obj;
		if (agentType == null) {
			if (other.agentType != null)
				return false;
		} else if (!agentType.equals(other.agentType))
			return false;
		if (area == null) {
			if (other.area != null)
				return false;
		} else if (!area.equals(other.area))
			return false;
		if (areaId == null) {
			if (other.areaId != null)
				return false;
		} else if (!areaId.equals(other.areaId))
			return false;
		if (cityId == null) {
			if (other.cityId != null)
				return false;
		} else if (!cityId.equals(other.cityId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (provinceId == null) {
			if (other.provinceId != null)
				return false;
		} else if (!provinceId.equals(other.provinceId))
			return false;
		if (shopId == null) {
			if (other.shopId != null)
				return false;
		} else if (!shopId.equals(other.shopId))
			return false;
		if (shopName == null) {
			if (other.shopName != null)
				return false;
		} else if (!shopName.equals(other.shopName))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
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
		return "UserShopVo [id=" + id + ", username=" + username + ", password=" + password + ", name=" + name
				+ ", state=" + state + ", agentType=" + agentType + ", userProvinceId=" + userProvinceId
				+ ", userCityId=" + userCityId + ", areaId=" + areaId + ", shopId=" + shopId + ", shopName=" + shopName
				+ ", type=" + type + ", area=" + area + ", provinceId=" + provinceId + ", cityId=" + cityId + "]";
	}

}
