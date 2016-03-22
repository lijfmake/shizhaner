package com.huawei.loan.bean;

import com.huawei.loan.IConstants;

/**
 * 贷款审查部门
 * @author lijf
 *
 */
public class LoanReviewDep extends Department implements TimeListener,IConstants
{ 
	//private TimeEvent lastTimeEvent;
	//private Queue<LoanRequest> loanRequestQueue;
	//private LoanRequestDAO loanRequestDAO;
	//private Map<Integer, LoanRequest> processingRequestMap = new HashMap<Integer, LoanRequest>();
	
	
	public LoanReviewDep() {
		// TODO Auto-generated constructor stub
		//loanRequestQueue = new ArrayBlockingQueue<LoanRequest>(3);
	}
	/**
     * 处理方法，调用此方法处理请求
     */
	@Override
    public void handleRequest(LoanRequest loanRequest,TimeEvent te) {
		System.out.println("review LoanId: "+loanRequest.getLoanRequestId()+" time: "+te.getTime());
    	//月收入小于5k
		if(loanRequest.getIncome()<=INCOME_LOW_MAX){
			//月收入小于5k的贷款超过50
			if(loanRequest.getMoney()>AMOUNT_LOW_MAX/10000){
				//重新申请
				loanRequest.setStatusInfo("贷款本金超出范围,审查贷款失败,待重新申请");
		    	loanRequestDAO.reviewToRelease(loanRequest, te.getTime(),true);
		    	return;
		    	
			}
			//年限
			if(loanRequest.getYear()>INSTALLMENTS_LOW_MAX/12){
				//重新申请
				loanRequest.setStatusInfo("贷款年限超出范围,审查贷款失败,待重新申请");
		    	loanRequestDAO.reviewToRelease(loanRequest, te.getTime(),true);
		    	return;
		    	
			}
			
		}
		//月收入大于5k
		if(loanRequest.getIncome()>INCOME_LOW_MAX){
		//月收入大于5k的贷款超过100
			if(loanRequest.getMoney()>AMOUNT_HIGH_MAX/10000){
				//return OperationResult.createReturnResult(ReturnCodeEnum.E004);
				//重新申请
				loanRequest.setStatusInfo("贷款本金超出范围,审查贷款失败,待重新申请");
		    	loanRequestDAO.reviewToRelease(loanRequest, te.getTime(),true);
		    	return;
		    	
			}
			//年限
			if(loanRequest.getYear()>INSTALLMENTS_HIGH_MAX/12){
				//return OperationResult.createReturnResult(ReturnCodeEnum.E005);
				//重新申请
				loanRequest.setStatusInfo("贷款年限超出范围,审查贷款失败,待重新申请");
		    	loanRequestDAO.reviewToRelease(loanRequest, te.getTime(),true);
		    	return;
		    	
			}
		}
		loanRequest.setStatusInfo("贷款审查成功待发放");
    	loanRequestDAO.reviewToRelease(loanRequest, te.getTime(),false);
    	
		
    	
    }
    //时间增加处理
	@Override
	public void handleEvent(TimeEvent te) {
		// TODO Auto-generated method stub
		{
			//从DAO里取出要review的request
			//LoanRequest loanRequest = loanRequestQueue.poll();			
			LoanRequest loanRequest = loanRequestDAO.getReviewRequest();
			if(loanRequest!=null){
				
				this.handleRequest(loanRequest, te);
				//loanRequestDAO.reviewToRelease(loanRequest, te.getTime());
			}
			//getSuccessor().handleRequest(loanRequest,te);
			
		}
		//this.setLastTimeEvent(te);
	}
	
/*	public TimeEvent getLastTimeEvent() {
		return lastTimeEvent;
	}
	public void setLastTimeEvent(TimeEvent lastTimeEvent) {
		this.lastTimeEvent = lastTimeEvent;
	}*/

}
