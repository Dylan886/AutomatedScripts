package cn.demo.getSysInfo;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

/*
 *author: dylan
 *脚本的基本方法模板
 * 1.使用jsch链接服务器执行命令
 * 2.获取返回的运行结果
 * 3.读取文件内容（文件格式考虑xml或者json格式的），读取对应的系统的版本
 */
public class Template {

	public Template() {
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * 使用jsch
	 */
	  /** 
	   * 远程 执行命令并返回结果调用过程 是同步的（执行完才会返回） 
	   * @param host    主机名 
	   * @param user    用户名 
	   * @param psw 	密码 
	   * @param port    端口 
	   * @param command 命令 
	   * @return        运行结果
	   */  
	  public String exec(String host,String user,String psw,int port,String command){  
	    //用于保存结果
		String result="";  
	    Session session =null;  
	    ChannelExec openChannel =null;  
	    try {
	      //	创建session并连接服务器
	      session = new JSch().getSession(user, host, port);  
	      //不将连接主机加入到known_hosts中
	      session.setConfig("StrictHostKeyChecking", "no"); 
	      session.setPassword(psw);  
	      session.connect();  
	      
	      // 调用openChannel(String type) 可以在session上打开指定类型的channel
	      openChannel = (ChannelExec) session.openChannel("exec");  
	      openChannel.setCommand(command); 
	      int exitStatus = openChannel.getExitStatus();  
	      System.out.println(exitStatus);  
	      openChannel.connect();
	      
	      //获取运行结果
	      InputStream in = openChannel.getInputStream();    
	      BufferedReader reader = new BufferedReader(new InputStreamReader(in));    
	      String buf = null;  
	      while ((buf = reader.readLine()) != null) {  
	                result+= new String(buf.getBytes("gbk"),"UTF-8")+"    \r\n";    
	            }    
	    } catch (JSchException | IOException e) {  
	      result+=e.getMessage();  
	    }finally{  
	      if(openChannel!=null&&!openChannel.isClosed()){  
	        openChannel.disconnect();  
	      }  
	      if(session!=null&&session.isConnected()){  
	        session.disconnect();  
	      }
	      System.out.println(result);
	    }  
	    return result;  
	  }  
	
	/*
	 * 入参filePath：文件路径
	 * 获取文件内容
	 */
	public String getFileContent(String filePath) throws DocumentException { 
		File file = new File(filePath);
		/*
		 * 
		 */
		//获取文档对象
        SAXReader reader=new SAXReader();
        Document doc=reader.read(file);
        //获取根元素
        Element root=doc.getRootElement();
        //获取所有的子元素
        List<Element> list = root.elements("sysInfo");
        //输入name元素的内容
        for (Element element : list) {
            System.out.println(element.elementTextTrim("name"));
            System.out.println(element.elementTextTrim("version"));
            System.out.println("---------------");
        }
        
		return "";
	}
	
	public static void main(String[] args) {
		Template tem = new Template();
		try {
//			tem.getFileContent("src/cn/demo/getSysInfo/template.xml");
			String result = tem.exec("47.97.x.x", "root", "x", 22, "uname -a");
			System.out.println(result);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
//			p.destroy();
		}
	}
}
