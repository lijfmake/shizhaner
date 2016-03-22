package com.huawei.loan;

import com.huawei.exam.OperationResult;
import com.huawei.exam.ReturnCodeEnum;
import com.huawei.loan.bean.LoanApplicationDep;
import com.huawei.loan.bean.LoanReleaseDep;
import com.huawei.loan.bean.LoanRequest;
import com.huawei.loan.bean.LoanRequestDAO;
import com.huawei.loan.bean.LoanReviewDep;
import com.huawei.loan.bean.TimeEvent;
import com.huawei.loan.bean.TimeSource;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Title:�������Ը����Լ��������ڱ��������ӱ����ͺ���
 * </p>
 * <p/>
 * <p>
 * Description:�������ʵ����
 * </p>
 * <p/>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p/>
 * <p>
 * Company:
 * </p>
 * 
 * @author unknown
 * @version 1.0
 */
public class LoanImpl 
{
	private TimeSource timeSource;
	private LoanApplicationDep loanApplicationDep;
	private LoanReviewDep loanReviewDep;
	private LoanReleaseDep loanReleaseDep;
	private LoanRequestDAO loanRequestDAO;
	private Map<Integer, TimeEvent> timeEventMap;
	public final static int SYS_INIT_TIME = 0;
	
	/*
	 * �޲������캯��������������ģ�
	 */
	public LoanImpl() {
		timeSource = new TimeSource();     
        //�����������¼�Դ�����еǼǣ�     
        loanApplicationDep = new LoanApplicationDep();
        loanReviewDep = new LoanReviewDep();
        loanReleaseDep = new LoanReleaseDep();
        loanRequestDAO = new LoanRequestDAO();
        loanApplicationDep.setLoanRequestDAO(loanRequestDAO);
        loanReviewDep.setLoanRequestDAO(loanRequestDAO);
        loanReleaseDep.setLoanRequestDAO(loanRequestDAO);
        
        this.setSysTime(SYS_INIT_TIME);
        /*loanReviewDep.setSuccessor(loanReleaseDep);
        loanApplicationDep.setSuccessor(loanReviewDep);*/
        timeSource.addTimeListener(loanReleaseDep);
        timeSource.addTimeListener(loanReviewDep);
        timeSource.addTimeListener(loanApplicationDep);
        
        
        timeEventMap=new HashMap<Integer, TimeEvent>();
        for(int i = 1 ; i <= 24 ;i++){
        	timeEventMap.put(i, new TimeEvent(timeSource, i));
        }
        
	}

	/**
	 * ������Ҫʵ�ֵĽӿ� reboot�����ڣ�ϵͳ����/��ʼ������
	 * 
	 * @return OperationResult����������ֱ�ӷ��ش�������ö������
	 */
	public OperationResult opReboot() {
		// TODO ����������е��߼����˴���������ο���
		timeSource = new TimeSource();     
        //�����������¼�Դ�����еǼǣ�     
        loanApplicationDep = new LoanApplicationDep();
        loanReviewDep = new LoanReviewDep();
        loanReleaseDep = new LoanReleaseDep();
        loanRequestDAO = new LoanRequestDAO();
        loanApplicationDep.setLoanRequestDAO(loanRequestDAO);
        loanReviewDep.setLoanRequestDAO(loanRequestDAO);
        loanReleaseDep.setLoanRequestDAO(loanRequestDAO);
        this.setSysTime(SYS_INIT_TIME);
        /*loanReviewDep.setSuccessor(loanReleaseDep);
        loanApplicationDep.setSuccessor(loanReviewDep);*/
        timeSource.addTimeListener(loanReleaseDep);
        timeSource.addTimeListener(loanReviewDep);
        timeSource.addTimeListener(loanApplicationDep);
		return OperationResult.createReturnResult(ReturnCodeEnum.E001);

	}
	
