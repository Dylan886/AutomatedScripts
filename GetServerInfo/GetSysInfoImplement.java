package cn.demo.getSysInfo;

import org.dom4j.DocumentException;

import cn.demo.template.Template;

public class GetSysInfoImplement extends Template {

	public GetSysInfoImplement() {
		// TODO Auto-generated constructor stub
	}
	/*
	 * 对比获取的版本信息和子提供的版本信息进行匹配
	 */
	public Boolean compare(String username, String password, String ipAddress, String port, String filePath) {
		Boolean result = false;
		String currentSysInfo = "";
		// 获取运行结果
		String sysInfo = super.exec(username, password, ipAddress, Integer.valueOf(port) , "uname -a");
		System.out.println("sysInfo"+sysInfo);
		//处理数据，获取版本和版本号		
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
