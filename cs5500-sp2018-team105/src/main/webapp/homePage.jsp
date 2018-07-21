<%@page import="model.UserStats"%>
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
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<meta content="text/html; charset=utf-8">
<title>PlagiarismCheck</title>

</head>

<body background="image.jpg">
	<nav class="navbar navbar-default">
	<div class="container-fluid">

		<a href="homePage.jsp"> <img alt="Logo" src="logo.jpg">
		</a> <b>PlagiarismCheck</b>
	</div>

	<div class="w3-display-right">
		<a href="logout.jsp"> <b> Logout </b>
		</a>
	</div>

	</nav>

	<div class="header">
		<h1 style="color: white;" class="home">
			<b>Home </b>
		</h1>
	</div>

	<div class="batman">

		<table align="center">

			<tr>
				<td>
					<h3 style="color: white;" align="center">
						Welcome,
						<%=session.getAttribute("username")%>
					</h3>
				</td>
			</tr>

			<tr>
				<td>&nbsp;</td>
			</tr>

			<tr>
				<td style="color: white;"><font size="4.75px"> <a
						href="newTest.jsp" class="button"><b> Run a New Test </b></a>
				</font></td>
			</tr>

			<tr>
				<td>&nbsp;</td>
			</tr>

		</table>

		
		<table align="center">

			<form action="InstructorUsageController" method="get">

				<input type="submit" style="font-weight: bold;"
					value="Get Last Test Stats">

				<%
					UserStats stats = (UserStats) request.getAttribute("userStats");

					if (stats != null) {
				%>

				<table align="center">

					<tr>
						<td>&nbsp;</td>
					</tr>


					<tr>
						<td style="color: white;"><label>Number of Plagiarism Cases
								retrieved from the last run: </label></td>

						<td style="color: white;"><label> <%=stats.getResultsSize()%></label>
						</td>

					</tr>

					<%
						}
					%>

				</table>

			</form>
</table>

	</div>

</body>
</html>