	public void setSysTime(int sysTime){
		loanApplicationDep.setSysTime(sysTime);
		loanReviewDep.setSysTime(sysTime);
		loanReleaseDep.setSysTime(sysTime);
	}
	
	
	/**
	 * ������Ҫʵ�ֵĽӿ� request����ӿڣ���������Ϣ��ʼ��
	 * 
	 * @param paraArray
	 *            ����͹����Ĳ����б�Ϊsplit("-")�Ľ�� �������ͣ�������-������������-�����-��������-����ʱ��
	 * @return OperationResult����������ֱ�ӷ��ش�������ö������
	 */
	public OperationResult opReqeustLoan(int[] paraArray) {
		// TODO ����������е��߼����˴���������ο���
		LoanRequest loanRequest = new LoanRequest(paraArray);
		
		int endTime = paraArray[4];
		
		//System.out.println("endTime"+endTime);
		OperationResult operationResultCode = loanApplicationDep.applyLastCheck(loanRequest);
		
		
		
		
		if(operationResultCode.toString().equals(OperationResult.createReturnResult(ReturnCodeEnum.E009).toString())
				&&endTime>=loanApplicationDep.getSysTime())
		{
			this.timeEventTrigger(loanApplicationDep.getSysTime(), endTime);
			loanApplicationDep.saveRequest(loanRequest);
			this.setSysTime(endTime);
			//this.watchDBtest(endTime);
			//System.out.println("sysTime" +endTime);
		}
		
		
		return operationResultCode;
		
		//return OperationResult.createReturnResult(ReturnCodeEnum.E009);
	}
	
	
	public void timeEventTrigger(int startTime,int endTime){
		
		//int total = endTime - startTime;
		TimeEvent timeEvent;
		for(int i = startTime;i<=endTime ; i ++){
			timeEvent = timeEventMap.get(i);
			timeSource.notifyEvent(timeEvent);
			
		}
		
	}
	
