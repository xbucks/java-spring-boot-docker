package com.bonsai.model;

import java.util.List;

public class PageDataDTO {
	int total;
	List<Image> pageData;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<Image> getPageData() {
		return pageData;
	}

	public void setPageData(List<Image> pageData) {
		this.pageData = pageData;
	}

}
