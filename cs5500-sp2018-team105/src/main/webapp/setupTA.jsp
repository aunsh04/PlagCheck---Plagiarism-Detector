<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="stylesheet" type="text/css" href="basic.css">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js">
</script>

<script language="javascript">

function validateEmail() 
{
    var x = document.forms["setup"]["email"].value;
    var atpos = x.indexOf("@");
    var dotpos = x.lastIndexOf(".");
    if (atpos<1 || dotpos<atpos+2 || dotpos+2>=x.length) 
    {
        alert("Not a valid e-mail address");
        return false;
    }
}

</script>


<meta content="text/html; charset=utf-8">
<title>PlagiarismCheck</title>
</head>
<body background="image.jpg">

	<nav class="navbar navbar-default">
	
	<div class="container-fluid">
		
	<%
		if (session.getAttribute("userType").equals("admin"))
		{
	%>
		<a href="admin.jsp"> 
			<img alt="Logo" src="logo.jpg"> 
		</a>
	<%
		}
	%>
	
	<%
		if (session.getAttribute("userType").equals("user"))
		{
	%>
		<a href="homePage.jsp"> 
			<img alt="Logo" src="logo.jpg"> 
		</a>
	<%
		}
	%>
		
		<b> PlagiarismCheck </b>
	</div>
	
	<div class="w3-display-right">
			<a href="logout.jsp"> <b> LogOut </b></a>
	</div>
	
	</nav>
	
		<font size="4.75px">
		<button type="button" name="back" onclick="history.back()"><b>Back</b></button>
	</font>

	<div class="setup">
		<h1 style="color: white;" class="setup" align="center"> <b>Teaching Assistant Account Setup</b></h1>
	</div>

	<% session.setAttribute("instructor", 1); %>

	<div class="superman" style="position:relative; ; left: 190px ; right: 5px">

		<form name="setup" action="AdminController" onsubmit="return validateEmail();" method="get">

			<table align="center">
			
				<tr>
					<td>
						<span style="color:red;">${emptyFields}</span>
					</td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td>
						<span style="color:red;">${duplicate}</span>
					</td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td style="color: white;"><label>First Name: </label> &nbsp; <input style="color: black" type="text"
						id="fname" name="fname" /></td>
				</tr>

				<tr>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td style="color: white;"><label>Last Name: </label> &nbsp; <input style="color: black" type="text"
						id="lname" name="lname" /></td>
				</tr>

				<tr>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td style="color: white;"><label>Username: </label> &nbsp; <input style="color: black" type="text"
						id="uname" name="uname" /></td>
				</tr>

				<tr>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td style="color: white;"><label>Password: </label> &nbsp; <input style="color: black" type="password"
						id="pwd" name="pwd" /></td>
				</tr>

				<tr>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td style="color: white;"><label>Email: </label> &nbsp; <input style="color: black" type="text"
						id="email" name="email" /></td>
				</tr>

				<tr>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td><input type="submit" value="Submit" class="loginSubmit">
					</td>
				</tr>

			</table>

		</form>

	</div>

</body>
</html>