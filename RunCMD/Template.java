package cn.demo.getSysInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 用于执行cmd命令的基本脚本模板
public class Template {

	public Template() {
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * 入参command：需要执行的命令
	 * 调用cmd运行命令
	 */
	public Process runCmd(String command) throws IOException {
		return Runtime.getRuntime().exec("cmd /c " + command);
		
//		可供参考cmd命令
//		cmd /c dir 是执行完dir命令后关闭命令窗口。
//		cmd /k dir 是执行完dir命令后不关闭命令窗口。
//		cmd /c start dir 会打开一个新窗口后执行dir指令，原窗口会关闭。
//		cmd /k start dir 会打开一个新窗口后执行dir指令，原窗口不会关闭。
	}
	
	/*
	 * 入参p：Process 目前使用的cmd窗口程序
	 * 获取当前cmd的屏幕,并打印显示，保留此数据用于比对
	 * 通常在运行cmd的执行之后执行
	 */
	public String getCmd(Process p) throws IOException {
		
		//用于保存脚本结果
		String console = "";
		//用于获取cmd当前显示的结果
		BufferedReader br = null;
		
		br = new BufferedReader(new InputStreamReader(p.getInputStream()));
		try {
			//逐行读取数据
			String readLine = br.readLine();
			StringBuilder builder = new StringBuilder();
			while(readLine != null) {
				readLine = br.readLine();
				builder.append(readLine);
			}
			System.out.println(builder.toString());
			console = builder.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			if(br != null) {
				//关闭流
				br.close();
			}
		}
		return console;
	}
	public static void main(String[] args) {
		Template tem = new Template();
		try {
			Process p = tem.runCmd("ping www.baidu.com");
			String console = tem.getCmd(p);
			System.out.println(console);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
}
