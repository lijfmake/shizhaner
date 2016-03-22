package com.huawei.loan.test;

import com.huawei.loan.bean.TimeEvent;
import com.huawei.loan.bean.TimeListener;

public class TimeListener1 implements TimeListener {     
    public void handleEvent(TimeEvent te) {     
        System.out.println("Inside listener1...");     
        
 }     
}   
