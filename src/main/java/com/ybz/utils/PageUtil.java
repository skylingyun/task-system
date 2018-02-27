package com.ybz.utils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 分页工具类
 *
 * @author zhangybt
 * @email zhangybt@yonyou.com
 * @date 2017年8月1日 下午12:59:00
 */
public class PageUtil implements Serializable {
	private static final long serialVersionUID = 1L;
	//列表数据
	private List<?> list;
	//条件列表
	private Map<String,Integer> pagination;


	/**
	 * 分页
	 * @param list        列表数据
	 * @param pagination  总记录数
	 */
	public PageUtil(List<?> list, Map<String,Integer> pagination) {
		this.list = list;
		this.pagination = pagination;
	}

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

	public Map<String, Integer> getPagination() {
		return pagination;
	}

	public void setPagination(Map<String, Integer> pagination) {
		this.pagination = pagination;
	}
}