	public void watchDBtest(int time ){
		
		System.out.println("================="+time+"=================");
		List<LoanRequest> list =loanRequestDAO.getLoanRequestList();
		for (LoanRequest loanRequest : list) {
			System.out.println(loanRequest);
		}
		System.out.println("================="+time+"=================");
	}
	
	
	/**
	 * ������Ҫʵ�ֵĽӿ� request����ӿڣ���������Ϣ��ʼ��
	 * 
	 * @param paraArray
	 *            �������ͣ�������-��x���·�-ס�������𻹿���-��ѯʱ��
	 * @return OperationResult����������ֱ�ӷ��ش�������ö������
	 */
	public OperationResult opList(int[] paraArray) {
		int loanRequestId=paraArray[0];
		int loanRequestMonth=paraArray[1];
		int houseFund=paraArray[2];
		int queryTime=paraArray[3];
		
		if(loanRequestId<1 || loanRequestId>10)
			return OperationResult.createReturnResult(ReturnCodeEnum.E002);
		else if(loanRequestDAO.queryById(loanRequestId)==null)
			return OperationResult.createReturnResult(ReturnCodeEnum.E010);
		else if(loanRequestMonth<1 || loanRequestMonth>240)
			return OperationResult.createReturnResult(ReturnCodeEnum.E011);
		else if(houseFund<0 || houseFund>100)
			return OperationResult.createReturnResult(ReturnCodeEnum.E012);
		else if(queryTime<1 || queryTime>24)
			return OperationResult.createReturnResult(ReturnCodeEnum.E013);
		else if(queryTime<loanRequestDAO.queryById(loanRequestId).getApplyInTime())
			return OperationResult.createReturnResult(ReturnCodeEnum.E014);
		
		if(queryTime<loanApplicationDep.getSysTime()){
			return OperationResult.createReturnResult(ReturnCodeEnum.E014);
		}
		
		if(queryTime==loanApplicationDep.getSysTime()){
			
		}
		this.timeEventTrigger(loanApplicationDep.getSysTime(), queryTime);
		this.setSysTime(queryTime);
		//��ѯ�ɹ�֮��
		LoanRequest loanrequest=loanRequestDAO.queryById(loanRequestId);
		double initYearRate;
		double actualYearRate;
		double actualMonthRate;
		
		if(loanrequest.getYear()<=10){
			initYearRate=0.024;
			if(loanrequest.getMoney()<=40)
				actualYearRate=initYearRate;
			else
				actualYearRate=initYearRate*0.5;
		}
		else{
			initYearRate=0.048;
			if(loanrequest.getMoney()<=40)
				actualYearRate=initYearRate;
			else
				actualYearRate=initYearRate*0.5;
		}
		actualMonthRate=actualYearRate/12;
		//if(queryTime>=loanrequest.getApplyOutTime() && queryTime<loanrequest.getReviewOutTime())
		if(loanrequest.getStatusInfo().equals("��������ɹ������"))
		{
			//һ����������ɹ������
			StringBuffer stringBuffer = new StringBuffer();
			//stringBuffer.append("\n");

			double[] calcResults = new double[] { 0, 0, 0 };
			// ��������
			DecimalFormat df = new DecimalFormat("0");
			// �����Ϣ
			stringBuffer.append("E015:��������").append(loanRequestId).append("��ϸ��Ϣ\r\n");
			stringBuffer.append("��������״̬:��������ɹ������").append("\r\n")
					.append("�����(��Ԫ):").append(loanrequest.getMoney()).append("\r\n")
					.append("��������:").append(12*loanrequest.getYear()).append("\r\n")
					.append("ʵ��������(ǧ��֮):").append(0).append("\r\n")
					.append("��").append(paraArray[1]).append("���·ݵĻ��Ϣ�ܽ��:")
					.append(df.format(calcResults[0])).append("\r\n")
					.append("�軹�Ϣ�ܽ��:").append(df.format(calcResults[1]))
					.append("\r\n").append("����˻��Ϣ�ܽ��:")
					.append(df.format(calcResults[2]));
			OperationResult res=new OperationResult(stringBuffer.toString());
			return res;
		}
		else if(loanrequest.getStatusInfo().equals("�������ɹ�������")){
			//�����������ɹ�������
			
			StringBuffer stringBuffer = new StringBuffer();
			//stringBuffer.append("\n");

			double[] calcResults = new double[] { 0, 0, 0 };
			// ��������
			DecimalFormat df = new DecimalFormat("0");
			// �����Ϣ
			stringBuffer.append("E015:��������").append(loanRequestId).append("��ϸ��Ϣ\r\n");
			stringBuffer.append("��������״̬:�������ɹ�������").append("\r\n")
					.append("�����(��Ԫ):").append(loanrequest.getMoney()).append("\r\n")
					.append("��������:").append(12*loanrequest.getYear()).append("\r\n")
					.append("ʵ��������(ǧ��֮):").append(0).append("\r\n")
					.append("��").append(paraArray[1]).append("���·ݵĻ��Ϣ�ܽ��:")
					.append(df.format(calcResults[0])).append("\r\n")
					.append("�軹�Ϣ�ܽ��:").append(df.format(calcResults[1]))
					.append("\r\n").append("����˻��Ϣ�ܽ��:")
					.append(df.format(calcResults[2]));
			OperationResult res=new OperationResult(stringBuffer.toString());
			return res;
		}
		else if(loanrequest.getStatusInfo().equals("����ųɹ�")){
			//��������ųɹ�
			
			StringBuffer stringBuffer = new StringBuffer();
			//stringBuffer.append("\n");
			
			double[] calcResults = new double[] { 0, 0, 0 };
			double monthFund=(double)loanrequest.getMoney()*10000/(12*loanrequest.getYear());
			double monthLixi=monthFund*(12*loanrequest.getYear()-loanRequestMonth+1)*actualMonthRate;
			calcResults[0]=monthFund+monthLixi;
			double lixi=(double)loanrequest.getMoney()*10000*actualMonthRate*(12*loanrequest.getYear()+1)/2;
			calcResults[1]=(double)loanrequest.getMoney()*10000+lixi;
			calcResults[2]=calcResults[1]-houseFund*10000*0.5;
			
			// ��������
			DecimalFormat df = new DecimalFormat("0");
			// �����Ϣ
			stringBuffer.append("E015:��������").append(loanRequestId).append("��ϸ��Ϣ\r\n");
			stringBuffer.append("��������״̬:����ųɹ�").append("\r\n")
					.append("�����(��Ԫ):").append(loanrequest.getMoney()).append("\r\n")
					.append("��������:").append(12*loanrequest.getYear()).append("\r\n")
					.append("ʵ��������(ǧ��֮):").append(df.format(actualMonthRate*1000)).append("\r\n")
					.append("��").append(paraArray[1]).append("���·ݵĻ��Ϣ�ܽ��:")
					.append(df.format(calcResults[0])).append("\r\n")
					.append("�軹�Ϣ�ܽ��:").append(df.format(calcResults[1]))
					.append("\r\n").append("����˻��Ϣ�ܽ��:")
					.append(df.format(calcResults[2]));
			OperationResult res=new OperationResult(stringBuffer.toString());
			return res;
			
		}
		else
		{
			//����������״̬
			StringBuffer stringBuffer = new StringBuffer();
			//stringBuffer.append("\n");

			double[] calcResults = new double[] { 0, 0, 0 };
			// ��������
			DecimalFormat df = new DecimalFormat("0");
			stringBuffer.append("E015:��������").append(loanRequestId).append("��ϸ��Ϣ\r\n");
			stringBuffer.append("��������״̬:"+loanrequest.getStatusInfo()).append("\r\n");
			// �����Ϣ
			stringBuffer.append("�����(��Ԫ):").append(loanrequest.getMoney()).append("\r\n")
			.append("��������:").append(12*loanrequest.getYear()).append("\r\n")
			.append("ʵ��������(ǧ��֮):").append(0).append("\r\n")
			.append("��").append(paraArray[1]).append("���·ݵĻ��Ϣ�ܽ��:")
			.append(df.format(calcResults[0])).append("\r\n")
			.append("�軹�Ϣ�ܽ��:").append(df.format(calcResults[1]))
			.append("\r\n").append("����˻��Ϣ�ܽ��:")
			.append(df.format(calcResults[2]));
			OperationResult res=new OperationResult(stringBuffer.toString());
			return res;
		}
	}
}
	

