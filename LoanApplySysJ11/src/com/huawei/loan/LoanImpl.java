package com.huawei.loan;

import com.huawei.exam.OperationResult;
import com.huawei.exam.ReturnCodeEnum;

import java.text.DecimalFormat;

/**
 * <p>
 * Title:�������Ը����Լ��������ڱ��������ӱ����ͺ���
 * </p>
 * <p/>
 * <p>
 * Description:�������ʵ����
 * </p>
 * <p/>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p/>
 * <p>
 * Company:
 * </p>
 * 
 * @author unknown
 * @version 1.0
 */
public class LoanImpl 
{

	/*
	 * �޲������캯��������������ģ�
	 */
	public LoanImpl() {

	}

	/**
	 * ������Ҫʵ�ֵĽӿ� reboot�����ڣ�ϵͳ����/��ʼ������
	 * 
	 * @return OperationResult����������ֱ�ӷ��ش�������ö������
	 */
	public OperationResult opReboot() {
		// TODO ����������е��߼����˴���������ο���
		return OperationResult.createReturnResult(ReturnCodeEnum.E001);

	}

	/**
	 * ������Ҫʵ�ֵĽӿ� request����ӿڣ���������Ϣ��ʼ��
	 * 
	 * @param paraArray
	 *            ����͹����Ĳ����б�Ϊsplit("-")�Ľ�� �������ͣ�������-������������-�����-��������-����ʱ��
	 * @return OperationResult����������ֱ�ӷ��ش�������ö������
	 */
	public OperationResult opReqeustLoan(int[] paraArray) {
		// TODO ����������е��߼����˴���������ο���
		return OperationResult.createReturnResult(ReturnCodeEnum.E009);
	}

	/**
	 * ������Ҫʵ�ֵĽӿ� request����ӿڣ���������Ϣ��ʼ��
	 * 
	 * @param paraArray
	 *            �������ͣ�������-��x���·�-ס�������𻹿���-��ѯʱ��
	 * @return OperationResult����������ֱ�ӷ��ش�������ö������
	 */
	public OperationResult opList(int[] paraArray) {
		
		// TODO ����������е��߼����˴���������ο���
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("\n");

		double[] calcResults = new double[] { 0, 0, 0 };
		// ��������
		DecimalFormat df = new DecimalFormat("0");
		// �����Ϣ
		stringBuffer.append("��").append(paraArray[0]).append("���·ݵĻ��Ϣ�ܽ��:")
				.append(df.format(calcResults[0])).append("\r\n")
				.append("�軹�Ϣ�ܽ��:").append(df.format(calcResults[1]))
				.append("\r\n").append("����˻��Ϣ�ܽ��:")
				.append(df.format(calcResults[2]));

		return new OperationResult(stringBuffer.toString());
	}

}
