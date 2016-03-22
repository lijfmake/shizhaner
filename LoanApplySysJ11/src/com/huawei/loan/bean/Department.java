package com.huawei.loan.bean;


public abstract class Department {
    

	protected int sysTime;//系统时间
	protected LoanRequestDAO loanRequestDAO;
	public abstract void handleRequest(LoanRequest loanRequest,TimeEvent te);
	
	public LoanRequestDAO getLoanRequestDAO() {
		return loanRequestDAO;
	}
	public void setLoanRequestDAO(LoanRequestDAO loanRequestDAO) {
		this.loanRequestDAO = loanRequestDAO;
	}

	public int getSysTime() {
		return sysTime;
	}

	public void setSysTime(int sysTime) {
		this.sysTime = sysTime;
	}

    
}