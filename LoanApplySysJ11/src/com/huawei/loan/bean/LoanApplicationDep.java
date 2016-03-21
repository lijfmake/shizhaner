package com.huawei.loan.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import com.sun.swing.internal.plaf.metal.resources.metal_zh_TW;

/**
 * 贷款申请部门
 * @author lijf
 *
 */
public class LoanApplicationDep extends Handler implements TimeListener{
	
	
	
	
	private Queue<LoanRequest> loanRequestQueue;
	private TimeEvent lastTimeEvent;
	
	public LoanApplicationDep() {
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
        if(getSuccessor() != null)
        {   
        	
        	//this.addApply(loanRequest);        	
            //System.out.println("贷款号："+loanRequest.getLoanRequestNum()+"申请通过 时间："+te.getTime());
            getSuccessor().handleRequest(loanRequest,te);            
        }else
        {            
            System.out.println("无贷款审查人员跟进");
        }
    }
    //时间增加处理
	@Override
	public void handleEvent(TimeEvent te) 
	{
		//System.out.println("apply time"+te.getTime());
		// TODO Auto-generated method stub
		while(!loanRequestQueue.isEmpty())
		{
			LoanRequest loanRequest = loanRequestQueue.poll();
			//if(applyCheck(loanRequest)){
			//te.timeIncrease();
			System.out.println("贷款号："+loanRequest.getLoanRequestNum()+"申请通过 时间："+te.getTime());
			this.handleRequest(loanRequest, te);
			//每次向Review提交一个
			break;
			//}
		}
	}
	
	public boolean addApply(LoanRequest lRequest){
		//申请队列已满 或申请不符合条件
		//System.out.println(loanRequestQueue.size());
		if(loanRequestQueue.size()==3||
				(!this.applyCheck(lRequest)))
		{
			return false;
		}
		loanRequestQueue.add(lRequest);
		//System.out.println("add Apply");
		return true;
		
	}
	
	
	public boolean applyCheck(LoanRequest lRequest)
	{
		return true;
	}

	/*@Override
	public void handleRequest(LoanRequest loanRequest) {
		// TODO Auto-generated method stub
		
	}*/
}
