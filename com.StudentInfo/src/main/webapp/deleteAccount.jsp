<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
if (session.getAttribute("uemail") == null) {
    response.sendRedirect("login.jsp");
}
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Delete Account</title>
    <link rel="stylesheet" href="./delete.css">
</head>
<body>
    <h2>Are you sure you want to delete your account?</h2>
    <form action="DeleteAccount" method="post">
        <input type="hidden" name="uemail" value="<%= session.getAttribute("uemail") %>"/>
        <button type="submit">Yes, Delete My Account</button>
        <a href="dashboard.jsp"><button type="button">Cancel</button></a>
    </form>
</body>
</html>
