package com.StudentInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConnectionPool {
	private static String url="jdbc:postgresql://localhost:5432/StudentInfodb";
	private static String user="postgres";
	private static String password="root";
	private static String driver="org.postgresql.Driver";
	private static ArrayList<Connection> connectionPool=new ArrayList<Connection>();
	private static final int POOL_SIZE=5;
	
	static {
		try {
			Class.forName(driver);
			Connection connection=null;
						
			for (int i =1; i <=POOL_SIZE; i++) {
				connection=DriverManager.getConnection(url, user, password);
				connectionPool.add(connection);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		if (!connectionPool.isEmpty()) {
			return connectionPool.remove(0);
		} else {
            return createConnection();
		}
	}
	
	private static Connection createConnection() {
		Connection con=null;
		
		try {
			con=DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public static void submitConnection(Connection connection) {
		if (connectionPool.size()<POOL_SIZE) {
			connectionPool.add(connection);
		} else {
            try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}