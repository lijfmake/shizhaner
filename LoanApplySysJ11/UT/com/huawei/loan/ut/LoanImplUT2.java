/*
 * Copyright Notice:
 *      Copyright  1998-2009, Huawei Technologies Co., Ltd.  ALL Rights Reserved.
 *
 *      Warning: This computer software sourcecode is protected by copyright law
 *      and international treaties. Unauthorized reproduction or distribution
 *      of this sourcecode, or any portion of it, may result in severe civil and
 *      criminal penalties, and will be prosecuted to the maximum extent
 *      possible under the law.
 */
package com.huawei.loan.ut;

import org.junit.*;
import com.huawei.exam.*;
import com.huawei.loan.*;

public class LoanImplUT2
{
    LoanImpl loanImpl = null;
    
    @Before
    public void setUp()
        throws Exception
    {
        loanImpl = new LoanImpl();
    }
    
    @After
    public void tearDown()
        throws Exception
    {
        loanImpl = null;
    }
    
    @Test
    public void testOpReboot()
    {
        OperationResult actual = loanImpl.opReboot();        
        OperationResult expected = OperationResult.createReturnResult(ReturnCodeEnum.E001);
        Assert.assertNotNull(actual);
        Assert.assertEquals("", expected.toString(), actual.toString());
    }
    
    @Test
    public void testOpReqeustLoan()
    {
    	LoanImpl loanImpl = new LoanImpl();
        loanImpl.opReboot();
        int[] paraArray = new int[]{1,2000,20,5,1};
        OperationResult expected = OperationResult.createReturnResult(ReturnCodeEnum.E009);
        OperationResult actual =  loanImpl.opReqeustLoan(paraArray);
        Assert.assertEquals("", expected.toString(), actual.toString());
    }
  
    @Test
    public void testOpReqeustLoan_1(){
    	//�����ţ�[1,10]���뷶Χ���� request 11-2000-10-5-1
    	LoanImpl loanImpl = new LoanImpl();
        loanImpl.opReboot();
        int[] paraArray = new int[]{11,2000,10,5,1};
        OperationResult expected = OperationResult.createReturnResult(ReturnCodeEnum.E002);
        OperationResult actual =  loanImpl.opReqeustLoan(paraArray);
        Assert.assertEquals("", expected.toString(), actual.toString());
    }
    
    @Test
    public void testOpReqeustLoan_2(){
    	//������������:[1000,10000],(��λ,Ԫ)���뷶Χ����   request 1-11000-10-5-1
    	LoanImpl loanImpl = new LoanImpl();
        loanImpl.opReboot();
        int[] paraArray = new int[]{1,11000,10,5,1};
        OperationResult expected = OperationResult.createReturnResult(ReturnCodeEnum.E003);
        OperationResult actual =  loanImpl.opReqeustLoan(paraArray);
        Assert.assertEquals("", expected.toString(), actual.toString());
    }
    @Test
    public void testOpReqeustLoan_3(){
    	//�����:[10,100],��ʽΪ����,(��λ,��Ԫ)���뷶Χ����   request 1-1000-5-5-1
    	LoanImpl loanImpl = new LoanImpl();
        loanImpl.opReboot();
        int[] paraArray = new int[]{1,1000,5,5,1};
        OperationResult expected = OperationResult.createReturnResult(ReturnCodeEnum.E004);
        OperationResult actual =  loanImpl.opReqeustLoan(paraArray);
        Assert.assertEquals("", expected.toString(), actual.toString());
    }
    @Test
    public void testOpReqeustLoan_4(){
    	//��������:[1,20],(��λ,��)���뷶Χ����   request 1-1000-20-25-1
    	LoanImpl loanImpl = new LoanImpl();
        loanImpl.opReboot();
        int[] paraArray = new int[]{1,1000,20,25,1};
        OperationResult expected = OperationResult.createReturnResult(ReturnCodeEnum.E005);
        OperationResult actual =  loanImpl.opReqeustLoan(paraArray);
        Assert.assertEquals("", expected.toString(), actual.toString());
    }
    
