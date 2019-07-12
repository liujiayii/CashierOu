package com.cashier.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;


/**
 *
 * @ClassName: Level
 * @description 会员等级表实体类
 *
 * @author dujiawei
 * @createDate 2019年6月20日
 */
public class Level implements Serializable {
	
	/** UID  */
	private static final long serialVersionUID = 1L;
	/** 会员等级优惠id */
	private BigInteger id;
	/** 累计消费金额下限 */
	private BigDecimal minimum;
	/** 累计消费金额上限 */
	private BigDecimal maximum;
	/** 会员等级 */
	private String level;
	
	/** 累计消费金额 */
	private BigDecimal totalMoney;
	
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
	public BigDecimal getMinimum() {
		return minimum;
	}
	public void setMinimum(BigDecimal minimum) {
		this.minimum = minimum;
	}
	public BigDecimal getMaximum() {
		return maximum;
	}
	public void setMaximum(BigDecimal maximum) {
		this.maximum = maximum;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public BigDecimal getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
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
		result = prime * result + ((count == null) ? 0 : count.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((level == null) ? 0 : level.hashCode());
		result = prime * result + limit;
		result = prime * result + ((maximum == null) ? 0 : maximum.hashCode());
		result = prime * result + ((minimum == null) ? 0 : minimum.hashCode());
		result = prime * result + page;
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
		Level other = (Level) obj;
		if (count == null) {
			if (other.count != null)
				return false;
		} else if (!count.equals(other.count))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (level == null) {
			if (other.level != null)
				return false;
		} else if (!level.equals(other.level))
			return false;
		if (limit != other.limit)
			return false;
		if (maximum == null) {
			if (other.maximum != null)
				return false;
		} else if (!maximum.equals(other.maximum))
			return false;
		if (minimum == null) {
			if (other.minimum != null)
				return false;
		} else if (!minimum.equals(other.minimum))
			return false;
		if (page != other.page)
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
		return "Level [id=" + id + ", minimum=" + minimum + ", maximum=" + maximum + ", level=" + level
				+ ", totalMoney=" + totalMoney + ", page=" + page + ", limit=" + limit + ", count=" + count + "]";
	}
	
}
