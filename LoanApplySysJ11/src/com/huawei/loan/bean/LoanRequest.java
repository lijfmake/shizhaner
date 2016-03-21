package com.huawei.loan.bean;

public class LoanRequest {
	private int loanRequestNum;//代码编号
	
	private int income;//月收入
	
	private int year;//贷款年限
	
	private int money;//贷款本金
	
	//private int nowTime;//现在时刻
	
	private int applyInTime;//申请进入时间
	
	private int applyOutTime;//申请出时间
	
	private int reviewInTime;//审核入时间
	
	private int reviewOutTime;//审核出时间
	
	private int releaseInTime;//发放入时间
	
	private int releaseOutTime;//发放出时间
	
	public int getLoanRequestNum() {
		return loanRequestNum;
	}
	public void setLoanRequestNum(int loanRequestNum) {
		this.loanRequestNum = loanRequestNum;
	}
	public int getIncome() {
		return income;
	}
	public void setIncome(int income) {
		this.income = income;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public int getApplyInTime() {
		return applyInTime;
	}
	public void setApplyInTime(int applyInTime) {
		this.applyInTime = applyInTime;
	}
	public int getApplyOutTime() {
		return applyOutTime;
	}
	public void setApplyOutTime(int applyOutTime) {
		this.applyOutTime = applyOutTime;
	}
	public int getReviewInTime() {
		return reviewInTime;
	}
	public void setReviewInTime(int reviewInTime) {
		this.reviewInTime = reviewInTime;
	}
	public int getReviewOutTime() {
		return reviewOutTime;
	}
	public void setReviewOutTime(int reviewOutTime) {
		this.reviewOutTime = reviewOutTime;
	}
	public int getReleaseInTime() {
		return releaseInTime;
	}
	public void setReleaseInTime(int releaseInTime) {
		this.releaseInTime = releaseInTime;
	}
	public int getReleaseOutTime() {
		return releaseOutTime;
	}
	public void setReleaseOutTime(int releaseOutTime) {
		this.releaseOutTime = releaseOutTime;
	}
	/*public int getNowTime() {
		return nowTime;
	}
	public void setNowTime(int nowTime) {
		this.nowTime = nowTime;
	}*/
	
}
