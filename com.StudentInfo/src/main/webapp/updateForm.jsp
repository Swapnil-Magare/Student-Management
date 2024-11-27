<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Data</title>
    <link rel="stylesheet" href="./update.css">
</head>
<body>

<main>
    <header>
        <h1>Update Now</h1>
    </header>

    <form method="post" action="Update">
        <h2>Update Details</h2>
        
        <!-- Display status message if available -->
        <p>${status}</p>

        <!-- Hidden field to keep the ID -->
        <input type="hidden" name="id" value="${id}">

        <label for="name">New Full Name:</label>
        <input type="text" placeholder="New Full Name" name="uname" id="name" value="${uname}" required>
<br>
        <input type="hidden" name="uemail" id="email" value="${uemail}">

        <label for="phone">New Phone Number:</label>
        <input type="text" placeholder="New Phone Number" name="phone" id="phone" value="${phone}" 
               pattern="[1-9]{1}[0-9]{9}" maxlength="10" required>
<br>
        <label for="pwd">New Password:</label>
        <input type="password" placeholder="New Password" name="upassword" id="pwd" value="${upassword}" required>
<br>
        <button type="submit">Update</button>
<br>
        <p><a href="./dashboard.jsp">Return to Dashboard</a></p>
    </form>
</main>

</body>
</html>
