package com.cashier.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
/**
 * 
 *
 * @ClassName: Member
 * @description 会员表的实体类
 *
 * @author dujiawei
 * @createDate 2018年11月7日
 */
public class Member implements Serializable {

	/** UID  */
	private static final long serialVersionUID = 1L;
	/** 会员ID */
	private BigInteger id;	
	/** 会员卡号 */
	private String number;
	
	/** 会员电话 */
	private String phone;
	/** 会员姓名 */
	private String name;
	/**
	 * 会员性别:<br>
	 * 1:男;<br>
	 * 2:女;<br>
	 */
	private Integer sex;
	/** 会员生日 */
	private Date birthday;		

	/** 会员卡积分 */
	private BigInteger credits;
	/** 会员等级id */
	private BigInteger ldLevelId;
	
	/** 会员等级名称 */
	private String level;
	
	/**
	 * 会员卡状态:<br>
	 * 1:冻结;<br>
	 * 2:正常;<br>
	 */
	private Integer state;
	/** 会员累计消费金额 */
	private BigDecimal totalMoney;
	/** 办卡时间 */
	private Timestamp creattime;
	
	/** 支付密码 */
	private String password;
	/** 信息凭证 */
	private String evidence;
	
	/**
	 * 是否可用:<br>
	 * 0:可用;<br>
	 * 1:不可用;<br>
	 */
	private Integer canUse;
	

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public BigInteger getCredits() {
		return credits;
	}

	public void setCredits(BigInteger credits) {
		this.credits = credits;
	}

	public BigInteger getLdLevelId() {
		return ldLevelId;
	}

	public void setLdLevelId(BigInteger ldLevelId) {
		this.ldLevelId = ldLevelId;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public BigDecimal getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}

	public Timestamp getCreattime() {
		return creattime;
	}

	public void setCreattime(Timestamp creattime) {
		this.creattime = creattime;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEvidence() {
		return evidence;
	}

	public void setEvidence(String evidence) {
		this.evidence = evidence;
	}

	public Integer getCanUse() {
		return canUse;
	}

	public void setCanUse(Integer canUse) {
		this.canUse = canUse;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
		result = prime * result + ((canUse == null) ? 0 : canUse.hashCode());
		result = prime * result + ((creattime == null) ? 0 : creattime.hashCode());
		result = prime * result + ((credits == null) ? 0 : credits.hashCode());
		result = prime * result + ((evidence == null) ? 0 : evidence.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((ldLevelId == null) ? 0 : ldLevelId.hashCode());
		result = prime * result + ((level == null) ? 0 : level.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((sex == null) ? 0 : sex.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((totalMoney == null) ? 0 : totalMoney.hashCode());
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
		Member other = (Member) obj;
		if (birthday == null) {
			if (other.birthday != null)
				return false;
		} else if (!birthday.equals(other.birthday))
			return false;
		if (canUse == null) {
			if (other.canUse != null)
				return false;
		} else if (!canUse.equals(other.canUse))
			return false;
		if (creattime == null) {
			if (other.creattime != null)
				return false;
		} else if (!creattime.equals(other.creattime))
			return false;
		if (credits == null) {
			if (other.credits != null)
				return false;
		} else if (!credits.equals(other.credits))
			return false;
		if (evidence == null) {
			if (other.evidence != null)
				return false;
		} else if (!evidence.equals(other.evidence))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (ldLevelId == null) {
			if (other.ldLevelId != null)
				return false;
		} else if (!ldLevelId.equals(other.ldLevelId))
			return false;
		if (level == null) {
			if (other.level != null)
				return false;
		} else if (!level.equals(other.level))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
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
		if (sex == null) {
			if (other.sex != null)
				return false;
		} else if (!sex.equals(other.sex))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (totalMoney == null) {
			if (other.totalMoney != null)
				return false;
		} else if (!totalMoney.equals(other.totalMoney))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", number=" + number + ", phone=" + phone + ", name=" + name + ", sex=" + sex
				+ ", birthday=" + birthday + ", credits=" + credits + ", ldLevelId=" + ldLevelId + ", level=" + level
				+ ", state=" + state + ", totalMoney=" + totalMoney + ", creattime=" + creattime + ", password="
				+ password + ", evidence=" + evidence + ", canUse=" + canUse + "]";
	}
	
}
