package com.huawei.loan.bean;

import java.util.Enumeration;
import java.util.Vector;
/**
 * 
 * @author lijf
 *
 */
public class TimeSource {   
    private Vector repository = new Vector();//监听自己的监听器队列   
    public TimeSource(){}   
    public void addTimeListener(TimeListener tl) {   
           repository.addElement(tl);   
    }   
    /*public void notifyEvent() {//通知所有的监听器   
           Enumeration en = repository.elements();   
           while(en.hasMoreElements()) {   
        	   TimeListener tl = (TimeListener)en.nextElement();   
                 tl.handleEvent(new TimeEvent(this));   
           }   
    } 
    */
    public void notifyEvent(TimeEvent te) {//通知所有的监听器   
        Enumeration en = repository.elements();   
        while(en.hasMoreElements()) {   
     	   TimeListener tl = (TimeListener)en.nextElement();   
              tl.handleEvent(te);   
        }   
 }
}  
