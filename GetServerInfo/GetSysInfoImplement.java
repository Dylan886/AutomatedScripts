package cn.demo.getSysInfo;

import org.dom4j.DocumentException;

import cn.demo.template.Template;

public class GetSysInfoImplement extends Template {

	public GetSysInfoImplement() {
		// TODO Auto-generated constructor stub
	}
	/*
	 * �ԱȻ�ȡ�İ汾��Ϣ�����ṩ�İ汾��Ϣ����ƥ��
	 */
	public Boolean compare(String username, String password, String ipAddress, String port, String filePath) {
		Boolean result = false;
		String currentSysInfo = "";
		// ��ȡ���н��
		String sysInfo = super.exec(username, password, ipAddress, Integer.valueOf(port) , "uname -a");
		System.out.println("sysInfo"+sysInfo);
		//�������ݣ���ȡ�汾�Ͱ汾��		
		String[] info = sysInfo.split(" ");
		try {
			currentSysInfo = super.getFileContent(filePath, info[0]);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();
		}finally {
			System.out.println("currentSysInfo"+currentSysInfo);			
		}
		System.out.println(currentSysInfo+ "      "+info[2] );
		if(currentSysInfo != "" && currentSysInfo.equals(info[2])) {
			result = true;
		}
		return result;
	}
}
