package com.huawei.loan;

public enum StatueEnum {
    A, 
    B, /* */
    C, /*  */
    D,
    E,
    F,
    G;
    
    private static final String[] CARD_TYPE_STR;

    public String toString() {
    	return CARD_TYPE_STR[this.ordinal()];
    }

    static {
    	CARD_TYPE_STR = new String[] {"�������޳�����Χ,������ʧ��,����������", 
    			"����𳬳���Χ,������ʧ��,����������", "�����޿��ô���,���Ŵ���ʧ��,����������", 
    			"���Ŵ���ϵͳ��������,���Ŵ���ʧ��,����������","��������ɹ������",
    			"�������ɹ�������","����ųɹ�"};
    }
}
