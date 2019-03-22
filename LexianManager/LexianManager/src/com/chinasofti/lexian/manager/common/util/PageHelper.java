package com.chinasofti.lexian.manager.common.util;

import java.util.List;

// 分页查询帮助类
public class PageHelper<T> {
	// 当前页码
	private transient int page = 1;
	// 每页返回行数
	private transient int rows = 15;
	// 总行数
	private int total;
	// 总页数
	private int pageCount;
	// 当前页数据集
	private List<T> results;
	// 是否返回总行数
	private transient boolean isgetTotal = true;

	public boolean isIsgetTotal() {
		return isgetTotal;
	}

	public void setIsgetTotal(boolean isgetTotal) {
		this.isgetTotal = isgetTotal;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
		this.pageCount = total % rows == 0 ? total / rows : total / rows + 1;
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
		builder.append("Page [page=").append(page).append(", rows=").append(rows).append(", results=")
				.append(results).append(", total=").append(total).append(", pageCount=").append(pageCount)
				.append("]");
		return builder.toString();
	}
}