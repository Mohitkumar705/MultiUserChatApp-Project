package com.Mohit.chatapp.views;

import com.Mohit.chatapp.dao.UserDAO;
import com.Mohit.chatapp.dto.UserDTO;
import com.Mohit.chatapp.utils.UserInfo;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class UserScreen extends JFrame {
	private JTextField useridtxt;
	Object o;
	private JPasswordField passwordField;

	
	public static void main(String[] args) {
		
					UserScreen window = new UserScreen();
					
	}
	UserDAO userDAO = new UserDAO();
	private void doLogin() {
		String userid =useridtxt.getText();
		char []password = passwordField.getPassword();
		UserDTO userDTO = new UserDTO(userid, password);
		try {
			String message = "";
			 if(userDAO.isLogin(userDTO)) {
				 message = "Welcome "+userid;
				 UserInfo.USER_NAME = userid;
				 JOptionPane.showMessageDialog(this, message);
				 setVisible(false);
				 dispose();
				 DashBoard dashBoard = new DashBoard(message);
				 dashBoard.setVisible(true);
			 }
			 else {
				 message = "Invalid Userid or Password";
			 }
			 JOptionPane.showMessageDialog(this, message);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void register() {
		String userid =useridtxt.getText();
		char []password = passwordField.getPassword();
		UserDAO userDAO = new UserDAO();
		UserDTO userDTO = new UserDTO(userid, password);
		try {
		int result=userDAO.add(userDTO);
		if(result>0) {
			JOptionPane.showMessageDialog(this,"Register Succussfully");
//			System.out.println("Record Added....");
		}
		else {
//			JOptionPane.showMessageDialog(this,"Register Succussfully");
			JOptionPane.showMessageDialog(this,"Register Fail");

//			System.out.println("Record Not Added");
		}
		}
		catch(ClassNotFoundException | SQLException ex) {
			System.out.println("DB Issue......");
			ex.printStackTrace();
		}
		catch(Exception ex) {
			System.out.println("some generic exception raised..");
		}
		System.out.println("userid"+userid+"password"+password.toString());
	}

	
	public UserScreen() {
		setBackground(new Color(128, 255, 255));
		setResizable(false);
		setTitle("LOGIN");
		getContentPane().setBackground(new Color(255, 255, 128));
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LOGIN");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(110, 0, 219, 104);
		getContentPane().add(lblNewLabel);
		
		useridtxt = new JTextField();
		useridtxt.setBounds(141, 116, 255, 40);
		getContentPane().add(useridtxt);
		useridtxt.setColumns(10);
		
		JLabel userlbl = new JLabel("User Id");
		userlbl.setFont(new Font("Tahoma", Font.BOLD, 18));
		userlbl.setBounds(22, 112, 139, 42);
		getContentPane().add(userlbl);
		
		JLabel pwdlbl = new JLabel("Password");
		pwdlbl.setFont(new Font("Tahoma", Font.BOLD, 18));
		pwdlbl.setBounds(22, 178, 139, 42);
		getContentPane().add(pwdlbl);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(141, 181, 255, 42);
		getContentPane().add(passwordField);
		
		JButton loginbt = new JButton("Login");
		loginbt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doLogin();
			}
		});
		loginbt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		loginbt.setBounds(76, 260, 112, 40);
		getContentPane().add(loginbt);
		
		JButton registerbt = new JButton("Register");
		registerbt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				register();
			}
		});
		registerbt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		registerbt.setBounds(269, 260, 112, 40);
		getContentPane().add(registerbt);
		setSize( 488, 376);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}