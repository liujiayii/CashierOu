package com.cashier.entityDTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import com.cashier.entity.Regulation;

/**
 * 
 * @Description  添加活动时携带的数据 
 * @author zhoujiaxin  
 * @createDate 2019年6月20日  
 */
public class SpecialOffersDTO implements Serializable{

    
    private static final long serialVersionUID = 1L;

    /** 活动信息id */
    private BigInteger id;
    /** 分店id */
    private BigInteger shopId;
    /** 活动状态
     * 1.未开始
     * 2.进行中
     * 3.已结束
     *  */
    private Integer state;
    /** 活动名称 */
    private String name;
    /** 活动类型
     *  1.满减活动
     *  2.商品打折
     */
    private Integer type;
    /** 折扣率 */
    private BigDecimal discount;
    /** 活动开始时间 */
    private String startTime;
    /** 活动结束时间 */
    private String endTime;
    /** 活动优先级(1-100)数字越大优先级越高 */
    private Integer priority;
    /** 商品活动级别
     * 1.通用级别 
     * 2.分类级别
     * 3.商品级别
     */
    private Integer scope;
    
    /** 参加活动的商品ID */
    private List<Integer> ids;
    /** 参加活动的满减规则 */
    private List<Regulation> regulations;
    /** 这次新活动与其他活动冲突，其他活动的ID */
    private List<Integer> activityIds;
	public SpecialOffersDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SpecialOffersDTO(BigInteger id, BigInteger shopId, Integer state, String name, Integer type,
			BigDecimal discount, String startTime, String endTime, Integer priority, Integer scope, List<Integer> ids,
			List<Regulation> regulations, List<Integer> activityIds) {
		super();
		this.id = id;
		this.shopId = shopId;
		this.state = state;
		this.name = name;
		this.type = type;
		this.discount = discount;
		this.startTime = startTime;
		this.endTime = endTime;
		this.priority = priority;
		this.scope = scope;
		this.ids = ids;
		this.regulations = regulations;
		this.activityIds = activityIds;
	}
	@Override
	public String toString() {
		return "SpecialOffersDTO [id=" + id + ", shopId=" + shopId + ", state=" + state + ", name=" + name + ", type="
				+ type + ", discount=" + discount + ", startTime=" + startTime + ", endTime=" + endTime + ", priority="
				+ priority + ", scope=" + scope + ", ids=" + ids + ", regulations=" + regulations + ", activityIds="
				+ activityIds + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((activityIds == null) ? 0 : activityIds.hashCode());
		result = prime * result + ((discount == null) ? 0 : discount.hashCode());
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((ids == null) ? 0 : ids.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((priority == null) ? 0 : priority.hashCode());
		result = prime * result + ((regulations == null) ? 0 : regulations.hashCode());
		result = prime * result + ((scope == null) ? 0 : scope.hashCode());
		result = prime * result + ((shopId == null) ? 0 : shopId.hashCode());
		result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
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
		SpecialOffersDTO other = (SpecialOffersDTO) obj;
		if (activityIds == null) {
			if (other.activityIds != null)
				return false;
		} else if (!activityIds.equals(other.activityIds))
			return false;
		if (discount == null) {
			if (other.discount != null)
				return false;
		} else if (!discount.equals(other.discount))
			return false;
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (ids == null) {
			if (other.ids != null)
				return false;
		} else if (!ids.equals(other.ids))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (priority == null) {
			if (other.priority != null)
				return false;
		} else if (!priority.equals(other.priority))
			return false;
		if (regulations == null) {
			if (other.regulations != null)
				return false;
		} else if (!regulations.equals(other.regulations))
			return false;
		if (scope == null) {
			if (other.scope != null)
				return false;
		} else if (!scope.equals(other.scope))
			return false;
		if (shopId == null) {
			if (other.shopId != null)
				return false;
		} else if (!shopId.equals(other.shopId))
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
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
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public BigInteger getShopId() {
		return shopId;
	}
	public void setShopId(BigInteger shopId) {
		this.shopId = shopId;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
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
	public BigDecimal getDiscount() {
		return discount;
	}
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public Integer getScope() {
		return scope;
	}
	public void setScope(Integer scope) {
		this.scope = scope;
	}
	public List<Integer> getIds() {
		return ids;
	}
	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}
	public List<Regulation> getRegulations() {
		return regulations;
	}
	public void setRegulations(List<Regulation> regulations) {
		this.regulations = regulations;
	}
	public List<Integer> getActivityIds() {
		return activityIds;
	}
	public void setActivityIds(List<Integer> activityIds) {
		this.activityIds = activityIds;
	}
   
    
    
	
    
}
