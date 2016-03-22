package com.huawei.loan.bean;

import java.util.Enumeration;
import java.util.Vector;
/**
 * 
 * @author lijf
 *
 */
public class TimeSource {   
    private Vector repository = new Vector();//�����Լ��ļ���������   
    public TimeSource(){}   
    public void addTimeListener(TimeListener tl) {   
           repository.addElement(tl);   
    }   
    /*public void notifyEvent() {//֪ͨ���еļ�����   
           Enumeration en = repository.elements();   
           while(en.hasMoreElements()) {   
        	   TimeListener tl = (TimeListener)en.nextElement();   
                 tl.handleEvent(new TimeEvent(this));   
           }   
    } 
    */
    public void notifyEvent(TimeEvent te) {//֪ͨ���еļ�����   
        Enumeration en = repository.elements();   
        while(en.hasMoreElements()) {   
     	   TimeListener tl = (TimeListener)en.nextElement();   
              tl.handleEvent(te);   
        }   
 }
}  
