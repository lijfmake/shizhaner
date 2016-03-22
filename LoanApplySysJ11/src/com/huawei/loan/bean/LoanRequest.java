package com.huawei.loan.bean;

public class LoanRequest {
	private int loanRequestId;//������
	
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
	
	private boolean isReApply;
	
	private String statusInfo;
	
	//private boolean isPassRelease;
	public LoanRequest(){
		
	}
	//������-������������-�����-��������-����ʱ��
	public LoanRequest(int[] array){
		this.loanRequestId = array[0];
		this.income = array[1];
		this.money = array[2];
		this.year = array[3];
		this.applyInTime = array[4];
	}
	
	public String toString(){
		String s="loanRequestId: "+loanRequestId+" income: "+income+" year: "+year+" money: "+money+" applyInTime: "
				+applyInTime+" applyOutTime: "+applyOutTime+" reviewInTime: "+reviewInTime+" reviewOutTime: "+reviewOutTime
				+" releaseInTime: "+releaseInTime+" releaseOutTime: "+releaseOutTime+" isReApply: "+isReApply
				+" statusInfo: "+statusInfo
				;
		return s;
		
	}
	public int getLoanRequestId() {
		return loanRequestId;
	}
	public void setLoanRequestId(int loanRequestId) {
		this.loanRequestId = loanRequestId;
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
	public boolean isReApply() {
		return isReApply;
	}
	public void setReApply(boolean isReApply) {
		this.isReApply = isReApply;
	}
	public String getStatusInfo() {
		return statusInfo;
	}
	public void setStatusInfo(String statusInfo) {
		this.statusInfo = statusInfo;
	}


	
	
	
	
}
