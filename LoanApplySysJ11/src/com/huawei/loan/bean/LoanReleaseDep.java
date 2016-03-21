package com.huawei.loan.bean;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 贷款发放部门
 * @author lijf
 *
 */
public class LoanReleaseDep extends Handler implements TimeListener{

	private TimeEvent lastTimeEvent;
	private Queue<LoanRequest> loanRequestQueue;
	public LoanReleaseDep() {
		// TODO Auto-generated constructor stub
		loanRequestQueue = new ArrayBlockingQueue<LoanRequest>(3);
	}
	/**
     * 处理方法，调用此方法处理请求
     */
    @Override
    public void handleRequest(LoanRequest loanRequest,TimeEvent te) {
        /**
         * 判断是否有后继的责任对象
         * 如果有，就转发请求给后继的责任对象
         * 如果没有，则处理请求
         */
    	System.out.println("贷款号："+loanRequest.getLoanRequestNum()+"加入Release工作任务 加入时间："+te.getTime());
    	this.loanRequestQueue.add(loanRequest);
        if(getSuccessor() != null)
        {   
        	
        	//this.setLoanReq(loanRequest); 
        	
        	
                       
        }else
        {            
            System.out.println("没发钱");
        }
    }
    //时间增加处理
	@Override
	public void handleEvent(TimeEvent te) {
		// TODO Auto-generated method stub
		//this.handleRequest(loanRequest);
		//System.out.println("review time "+te.getTime());
		//System.out.println("release time"+te.getTime());
		//te.timeIncrease();
		//if(loanRequestQueue.size()!=0&&lastTimeEvent!=null&&te.getTime()==this.getLastTimeEvent().getTime()+1)
		if(loanRequestQueue.size()!=0&&lastTimeEvent!=null&&te.getTime()==this.getLastTimeEvent().getTime()+1)
		{
			LoanRequest loanRequest = loanRequestQueue.poll();
			System.out.println("贷款号："+loanRequest.getLoanRequestNum()+"发放通过 时间："+te.getTime());
			//getSuccessor().handleRequest(loanRequest,te);
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
