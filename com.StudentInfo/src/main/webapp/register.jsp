<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Student Information Registration</title>
<!-- Uncomment the line below to include custom CSS -->
<link rel="stylesheet" href="./register.css"> 
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css">
</head>
<body>

	<input type="hidden" id="status"
		value="<%=request.getAttribute("status")%>">

	<main>
		<header>
			<h1>Register Now</h1>
		</header>

		<form method="post" action="registerserver" class="register-form"
			id="register-form">
			<h2>Register</h2>
			<!-- Display status message if available -->
			<p>${status}</p>

			<label for="name">Full Name:</label> <input type="text"
				placeholder="Full Name" name="uname" id="name" required> 
			 <label for="email">Email:</label> <input type="email"
				placeholder="Email Id" name="uemail" id="email" required>  
			  <label for="phone">Phone Number:</label> <input type="text"
				placeholder="Phone Number" name="phone" id="phone"
				pattern="[1-9]{1}[0-9]{9}" maxlength="10" required>  
			  <label for="pwd">Password:</label> <input type="password"
				placeholder="Password" name="upassword" id="pwd" required>  
			  <label for="confirm_pwd">Confirm Password:</label> <input
				type="password" placeholder="Confirm Password"
				name="confirm_password" id="confirm_pwd" required>  
			 
			<button type="submit">Register</button>
			 
			 
			<p>
				Already have an account? <a href="./login.jsp">Login Now</a>
			</p>
		</form>
	</main>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>
	<script>
		// Display success alert if status is "success"
		const status = document.getElementById("status").value;
		if (status === "success") {
			swal("Congrats", "Account Created Successfully", "success");
		}
	</script>

</body>
</html>
