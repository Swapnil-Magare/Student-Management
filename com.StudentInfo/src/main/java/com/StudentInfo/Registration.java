package com.StudentInfo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/registerserver")
public class Registration extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uname = request.getParameter("uname");
		String uemail = request.getParameter("uemail");
		String phoneString = request.getParameter("phone");
		long phone = Long.parseLong(phoneString);
		String upassword = request.getParameter("upassword");

		RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
		
		Connection connection = ConnectionPool.getConnection();
		try {
			System.out.println("conn done");

			PreparedStatement pst = connection
					.prepareStatement("INSERT INTO  register (uname, uemail, phone, upassword) VALUES (?, ?, ?, ?)");
			pst.setString(1, uname);
			pst.setString(2, uemail);
			pst.setLong(3, phone);
			pst.setString(4, upassword);

			int rowCount = pst.executeUpdate();
			System.out.println("Recoard insert sucessfully!!!!");
			if (rowCount > 0) {
				request.setAttribute("status", "success");
			} else {
				request.setAttribute("status", "error");
			}
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("status", "Error: An internal error occurred.");
			dispatcher.forward(request, response);
		}finally {
			ConnectionPool.submitConnection(connection);
		}
	}
}
