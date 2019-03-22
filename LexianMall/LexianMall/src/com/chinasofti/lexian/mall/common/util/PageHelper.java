package com.chinasofti.lexian.mall.common.util;

import java.util.List;

// 分页数据查询辅助类
public class PageHelper<T> {
	// 当前页码
	private transient int pageNo = 1;
	// 每页显示条目数
	private transient int pageSize = 10;
	// 总条目数
	private int totalSize;
	// 总页数
	private int pageCount;
	// 当前页的数据集合
	private List<T> results;
	// 是否计算总条目数
	private transient boolean isgetTotal = true;

	public boolean isIsgetTotal() {
		return isgetTotal;
	}

	public void setIsgetTotal(boolean isgetTotal) {
		this.isgetTotal = isgetTotal;
	}
	
	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
		this.pageCount = totalSize % pageSize == 0 ? totalSize / pageSize : totalSize / pageSize + 1;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public void setResults(List<T> results) {
		this.results = results;
	}

	public List<T> getResults() {
		return results;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Page [pageNo=").append(pageNo).append(", pageSize=").append(pageSize).append(", results=")
				.append(results).append(", totalSize=").append(totalSize).append(", pageCount=").append(pageCount)
				.append("]");
		return builder.toString();
	}

}