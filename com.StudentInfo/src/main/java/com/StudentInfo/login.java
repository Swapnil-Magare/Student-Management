package com.StudentInfo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class login
 */
@WebServlet("/loginpage")
public class login extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uemail = request.getParameter("uemail");
		String upassword = request.getParameter("upassword");
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher = null;
		PrintWriter pw = response.getWriter();
		pw.print("<html><body>");
		pw.print("<a href='pageb?username=" + uemail + "'>Click here!!!</a>");

		pw.print("</form></body></html>");

		Connection connection = ConnectionPool.getConnection();

		try {
			PreparedStatement pst = connection
					.prepareStatement("SELECT * FROM  register WHERE uemail = ? AND upassword = ?");
			pst.setString(1, uemail);
			pst.setString(2, upassword);

			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				// Successful login
				System.out.println("Login sucessfully!!!!");
				session.setAttribute("uemail", rs.getString("uemail"));
				session.setAttribute("uname", rs.getString("uname"));
				session.setAttribute("phone", rs.getString("phone"));
				session.setAttribute("upassword", rs.getString("upassword"));
				dispatcher = request.getRequestDispatcher("dashboard.jsp");
			} else {
				// Failed login
				System.out.println("Login Failed !!!!");
				request.setAttribute("status", "error");
				dispatcher = request.getRequestDispatcher("login.jsp");
			}
			dispatcher.forward(request, response);

		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("status", "Error: An internal error occurred.");

		} finally {
			ConnectionPool.submitConnection(connection);
		}
	}

}
