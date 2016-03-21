package com.huawei.loan.bean;


public abstract class Handler implements TimeListener{
    
    /**
     * ���к�̵����ζ���
     */
    protected Handler successor;
    /**
     * ʾ�⴦������ķ�������Ȼ���ʾ�ⷽ����û�д��������
     * ��ʵ���ǿ��Դ�������ģ����ݾ�����Ҫ��ѡ���Ƿ񴫵ݲ���
     */
    public abstract void handleRequest(LoanRequest loanRequest,TimeEvent te);
    //public abstract void handleRequest(LoanRequest loanRequest);
    /**
     * ȡֵ����
     */
    public Handler getSuccessor() {
        return successor;
    }
    /**
     * ��ֵ���������ú�̵����ζ���
     */
    public void setSuccessor(Handler successor) {
        this.successor = successor;
    }
    
}