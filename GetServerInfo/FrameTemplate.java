package cn.demo.getSysInfo;

import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

//GUI模板
public class frame extends JFrame{
	private JTextField jTextField = new JTextField(8);
	private JPanel panel = new JPanel();
	private JLabel label = new JLabel("输入需要的信息");
	private JButton button = new JButton("确定");
	//需要补一个单例模式
	public frame() {
		// TODO Auto-generated constructor stub
	}
	/*
	 * 创建并显示GUI
	 */
	private  void createAndShowGUI() {
		
        // 创建及设置窗口。
		this.setTitle("某脚本   ");
		this.setLocation(300, 450);
		this.setSize(300, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		//拼接图形界面
		panel.add(label);
		panel.add(jTextField);
		panel.add(button);
		this.add(panel);
		
        // 显示窗口
//        this.pack();
		setButtonAction();
        this.setVisible(true);
    }
	
	//设置监听事件
	private void setButtonAction() {
		ActionListener sendListener = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String content = jTextField.getText();
				if((content.trim()).length() != 0) {

				jTextField.setText("");		
				System.out.println(content);
				}else {
					System.out.println("不能设置为空");
					//("不能设置为空\n");
				}
			}
		};
		button.addActionListener(sendListener);
		
	}
	
	public static void main(String[] args) {
		 // 显示应用 GUI
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new frame().createAndShowGUI();
            }
        });
	}
}
