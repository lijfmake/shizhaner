package com.huawei.loan.bean;

public class LoanRequest {
	private int loanRequestNum;//������
	
	private int income;//������
	
	private int year;//��������
	
	private int money;//�����
	
	//private int nowTime;//����ʱ��
	
	private int applyInTime;//�������ʱ��
	
	private int applyOutTime;//�����ʱ��
	
	private int reviewInTime;//�����ʱ��
	
	private int reviewOutTime;//��˳�ʱ��
	
	private int releaseInTime;//������ʱ��
	
	private int releaseOutTime;//���ų�ʱ��
	
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
