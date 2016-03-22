package com.huawei.loan.bean;

import com.huawei.loan.IConstants;

/**
 * 贷款发放部门
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
     * 处理方法，调用此方法处理请求
     */
    @Override
    public void handleRequest(LoanRequest loanRequest,TimeEvent te) {
        
    	
    	//银行贷款份数
		if(releaseNum>=5){
			loanRequest.setStatusInfo("发放贷款系统能力不足,发放贷款失败,待重新申请");
			loanRequestDAO.release(loanRequest, te.getTime(),true);
			return;
		}
		
		//银行可用余额
		if(remainMoeny-loanRequest.getMoney()*10000<0){
			loanRequest.setStatusInfo("银行无可用贷款,发放贷款失败,待重新申请");
	    	loanRequestDAO.release(loanRequest, te.getTime(),true);
	    	//System.out.println(loanRequest.getLoanRequestId());
	    	return;
			
		}
		
    	if(releaseNum<5&&remainMoeny-loanRequest.getMoney()*10000>0){
    		releaseNum++;
    		remainMoeny-=loanRequest.getMoney()*10000;
    		System.out.println("remainMoeny: "+remainMoeny+" loanID: "+loanRequest.getLoanRequestId()+"time: "+te.getTime());
    		loanRequest.setStatusInfo("贷款发放成功");
			loanRequestDAO.release(loanRequest, te.getTime(),false);
			return;
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
		//if(lastTimeEvent!=null&&te.getTime()==this.getLastTimeEvent().getTime()+1)
		{
			LoanRequest loanRequest = loanRequestDAO.getReleaseRequest();
			if(loanRequest!=null){
				this.handleRequest(loanRequest, te);
			}
		}
		
	}
	


	


}
