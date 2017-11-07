package com.Pcavers.bean;

public class Page {
	private int pageIndex; //当前页数
	private int startRecord; //当前页起始记录数
	private int count;     //每页显示的条数
	private int counts;    //总记录数
	private int coutPage;  //总页数
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getStartRecord() {
		return startRecord;
	}
	public void setStartRecord(int startRecord) {
		this.startRecord = startRecord;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getCounts() {
		return counts;
	}
	public void setCounts(int counts) {
		this.counts = counts;
	}
	public int getCoutPage() {
		return coutPage;
	}
	public void setCoutPage(int coutPage) {
		this.coutPage = coutPage;
	}
	public Page(int pageIndex, int startRecord, int count, int counts, int coutPage) {
		super();
		this.pageIndex = pageIndex;
		this.startRecord = startRecord;
		this.count = count;
		this.counts = counts;
		this.coutPage = coutPage;
	}
	
	
}
