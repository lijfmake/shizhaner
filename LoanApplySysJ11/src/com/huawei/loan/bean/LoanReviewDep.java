package com.huawei.loan.bean;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * ������鲿��
 * @author lijf
 *
 */
public class LoanReviewDep extends Handler implements TimeListener
{ 
	private TimeEvent lastTimeEvent;
	private Queue<LoanRequest> loanRequestQueue;
	public LoanReviewDep() {
		// TODO Auto-generated constructor stub
		loanRequestQueue = new ArrayBlockingQueue<LoanRequest>(3);
	}
	/**
     * �����������ô˷�����������
     */
    @Override
    public void handleRequest(LoanRequest loanRequest,TimeEvent te) {
        /**
         * �ж��Ƿ��к�̵����ζ���
         * ����У���ת���������̵����ζ���
         * ���û�У���������
         */
        if(getSuccessor() != null)
        {   
        	
        	//this.setLoanReq(loanRequest); 
        	System.out.println("����ţ�"+loanRequest.getLoanRequestNum()+"����Review�������� ����ʱ�䣺"+te.getTime());
        	this.loanRequestQueue.add(loanRequest);
        	
                       
        }else
        {            
            System.out.println("�޴����Ų����");
        }
    }
    //ʱ�����Ӵ���
	@Override
	public void handleEvent(TimeEvent te) {
		// TODO Auto-generated method stub
		//this.handleRequest(loanRequest);
		//System.out.println("review time "+te.getTime());
		//System.out.println("review time"+te.getTime());
		//te.timeIncrease();
		//if(loanRequestQueue.size()!=0&&lastTimeEvent!=null&&te.getTime()==this.getLastTimeEvent().getTime()+1)
		if(loanRequestQueue.size()!=0&&lastTimeEvent!=null&&te.getTime()==this.getLastTimeEvent().getTime()+1)
		{
			LoanRequest loanRequest = loanRequestQueue.poll();
			System.out.println("����ţ�"+loanRequest.getLoanRequestNum()+"���ͨ�� ʱ�䣺"+te.getTime());
			getSuccessor().handleRequest(loanRequest,te);
			
		}
		this.setLastTimeEvent(te);
	}
	
	public TimeEvent getLastTimeEvent() {
		return lastTimeEvent;
	}
	public void setLastTimeEvent(TimeEvent lastTimeEvent) {
		this.lastTimeEvent = lastTimeEvent;
	}
}
