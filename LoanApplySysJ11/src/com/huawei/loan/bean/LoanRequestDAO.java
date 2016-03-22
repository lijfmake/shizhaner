package com.huawei.loan.bean;

import java.util.ArrayList;
import java.util.List;

public class LoanRequestDAO {

	private List<LoanRequest> loanRequestList = new ArrayList<LoanRequest>();
	public void add(LoanRequest loanRequest)
	{
		loanRequestList.add(loanRequest);
	}
	
	public void update(LoanRequest loanRequest)
	{
		int id =loanRequest.getLoanRequestId();
		int i=0;
		for (;i<loanRequestList.size();i++) {
			if(id==loanRequest.getLoanRequestId()){
				break;
			}
		}
		if(i!=0){
			loanRequestList.remove(i);//ɾ��ԭ���ģ��滻���µ�
			loanRequestList.add(loanRequest);
		}
	}
	
	public LoanRequest queryById(int id)
	{
		for (LoanRequest loanRequest : loanRequestList) {
			if(id==loanRequest.getLoanRequestId()){
				return loanRequest;
			}
		}
		return null;
	}
	
	public void applyToReview(LoanRequest loanRequest,int time){
		//System.out.println("ApplyOutTime " +loanRequest.getApplyOutTime()+"to " +time);
		//System.out.println("ReviewInTime " +loanRequest.getReviewInTime()+"to " +time);
		loanRequest.setApplyOutTime(time);
		loanRequest.setReviewInTime(time);
		this.update(loanRequest);
	}
	
	public void reviewToRelease(LoanRequest loanRequest,int time,boolean isReApply){
		//System.out.println("ReviewOutTime " +loanRequest.getReviewOutTime()+"to " +time);
		//System.out.println("ReleaseInTime " +loanRequest.getReleaseInTime()+"to " +time);
		loanRequest.setReviewOutTime(time);
		loanRequest.setReApply(isReApply);
		//����Ҫ�������룬ֱ�ӽ��뷢�Ž׶�
		if(!isReApply)
		{
			loanRequest.setReleaseInTime(time);
		}
		this.update(loanRequest);
	}
	
	
	public void release(LoanRequest loanRequest,int time,boolean isReApply){
		//loanRequest.setReviewOutTime(time);
		
		//System.out.println("ReleaseOutTime " +loanRequest.getReleaseOutTime()+"to " +time);
		loanRequest.setReleaseOutTime(time);
		loanRequest.setReApply(isReApply);
		this.update(loanRequest);
	}
	
	public LoanRequest getReviewRequest()
	{
		for (LoanRequest loanRequest : loanRequestList) {
			if(loanRequest.getReviewOutTime()==0&&loanRequest.getReviewInTime()!=0){
				return loanRequest;
			}
		}
		return null;
	}

	public LoanRequest getReleaseRequest()
	{
		for (LoanRequest loanRequest : loanRequestList) {
			if(loanRequest.getReleaseOutTime()==0&&loanRequest.getReleaseInTime()!=0){
				return loanRequest;
			}
		}
		return null;
	}

	public List<LoanRequest> getLoanRequestList() {
		return loanRequestList;
	}	
	
	
	
}
