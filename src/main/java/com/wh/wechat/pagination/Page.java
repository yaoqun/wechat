package com.wh.wechat.pagination;

import com.wh.wechat.Command;

import java.util.List;

/**
 * Created by Whiker on 2016/1/25.
 */
public class Page<T> {

	public static final int ItemNumOfEachPage = 3;  // 一页最大包含的条目数

	private int totalPageNum;  // 总页数
	private int currPage;      // 当前页是第几页, 从1开始

	private int itemStart;     // 当前页的条目indexStart

	private List<T> itemList;

	public void setTotalPageNum(int totalItemNum) {
		if (totalItemNum < 0) {
			totalItemNum = 0;
		}
		int pageNum = totalItemNum / ItemNumOfEachPage;
		if (pageNum * ItemNumOfEachPage < totalItemNum) {
			++pageNum;
		}
		this.totalPageNum = pageNum;
	}

	public int getTotalPageNum() {
		return totalPageNum;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}

	public int getCurrPage() {
		if (currPage <= 0 || totalPageNum <= 0) {
			currPage = 1;
		}
		else if (currPage > totalPageNum) {
			currPage = totalPageNum;
		}
		return currPage;
	}

	public int getItemStart() {
		int pageI = getCurrPage();
		return (pageI-1) * ItemNumOfEachPage;
	}

	public void setItemList(List<T> itemList) {
		this.itemList = itemList;
	}

	public List<T> getItemList() {
		return this.itemList;
	}

}
