package com.StudentInfo;

import java.io.IOException;
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

@WebServlet("/Update")
public class Update extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id"); // Assuming `id` is passed as a URL parameter
		if (id == null || id.isEmpty()) {
			response.sendRedirect("errorPage.jsp"); // Redirect if id is not provided
			return;
		}

		Connection connection = ConnectionPool.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM register WHERE id=?");
			statement.setInt(1, Integer.parseInt(id));
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				// Set attributes for each field to be retrieved
				request.setAttribute("id", id);
				request.setAttribute("uname", rs.getString("uname"));
				request.setAttribute("phone", rs.getString("phone"));
				request.setAttribute("upassword", rs.getString("upassword"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("updateForm.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uname = req.getParameter("uname");
		String uemail = req.getParameter("uemail");
		String phoneString = req.getParameter("phone");
		String upassword = req.getParameter("upassword");
		Connection connection = ConnectionPool.getConnection();
		try {

			// Convert the phone number string to long
			long phone = Long.parseLong(phoneString);

			// Prepare the SQL statement

			PreparedStatement pst = connection
					.prepareStatement("UPDATE register SET uname=?, phone=?, upassword=? WHERE uemail=?");

			// Set parameters
			pst.setString(1, uname); // For uname
			pst.setLong(2, phone); // For phone as long
			pst.setString(3, upassword); // For upassword
			pst.setString(4, uemail); // For uemail (WHERE clause)

			int rowUpdated = pst.executeUpdate();
			if (rowUpdated > 0) {
				// Update session attributes with new values
				HttpSession session = req.getSession();
				session.setAttribute("uname", uname);
				session.setAttribute("uemail", uemail);
				session.setAttribute("phone", phoneString); // Keep phone as String for session
				session.setAttribute("upassword", upassword);

				// Redirect to the dashboard with success message
				resp.sendRedirect("dashboard.jsp?status=success&message=Data updated successfully!");
			} else {
				// Redirect back with an error message if update fails
				resp.sendRedirect("dashboard.jsp?status=error&message=Failed to update data!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// Redirect with an error message
			resp.sendRedirect("dashboard.jsp?status=error&message=An internal error occurred.");
		} catch (NumberFormatException e) {
			// Handle invalid phone number format
			resp.sendRedirect("updateForm.jsp?status=error&message=Invalid phone number format!");
		} finally {
			ConnectionPool.submitConnection(connection);
		}
	}
}
