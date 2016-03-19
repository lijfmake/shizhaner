package com.huawei.loan;

/**
 * <p>
 * Title: �������Ը����Լ��������ڱ��������ӱ����ͺ���
 * </p>
 * <p>
 * Description: ��������
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author unknown
 * @version 1.0
 */
public class Constants 
{
    // �����ŵı߽�ֵ
    public final static int LOANID_MIN = 1;
    public final static int LOANID_MAX = 10;

    // ������ı߽�ֵ
    // ���������Сֵ
    public final static int INCOME_LOW_MIN = 1000;
    // �͵���������ֵ
    public final static int INCOME_LOW_MAX = 5000;
    // �ߵ��������Сֵ
    public final static int INCOME_HIGH_MIN = 5001;
    // �ߵ���������ֵ
    public final static int INCOME_HIGH_MAX = 10000;

    // �������޵ı߽�ֵ
    // ��Сֵ
    public final static int INSTALLMENTS_MIN = 12;
    // ��������Ⱥ���޵����ֵ
    public final static int INSTALLMENTS_LOW_MAX = 120;
    // ��������Ⱥ���޵����ֵ
    public final static int INSTALLMENTS_HIGH_MAX = 240;
    // ���޵������ۿ۹յ�
    public final static int INSTALLMENTS_RATE_DISCOUNT = 120;

    // ������ı߽�ֵ
    // ��Сֵ
    public final static int AMOUNT_MIN = 100000;
    // ��������Ⱥ�������ֵ
    public final static int AMOUNT_LOW_MAX = 500000;
    // ��������Ⱥ�������ֵ
    public final static int AMOUNT_HIGH_MAX = 1000000;
    // �����ۿ۵Ĺյ�
    public final static int AMOUNT_RATE_DISCOUNT = 400000;

    // ����ʱ��ı߽�ֵ
    public final static int APPLY_TIME_MIN = 1;
    public final static int APPLY_TIME_MAX = 10;

    // ��ѯʱ��ı߽�ֵ
    public final static int QUERY_TIME_MIN = 1;
    public final static int QUERY_TIME_MAX = 24;

    // �����ʵ�����
    public final static double LOAN_RATE_TYPE_0_24 = 0.024;
    public final static double LOAN_RATE_TYPE_0_48 = 0.048;

    // ���ʵ��ۿ�����
    public final static double LOAD_RATE_DISCOUNT_TYPE_0_5 = 0.5;

    // ���еĴ����޶�
    public static int BANK_LOAN_AMOUNT_LIMIT = 3000000;
    // �����ܹ���������ķ�������
    public static int BANK_LOAN_COUNT_LIMIT = 5;
}
