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
 * Title:考生可以根据自己的需求在本类中增加变量和函数
 * </p>
 * <p/>
 * <p>
 * Description:贷款操作实现类
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
	 * 无参数构造函数，考生无须关心；
	 */
	public LoanImpl() {
		timeSource = new TimeSource();     
        //将监听器在事件源对象中登记：     
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
	 * 考生需要实现的接口 reboot命令借口：系统重启/初始化操作
	 * 
	 * @return OperationResult：处理结果，直接返回处理结果的枚举类型
	 */
	public OperationResult opReboot() {
		// TODO 考生完成其中的逻辑，此处代码仅供参考。
		timeSource = new TimeSource();     
        //将监听器在事件源对象中登记：     
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
	 * 考生需要实现的接口 request命令接口：贷款人信息初始化
	 * 
	 * @param paraArray
	 *            命令发送过来的参数列表，为split("-")的结果 参数类型：贷款编号-贷款人月收入-贷款本金-贷款年限-申请时间
	 * @return OperationResult：处理结果，直接返回处理结果的枚举类型
	 */
	public OperationResult opReqeustLoan(int[] paraArray) {
		// TODO 考生完成其中的逻辑，此处代码仅供参考。
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
	 * 考生需要实现的接口 request命令接口：贷款人信息初始化
	 * 
	 * @param paraArray
	 *            参数类型：贷款编号-第x个月份-住房公积金还款金额-查询时间
	 * @return OperationResult：处理结果，直接返回处理结果的枚举类型
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
		//查询成功之后
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
		if(loanrequest.getStatusInfo().equals("贷款申请成功待审查"))
		{
			//一：贷款申请成功待审查
			StringBuffer stringBuffer = new StringBuffer();
			//stringBuffer.append("\n");

			double[] calcResults = new double[] { 0, 0, 0 };
			// 四舍五入
			DecimalFormat df = new DecimalFormat("0");
			// 金额信息
			stringBuffer.append("E015:贷款申请").append(loanRequestId).append("详细信息\r\n");
			stringBuffer.append("贷款申请状态:贷款申请成功待审查").append("\r\n")
					.append("贷款本金(万元):").append(loanrequest.getMoney()).append("\r\n")
					.append("还款期数:").append(12*loanrequest.getYear()).append("\r\n")
					.append("实际月利率(千分之):").append(0).append("\r\n")
					.append("第").append(paraArray[1]).append("个月份的还款本息总金额:")
					.append(df.format(calcResults[0])).append("\r\n")
					.append("需还款本息总金额:").append(df.format(calcResults[1]))
					.append("\r\n").append("需个人还款本息总金额:")
					.append(df.format(calcResults[2]));
			OperationResult res=new OperationResult(stringBuffer.toString());
			return res;
		}
		else if(loanrequest.getStatusInfo().equals("贷款审查成功待发放")){
			//二：贷款审查成功待发放
			
			StringBuffer stringBuffer = new StringBuffer();
			//stringBuffer.append("\n");

			double[] calcResults = new double[] { 0, 0, 0 };
			// 四舍五入
			DecimalFormat df = new DecimalFormat("0");
			// 金额信息
			stringBuffer.append("E015:贷款申请").append(loanRequestId).append("详细信息\r\n");
			stringBuffer.append("贷款申请状态:贷款审查成功待发放").append("\r\n")
					.append("贷款本金(万元):").append(loanrequest.getMoney()).append("\r\n")
					.append("还款期数:").append(12*loanrequest.getYear()).append("\r\n")
					.append("实际月利率(千分之):").append(0).append("\r\n")
					.append("第").append(paraArray[1]).append("个月份的还款本息总金额:")
					.append(df.format(calcResults[0])).append("\r\n")
					.append("需还款本息总金额:").append(df.format(calcResults[1]))
					.append("\r\n").append("需个人还款本息总金额:")
					.append(df.format(calcResults[2]));
			OperationResult res=new OperationResult(stringBuffer.toString());
			return res;
		}
		else if(loanrequest.getStatusInfo().equals("贷款发放成功")){
			//三：贷款发放成功
			
			StringBuffer stringBuffer = new StringBuffer();
			//stringBuffer.append("\n");
			
			double[] calcResults = new double[] { 0, 0, 0 };
			double monthFund=(double)loanrequest.getMoney()*10000/(12*loanrequest.getYear());
			double monthLixi=monthFund*(12*loanrequest.getYear()-loanRequestMonth+1)*actualMonthRate;
			calcResults[0]=monthFund+monthLixi;
			double lixi=(double)loanrequest.getMoney()*10000*actualMonthRate*(12*loanrequest.getYear()+1)/2;
			calcResults[1]=(double)loanrequest.getMoney()*10000+lixi;
			calcResults[2]=calcResults[1]-houseFund*10000*0.5;
			
			// 四舍五入
			DecimalFormat df = new DecimalFormat("0");
			// 金额信息
			stringBuffer.append("E015:贷款申请").append(loanRequestId).append("详细信息\r\n");
			stringBuffer.append("贷款申请状态:贷款发放成功").append("\r\n")
					.append("贷款本金(万元):").append(loanrequest.getMoney()).append("\r\n")
					.append("还款期数:").append(12*loanrequest.getYear()).append("\r\n")
					.append("实际月利率(千分之):").append(df.format(actualMonthRate*1000)).append("\r\n")
					.append("第").append(paraArray[1]).append("个月份的还款本息总金额:")
					.append(df.format(calcResults[0])).append("\r\n")
					.append("需还款本息总金额:").append(df.format(calcResults[1]))
					.append("\r\n").append("需个人还款本息总金额:")
					.append(df.format(calcResults[2]));
			OperationResult res=new OperationResult(stringBuffer.toString());
			return res;
			
		}
		else
		{
			//待重新申请状态
			StringBuffer stringBuffer = new StringBuffer();
			//stringBuffer.append("\n");

			double[] calcResults = new double[] { 0, 0, 0 };
			// 四舍五入
			DecimalFormat df = new DecimalFormat("0");
			stringBuffer.append("E015:贷款申请").append(loanRequestId).append("详细信息\r\n");
			stringBuffer.append("贷款申请状态:"+loanrequest.getStatusInfo()).append("\r\n");
			// 金额信息
			stringBuffer.append("贷款本金(万元):").append(loanrequest.getMoney()).append("\r\n")
			.append("还款期数:").append(12*loanrequest.getYear()).append("\r\n")
			.append("实际月利率(千分之):").append(0).append("\r\n")
			.append("第").append(paraArray[1]).append("个月份的还款本息总金额:")
			.append(df.format(calcResults[0])).append("\r\n")
			.append("需还款本息总金额:").append(df.format(calcResults[1]))
			.append("\r\n").append("需个人还款本息总金额:")
			.append(df.format(calcResults[2]));
			OperationResult res=new OperationResult(stringBuffer.toString());
			return res;
		}
	}
}
	

