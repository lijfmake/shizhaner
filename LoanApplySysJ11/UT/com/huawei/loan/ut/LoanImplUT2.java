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
    	//贷款编号：[1,10]输入范围错误 request 11-2000-10-5-1
    	LoanImpl loanImpl = new LoanImpl();
        loanImpl.opReboot();
        int[] paraArray = new int[]{11,2000,10,5,1};
        OperationResult expected = OperationResult.createReturnResult(ReturnCodeEnum.E002);
        OperationResult actual =  loanImpl.opReqeustLoan(paraArray);
        Assert.assertEquals("", expected.toString(), actual.toString());
    }
    
    @Test
    public void testOpReqeustLoan_2(){
    	//贷款人月收入:[1000,10000],(单位,元)输入范围错误   request 1-11000-10-5-1
    	LoanImpl loanImpl = new LoanImpl();
        loanImpl.opReboot();
        int[] paraArray = new int[]{1,11000,10,5,1};
        OperationResult expected = OperationResult.createReturnResult(ReturnCodeEnum.E003);
        OperationResult actual =  loanImpl.opReqeustLoan(paraArray);
        Assert.assertEquals("", expected.toString(), actual.toString());
    }
    @Test
    public void testOpReqeustLoan_3(){
    	//贷款本金:[10,100],格式为整数,(单位,万元)输入范围错误   request 1-1000-5-5-1
    	LoanImpl loanImpl = new LoanImpl();
        loanImpl.opReboot();
        int[] paraArray = new int[]{1,1000,5,5,1};
        OperationResult expected = OperationResult.createReturnResult(ReturnCodeEnum.E004);
        OperationResult actual =  loanImpl.opReqeustLoan(paraArray);
        Assert.assertEquals("", expected.toString(), actual.toString());
    }
    @Test
    public void testOpReqeustLoan_4(){
    	//贷款年限:[1,20],(单位,年)输入范围错误   request 1-1000-20-25-1
    	LoanImpl loanImpl = new LoanImpl();
        loanImpl.opReboot();
        int[] paraArray = new int[]{1,1000,20,25,1};
        OperationResult expected = OperationResult.createReturnResult(ReturnCodeEnum.E005);
        OperationResult actual =  loanImpl.opReqeustLoan(paraArray);
        Assert.assertEquals("", expected.toString(), actual.toString());
    }
    
    @Test
    public void testOpReqeustLoan_5(){
    	//申请时间:[1,10],(单位,t)输入范围错误   request 1-1000-20-5-11
    	LoanImpl loanImpl = new LoanImpl();
        loanImpl.opReboot();
        int[] paraArray = new int[]{1,1000,20,5,11};
        OperationResult expected = OperationResult.createReturnResult(ReturnCodeEnum.E006);
        OperationResult actual =  loanImpl.opReqeustLoan(paraArray);
        Assert.assertEquals("", expected.toString(), actual.toString());
    }
    
    @Test
    public void testOpReqeustLoan_6(){
    	//后执行命令的申请时间不能小于先执行命令成功的申请时间  request 1-1000-20-5-5  request 2-1000-20-5-4
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
    	//在非待重新申请状态:(包含发放成功)重新申请 request 1-1000-20-5-5  request 1-1000-20-5-6
    	LoanImpl loanImpl = new LoanImpl();
        loanImpl.opReboot();
        int[] paraArray = new int[]{1,1000,20,5,5};
        OperationResult expected = OperationResult.createReturnResult(ReturnCodeEnum.E008);
        OperationResult actual =  loanImpl.opReqeustLoan(paraArray);
        actual = loanImpl.opReqeustLoan(new int[]{1,1000,20,5,6});
        Assert.assertEquals("", expected.toString(), actual.toString());
    }
    //基本用例1
    @Test
    public void testOpList_1()
    {
//    	系统重启、初始化；
//    	贷款申请、审查、放贷成功；
//    	有计算贷款信息
    	LoanImpl loanImpl = new LoanImpl();
        loanImpl.opReboot();
        int[] paraArray = new int[]{1,2000,20,5,1};
        loanImpl.opReqeustLoan(paraArray);
        paraArray = new int[]{1,1,10,4};
        OperationResult actual = loanImpl.opList(paraArray);
        String expected="E015:贷款申请1详细信息\r\n"+
			"贷款申请状态:贷款发放成功\r\n"+
			"贷款本金(万元):20\r\n"+
			"还款期数:60\r\n"+
			"实际月利率(千分之):2\r\n"+
			"第1个月份的还款本息总金额:3733\r\n"+
			"需还款本息总金额:212200\r\n"+
			"需个人还款本息总金额:162200";
        Assert.assertEquals("", expected, actual.toString());
    }
    
    //基本用例2
    @Test
    public void testOpList_2()
    {
//    	系统重启、初始化；
//    	贷款申请成功,审查失败；
//    	无计算贷款信息
    	LoanImpl loanImpl = new LoanImpl();
        loanImpl.opReboot();
        int[] paraArray = new int[]{1,2000,20,15,1};
        loanImpl.opReqeustLoan(paraArray);
        paraArray = new int[]{1,1,0,4};
        OperationResult actual = loanImpl.opList(paraArray);
        String expected="E015:贷款申请1详细信息\r\n"+
			"贷款申请状态:贷款年限超出范围,审查贷款失败,待重新申请\r\n"+
			"贷款本金(万元):20\r\n"+
			"还款期数:180\r\n"+
			"实际月利率(千分之):0\r\n"+
			"第1个月份的还款本息总金额:0\r\n"+
			"需还款本息总金额:0\r\n"+
			"需个人还款本息总金额:0";
        Assert.assertEquals("", expected, actual.toString());
    }
    
    
    //基本用例3
    @Test
    public void testOpList_3()
    {
//    	系统重启、初始化；
//    	贷款申请成功,审查失败；
//    	无计算贷款信息
    	LoanImpl loanImpl = new LoanImpl();
        loanImpl.opReboot();
        int[] paraArray = new int[]{1,9000,80,15,1};
        loanImpl.opReqeustLoan(paraArray);
        loanImpl.opReqeustLoan( new int[]{2,8000,80,15,1});
        loanImpl.opReqeustLoan( new int[]{3,8600,80,15,1});
        loanImpl.opReqeustLoan( new int[]{4,8100,80,15,1});
        paraArray = new int[]{4,10,10,6};
        OperationResult actual = loanImpl.opList(paraArray);
        String expected="E015:贷款申请4详细信息\r\n"+
			"贷款申请状态:银行无可用贷款,发放贷款失败,待重新申请\r\n"+
			"贷款本金(万元):80\r\n"+
			"还款期数:180\r\n"+
			"实际月利率(千分之):0\r\n"+
			"第10个月份的还款本息总金额:0\r\n"+
			"需还款本息总金额:0\r\n"+
			"需个人还款本息总金额:0";
        Assert.assertEquals("", expected, actual.toString());
    }
    
  //基本用例4
    @Test
    public void testOpList_4()
    {
//    	系统重启、初始化；
//    	贷款申请成功后,再次申请；
//    	贷款申请失败后,再次申请；

    	LoanImpl loanImpl = new LoanImpl();
        loanImpl.opReboot();
        loanImpl.opReqeustLoan( new int[]{1,9000,80,15,1});
        loanImpl.opReqeustLoan( new int[]{2,8000,80,15,1});
        loanImpl.opReqeustLoan( new int[]{3,8600,80,15,2});
        loanImpl.opReqeustLoan( new int[]{4,8100,80,15,3});
        loanImpl.opReqeustLoan( new int[]{3,8630,80,15,4});
        loanImpl.opReqeustLoan( new int[]{4,8100,80,13,5});
        OperationResult actual = loanImpl.opReqeustLoan( new int[]{4,8100,80,13,8});
        String expected="E009:贷款申请成功";
        Assert.assertEquals("", expected, actual.toString());
    }
    
  //基本用例4
    @Test
    public void testOpList_5()
    {
//    	系统重启、初始化；
//    	贷款申请成功后、再次申请；
//    	查询不同时刻的贷款状态


    	LoanImpl loanImpl = new LoanImpl();
        loanImpl.opReboot();
        loanImpl.opReqeustLoan( new int[]{1,2000,20,15,1});
        loanImpl.opReqeustLoan( new int[]{2,8000,20,13,2});
        loanImpl.opReqeustLoan( new int[]{3,8000,20,13,3});
        loanImpl.opReqeustLoan( new int[]{1,2000,20,15,3});
        OperationResult actual =  loanImpl.opList( new int[]{1,1,0,4});
        String expected="E015:贷款申请1详细信息\r\n"+
			"贷款申请状态:贷款申请成功待审查\r\n"+
			"贷款本金(万元):20\r\n"+
			"还款期数:180\r\n"+
			"实际月利率(千分之):0\r\n"+
			"第1个月份的还款本息总金额:0\r\n"+
			"需还款本息总金额:0\r\n"+
			"需个人还款本息总金额:0";
        Assert.assertEquals("", expected, actual.toString());
        actual = loanImpl.opList( new int[]{1,1,0,6});
        expected="E015:贷款申请1详细信息\r\n"+
    			"贷款申请状态:贷款年限超出范围,审查贷款失败,待重新申请\r\n"+
    			"贷款本金(万元):20\r\n"+
    			"还款期数:180\r\n"+
    			"实际月利率(千分之):0\r\n"+
    			"第1个月份的还款本息总金额:0\r\n"+
    			"需还款本息总金额:0\r\n"+
    			"需个人还款本息总金额:0";
        Assert.assertEquals("", expected, actual.toString());
    }
}
