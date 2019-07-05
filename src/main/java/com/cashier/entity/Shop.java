package com.cashier.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

/**
*
* @ClassName: Shop
* @description 店铺表
*
* @author zhoujiaxin
* @createDate 2018年11月8日
*/
public class Shop implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	/** 店铺ID */
	private BigInteger id;
	/** 店铺名称 */
	private String name;
	/**
     * 店铺类型：
     * 1.总店;
     * 2.分店;
     */
	private Integer type;
	/** 省id*/
	private Integer provinceId;
	/** 市id*/
	private Integer cityId;
	/** 店铺所属地区 */
	private Integer areaid;
	/**
     * 店铺会员通用范围：
     * 1.全国;
     * 2.本店;
     * 其他（省市区 area_id ）
     */
	private Integer generalScope;
	/** 店铺详细地址 */
	private String addr;
	/** 店铺联系电话 */
	private String phone;
	/** 店铺开业时间 */
	private Timestamp createTime;
	/**
     * 店铺状态：
     * 1.营业中;
     * 2.未营业;
     */
	private Integer state;
	/** 备注 */
	private String remarks;
	
	public Shop() {
		super();
	}

	public Shop(BigInteger id, String name, Integer type, Integer provinceId, Integer cityId, Integer areaid,
			Integer generalScope, String addr, String phone, Timestamp createTime, Integer state, String remarks) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.provinceId = provinceId;
		this.cityId = cityId;
		this.areaid = areaid;
		this.generalScope = generalScope;
		this.addr = addr;
		this.phone = phone;
		this.createTime = createTime;
		this.state = state;
		this.remarks = remarks;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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

	public Integer getAreaid() {
		return areaid;
	}

	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}

	public Integer getGeneralScope() {
		return generalScope;
	}

	public void setGeneralScope(Integer generalScope) {
		this.generalScope = generalScope;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((addr == null) ? 0 : addr.hashCode());
		result = prime * result + ((areaid == null) ? 0 : areaid.hashCode());
		result = prime * result + ((cityId == null) ? 0 : cityId.hashCode());
		result = prime * result + ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + ((generalScope == null) ? 0 : generalScope.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((provinceId == null) ? 0 : provinceId.hashCode());
		result = prime * result + ((remarks == null) ? 0 : remarks.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Shop other = (Shop) obj;
		if (addr == null) {
			if (other.addr != null)
				return false;
		} else if (!addr.equals(other.addr))
			return false;
		if (areaid == null) {
			if (other.areaid != null)
				return false;
		} else if (!areaid.equals(other.areaid))
			return false;
		if (cityId == null) {
			if (other.cityId != null)
				return false;
		} else if (!cityId.equals(other.cityId))
			return false;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (generalScope == null) {
			if (other.generalScope != null)
				return false;
		} else if (!generalScope.equals(other.generalScope))
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
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (provinceId == null) {
			if (other.provinceId != null)
				return false;
		} else if (!provinceId.equals(other.provinceId))
			return false;
		if (remarks == null) {
			if (other.remarks != null)
				return false;
		} else if (!remarks.equals(other.remarks))
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
		return true;
	}
	
	
}
