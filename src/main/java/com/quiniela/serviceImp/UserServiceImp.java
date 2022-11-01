package com.quiniela.serviceImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.quiniela.dbutil.DBUtil;
import com.quiniela.service.UserService;

@Service
public class UserServiceImp implements UserService{

	Connection connection;
	int flag = 0;
	
	public  UserServiceImp() throws SQLException {
		connection = DBUtil.getConnection();
	}
	
	@Override
	public int loginValidation(String username, String password) {
		
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username = '"+username+"'");
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				if(rs.getString(4).equals(username) && rs.getString(5).equals(password)) {
					flag = 1;
				}else {
					System.out.println("Usuario/Contraseña invalido");	
					flag = 0;
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return flag;
	}
	
	

}