    @Test
    public void testOpReqeustLoan_5(){
    	//����ʱ��:[1,10],(��λ,t)���뷶Χ����   request 1-1000-20-5-11
    	LoanImpl loanImpl = new LoanImpl();
        loanImpl.opReboot();
        int[] paraArray = new int[]{1,1000,20,5,11};
        OperationResult expected = OperationResult.createReturnResult(ReturnCodeEnum.E006);
        OperationResult actual =  loanImpl.opReqeustLoan(paraArray);
        Assert.assertEquals("", expected.toString(), actual.toString());
    }
    
    @Test
    public void testOpReqeustLoan_6(){
    	//��ִ�����������ʱ�䲻��С����ִ������ɹ�������ʱ��  request 1-1000-20-5-5  request 2-1000-20-5-4
    	LoanImpl loanImpl = new LoanImpl();
        loanImpl.opReboot();
        int[] paraArray = new int[]{1,1000,20,5,5};
        OperationResult expected = OperationResult.createReturnResult(ReturnCodeEnum.E007);
        OperationResult actual =  loanImpl.opReqeustLoan(paraArray);
        actual = loanImpl.opReqeustLoan(new int[]{2,1000,20,5,4});
        Assert.assertEquals("", expected.toString(), actual.toString());
    }
    
    @Test
    public void testOpReqeustLoan_7(){
    	//�ڷǴ���������״̬:(�������ųɹ�)�������� request 1-1000-20-5-5  request 1-1000-20-5-6
    	LoanImpl loanImpl = new LoanImpl();
        loanImpl.opReboot();
        int[] paraArray = new int[]{1,1000,20,5,5};
        OperationResult expected = OperationResult.createReturnResult(ReturnCodeEnum.E008);
        OperationResult actual =  loanImpl.opReqeustLoan(paraArray);
        actual = loanImpl.opReqeustLoan(new int[]{1,1000,20,5,6});
        Assert.assertEquals("", expected.toString(), actual.toString());
    }
    //��������1
    @Test
    public void testOpList_1()
    {
//    	ϵͳ��������ʼ����
//    	�������롢��顢�Ŵ��ɹ���
//    	�м��������Ϣ
    	LoanImpl loanImpl = new LoanImpl();
        loanImpl.opReboot();
        int[] paraArray = new int[]{1,2000,20,5,1};
        loanImpl.opReqeustLoan(paraArray);
        paraArray = new int[]{1,1,10,4};
        OperationResult actual = loanImpl.opList(paraArray);
        String expected="E015:��������1��ϸ��Ϣ\r\n"+
			"��������״̬:����ųɹ�\r\n"+
			"�����(��Ԫ):20\r\n"+
			"��������:60\r\n"+
			"ʵ��������(ǧ��֮):2\r\n"+
			"��1���·ݵĻ��Ϣ�ܽ��:3733\r\n"+
			"�軹�Ϣ�ܽ��:212200\r\n"+
			"����˻��Ϣ�ܽ��:162200";
        Assert.assertEquals("", expected, actual.toString());
    }
    
    //��������2
    @Test
    public void testOpList_2()
    {
//    	ϵͳ��������ʼ����
//    	��������ɹ�,���ʧ�ܣ�
//    	�޼��������Ϣ
    	LoanImpl loanImpl = new LoanImpl();
        loanImpl.opReboot();
        int[] paraArray = new int[]{1,2000,20,15,1};
        loanImpl.opReqeustLoan(paraArray);
        paraArray = new int[]{1,1,0,4};
        OperationResult actual = loanImpl.opList(paraArray);
        String expected="E015:��������1��ϸ��Ϣ\r\n"+
			"��������״̬:�������޳�����Χ,������ʧ��,����������\r\n"+
			"�����(��Ԫ):20\r\n"+
			"��������:180\r\n"+
			"ʵ��������(ǧ��֮):0\r\n"+
			"��1���·ݵĻ��Ϣ�ܽ��:0\r\n"+
			"�軹�Ϣ�ܽ��:0\r\n"+
			"����˻��Ϣ�ܽ��:0";
        Assert.assertEquals("", expected, actual.toString());
    }
    
    
    //��������3
    @Test
    public void testOpList_3()
    {
//    	ϵͳ��������ʼ����
//    	��������ɹ�,���ʧ�ܣ�
//    	�޼��������Ϣ
    	LoanImpl loanImpl = new LoanImpl();
        loanImpl.opReboot();
        int[] paraArray = new int[]{1,9000,80,15,1};
        loanImpl.opReqeustLoan(paraArray);
        loanImpl.opReqeustLoan( new int[]{2,8000,80,15,1});
        loanImpl.opReqeustLoan( new int[]{3,8600,80,15,1});
        loanImpl.opReqeustLoan( new int[]{4,8100,80,15,1});
        paraArray = new int[]{4,10,10,6};
        OperationResult actual = loanImpl.opList(paraArray);
        String expected="E015:��������4��ϸ��Ϣ\r\n"+
			"��������״̬:�����޿��ô���,���Ŵ���ʧ��,����������\r\n"+
			"�����(��Ԫ):80\r\n"+
			"��������:180\r\n"+
			"ʵ��������(ǧ��֮):0\r\n"+
			"��10���·ݵĻ��Ϣ�ܽ��:0\r\n"+
			"�軹�Ϣ�ܽ��:0\r\n"+
			"����˻��Ϣ�ܽ��:0";
        Assert.assertEquals("", expected, actual.toString());
    }
    
