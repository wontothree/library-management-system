package com.cruds.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionManager {
	static
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/library_management?useSSL=false&serverTimezone=Asia/Seoul",
					"root",
					"Impossible1379!"
			);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

}
