package com.huawei.loan.bean;

import com.huawei.loan.IConstants;

/**
 * ������鲿��
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
     * �����������ô˷�����������
     */
	@Override
    public void handleRequest(LoanRequest loanRequest,TimeEvent te) {
		System.out.println("review LoanId: "+loanRequest.getLoanRequestId()+" time: "+te.getTime());
    	//������С��5k
		if(loanRequest.getIncome()<=INCOME_LOW_MAX){
			//������С��5k�Ĵ����50
			if(loanRequest.getMoney()>AMOUNT_LOW_MAX/10000){
				//��������
				loanRequest.setStatusInfo("����𳬳���Χ,������ʧ��,����������");
		    	loanRequestDAO.reviewToRelease(loanRequest, te.getTime(),true);
		    	return;
		    	
			}
			//����
			if(loanRequest.getYear()>INSTALLMENTS_LOW_MAX/12){
				//��������
				loanRequest.setStatusInfo("�������޳�����Χ,������ʧ��,����������");
		    	loanRequestDAO.reviewToRelease(loanRequest, te.getTime(),true);
		    	return;
		    	
			}
			
		}
		//���������5k
		if(loanRequest.getIncome()>INCOME_LOW_MAX){
		//���������5k�Ĵ����100
			if(loanRequest.getMoney()>AMOUNT_HIGH_MAX/10000){
				//return OperationResult.createReturnResult(ReturnCodeEnum.E004);
				//��������
				loanRequest.setStatusInfo("����𳬳���Χ,������ʧ��,����������");
		    	loanRequestDAO.reviewToRelease(loanRequest, te.getTime(),true);
		    	return;
		    	
			}
			//����
			if(loanRequest.getYear()>INSTALLMENTS_HIGH_MAX/12){
				//return OperationResult.createReturnResult(ReturnCodeEnum.E005);
				//��������
				loanRequest.setStatusInfo("�������޳�����Χ,������ʧ��,����������");
		    	loanRequestDAO.reviewToRelease(loanRequest, te.getTime(),true);
		    	return;
		    	
			}
		}
		loanRequest.setStatusInfo("�������ɹ�������");
    	loanRequestDAO.reviewToRelease(loanRequest, te.getTime(),false);
    	
		
    	
    }
    //ʱ�����Ӵ���
	@Override
	public void handleEvent(TimeEvent te) {
		// TODO Auto-generated method stub
		{
			//��DAO��ȡ��Ҫreview��request
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
