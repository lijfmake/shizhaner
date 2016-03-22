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
    	CARD_TYPE_STR = new String[] {"贷款年限超出范围,审查贷款失败,待重新申请", 
    			"贷款本金超出范围,审查贷款失败,待重新申请", "银行无可用贷款,发放贷款失败,待重新申请", 
    			"发放贷款系统能力不足,发放贷款失败,待重新申请","贷款申请成功待审查",
    			"贷款审查成功待发放","贷款发放成功"};
    }
}
