package com.huawei.loan.bean;

import java.util.EventObject;

public class TimeEvent extends EventObject {     
	private int time;
    public TimeEvent(Object source,int time) {     
      super(source);//source���¼�Դ�������ڽ����Ϸ����ĵ����ť�¼��еİ�ť     
       //���� Event �ڹ���ʱ�������˶��� "source"�����߼�����Ϊ�ö�������������й� Event �Ķ���     
      this.time =time;
    }     
   /* public void timeIncrease() {    
    	this.time++;
        System.out.println("This time is "+time);     
    }*/
	public int getTime() {
		return time;
	}
	/*public void setTime(int time) {
		this.time = time;
	} */    
} 