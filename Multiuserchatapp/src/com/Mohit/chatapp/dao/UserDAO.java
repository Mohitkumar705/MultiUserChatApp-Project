package com.Mohit.chatapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.Mohit.chatapp.dto.UserDTO;
import com.Mohit.chatapp.utils.Encryption;

public class UserDAO {
	public boolean isLogin(UserDTO userDTO) throws SQLException, ClassNotFoundException, Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		final String SQL = "Select userid from users where userid=? and password=?";
		try {
			con = CommanDAO.createConnection();
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, userDTO.getUserid());
			String encryptedPwd = Encryption.passwordEncrypt(new String(userDTO.getPassword()));
			pstmt.setString(2, encryptedPwd);
			rs = pstmt.executeQuery();
			return rs.next();
		}
		finally {
			if(rs!=null) {
				rs.close();
			}
			if(pstmt!=null) {
				pstmt.close();
			}
			if(con!=null) { 
				con.close();
			}	
		}
	}
    	 public int add(UserDTO userDTO) throws ClassNotFoundException, SQLException, Exception{
    		 System.out.println("Rec"+ userDTO.getUserid()+" "+userDTO.getPassword());
    		 Connection connection = null;
    		 Statement stmt =null;//Query
    		 try {//Guarded region
    		 connection= CommanDAO.createConnection();// step 1. connection create
    		 // we do a Query
    		 stmt = connection.createStatement();
    		int record=stmt.executeUpdate( "insert into users (userid,password) values('"+userDTO.getUserid()+"','"+Encryption.passwordEncrypt(new String(userDTO.getPassword()))+"')");
    		return record;
}
    		 finally{
    			 if(stmt!=null) {
    			 stmt.close();
    		 }
    			 if(connection!=null) {
    		connection.close();
    		 }
    		 }
     }
}
