package com.company.dao.inter;

import java.sql.Connection;
import java.sql.DriverManager;

public abstract class AbstractDAO {

	public Connection connection() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		String url="jdbc:mysql://localhost:3306/resume?serverTimezone=UTC";
		String userName="root";
		String password="1234";
		Connection c=DriverManager.getConnection(url, userName, password);
		return c;
	}
}
