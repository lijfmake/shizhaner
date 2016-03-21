package com.huawei.loan.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import com.sun.swing.internal.plaf.metal.resources.metal_zh_TW;

/**
 * �������벿��
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
        	
        	//this.addApply(loanRequest);        	
            //System.out.println("����ţ�"+loanRequest.getLoanRequestNum()+"����ͨ�� ʱ�䣺"+te.getTime());
            getSuccessor().handleRequest(loanRequest,te);            
        }else
        {            
            System.out.println("�޴��������Ա����");
        }
    }
    //ʱ�����Ӵ���
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
			System.out.println("����ţ�"+loanRequest.getLoanRequestNum()+"����ͨ�� ʱ�䣺"+te.getTime());
			this.handleRequest(loanRequest, te);
			//ÿ����Review�ύһ��
			break;
			//}
		}
	}
	
	public boolean addApply(LoanRequest lRequest){
		//����������� �����벻��������
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
