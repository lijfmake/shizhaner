package com.huawei.loan.bean;

import java.util.EventListener;

public interface TimeListener extends EventListener {   
    //EventListener�������¼��������ӿڱ�����չ�ı�ǽӿڡ���Ϊ���������ݵı�ǽӿڡ�   
    //�����¼��������������Լ��������£�   
    public void handleEvent(TimeEvent te);   
}