/**
 *
 * @Title: ShopListDTO.java
 * @Package com.cashier.entityDTO
 * @Description: TODO)
 * @author zhoujiaxin
 * @date 2019年7月26日
 */

package com.cashier.entity;

import java.io.Serializable;

/**
 * 
 * @Description 在添加区域经理时，异步加载对应区域的ID，新建一个实体类
 *    
 * @author zhoujiaxin  
 * @createDate 2019年7月26日  
 */
public class ShopListDTO implements Serializable{
    /**
    * @Title: ShopListDTO.java
    * @Package com.cashier.entityDTO
    * @Description: TODO
    * @author Administrator
    * @date 2019年7月29日
    * @version V2.0
    */
    
    private static final long serialVersionUID = 1L;
    private Integer beginNumber;
    private Integer endNumber;
    /**
     * 
     */
    public ShopListDTO() {
        super();
        // TODO Auto-generated constructor stub
    }
    /**
     * @param beginNumber
     * @param endNumber
     */
    public ShopListDTO(Integer beginNumber, Integer endNumber) {
        super();
        this.beginNumber = beginNumber;
        this.endNumber = endNumber;
    }
    @Override
    public String toString() {
        return "ShopListDTO [beginNumber=" + beginNumber + ", endNumber=" + endNumber + "]";
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((beginNumber == null) ? 0 : beginNumber.hashCode());
        result = prime * result + ((endNumber == null) ? 0 : endNumber.hashCode());
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
        ShopListDTO other = (ShopListDTO) obj;
        if (beginNumber == null) {
            if (other.beginNumber != null)
                return false;
        } else if (!beginNumber.equals(other.beginNumber))
            return false;
        if (endNumber == null) {
            if (other.endNumber != null)
                return false;
        } else if (!endNumber.equals(other.endNumber))
            return false;
        return true;
    }
    public Integer getBeginNumber() {
        return beginNumber;
    }
    public void setBeginNumber(Integer beginNumber) {
        this.beginNumber = beginNumber;
    }
    public Integer getEndNumber() {
        return endNumber;
    }
    public void setEndNumber(Integer endNumber) {
        this.endNumber = endNumber;
    }
    
    

}
