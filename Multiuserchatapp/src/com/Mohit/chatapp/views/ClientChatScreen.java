package com.Mohit.chatapp.views;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.Mohit.chatapp.network.Client;
import com.Mohit.chatapp.utils.UserInfo;
import java.awt.Color;

public class ClientChatScreen extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextArea textArea ;
	private Client client;
	

	public static void main(String[] args) {
		
					try {
						ClientChatScreen frame = new ClientChatScreen();
						//frame.setVisible(true);
					} catch (UnknownHostException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				
	}
	private void Sendit() {
		String message = textField.getText();
		try {
			client.sendMessage(UserInfo.USER_NAME+"-"+message);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ClientChatScreen() throws UnknownHostException, IOException {

		setFont(new Font("Dialog", Font.BOLD, 10));
		setTitle(" Chit Chat");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 510, 344);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 474, 244);
		contentPane.add(scrollPane);
		
		textField = new JTextField();
		textField.setBackground(new Color(255, 128, 255));
		textField.setForeground(new Color(0, 0, 0));
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField.setBounds(10, 266, 360, 34);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton sendit = new JButton("Send Message");
		sendit.setForeground(new Color(255, 128, 255));
		sendit.setBackground(new Color(0, 0, 0));
		sendit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sendit();
				//textArea.setText("");
				
			}
		});
		sendit.setFont(new Font("Tahoma", Font.BOLD, 10));
		sendit.setBounds(380, 267, 112, 28);
		contentPane.add(sendit);
		textArea= new JTextArea();
		contentPane.add(textArea);
		textArea.setLineWrap(true);
		client= new Client(textArea);
		
		
		textArea.setFont(new Font("Monospaced", Font.ITALIC, 16));
		textArea.setBounds(10, 11, 474, 244);
		setVisible(true);
	}
}
