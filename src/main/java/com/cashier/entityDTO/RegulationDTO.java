/**
 *
 * @Title: RegulationDTO.java
 * @Package com.cashier.entityDTO
 * @Description: TODO)
 * @author zhoujiaxin
 * @date 2019年6月21日
 */

package com.cashier.entityDTO;

import java.math.BigDecimal;

/**
 * 
 * @Description 
 *    
 * @author zhoujiaxin  
 * @createDate 2019年6月21日  
 */
public class RegulationDTO {
    
    /** 上限金额 */
    private Integer money;
    /** 减去金额 */
    private Integer reduceMoney;
    
    public Integer getMoney() {
        return money;
    }
    public void setMoney(Integer money) {
        this.money = money;
    }
    public Integer getReduceMoney() {
        return reduceMoney;
    }
    public void setReduceMoney(Integer reduceMoney) {
        this.reduceMoney = reduceMoney;
    }
    
    
}
