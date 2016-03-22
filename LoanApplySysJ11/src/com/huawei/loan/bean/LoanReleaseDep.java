package com.huawei.loan.bean;

import com.huawei.loan.IConstants;

/**
 * ����Ų���
 * @author lijf
 *
 */
public class LoanReleaseDep extends Department implements TimeListener,IConstants{

	//private TimeEvent lastTimeEvent;
	//private Queue<LoanRequest> loanRequestQueue;
	 
	private int remainMoeny = BANK_LOAN_AMOUNT_LIMIT;
	private int releaseNum = 0;
	public LoanReleaseDep() {
		// TODO Auto-generated constructor stub
		//loanRequestQueue = new ArrayBlockingQueue<LoanRequest>(3);
	}
	/**
     * �����������ô˷�����������
     */
    @Override
    public void handleRequest(LoanRequest loanRequest,TimeEvent te) {
        
    	
    	//���д������
		if(releaseNum>=5){
			loanRequest.setStatusInfo("���Ŵ���ϵͳ��������,���Ŵ���ʧ��,����������");
			loanRequestDAO.release(loanRequest, te.getTime(),true);
			return;
		}
		
		//���п������
		if(remainMoeny-loanRequest.getMoney()*10000<0){
			loanRequest.setStatusInfo("�����޿��ô���,���Ŵ���ʧ��,����������");
	    	loanRequestDAO.release(loanRequest, te.getTime(),true);
	    	//System.out.println(loanRequest.getLoanRequestId());
	    	return;
			
		}
		
    	if(releaseNum<5&&remainMoeny-loanRequest.getMoney()*10000>0){
    		releaseNum++;
    		remainMoeny-=loanRequest.getMoney()*10000;
    		System.out.println("remainMoeny: "+remainMoeny+" loanID: "+loanRequest.getLoanRequestId()+"time: "+te.getTime());
    		loanRequest.setStatusInfo("����ųɹ�");
			loanRequestDAO.release(loanRequest, te.getTime(),false);
			return;
    	}
    	
    }
    //ʱ�����Ӵ���
	@Override
	public void handleEvent(TimeEvent te) {
		// TODO Auto-generated method stub
		//this.handleRequest(loanRequest);
		//System.out.println("review time "+te.getTime());
		//System.out.println("release time"+te.getTime());
		//te.timeIncrease();
		//if(loanRequestQueue.size()!=0&&lastTimeEvent!=null&&te.getTime()==this.getLastTimeEvent().getTime()+1)
		//if(lastTimeEvent!=null&&te.getTime()==this.getLastTimeEvent().getTime()+1)
		{
			LoanRequest loanRequest = loanRequestDAO.getReleaseRequest();
			if(loanRequest!=null){
				this.handleRequest(loanRequest, te);
			}
		}
		
	}
	


	


}
