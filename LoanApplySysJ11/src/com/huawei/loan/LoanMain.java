package com.huawei.loan;

import com.huawei.exam.Command;
import com.huawei.exam.ExamCommand;
import com.huawei.exam.ExamServer;

/**
 * <p>Title: ��ִ����</p>
 * <p/>
 * <p>Description: ���������޸ģ��������ע���ļ�����</p>
 * <p/>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p/>
 * <p>Company: </p>
 *
 * @author unknown
 * @version 1.0
 */

public class LoanMain {
    public static void main(String[] args) 
    {
        /**
         * ����Socket��������5555�˿ڣ���Socket��ȡ����ᶪ��Command���command����ִ��
         * Command���command�����Ѿ�ʵ���˴�Socket���յ��ַ�����Ľ�����ַ� ����ֻ��Ҫʵ��LoanImpl��ĸ�����ӿڼ��ɡ�
         */
        Command cmd = new ExamCommand();
        ExamServer examServer = new ExamServer(cmd);
        examServer.run(args);
    }

    /**
     * ˽�й��캯��,��������ʵ����
     */
    private LoanMain() {

    }
}
