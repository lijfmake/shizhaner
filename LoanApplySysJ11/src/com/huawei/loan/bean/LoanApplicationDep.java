package com.huawei.loan.bean;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import com.huawei.exam.OperationResult;
import com.huawei.exam.ReturnCodeEnum;
import com.huawei.loan.IConstants;

/**
 * �������벿��
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
     * �����������ô˷�����������
     */
	@Override
    public void handleRequest(LoanRequest loanRequest,TimeEvent te) {

    	loanRequest.setStatusInfo("��������ɹ������");
    	loanRequestDAO.applyToReview(loanRequest, te.getTime());
    	System.out.println("apply LoanId: "+loanRequest.getLoanRequestId()+" time: "+te.getTime());
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

			
			this.handleRequest(loanRequest, te);
			//ÿ����Review�ύһ��
			break;
			//}
		}
	}
	
	public boolean addApplyTest(LoanRequest lRequest){
		//����������� �����벻��������
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
		//�����ʼ���ͨ��
		OperationResult operationResultCode = this.applyFirstCheck(loanRequest);
		if(operationResultCode.toString().equals(OperationResult.createReturnResult(ReturnCodeEnum.E009).toString()))
		{

			if(sysTime>loanRequest.getApplyInTime())
			{
				//E007:����ʱ�����ݼ�  ��������ʱ����ϴ�С
				return OperationResult.createReturnResult(ReturnCodeEnum.E007);
			}
			//System.out.println("add Apply");
			//E008:������������������޸ĳ�ʼ��Ϣ to be implemented
			LoanRequest loanRequestInDB= loanRequestDAO.queryById(loanRequest.getLoanRequestId());
			//���֮ǰ�м�¼��DB����
			if(loanRequestInDB!=null){
				/**
				 * to be implemented
				 *
				 *if(loanRequestInDB.getApplyOutTime()==0||
						loanRequestInDB.getReviewOutTime()==0
						)
				 */
				//status���������������룬������������
				/*��������ɹ������
				�������ɹ�������
				����ųɹ�
				*/

				if(					
						//loanRequestInDB.getApplyOutTime()==0//�������������
						//||!loanRequestInDB.getStatusInfo().contains("����������")
						//||(loanRequestInDB.getReleaseInTime()!=0&&loanRequestInDB.getReleaseOutTime()==0)
						(loanRequestInDB.getStatusInfo().equals("��������ɹ������")//���ʧ��
								&&loanRequest.getApplyInTime()==loanRequestInDB.getApplyOutTime())
						||(loanRequestInDB.getStatusInfo().equals("�������ɹ�������")//����ʧ��
								&&loanRequest.getApplyInTime()==loanRequestInDB.getReviewOutTime())
						||loanRequestInDB.getStatusInfo().equals("����ųɹ�")
						)
				{
					return OperationResult.createReturnResult(ReturnCodeEnum.E008);
				}
			}
			return OperationResult.createReturnResult(ReturnCodeEnum.E009);
			
		}
		//��ӳɹ�
		return operationResultCode;
		
		
		
	}
	
	
	public void saveRequest(LoanRequest loanRequest){
		loanRequest.setStatusInfo("��������ɹ������");
		loanRequestQueue.add(loanRequest);
		loanRequestDAO.add(loanRequest);
		//minTime = loanRequest.getApplyInTime();
	}
	
	
	public OperationResult applyFirstCheck(LoanRequest loanRequest)
	{
		//E002:�������������
		if(loanRequest.getLoanRequestId()<LOANID_MIN||loanRequest.getLoanRequestId()>LOANID_MAX){
			return OperationResult.createReturnResult(ReturnCodeEnum.E002); 
		}
		
		//E003:�������������������
		if(loanRequest.getIncome()<INCOME_LOW_MIN||loanRequest.getIncome()>INCOME_HIGH_MAX){
			return OperationResult.createReturnResult(ReturnCodeEnum.E003); 
		}
		
		//������������
		if(loanRequest.getMoney()<AMOUNT_MIN/10000||loanRequest.getMoney()>AMOUNT_HIGH_MAX/10000){
			return OperationResult.createReturnResult(ReturnCodeEnum.E004); 
		}
		
		//������������
		if(loanRequest.getYear()<INSTALLMENTS_MIN/12||loanRequest.getYear()>INSTALLMENTS_HIGH_MAX/12){
			return OperationResult.createReturnResult(ReturnCodeEnum.E005); 
		}
		
		
		//����ʱ�����
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
