package com.cashier.entity;

import java.io.Serializable;
import java.math.BigInteger;

/**
 *
 * @ClassName: DmArea

 * @description 地区表实体类
 *
 * @author linhongyu
 * @createDate 2018年11月9日
 */

public class DmArea implements Serializable {
	/** 序列号 */
	private static final long serialVersionUID = 1L;
	/** 地区id */
	private Integer area_id;
	/** 地区名称 */
	private String title;
	/** 父级地区ID */
	private Integer pid;
	/** 排序值 */
	private Integer sort;
	public Integer getArea_id() {
		return area_id;
	}
	public void setArea_id(Integer area_id) {
		this.area_id = area_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "DmArea [area_id=" + area_id + ", title=" + title + ", pid=" + pid + ", sort=" + sort + "]";
	}
	
}
