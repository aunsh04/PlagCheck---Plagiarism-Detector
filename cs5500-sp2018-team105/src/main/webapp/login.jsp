<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<link rel="stylesheet" type="text/css" href="basic.css">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<head>
<meta content="text/html; charset=utf-8">
<title>PlagiarismCheck</title>

<style type="text/css">
#wrap {
	width: 500px;
}

.ironman {
	width: 400px;
	height: 200px;
	float: left;
	text-align: center;
}
</style>

</head>
<body background="image.jpg">

	<nav class="navbar navbar-default">
	<div class="container-fluid">
		<img alt="Logo" src="logo.jpg"> 
		<b> PlagiarismCheck </b>
	</div>
	</nav>

	<div class="login">
		<h1 class="login"> <b> Please identify yourself </b> </h1>
	</div>

	<div class="ironman" align="center" style="position:relative; ; left: 200px ; right: 10px">

		<form action="LoginController" method="get">

			<table align="center">

				<tr>
					<td><label> Username: </label> &nbsp; <input type="text"
						id="uname" name="uname" /></td>
				</tr>

				<tr>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td><label>Password: </label> &nbsp; <input type="password"
						id="pwd" name="pwd" /></td>
				</tr>

				<tr>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td>

 					 <input type="submit" value="Login" class="loginSubmit">

					</td>
				</tr>
				
				<tr>
				
					<td>
						<span style="color:red;">${errMsg}</span>
					</td>
				
				</tr>
				
				<tr>
					<td>
						<span style="color:red;">${userNameErr}</span>
					</td>
				
				</tr>
				
				<tr>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td style="color: white;"><a href="javascript:;"
						onclick="alert('Please contact admin')">Forgot Password?</a></td>
				</tr>

			</table>

		</form>

	</div>

</body>
</html>