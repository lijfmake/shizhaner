package com.huawei.loan.bean;

import java.util.EventObject;

public class TimeEvent extends EventObject {     
	private int time;
    public TimeEvent(Object source,int time) {     
      super(source);//source—事件源对象—如在界面上发生的点击按钮事件中的按钮     
       //所有 Event 在构造时都引用了对象 "source"，在逻辑上认为该对象是最初发生有关 Event 的对象     
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