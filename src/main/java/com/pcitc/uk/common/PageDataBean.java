package com.pcitc.uk.common;

import java.io.Serializable;
import java.util.List;

public class PageDataBean<T> implements Serializable {

	private static final long serialVersionUID = -4780915883155086324L;

	private int total;

	private List<T> list;

	public int getTotal() {

		return total;
	}

	public void setTotal(int total) {

		this.total = total;
	}

	public List<T> getList() {

		return list;
	}

	public void setList(List<T> list) {

		this.list = list;
	}
}
