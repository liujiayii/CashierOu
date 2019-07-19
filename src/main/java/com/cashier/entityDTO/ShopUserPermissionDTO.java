package com.cashier.entityDTO;

import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * @author 
 * @return
 * @author
 */
/**
 *
 * @ClassName: ShopUserPermissionDTO

 * @description 
 *
 * @author 
 * @createDate 2019年1月21日
 */

public class ShopUserPermissionDTO {
    
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
    /** 用户ID */
    private BigInteger uId;
    /** 用户账号 */
    private String username;
    /** 用户密码 */
    private String password;
    /** 用户姓名 */
    private String uname;
    /** 用户联系电话 */
    private String uphone;
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
    /** 用户角色id */
    private BigInteger roleId;
    /** 用户生日 */
    private Timestamp birthday;
    /** 备注 */
    private String uremarks;
    /** 所属店铺ID */
    private BigInteger shopId;
    
    /** 用户状态 */
    private Integer ustate;
    /** 区域经理类型:
     *（  0.不是经理;
     *  1.省级;
     *  2.市级;
     *  3.区级） */
    private Integer agentType;
    /** 区域ID */
    private Integer areaId;
    /** 用户省ID */
    private Integer userProvinceId;
    /** 用户市ID */
    private Integer userCityId;
    
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
	public BigInteger getuId() {
		return uId;
	}
	public void setuId(BigInteger uId) {
		this.uId = uId;
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
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUphone() {
		return uphone;
	}
	public void setUphone(String uphone) {
		this.uphone = uphone;
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
	public BigInteger getRoleId() {
		return roleId;
	}
	public void setRoleId(BigInteger roleId) {
		this.roleId = roleId;
	}
	public Timestamp getBirthday() {
		return birthday;
	}
	public void setBirthday(Timestamp birthday) {
		this.birthday = birthday;
	}
	public String getUremarks() {
		return uremarks;
	}
	public void setUremarks(String uremarks) {
		this.uremarks = uremarks;
	}
	public BigInteger getShopId() {
		return shopId;
	}
	public void setShopId(BigInteger shopId) {
		this.shopId = shopId;
	}
	public Integer getUstate() {
		return ustate;
	}
	public void setUstate(Integer ustate) {
		this.ustate = ustate;
	}
	public Integer getAgentType() {
		return agentType;
	}
	public void setAgentType(Integer agentType) {
		this.agentType = agentType;
	}
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((addr == null) ? 0 : addr.hashCode());
		result = prime * result + ((age == null) ? 0 : age.hashCode());
		result = prime * result + ((agentType == null) ? 0 : agentType.hashCode());
		result = prime * result + ((areaId == null) ? 0 : areaId.hashCode());
		result = prime * result + ((areaid == null) ? 0 : areaid.hashCode());
		result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
		result = prime * result + ((bonusType == null) ? 0 : bonusType.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((cityid == null) ? 0 : cityid.hashCode());
		result = prime * result + ((count == null) ? 0 : count.hashCode());
		result = prime * result + ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + ((entryTime == null) ? 0 : entryTime.hashCode());
		result = prime * result + ((generalScope == null) ? 0 : generalScope.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + limit;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + page;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((provice == null) ? 0 : provice.hashCode());
		result = prime * result + ((provid == null) ? 0 : provid.hashCode());
		result = prime * result + ((remarks == null) ? 0 : remarks.hashCode());
		result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
		result = prime * result + ((sex == null) ? 0 : sex.hashCode());
		result = prime * result + ((shopId == null) ? 0 : shopId.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((uId == null) ? 0 : uId.hashCode());
		result = prime * result + ((uname == null) ? 0 : uname.hashCode());
		result = prime * result + ((uphone == null) ? 0 : uphone.hashCode());
		result = prime * result + ((uremarks == null) ? 0 : uremarks.hashCode());
		result = prime * result + ((userCityId == null) ? 0 : userCityId.hashCode());
		result = prime * result + ((userProvinceId == null) ? 0 : userProvinceId.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		result = prime * result + ((ustate == null) ? 0 : ustate.hashCode());
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
		ShopUserPermissionDTO other = (ShopUserPermissionDTO) obj;
		if (addr == null) {
			if (other.addr != null)
				return false;
		} else if (!addr.equals(other.addr))
			return false;
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
		if (areaid == null) {
			if (other.areaid != null)
				return false;
		} else if (!areaid.equals(other.areaid))
			return false;
		if (birthday == null) {
			if (other.birthday != null)
				return false;
		} else if (!birthday.equals(other.birthday))
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
		if (entryTime == null) {
			if (other.entryTime != null)
				return false;
		} else if (!entryTime.equals(other.entryTime))
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
		if (roleId == null) {
			if (other.roleId != null)
				return false;
		} else if (!roleId.equals(other.roleId))
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
		if (uId == null) {
			if (other.uId != null)
				return false;
		} else if (!uId.equals(other.uId))
			return false;
		if (uname == null) {
			if (other.uname != null)
				return false;
		} else if (!uname.equals(other.uname))
			return false;
		if (uphone == null) {
			if (other.uphone != null)
				return false;
		} else if (!uphone.equals(other.uphone))
			return false;
		if (uremarks == null) {
			if (other.uremarks != null)
				return false;
		} else if (!uremarks.equals(other.uremarks))
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
		if (ustate == null) {
			if (other.ustate != null)
				return false;
		} else if (!ustate.equals(other.ustate))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ShopUserPermissionDTO [id=" + id + ", name=" + name + ", type=" + type + ", areaid=" + areaid
				+ ", provid=" + provid + ", cityid=" + cityid + ", provice=" + provice + ", city=" + city + ", title="
				+ title + ", generalScope=" + generalScope + ", addr=" + addr + ", phone=" + phone + ", createTime="
				+ createTime + ", state=" + state + ", remarks=" + remarks + ", page=" + page + ", limit=" + limit
				+ ", count=" + count + ", bonusType=" + bonusType + ", uId=" + uId + ", username=" + username
				+ ", password=" + password + ", uname=" + uname + ", uphone=" + uphone + ", sex=" + sex + ", age=" + age
				+ ", entryTime=" + entryTime + ", roleId=" + roleId + ", birthday=" + birthday + ", uremarks="
				+ uremarks + ", shopId=" + shopId + ", ustate=" + ustate + ", agentType=" + agentType + ", areaId="
				+ areaId + ", userProvinceId=" + userProvinceId + ", userCityId=" + userCityId + "]";
	}
    
}
