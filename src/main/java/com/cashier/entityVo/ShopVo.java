package com.cashier.entityVo;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

public class ShopVo implements Serializable {
	/* UID */
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
	/** 店铺所属地区id */
	private Integer areaid;
	/** 店铺所属省份id */
	private Integer provid;
	/** 店铺所属城市id */
	private Integer cityid;
	/** 店铺所属省份名称 */
	private String provice;
	/** 店铺所属城市名称 */
	private String city;
	/** 店铺所属地区名称 */
	private String title;
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
	/** 页数 */
	private int page = 1;
	/** 分页条数 */
	private int limit = 5;
	/** 数据条数 */
	private Integer count;
	/** 分红类型id */
	private Integer bonusType;
	/** 分红类型名称 */
	private String bonusName;
	
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
	public Integer getAreaid() {
		return areaid;
	}
	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}
	public Integer getProvid() {
		return provid;
	}
	public void setProvid(Integer provid) {
		this.provid = provid;
	}
	public Integer getCityid() {
		return cityid;
	}
	public void setCityid(Integer cityid) {
		this.cityid = cityid;
	}
	public String getProvice() {
		return provice;
	}
	public void setProvice(String provice) {
		this.provice = provice;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public Integer getBonusType() {
		return bonusType;
	}
	public void setBonusType(Integer bonusType) {
		this.bonusType = bonusType;
	}
	public String getBonusName() {
		return bonusName;
	}
	public void setBonusName(String bonusName) {
		this.bonusName = bonusName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((addr == null) ? 0 : addr.hashCode());
		result = prime * result + ((areaid == null) ? 0 : areaid.hashCode());
		result = prime * result + ((bonusName == null) ? 0 : bonusName.hashCode());
		result = prime * result + ((bonusType == null) ? 0 : bonusType.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((cityid == null) ? 0 : cityid.hashCode());
		result = prime * result + ((count == null) ? 0 : count.hashCode());
		result = prime * result + ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + ((generalScope == null) ? 0 : generalScope.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + limit;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + page;
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((provice == null) ? 0 : provice.hashCode());
		result = prime * result + ((provid == null) ? 0 : provid.hashCode());
		result = prime * result + ((remarks == null) ? 0 : remarks.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		ShopVo other = (ShopVo) obj;
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
		if (bonusName == null) {
			if (other.bonusName != null)
				return false;
		} else if (!bonusName.equals(other.bonusName))
			return false;
		if (bonusType == null) {
			if (other.bonusType != null)
				return false;
		} else if (!bonusType.equals(other.bonusType))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (cityid == null) {
			if (other.cityid != null)
				return false;
		} else if (!cityid.equals(other.cityid))
			return false;
		if (count == null) {
			if (other.count != null)
				return false;
		} else if (!count.equals(other.count))
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
		if (limit != other.limit)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (page != other.page)
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (provice == null) {
			if (other.provice != null)
				return false;
		} else if (!provice.equals(other.provice))
			return false;
		if (provid == null) {
			if (other.provid != null)
				return false;
		} else if (!provid.equals(other.provid))
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
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ShopVo [id=" + id + ", name=" + name + ", type=" + type + ", areaid=" + areaid + ", provid=" + provid
				+ ", cityid=" + cityid + ", provice=" + provice + ", city=" + city + ", title=" + title
				+ ", generalScope=" + generalScope + ", addr=" + addr + ", phone=" + phone + ", createTime="
				+ createTime + ", state=" + state + ", remarks=" + remarks + ", page=" + page + ", limit=" + limit
				+ ", count=" + count + ", bonusType=" + bonusType + ", bonusName=" + bonusName + "]";
	}
	
}
