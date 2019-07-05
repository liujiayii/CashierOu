package com.cashier.entity;
/**
 * @author 
 * @return
 * @author
 */
/**
 *
 * @ClassName: CustomException

 * @description 
 *
 * @author 
 * @createDate 2018年10月18日
 */

@SuppressWarnings("serial")
public class CustomException extends Exception {
    private String msg;

    /**
     * @param msg
     */
    public CustomException(String msg) {
        super();
        this.msg = msg;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    
}
