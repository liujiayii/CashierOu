package com.cashier.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @ClassName: SettingPlatform

 * @description 实体：平台参数设置
 *
 * @author chenshuxian
 * @createDate 2019年2月27日
 */

public class SettingPlatform implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**创建时间 */
	public Timestamp time;
	/**系统参数键名 */
	public String _key;
	/**系统参数值 */
	public String _value;
	/**系统参数描述 */
	public String description;
	/**备注 */
	public String remark;
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	/**
	 * @return the _key
	 */
	public String get_key() {
		return _key;
	}
	/**
	 * @param _key the _key to set
	 */
	public void set_key(String _key) {
		this._key = _key;
	}
	/**
	 * @return the _value
	 */
	public String get_value() {
		return _value;
	}
	/**
	 * @param _value the _value to set
	 */
	public void set_value(String _value) {
		this._value = _value;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_key == null) ? 0 : _key.hashCode());
		result = prime * result + ((_value == null) ? 0 : _value.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((remark == null) ? 0 : remark.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SettingPlatform other = (SettingPlatform) obj;
		if (_key == null) {
			if (other._key != null)
				return false;
		} else if (!_key.equals(other._key))
			return false;
		if (_value == null) {
			if (other._value != null)
				return false;
		} else if (!_value.equals(other._value))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (remark == null) {
			if (other.remark != null)
				return false;
		} else if (!remark.equals(other.remark))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SettingPlatform [time=" + time + ", _key=" + _key + ", _value=" + _value + ", description="
				+ description + ", remark=" + remark + "]";
	}

	
}
