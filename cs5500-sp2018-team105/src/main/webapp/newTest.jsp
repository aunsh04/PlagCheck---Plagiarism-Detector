<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="stylesheet" type="text/css" href="basic.css">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

<script type="text/javascript" src="resources/js/jquery.js"></script>
<script type="text/javascript">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PlagiarismCheck</title>
</head>
<body background="image.jpg">

	<nav class="navbar navbar-default">

	<div class="container-fluid">

		<%
		if (session.getAttribute("userType").equals("admin"))
		{
	%>
		<a href="admin.jsp"> <img alt="Logo" src="logo.jpg">
		</a>
		<%
		}
	%>

		<%
		if (session.getAttribute("userType").equals("user"))
		{
	%>
		<a href="homePage.jsp"> <img alt="Logo" src="logo.jpg">
		</a>
		<%
		}
	%>

		<b> PlagiarismCheck </b>
	</div>

	<div class="w3-display-right">
		<a href="logout.jsp"> <b> Logout </b></a>
	</div>
	</nav>
	<font size="4.75px">
		<button type="button" name="back" onclick="history.back()"><b>Back</b></button>
	</font>
	<div class="header">
		<h1 style="color: white;" class="home">
			<b> New Test </b>
		</h1>
	</div>

	<div class="vision"
		style="position: relative;; left: 225px; right: 5px">

		<form action="GITController" method="get">

			<table align="center">

				<tr>
					<td><b> <span style="color: red;">${github}</span>
					</b></td>
				</tr>

				<tr>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td style="color: white;"><label>Enter
							Homework/Assignment number below: </label> <input style="color: black;"
						type="text" id="hw" name="hw" /></td>
				</tr>

				<tr>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td style="color: white;"><label>Enter Github
							Enterprise Username: </label> <input style="color: black;" type="text"
						id="username" name="username" /></td>
				</tr>

				<tr>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td style="color: white;"><label>Enter Github
							Enterprise Password: </label> <input style="color: black;"
						type="password" id="password" name="password" /></td>
				</tr>

				<tr>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td style="color: white;"><label>Enter path for files
							to be stored on system: </label> <input style="color: black;" type="text"
						id="path" name="path" /></td>
				</tr>

				<tr>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td style="color: white;"><label>Select the comparison
							strategy: </label> <select style="color: black;" name="strategy">
							<option style="color: black;" value="AST">AST Algorithm</option>
							<option style="color: black;" value="LCS">LCS</option>
							<option style="color: black;" value="Edit">Edit Distance</option>
							<option style="color: black;" value="Weighted">Weighted
								Polynomial</option>
							<option style="color: black;" value="Moss">Moss Trained</option>
					</select></td>
				</tr>

				<tr>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td>&nbsp;</td>
				</tr>

				<tr align="center">
					<td><b><input type="submit" value="Submit"></b></td>
				</tr>

			</table>

		</form>

	</div>

</body>
</html>