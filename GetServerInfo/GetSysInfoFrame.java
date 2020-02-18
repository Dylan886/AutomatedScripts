package cn.demo.getSysInfo;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import cn.demo.template.frame;

public class GetSysInfoFrame extends JFrame{
	private JPanel firstPanel = new JPanel();
	private JPanel secondPanel = new JPanel();
	private JLabel label = new JLabel("输入需要的信息");
	private JLabel usernameLabel = new JLabel("用户名：");
	private JLabel passwordlabel = new JLabel("密码：");
	private JLabel ipAddresslabel = new JLabel("主机地址：");
	private JLabel portlabel = new JLabel("端口：");
	private JTextField usernameField = new JTextField(8);
	private JTextField passwordField = new JTextField(8);
	private JTextField ipAddressField = new JTextField(15);
	private JTextField portField = new JTextField(8);
	private JButton button = new JButton("确定");
	private JButton openFileButton = new JButton("版本文件选择");
	private String filePath = "";
	public GetSysInfoFrame() {
		// TODO Auto-generated constructor stub
	}
	/*
	 * 创建并显示GUI
	 */
	private  void createAndShowGUI() {
		//预设测试数据
		usernameField.setText("root");		
		passwordField.setText("");
		ipAddressField.setText("");
		portField.setText("22");
		
        // 创建及设置窗口
		label.setLocation(300, 450);
		this.setTitle("检查系统版本脚本");
		this.setLocation(300, 450);
		this.setSize(300, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		//拼接图形界面
		firstPanel.add(label);
		secondPanel.add(usernameLabel);
		secondPanel.add(usernameField);
		secondPanel.add(passwordlabel);
		secondPanel.add(passwordField);
		secondPanel.add(ipAddresslabel);
		secondPanel.add(ipAddressField);
		secondPanel.add(portlabel);
		secondPanel.add(portField);
		secondPanel.add(openFileButton);
		secondPanel.add(button);
		this.add(firstPanel);
		this.add(secondPanel);
		
        // 显示窗口
		setLayout(new FlowLayout());
		setButtonAction();
		setResizable(false);
		this.pack();
        this.setVisible(true);
    }
	
	//设置监听事件
	private void setButtonAction() {
		ActionListener sendListener = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String username = usernameField.getText();
				String password = passwordField.getText();
				String ipAddress = ipAddressField.getText();
				String port = portField.getText();
				if(filePath !="" && (username.trim()).length() != 0 && (password.trim()).length() != 0 && (ipAddress.trim()).length() != 0 && (port.trim()).length() != 0) {
					//清空数据
//					usernameField.setText("");		
//					passwordField.setText("");
//					ipAddressField.setText("");
//					portField.setText("");

					// 调用方法匹配
					if(new GetSysInfoImplement().compare(username, password, ipAddress, port, "src/cn/demo/getSysInfo/template.xml")) {
						JOptionPane.showMessageDialog(null, "与目前该系统最新的版本号一致", "提示", JOptionPane.INFORMATION_MESSAGE); 
					} else {
						JOptionPane.showMessageDialog(null, "发生错误，请查看你的参数是否有错误", "警告", JOptionPane.ERROR_MESSAGE); 
					}
				}else {
					System.out.println("不能设置为空");
					JOptionPane.showMessageDialog(null, "参数不能设置为空", "警告", JOptionPane.ERROR_MESSAGE); 
					//("不能设置为空\n");
				}
			}
		};
		button.addActionListener(sendListener);
		//选择文件
		ActionListener getFileListener = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"XML", "xml");
				//设置文件类型
				chooser.setFileFilter(filter);
					//打开选择器面板
				int returnVal = chooser.showOpenDialog(new JPanel()); 
				 //保存文件从这里入手，输出的是文件名
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					System.out.println("你打开的文件是: " +
				chooser.getSelectedFile().getAbsolutePath());
					filePath = chooser.getSelectedFile().getAbsolutePath();
				}
			}
		};
		openFileButton.addActionListener(getFileListener);
	}
	
	public static void main(String[] args) {
		 // 显示应用 GUI
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GetSysInfoFrame().createAndShowGUI();
            }
        });
	}
}
