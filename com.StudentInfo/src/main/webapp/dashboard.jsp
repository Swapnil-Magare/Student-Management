<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
if (session.getAttribute("uemail") == null) {
	response.sendRedirect("login.jsp");
	return;
}
String uemail = (String) session.getAttribute("uemail");
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Home Page</title>
<link rel="stylesheet" href="./dashboard.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"
	integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>

	<header class="navbar">
		<div class="logo">
			<img class="log" src="image/images (1).png" alt="Logo">
		</div>
		<h2>
			Welcome,
			<%=session.getAttribute("uname")%>!
		</h2>
	</header>

	<main>
		<section class="user-details">
			<h3>
				Username:
				<%=session.getAttribute("uname")%></h3>
			<h3>
				Email:
				<%=session.getAttribute("uemail")%></h3>
			<h3>
				Contact No:
				<%=session.getAttribute("phone")%></h3>
			<h3>
				Password:
				<%=session.getAttribute("upassword")%></h3>
		</section>

		<nav>
			<ul class="nav-links">
				<!--             <li><a href="./homepage.html">Details</a></li> -->
				<li><a href="showDetails?uemail=<%=uemail%>">Show Details</a></li>
				<li><a href="updateForm.jsp">Update</a></li>
				<li><a href="deleteAccount.jsp">Delete Account</a></li>
			</ul>
		</nav>

		<%-- Displaying success/error messages based on status --%>
		<%
		String status = request.getParameter("status");
		String message = request.getParameter("message");
		if (status != null && message != null) {
		%>
		<section
			class="alert alert-<%=status.equals("success") ? "success" : "danger"%>">
			<%=message%>
		</section>
		<%
		}
		%>
	</main>

	<section id="logout">
		<a href="Logout"><button>Log Out</button></a>
	</section>

	<footer>
		<p class="copy">© swapnilmagare42@gmail.com</p>
	</footer>

</body>
</html>
