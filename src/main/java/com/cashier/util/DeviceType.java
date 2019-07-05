package com.cashier.util;

/**
 *
 * @ClassName: DeviceType
 * 
 * @description 设备类型
 *
 * @author chenshuxian
 * @createDate 2019年2月27日
 */

public class DeviceType {
	/** 2:安卓客户端 */
	final Integer DEVICE_ANDROID = 2;
	/** 设备类型标识码 */
	public Integer code;
	/** 设备名称 */
	public String client;
	/** 描述 */
	public String descriprion;
	
	
	public DeviceType() {
		super();
	}


	public DeviceType(Integer code, String client, String descriprion) {
		super();
		this.code = code;
		this.client = client;
		this.descriprion = descriprion;
	}


	public Integer getCode() {
		return code;
	}


	public void setCode(Integer code) {
		this.code = code;
	}


	public String getClient() {
		return client;
	}


	public void setClient(String client) {
		this.client = client;
	}


	public String getDescriprion() {
		return descriprion;
	}


	public void setDescriprion(String descriprion) {
		this.descriprion = descriprion;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((client == null) ? 0 : client.hashCode());
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((descriprion == null) ? 0 : descriprion.hashCode());
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
		DeviceType other = (DeviceType) obj;
		if (client == null) {
			if (other.client != null)
				return false;
		} else if (!client.equals(other.client))
			return false;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (descriprion == null) {
			if (other.descriprion != null)
				return false;
		} else if (!descriprion.equals(other.descriprion))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "DeviceType [code=" + code + ", client=" + client + ", descriprion=" + descriprion + "]";
	}


	
	
	
}
