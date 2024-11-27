package com.StudentInfo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/DeleteAccount")
public class DeleteAccount extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String uemail = request.getParameter("uemail");
        HttpSession session = request.getSession();
    	Connection connection = ConnectionPool.getConnection();
        try {
                 PreparedStatement pst = connection.prepareStatement("DELETE FROM register WHERE uemail=?");

                pst.setString(1, uemail); // Set the email to delete the account

                int rowsAffected = pst.executeUpdate();
                if (rowsAffected > 0) {
                    // Invalidate session and redirect to login page with a message
                    session.invalidate();
                    response.sendRedirect("login.jsp?status=success&message=Account deleted successfully!");
                } else {
                    // Redirect with an error message if no account was found to delete
                    response.sendRedirect("dashboard.jsp?status=error&message=Failed to delete account!");
                }
            }
        catch (SQLException e) {
            e.printStackTrace();
            // Redirect with an error message
            response.sendRedirect("dashboard.jsp?status=error&message=An internal error occurred.");
        }finally {
			ConnectionPool.submitConnection(connection);
		}
    }
}