  //��������4
    @Test
    public void testOpList_4()
    {
//    	ϵͳ��������ʼ����
//    	��������ɹ���,�ٴ����룻
//    	��������ʧ�ܺ�,�ٴ����룻

    	LoanImpl loanImpl = new LoanImpl();
        loanImpl.opReboot();
        loanImpl.opReqeustLoan( new int[]{1,9000,80,15,1});
        loanImpl.opReqeustLoan( new int[]{2,8000,80,15,1});
        loanImpl.opReqeustLoan( new int[]{3,8600,80,15,2});
        loanImpl.opReqeustLoan( new int[]{4,8100,80,15,3});
        loanImpl.opReqeustLoan( new int[]{3,8630,80,15,4});
        loanImpl.opReqeustLoan( new int[]{4,8100,80,13,5});
        OperationResult actual = loanImpl.opReqeustLoan( new int[]{4,8100,80,13,8});
        String expected="E009:��������ɹ�";
        Assert.assertEquals("", expected, actual.toString());
    }
    
  //��������4
    @Test
    public void testOpList_5()
    {
//    	ϵͳ��������ʼ����
//    	��������ɹ����ٴ����룻
//    	��ѯ��ͬʱ�̵Ĵ���״̬


    	LoanImpl loanImpl = new LoanImpl();
        loanImpl.opReboot();
        loanImpl.opReqeustLoan( new int[]{1,2000,20,15,1});
        loanImpl.opReqeustLoan( new int[]{2,8000,20,13,2});
        loanImpl.opReqeustLoan( new int[]{3,8000,20,13,3});
        loanImpl.opReqeustLoan( new int[]{1,2000,20,15,3});
        OperationResult actual =  loanImpl.opList( new int[]{1,1,0,4});
        String expected="E015:��������1��ϸ��Ϣ\r\n"+
			"��������״̬:��������ɹ������\r\n"+
			"�����(��Ԫ):20\r\n"+
			"��������:180\r\n"+
			"ʵ��������(ǧ��֮):0\r\n"+
			"��1���·ݵĻ��Ϣ�ܽ��:0\r\n"+
			"�軹�Ϣ�ܽ��:0\r\n"+
			"����˻��Ϣ�ܽ��:0";
        Assert.assertEquals("", expected, actual.toString());
        actual = loanImpl.opList( new int[]{1,1,0,6});
        expected="E015:��������1��ϸ��Ϣ\r\n"+
    			"��������״̬:�������޳�����Χ,������ʧ��,����������\r\n"+
    			"�����(��Ԫ):20\r\n"+
    			"��������:180\r\n"+
    			"ʵ��������(ǧ��֮):0\r\n"+
    			"��1���·ݵĻ��Ϣ�ܽ��:0\r\n"+
    			"�軹�Ϣ�ܽ��:0\r\n"+
    			"����˻��Ϣ�ܽ��:0";
        Assert.assertEquals("", expected, actual.toString());
    }
}
