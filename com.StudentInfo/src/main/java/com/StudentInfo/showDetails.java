package com.StudentInfo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/showDetails")
public class showDetails extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uemail = request.getParameter("uemail"); // Retrieve email from URL parameter
		response.setContentType("text/html");

		if (uemail == null || uemail.isEmpty()) {
			response.getWriter().println("<h1>Error: Email parameter is missing.</h1>");
			return;
		}
		Connection connection = ConnectionPool.getConnection();
		try {
			String query = "SELECT uname, phone, upassword FROM register WHERE uemail = ?";
			try (PreparedStatement pstmt = connection.prepareStatement(query)) {
				pstmt.setString(1, uemail);
				ResultSet rs = pstmt.executeQuery();

				PrintWriter pw = response.getWriter();
				pw.print("<html><head>");
				pw.print("<link rel='stylesheet' href='showdetails1.css'>"); // Replace with your CSS file // path
				pw.print("</head><body>");
				pw.print("<div class='showdetails-container'>");
				pw.print("<h1>User Details</h1>");

				if (rs.next()) {
					pw.print("<h3>Username: " + rs.getString("uname") + "</h3>");
					pw.print("<h3>Email: " + uemail + "</h3>");
					pw.print("<h3>Contact No: " + rs.getString("phone") + "</h3>");
					pw.print("<h3>Password: " + rs.getString("upassword") + "</h3>");
				} else {
					 pw.print("<p class='error-message'>No record found for email: " + uemail + "</p>");
				}

				pw.print("<a href='./dashboard.jsp'><button type='button'>Go back to Home</button></a>");
				pw.print("</div>");
				pw.print("</body></html>");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			response.getWriter().println("<h1>Error accessing database</h1>");
		} finally {
			ConnectionPool.submitConnection(connection);
		}
	}
}
