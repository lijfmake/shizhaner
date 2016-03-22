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

public class LoanImplUT
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
    
    public int[] stringToint(String[] str){
    	int[] requestInput = {0,0,0,0,0};
    	
    	for(int i=0;i<str.length;i++)
    		requestInput[i]=Integer.valueOf(str[i]);
    	return requestInput;
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
    public void testOpReqeustLoan0()
    {
        //TODO:贷款申请、审查、放贷成功；
    	//request 1-2000-20-5-1
    	//E009:贷款申请成功
    	loanImpl.opReboot();  
    	int [] paras={1,2000,20,5,1};//贷款编号-贷款人月收入-贷款本金-贷款年限-申请时间
    	OperationResult actualRequest=loanImpl.opReqeustLoan(paras);
    	OperationResult expectedRequest = OperationResult.createReturnResult(ReturnCodeEnum.E009);
        Assert.assertNotNull(actualRequest);
        Assert.assertEquals("", expectedRequest.toString(), actualRequest.toString());
    	
    	int [] paras2={1,1,10,4};   //贷款编号-第x个月份-住房公积金还款金额-查询时间
    	OperationResult  actual=loanImpl.opList(paras2);

    	
		StringBuffer stringBuffer = new StringBuffer();
		
		stringBuffer.append("E015:贷款申请").append("1").append("详细信息\r\n")
		.append("贷款申请状态:").append(StatueEnum.G).append("\r\n")
		.append("贷款本金(万元):20").append("\r\n")
		.append("还款期数:60").append("\r\n")
		.append("实际月利率(千分之):2").append("\r\n")
		.append("第1个月份的还款本息总金额:3733").append("\r\n")
		.append("需还款本息总金额:212200").append("\r\n").
		append("需个人还款本息总金额:162200");
		OperationResult expected=new OperationResult(stringBuffer.toString());
        Assert.assertNotNull(actual);
        Assert.assertEquals("", expected.toString(), actual.toString());
    	
    }
    
    
    
/***************************测试用例******************************/
    
    
    
    @Test
    public void testOpReqeustLoan3()
    {
        //TODO:
    	loanImpl.opReboot();
        String request="1-2000-20-5-1";
        int[] requestData=stringToint(request.split("-"));
        OperationResult actual = loanImpl.opReqeustLoan(requestData);        
        OperationResult expected = OperationResult.createReturnResult(ReturnCodeEnum.E009);
        Assert.assertNotNull(actual);
        Assert.assertEquals("", expected.toString(), actual.toString());      
    }
    
    @Test
    public void testOpReqeustLoan4()
    {
        //TODO:
    	loanImpl.opReboot();
        String request="0-2000-20-5-1";
        int[] requestData=stringToint(request.split("-"));
        OperationResult actual = loanImpl.opReqeustLoan(requestData);        
        OperationResult expected = OperationResult.createReturnResult(ReturnCodeEnum.E002);
        Assert.assertNotNull(actual);
        Assert.assertEquals("", expected.toString(), actual.toString());      
    }
    

    
    @Test
    public void testOpReqeustLoan5()
    {
        //TODO:
    	loanImpl.opReboot();
        String request="1-12000-20-5-1";
        int[] requestData=stringToint(request.split("-"));
        OperationResult actual = loanImpl.opReqeustLoan(requestData);        
        OperationResult expected = OperationResult.createReturnResult(ReturnCodeEnum.E003);
        Assert.assertNotNull(actual);
        Assert.assertEquals("", expected.toString(), actual.toString());      
    }
    
    @Test
    public void testOpReqeustLoan6()
    {
        //TODO:
    	loanImpl.opReboot();
        String request="1-2000-200-5-1";
        int[] requestData=stringToint(request.split("-"));
        OperationResult actual = loanImpl.opReqeustLoan(requestData);        
        OperationResult expected = OperationResult.createReturnResult(ReturnCodeEnum.E004);
        Assert.assertNotNull(actual);
        Assert.assertEquals("", expected.toString(), actual.toString());      
    }
    
    @Test
    public void testOpReqeustLoan7()
    {
        //TODO:
    	loanImpl.opReboot();
        String request="1-2000-20-25-1";
        int[] requestData=stringToint(request.split("-"));
        OperationResult actual = loanImpl.opReqeustLoan(requestData);        
        OperationResult expected = OperationResult.createReturnResult(ReturnCodeEnum.E005);
        Assert.assertNotNull(actual);
        Assert.assertEquals("", expected.toString(), actual.toString());      
    }
    
    @Test
    public void testOpReqeustLoan8()
    {
        //TODO:
    	loanImpl.opReboot();
        String request="1-2000-20-5-11";
        int[] requestData=stringToint(request.split("-"));
        OperationResult actual = loanImpl.opReqeustLoan(requestData);        
        OperationResult expected = OperationResult.createReturnResult(ReturnCodeEnum.E006);
        Assert.assertNotNull(actual);
        Assert.assertEquals("", expected.toString(), actual.toString());      
    }
    
    @Test
    public void testOpReqeustLoan9()
    {
        //TODO:
    	loanImpl.opReboot();
        String request="1-2000-20-5-2";
        int[] requestData=stringToint(request.split("-"));
        loanImpl.opReqeustLoan(requestData);
        
        request="2-2000-20-5-1";
        requestData=stringToint(request.split("-"));      
        OperationResult actual = loanImpl.opReqeustLoan(requestData);        
        OperationResult expected = OperationResult.createReturnResult(ReturnCodeEnum.E007);
        Assert.assertNotNull(actual);
        Assert.assertEquals("", expected.toString(), actual.toString());      
    }
    
    @Test
    public void testOpReqeustLoan19()
    {
        //TODO:
    	loanImpl.opReboot();
        String request="1-9000-80-15-1";
        int[] requestData=stringToint(request.split("-"));      
        OperationResult actual = loanImpl.opReqeustLoan(requestData);        
        OperationResult expected = OperationResult.createReturnResult(ReturnCodeEnum.E009);
        Assert.assertNotNull(actual);
        Assert.assertEquals("", expected.toString(), actual.toString());      

        request="2-8000-80-15-1";
        requestData=stringToint(request.split("-")); 
        actual = loanImpl.opReqeustLoan(requestData);        
        expected = OperationResult.createReturnResult(ReturnCodeEnum.E009);
        Assert.assertNotNull(actual);
        Assert.assertEquals("", expected.toString(), actual.toString());      

        request="3-8600-80-15-2";
        requestData=stringToint(request.split("-")); 
        actual = loanImpl.opReqeustLoan(requestData);        
        expected = OperationResult.createReturnResult(ReturnCodeEnum.E009);
        Assert.assertNotNull(actual);
        Assert.assertEquals("", expected.toString(), actual.toString());      

        request="4-8100-80-15-3";
        requestData=stringToint(request.split("-"));  
        actual = loanImpl.opReqeustLoan(requestData);        
        expected = OperationResult.createReturnResult(ReturnCodeEnum.E009);
        Assert.assertNotNull(actual);
        Assert.assertEquals("", expected.toString(), actual.toString());      

        request="3-8630-80-15-4";
        requestData=stringToint(request.split("-"));  
        actual = loanImpl.opReqeustLoan(requestData);        
        expected = OperationResult.createReturnResult(ReturnCodeEnum.E008);
        Assert.assertNotNull(actual);
        Assert.assertEquals("", expected.toString(), actual.toString());      

        request="4-8100-80-13-5";
        requestData=stringToint(request.split("-"));  
        actual = loanImpl.opReqeustLoan(requestData);        
        expected = OperationResult.createReturnResult(ReturnCodeEnum.E008);
        Assert.assertNotNull(actual);
        Assert.assertEquals("", expected.toString(), actual.toString());      

        request="4-8100-80-13-8";
        requestData=stringToint(request.split("-"));  
        actual = loanImpl.opReqeustLoan(requestData);        
        expected = OperationResult.createReturnResult(ReturnCodeEnum.E009);
        Assert.assertNotNull(actual);
        Assert.assertEquals("", expected.toString(), actual.toString());      

     
    }
    
    @Test
    public void testOpReqeustLoan21()
    {
        //TODO:
    	loanImpl.opReboot();
        String request="1-5000-40-10-2";
        int[] requestData=stringToint(request.split("-")); 
        OperationResult actual = loanImpl.opReqeustLoan(requestData);        
        OperationResult expected = OperationResult.createReturnResult(ReturnCodeEnum.E009);
        Assert.assertNotNull(actual);
        Assert.assertEquals("", expected.toString(), actual.toString());      

        request="1-1000-40-10-4";
        requestData=stringToint(request.split("-")); 
        actual = loanImpl.opReqeustLoan(requestData);        
        expected = OperationResult.createReturnResult(ReturnCodeEnum.E008);
        Assert.assertNotNull(actual);
        Assert.assertEquals("", expected.toString(), actual.toString());      

        request="1-1000-40-10-3";
        requestData=stringToint(request.split("-")); 
        actual = loanImpl.opReqeustLoan(requestData);        
        expected = OperationResult.createReturnResult(ReturnCodeEnum.E008);
        Assert.assertNotNull(actual);
        Assert.assertEquals("", expected.toString(), actual.toString());      

        request="1-1000-40-10-1";
        requestData=stringToint(request.split("-"));  
        actual = loanImpl.opReqeustLoan(requestData);        
        expected = OperationResult.createReturnResult(ReturnCodeEnum.E007);
        Assert.assertNotNull(actual);
        Assert.assertEquals("", expected.toString(), actual.toString());      

    }
    
	public StringBuffer opListResult(StatueEnum statue,int[] result){
		StringBuffer stringBuffer = new StringBuffer();
		//stringBuffer.append("\n");

		// 金额信息
		
		stringBuffer.append("E015:贷款申请").append(result[0]).append("详细信息\r\n")
		.append("贷款申请状态:").append(statue).append("\r\n")
		.append("贷款本金(万元):").append(result[1]).append("\r\n")
		.append("还款期数:").append(result[2]).append("\r\n")
		.append("实际月利率(千分之):").append(result[3]).append("\r\n")
		.append("第").append(result[4]).append("个月份的还款本息总金额:")
		.append(result[5]).append("\r\n")
		.append("需还款本息总金额:").append(result[6])
		.append("\r\n").append("需个人还款本息总金额:")
		.append(result[7]);


		return stringBuffer;
    }
    @Test
    public void testOpList10()
    {
        //TODO:
    	loanImpl.opReboot();
        String request="1-1000-10-20-1";
        int[] requestData=stringToint(request.split("-"));
        loanImpl.opReqeustLoan(requestData);
        
        request="0-1-0-4";
        requestData=stringToint(request.split("-"));      
        OperationResult actual = loanImpl.opList(requestData);        
        OperationResult expected = OperationResult.createReturnResult(ReturnCodeEnum.E002);
        Assert.assertNotNull(actual);
        Assert.assertEquals("", expected.toString(), actual.toString());      

    }
    
    @Test
    public void testOpList11()
    {
        //TODO:
    	loanImpl.opReboot();
        String request="1-1000-10-20-1";
        int[] requestData=stringToint(request.split("-"));
        loanImpl.opReqeustLoan(requestData);
        
        request="1-0-0-4";
        requestData=stringToint(request.split("-"));      
        OperationResult actual = loanImpl.opList(requestData);        
        OperationResult expected = OperationResult.createReturnResult(ReturnCodeEnum.E011);
        Assert.assertNotNull(actual);
        Assert.assertEquals("", expected.toString(), actual.toString());      

    }
    
    @Test
    public void testOpList12()
    {
        //TODO:
    	loanImpl.opReboot();
        String request="1-1000-10-20-1";
        int[] requestData=stringToint(request.split("-"));
        loanImpl.opReqeustLoan(requestData);
        
        request="1-1-200-4";
        requestData=stringToint(request.split("-"));      
        OperationResult actual = loanImpl.opList(requestData);        
        OperationResult expected = OperationResult.createReturnResult(ReturnCodeEnum.E012);
        Assert.assertNotNull(actual);
        Assert.assertEquals("", expected.toString(), actual.toString());      

    }
    
    @Test
    public void testOpList13()
    {
        //TODO:
    	loanImpl.opReboot();
        String request="1-1000-10-20-1";
        int[] requestData=stringToint(request.split("-"));
        loanImpl.opReqeustLoan(requestData);
        
        request="1-1-0-4";
        requestData=stringToint(request.split("-"));      
        OperationResult actual = loanImpl.opList(requestData); 
        
        int[] calcResults = new int[] { 1,10, 240, 0 ,1,0,0,0};
        OperationResult expected = new OperationResult(opListResult(StatueEnum.A,calcResults).toString());
        Assert.assertNotNull(actual);
        Assert.assertEquals("", expected.toString(), actual.toString());      

        
    }
    
    @Test
    public void testOpList14()
    {
        //TODO:
    	loanImpl.opReboot();
        String request="1-1000-10-20-2";
        int[] requestData=stringToint(request.split("-"));
        loanImpl.opReqeustLoan(requestData);
        
        request="1-1-0-1";
        requestData=stringToint(request.split("-"));      
        OperationResult actual = loanImpl.opList(requestData);        
        OperationResult expected = OperationResult.createReturnResult(ReturnCodeEnum.E014);
        Assert.assertNotNull(actual);
        Assert.assertEquals("", expected.toString(), actual.toString());      

    }
    
    @Test
    public void testOpList15()
    {
        //TODO:
    	loanImpl.opReboot();
        String request="1-1000-10-20-1";
        int[] requestData=stringToint(request.split("-"));
        loanImpl.opReqeustLoan(requestData);
 
        request="1-1000-40-20-2";
        requestData=stringToint(request.split("-"));
        loanImpl.opReqeustLoan(requestData);

        request="1-1-0-1";
        requestData=stringToint(request.split("-"));      
        OperationResult actual = loanImpl.opList(requestData);        
        OperationResult expected = OperationResult.createReturnResult(ReturnCodeEnum.E014);
        Assert.assertNotNull(actual);
        Assert.assertEquals("", expected.toString(), actual.toString());      

    }
    

    @Test
    public void testOpList16()
    {
        //TODO:
    	loanImpl.opReboot();
        String request="1-1000-10-20-1";
        int[] requestData=stringToint(request.split("-"));
        loanImpl.opReqeustLoan(requestData);
 
        request="1-1-0-4";
        requestData=stringToint(request.split("-"));      
        OperationResult actual = loanImpl.opList(requestData); 
        

        int[] calcResults = new int[] { 1,10, 240, 0 ,1,0,0,0};
        OperationResult expected = new OperationResult(opListResult(StatueEnum.A,calcResults).toString());
        Assert.assertNotNull(actual);
        Assert.assertEquals("", expected.toString(), actual.toString());      

    }
    

    @Test
    public void testOpList17()
    {
        //TODO:
    	loanImpl.opReboot();
        String request="1-2000-20-5-1";
        int[] requestData=stringToint(request.split("-"));
        loanImpl.opReqeustLoan(requestData);
 
        request="1-1-10-4";
        requestData=stringToint(request.split("-"));      
        OperationResult actual = loanImpl.opList(requestData); 
        

        int[] calcResults = new int[] { 1,20, 60, 2 ,1,3733,212200,162200};
        OperationResult expected = new OperationResult(opListResult(StatueEnum.G,calcResults).toString());
        Assert.assertNotNull(actual);
        Assert.assertEquals("", expected.toString(), actual.toString());      

    }
    @Test
    public void testOpList18()
    {
        //TODO:
    	loanImpl.opReboot();
        String request="1-9000-80-15-1";
        int[] requestData=stringToint(request.split("-"));
        loanImpl.opReqeustLoan(requestData);
 
        request="2-8000-80-15-1";
        requestData=stringToint(request.split("-"));
        loanImpl.opReqeustLoan(requestData);

        request="3-8600-80-15-1";
        requestData=stringToint(request.split("-"));
        loanImpl.opReqeustLoan(requestData);
        
        request="4-8100-80-15-1";
        requestData=stringToint(request.split("-"));
        loanImpl.opReqeustLoan(requestData);

        request="4-10-10-6";
        requestData=stringToint(request.split("-"));   
        OperationResult actual = loanImpl.opList(requestData); 
        

        int[] calcResults = new int[] { 4,80, 180, 0 ,10,0,0,0};
        OperationResult expected = new OperationResult(opListResult(StatueEnum.C,calcResults).toString());
        Assert.assertNotNull(actual);
        Assert.assertEquals("", expected.toString(), actual.toString());      

    }
    @Test
    public void testOpList20()
    {
        //TODO:
    	loanImpl.opReboot();
        String request="1-2000-20-15-1";
        int[] requestData=stringToint(request.split("-"));
        loanImpl.opReqeustLoan(requestData);//加入失败
        
        request="2-8000-20-13-2";//base time 2
        requestData=stringToint(request.split("-"));
        loanImpl.opReqeustLoan(requestData);

        request="3-8000-20-13-3";
        requestData=stringToint(request.split("-"));
        loanImpl.opReqeustLoan(requestData);
        
        request="1-2000-20-15-3";
        requestData=stringToint(request.split("-"));
        //System.out.println(loanImpl.opReqeustLoan(requestData).toString());

        request="1-1-0-4";
        requestData=stringToint(request.split("-"));   
        OperationResult actual = loanImpl.opList(requestData); 
        
        int[] calcResults = new int[] { 1,20, 180, 0 ,1,0,0,0};
        OperationResult expected = new OperationResult(opListResult(StatueEnum.E,calcResults).toString());
        Assert.assertNotNull(actual);
        Assert.assertEquals("", expected.toString(), actual.toString());      

        request="1-1-0-6";
        requestData=stringToint(request.split("-"));   
        actual = loanImpl.opList(requestData); 
        

        calcResults = new int[] { 1,20, 180, 0 ,1,0,0,0};
        expected = new OperationResult(opListResult(StatueEnum.A,calcResults).toString());
        Assert.assertNotNull(actual);
        Assert.assertEquals("", expected.toString(), actual.toString());      

    }

    
}
