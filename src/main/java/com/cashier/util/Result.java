package com.cashier.util;

import java.io.Serializable;

/**
 * 返回结果类
 * 
 * @author pangchong
 * @createDate 2018年9月12日 上午10:30
 */
public class Result implements Serializable {

	private static final long serialVersionUID = -5731242026868350433L;

	private boolean success;// 是否成功

	private String message;// 返回信息

	public Result(boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}