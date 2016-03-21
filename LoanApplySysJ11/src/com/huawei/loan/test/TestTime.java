package com.huawei.loan.test;

import com.huawei.loan.bean.LoanApplicationDep;
import com.huawei.loan.bean.LoanReleaseDep;
import com.huawei.loan.bean.LoanRequest;
import com.huawei.loan.bean.LoanReviewDep;
import com.huawei.loan.bean.TimeEvent;
import com.huawei.loan.bean.TimeSource;

public class TestTime {     
	   TimeSource timeSource;     
	   public TestTime(){     
	      try{     
	         timeSource = new TimeSource();     
	         //将监听器在事件源对象中登记：     
	         LoanApplicationDep loanApplicationDep = new LoanApplicationDep();
	         LoanReviewDep loanReviewDep = new LoanReviewDep();
	         LoanReleaseDep loanReleaseDep = new LoanReleaseDep();
	         loanReviewDep.setSuccessor(loanReleaseDep);
	         loanApplicationDep.setSuccessor(loanReviewDep);
	         LoanRequest request1 = new LoanRequest();
	         request1.setLoanRequestNum(1);
	         LoanRequest request2 = new LoanRequest();
	         request2.setLoanRequestNum(2);
	         LoanRequest request3 = new LoanRequest();
	         request3.setLoanRequestNum(3);
	         LoanRequest request4 = new LoanRequest();
	         request4.setLoanRequestNum(4);
	         loanApplicationDep.addApply(request1);
	         loanApplicationDep.addApply(request2);
	         loanApplicationDep.addApply(request3);
	         loanApplicationDep.addApply(request4);
	         timeSource.addTimeListener(loanApplicationDep); 
	         timeSource.addTimeListener(loanReleaseDep);
	         timeSource.addTimeListener(loanReviewDep);
	         /*ds.addTimeListener(new TimeListener() {     
	            public void handleEvent(TimeEvent event) {     
	            System.out.println("Method come from 匿名类...");     
	          }     
	        }); */    
	         TimeEvent tEvent =new TimeEvent(timeSource);
	         tEvent.setTime(1);
	       timeSource.notifyEvent(tEvent);//触发事件、通知监听器    
	       
	       TimeEvent tEvent2 =new TimeEvent(timeSource);
	      tEvent2.setTime(tEvent.getTime()+1);
	       timeSource.notifyEvent(tEvent2);
	       
	       TimeEvent tEvent3 =new TimeEvent(timeSource);
		      tEvent3.setTime(tEvent2.getTime()+1);
		       timeSource.notifyEvent(tEvent3);
		       
		   TimeEvent tEvent4 =new TimeEvent(timeSource);
			  tEvent4.setTime(tEvent3.getTime()+1);
			   timeSource.notifyEvent(tEvent4);    
		   TimeEvent tEvent5 =new TimeEvent(timeSource);
			  tEvent5.setTime(tEvent4.getTime()+1);
			timeSource.notifyEvent(tEvent5);
	       /*tEvent.setTime(tEvent.getTime()+1);
	       ds.notifyEvent(tEvent);*/
	       
	       
	       //ds.notifyEvent(tEvent);
	     }catch(Exception ex){  
	       ex.printStackTrace();  
	       }     
	    }     
	    
	    public static void main(String args[]) {     
	           new TestTime();     
	    }     
	} 