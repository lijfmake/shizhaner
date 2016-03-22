package com.huawei.loan.bean;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import com.huawei.exam.OperationResult;
import com.huawei.exam.ReturnCodeEnum;
import com.huawei.loan.IConstants;

/**
 * 贷款申请部门
 * @author lijf
 *
 */
public class LoanApplicationDep  extends Department implements TimeListener,IConstants{
	
	
	
	//private int time;
	private Queue<LoanRequest> loanRequestQueue;
	//private int minTime = 0;
	//private LoanRequestDAO loanRequestDAO;
	
	public LoanApplicationDep() {
		// TODO Auto-generated constructor stub
		loanRequestQueue = new ArrayBlockingQueue<LoanRequest>(10);
	}
	/**
     * 处理方法，调用此方法处理请求
     */
	@Override
    public void handleRequest(LoanRequest loanRequest,TimeEvent te) {

    	loanRequest.setStatusInfo("贷款申请成功待审查");
    	loanRequestDAO.applyToReview(loanRequest, te.getTime());
    	System.out.println("apply LoanId: "+loanRequest.getLoanRequestId()+" time: "+te.getTime());
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

			
			this.handleRequest(loanRequest, te);
			//每次向Review提交一个
			break;
			//}
		}
	}
	
	public boolean addApplyTest(LoanRequest lRequest){
		//申请队列已满 或申请不符合条件
		//System.out.println(loanRequestQueue.size());
		if(loanRequestQueue.size()==3||
				(!this.applyCheckTest(lRequest)))
		{
			return false;
		}
		loanRequestQueue.add(lRequest);
		//System.out.println("add Apply");
		//lRequest.setApplyInTime(1);
		loanRequestDAO.add(lRequest);
		return true;
		
	}
	
	
	public boolean applyCheckTest(LoanRequest lRequest)
	{
		return true;
	}
	
	
	public OperationResult applyLastCheck(LoanRequest loanRequest){
		//如果初始检查通过
		OperationResult operationResultCode = this.applyFirstCheck(loanRequest);
		if(operationResultCode.toString().equals(OperationResult.createReturnResult(ReturnCodeEnum.E009).toString()))
		{

			if(sysTime>loanRequest.getApplyInTime())
			{
				//E007:申请时间错误递减  本次申请时间比上次小
				return OperationResult.createReturnResult(ReturnCodeEnum.E007);
			}
			//System.out.println("add Apply");
			//E008:贷款处理正常，不允许修改初始信息 to be implemented
			LoanRequest loanRequestInDB= loanRequestDAO.queryById(loanRequest.getLoanRequestId());
			//如果之前有记录在DB里面
			if(loanRequestInDB!=null){
				/**
				 * to be implemented
				 *
				 *if(loanRequestInDB.getApplyOutTime()==0||
						loanRequestInDB.getReviewOutTime()==0
						)
				 */
				//status不包含待重新申请，不能重新申请
				/*贷款申请成功待审查
				贷款审查成功待发放
				贷款发放成功
				*/

				if(					
						//loanRequestInDB.getApplyOutTime()==0//还在申请队列中
						//||!loanRequestInDB.getStatusInfo().contains("待重新申请")
						//||(loanRequestInDB.getReleaseInTime()!=0&&loanRequestInDB.getReleaseOutTime()==0)
						(loanRequestInDB.getStatusInfo().equals("贷款申请成功待审查")//审查失败
								&&loanRequest.getApplyInTime()==loanRequestInDB.getApplyOutTime())
						||(loanRequestInDB.getStatusInfo().equals("贷款审查成功待发放")//发放失败
								&&loanRequest.getApplyInTime()==loanRequestInDB.getReviewOutTime())
						||loanRequestInDB.getStatusInfo().equals("贷款发放成功")
						)
				{
					return OperationResult.createReturnResult(ReturnCodeEnum.E008);
				}
			}
			return OperationResult.createReturnResult(ReturnCodeEnum.E009);
			
		}
		//添加成功
		return operationResultCode;
		
		
		
	}
	
	
	public void saveRequest(LoanRequest loanRequest){
		loanRequest.setStatusInfo("贷款申请成功待审查");
		loanRequestQueue.add(loanRequest);
		loanRequestDAO.add(loanRequest);
		//minTime = loanRequest.getApplyInTime();
	}
	
	
	public OperationResult applyFirstCheck(LoanRequest loanRequest)
	{
		//E002:贷款编号输入错误
		if(loanRequest.getLoanRequestId()<LOANID_MIN||loanRequest.getLoanRequestId()>LOANID_MAX){
			return OperationResult.createReturnResult(ReturnCodeEnum.E002); 
		}
		
		//E003:贷款人月收入输入错误
		if(loanRequest.getIncome()<INCOME_LOW_MIN||loanRequest.getIncome()>INCOME_HIGH_MAX){
			return OperationResult.createReturnResult(ReturnCodeEnum.E003); 
		}
		
		//贷款本金输入错误
		if(loanRequest.getMoney()<AMOUNT_MIN/10000||loanRequest.getMoney()>AMOUNT_HIGH_MAX/10000){
			return OperationResult.createReturnResult(ReturnCodeEnum.E004); 
		}
		
		//贷款本金输入错误
		if(loanRequest.getYear()<INSTALLMENTS_MIN/12||loanRequest.getYear()>INSTALLMENTS_HIGH_MAX/12){
			return OperationResult.createReturnResult(ReturnCodeEnum.E005); 
		}
		
		
		//申请时间错误
		if(loanRequest.getApplyInTime()<APPLY_TIME_MIN||loanRequest.getApplyInTime()>APPLY_TIME_MAX){
			return OperationResult.createReturnResult(ReturnCodeEnum.E006); 
		}
		
		return OperationResult.createReturnResult(ReturnCodeEnum.E009);
		
	}

	/*@Override
	public void handleRequest(LoanRequest loanRequest) {
		// TODO Auto-generated method stub
		
	}*/
	
	

	
	
}
