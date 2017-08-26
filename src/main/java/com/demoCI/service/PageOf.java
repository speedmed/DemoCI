/**
 * 
 */
package com.demoCI.service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Med
 * 25 août 2017
 */
public class PageOf<T> {

	private List<T> contents = new ArrayList<T>();;
	private int page;
	private int size;
	private long totalValues;
	private int totalPages;
	
	public PageOf() {
		super();
	}

	public PageOf(List<T> contents, int page, int size, long totalValues, int totalPages) {
		super();
		this.contents = contents;
		this.page = page;
		this.size = size;
		this.totalValues = totalValues;
		this.totalPages = totalPages;
	}

	public List<T> getContents() {
		return contents;
	}

	public void setContents(List<T> contents) {
		this.contents = contents;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public long getTotalValues() {
		return totalValues;
	}

	public void setTotalValues(long totalValues) {
		this.totalValues = totalValues;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	
}
