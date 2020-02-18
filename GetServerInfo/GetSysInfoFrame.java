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
	private JLabel label = new JLabel("������Ҫ����Ϣ");
	private JLabel usernameLabel = new JLabel("�û�����");
	private JLabel passwordlabel = new JLabel("���룺");
	private JLabel ipAddresslabel = new JLabel("������ַ��");
	private JLabel portlabel = new JLabel("�˿ڣ�");
	private JTextField usernameField = new JTextField(8);
	private JTextField passwordField = new JTextField(8);
	private JTextField ipAddressField = new JTextField(15);
	private JTextField portField = new JTextField(8);
	private JButton button = new JButton("ȷ��");
	private JButton openFileButton = new JButton("�汾�ļ�ѡ��");
	private String filePath = "";
	public GetSysInfoFrame() {
		// TODO Auto-generated constructor stub
	}
	/*
	 * ��������ʾGUI
	 */
	private  void createAndShowGUI() {
		//Ԥ���������
		usernameField.setText("root");		
		passwordField.setText("");
		ipAddressField.setText("");
		portField.setText("22");
		
        // ���������ô���
		label.setLocation(300, 450);
		this.setTitle("���ϵͳ�汾�ű�");
		this.setLocation(300, 450);
		this.setSize(300, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		//ƴ��ͼ�ν���
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
		
        // ��ʾ����
		setLayout(new FlowLayout());
		setButtonAction();
		setResizable(false);
		this.pack();
        this.setVisible(true);
    }
	
	//���ü����¼�
	private void setButtonAction() {
		ActionListener sendListener = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String username = usernameField.getText();
				String password = passwordField.getText();
				String ipAddress = ipAddressField.getText();
				String port = portField.getText();
				if(filePath !="" && (username.trim()).length() != 0 && (password.trim()).length() != 0 && (ipAddress.trim()).length() != 0 && (port.trim()).length() != 0) {
					//�������
//					usernameField.setText("");		
//					passwordField.setText("");
//					ipAddressField.setText("");
//					portField.setText("");

					// ���÷���ƥ��
					if(new GetSysInfoImplement().compare(username, password, ipAddress, port, "src/cn/demo/getSysInfo/template.xml")) {
						JOptionPane.showMessageDialog(null, "��Ŀǰ��ϵͳ���µİ汾��һ��", "��ʾ", JOptionPane.INFORMATION_MESSAGE); 
					} else {
						JOptionPane.showMessageDialog(null, "����������鿴��Ĳ����Ƿ��д���", "����", JOptionPane.ERROR_MESSAGE); 
					}
				}else {
					System.out.println("��������Ϊ��");
					JOptionPane.showMessageDialog(null, "������������Ϊ��", "����", JOptionPane.ERROR_MESSAGE); 
					//("��������Ϊ��\n");
				}
			}
		};
		button.addActionListener(sendListener);
		//ѡ���ļ�
		ActionListener getFileListener = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"XML", "xml");
				//�����ļ�����
				chooser.setFileFilter(filter);
					//��ѡ�������
				int returnVal = chooser.showOpenDialog(new JPanel()); 
				 //�����ļ����������֣���������ļ���
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					System.out.println("��򿪵��ļ���: " +
				chooser.getSelectedFile().getAbsolutePath());
					filePath = chooser.getSelectedFile().getAbsolutePath();
				}
			}
		};
		openFileButton.addActionListener(getFileListener);
	}
	
	public static void main(String[] args) {
		 // ��ʾӦ�� GUI
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GetSysInfoFrame().createAndShowGUI();
            }
        });
	}
}
