<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login Here</title>
<link rel="stylesheet" href="./login.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css">
</head>
<body>

	<!-- Hidden field to store status attribute for JavaScript access -->
	<input type="hidden" id="status"
		value="<%=request.getAttribute("status")%>">

	<main>
		<form method="post" action="loginpage">
			<h2>LOG IN</h2>
			<!-- Display server-side status message if available -->
			<p>${status}</p>

			<label for="email">Email:</label> <input type="email"
				placeholder="Enter Email Id" name="uemail" id="email" required>
			<br> <label for="upassword">Password:</label> <input
				type="password" placeholder="Enter Password" name="upassword"
				id="upassword" required> <br>
			<button type="submit" onclick="myFunction()">Login</button>

			<p>
				Don't have an account? <a href="register.jsp" class="signup-link">Create
					an account</a>
			</p>

		</form>
	</main>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>
	<script>
		// Display error message using SweetAlert if status is "error"
		const status = document.getElementById("status").value;
		if (status === "error") {
			swal("Sorry", "Invalid Data!!!", "error");
		}
	</script>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>
	<script>
		// Display success alert if status is "success"
		function myFunction() {
			alert("Hello! I am an alert box!");
		}
		const status = document.getElementById("status").value;
		if (status === "success") {
			swal("Congrats", "Login Successfully", "success");
		}
	</script>


</body>
</html>
