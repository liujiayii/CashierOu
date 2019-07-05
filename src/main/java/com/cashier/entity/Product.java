package com.cashier.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * 商品表实体类
 *
 * @author pangchong
 * @createDate 2018年9月12日 上午10:30
 */
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;
	/**商品id */
	private BigInteger id;
	/**商品名称 */
	private String name;
	/**商品码 */
	private String barCode;
	/**商品图片 */
	private String image;
	/**商品进货价 */
	private BigDecimal pleased;
	/**商品销售价 */
	private BigDecimal salePrice;
	/**商品会员价 */
	private BigDecimal memberPrice;
	/** 商品规格*/
	private String specification;
	/** 商品颜色*/
	private String color;
	/**状态(1:上架/2：下架) */
	private Integer state;
	/**是否计入库存（1：是/2：否） */
	private Integer inventory;
	/**商品分类id */
	private BigInteger productTypeId;
	/**商品分类名称 */
	private String productType;
	/**店铺id */
	private BigInteger shopId;
	/**是否可用,软删除（1：可用/2：不可用） */
	private Integer canUse;
	
	public Product() {
		super();
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

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public BigDecimal getPleased() {
		return pleased;
	}

	public void setPleased(BigDecimal pleased) {
		this.pleased = pleased;
	}

	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	public BigDecimal getMemberPrice() {
		return memberPrice;
	}

	public void setMemberPrice(BigDecimal memberPrice) {
		this.memberPrice = memberPrice;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getInventory() {
		return inventory;
	}

	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}

	public BigInteger getProductTypeId() {
		return productTypeId;
	}

	public void setProductTypeId(BigInteger productTypeId) {
		this.productTypeId = productTypeId;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public BigInteger getShopId() {
		return shopId;
	}

	public void setShopId(BigInteger shopId) {
		this.shopId = shopId;
	}

	public Integer getCanUse() {
		return canUse;
	}

	public void setCanUse(Integer canUse) {
		this.canUse = canUse;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((barCode == null) ? 0 : barCode.hashCode());
		result = prime * result + ((canUse == null) ? 0 : canUse.hashCode());
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((image == null) ? 0 : image.hashCode());
		result = prime * result + ((inventory == null) ? 0 : inventory.hashCode());
		result = prime * result + ((memberPrice == null) ? 0 : memberPrice.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((pleased == null) ? 0 : pleased.hashCode());
		result = prime * result + ((productType == null) ? 0 : productType.hashCode());
		result = prime * result + ((productTypeId == null) ? 0 : productTypeId.hashCode());
		result = prime * result + ((salePrice == null) ? 0 : salePrice.hashCode());
		result = prime * result + ((shopId == null) ? 0 : shopId.hashCode());
		result = prime * result + ((specification == null) ? 0 : specification.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
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
		Product other = (Product) obj;
		if (barCode == null) {
			if (other.barCode != null)
				return false;
		} else if (!barCode.equals(other.barCode))
			return false;
		if (canUse == null) {
			if (other.canUse != null)
				return false;
		} else if (!canUse.equals(other.canUse))
			return false;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
			return false;
		if (inventory == null) {
			if (other.inventory != null)
				return false;
		} else if (!inventory.equals(other.inventory))
			return false;
		if (memberPrice == null) {
			if (other.memberPrice != null)
				return false;
		} else if (!memberPrice.equals(other.memberPrice))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (pleased == null) {
			if (other.pleased != null)
				return false;
		} else if (!pleased.equals(other.pleased))
			return false;
		if (productType == null) {
			if (other.productType != null)
				return false;
		} else if (!productType.equals(other.productType))
			return false;
		if (productTypeId == null) {
			if (other.productTypeId != null)
				return false;
		} else if (!productTypeId.equals(other.productTypeId))
			return false;
		if (salePrice == null) {
			if (other.salePrice != null)
				return false;
		} else if (!salePrice.equals(other.salePrice))
			return false;
		if (shopId == null) {
			if (other.shopId != null)
				return false;
		} else if (!shopId.equals(other.shopId))
			return false;
		if (specification == null) {
			if (other.specification != null)
				return false;
		} else if (!specification.equals(other.specification))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", barCode=" + barCode + ", image=" + image + ", pleased="
				+ pleased + ", salePrice=" + salePrice + ", memberPrice=" + memberPrice + ", specification="
				+ specification + ", color=" + color + ", state=" + state + ", inventory=" + inventory
				+ ", productTypeId=" + productTypeId + ", productType=" + productType + ", shopId=" + shopId
				+ ", canUse=" + canUse + "]";
	}
	
	
	
}
