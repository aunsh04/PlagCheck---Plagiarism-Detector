<%@page import="java.util.List"%>
<%@page import="model.PlagiarismResult"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

<link rel="stylesheet" type="text/css" href="basic.css">

<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

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
		<a href="logout.jsp"><b>Logout</b></a>
	</div>

	</nav>
	<font size="4.75px"> <a href="newTest.jsp" class="element"><b>Back</b></a>
	</font>
	<ul>
    <li>
        <div class="input-color">
            <input type="text" value="High Severity>60%" />
            <div class="color-box" style="background-color: red;"></div>
            <!-- Replace "#FF850A" to change the color -->
        </div>
    </li>
    <li>
        <div class="input-color">
            <input type="text" value="Medium Severity 30-60%" />
            <div class="color-box" style="background-color: orange;"></div>
            <!-- Replace "navy" to change the color -->
        </div>
    </li>
     <li>
        <div class="input-color">
            <input type="text" value="Low Severity 0-30%" />
            <div class="color-box" style="background-color: green;"></div>
            <!-- Replace "navy" to change the color -->
        </div>
    </li>
</ul>
	<div class="thanos">

		<%
	
			List<PlagiarismResult> highSeverity = (List<PlagiarismResult>) request.getAttribute("high");
			List<PlagiarismResult> mediumSeverity = (List<PlagiarismResult>) request.getAttribute("medium");
			List<PlagiarismResult> lowSeverity = (List<PlagiarismResult>)request.getAttribute("low");
		%>

		<table align="center">

			<tr>
				<td style="color: white;">
					<h1 align="center" class="home">
						<b> Plagiarism Statistics </b>
					</h1>
				</td>
			</tr>

			<tr>
				<td style="color: white;">
					<h2 align="center">
						<b> Test Results </b>
					</h2>
				</td>
			</tr>

			<tr>
				<td>&nbsp;</td>
			</tr>

		</table>

	</div>

	<p align="center" style="color: red; font-size: xx-large;">
		<b>High Severity</b>
	</p>

	<div id="high" class="high">

		<table align="center">

			<tr>
				<td>&nbsp;</td>
			</tr>

			<%
				int count = 0;
				
				for (PlagiarismResult pr : highSeverity) 
				{
					 
			%>

			<tr>
				<td style="color: white;"><label> <%=pr.getStudentId1()%>
				</label></td>

				<td style="color: white;"><label> <%=pr.getStudentId2()%>
				</label></td>
			</tr>

			<tr>
				<td style="color: white;"><label> <%=pr.getFile1().getName()%>
				</label></td>

				<td style="color: white;"><label> <%=pr.getFile2().getName()%>
				</label></td>
			</tr>

			<tr>
				<td style="color: white;"><label> Percentage: <%=(double)Math.round(pr.getPercentage1()*100)/100%>
				</label></td>

				<td style="color: white;"><label> Percentage: <%=(double)Math.round(pr.getPercentage2()*100)/100%>
				</label></td>

				<td><a style="color: white;"
					href=<%= "\"result.jsp?Id=" + count + "\"" %>> <b> See
							Comparison Results </b>
				</a></td>

				<% 
						session.setAttribute("obj" + count, pr); 
					%>
			</tr>

			<tr>
				<td>&nbsp;</td>
			</tr>

			<%
				count++;
				
				}
			%>

			<tr>
				<td>&nbsp;</td>
			</tr>

		</table>

	</div>

	<p align="center" style="color: orange; font-size: xx-large;">
		<b>Medium Severity</b>
	</p>

	<div id="medium" class="medium">

		<table align="center">

			<tr>
				<td></td>
			</tr>

			<tr>
				<td>&nbsp;</td>
			</tr>

			<%
				for (PlagiarismResult pr : mediumSeverity) 
				{
					
			%>

			<tr>
				<td style="color: white;"><label> <%= pr.getStudentId1()%>
				</label></td>

				<td><label> <%= pr.getStudentId2()%>
				</label></td>
			</tr>

			<tr>
				<td style="color: white;"><label> <%= pr.getFile1().getName()%>
				</label></td>

				<td style="color: white;"><label> <%= pr.getFile2().getName()%>
				</label></td>
			</tr>

			<tr>
				<td style="color: white;"><label> Percentage: <%= (double)Math.round(pr.getPercentage1()*100)/100%>
				</label></td>

				<td style="color: white;"><label> Percentage: <%= (double)Math.round(pr.getPercentage2()*100)/100%>
				</label></td>

				<td><a style="color: white;"
					href=<%= "\"result.jsp?Id=" + count + "\"" %>> <b>See
							Comparison Results</b>
				</a></td>

				<% 
						session.setAttribute("obj" + count, pr); 
					%>
			</tr>

			<tr>
				<td>&nbsp;</td>
			</tr>

			<%
				count++;
				
				}
			%>

			<tr>
				<td>&nbsp;</td>
			</tr>

		</table>

	</div>

	<p align="center" style="color: green; font-size: xx-large;">
		<b>Low Severity</b>
	</p>

	<div id="low" class="medium">

		<table align="center">

			<tr>
				<td>&nbsp;</td>
			</tr>

			<%
				for (PlagiarismResult pr : lowSeverity) 
				{
					
			%>

			<tr>
				<td style="color: white;"><label><%= pr.getStudentId1()%>
				</label></td>

				<td><label> <%= pr.getStudentId2()%>
				</label></td>
			</tr>

			<tr>
				<td style="color: white;"><label> <%= pr.getFile1().getName()%>
				</label></td>

				<td style="color: white;"><label> <%= pr.getFile2().getName()%>
				</label></td>
			</tr>

			<tr>
				<td style="color: white;"><label> Percentage: <%= (double)Math.round(pr.getPercentage1()*100)/100%>
				</label></td>

				<td style="color: white;"><label> Percentage: <%= (double)Math.round(pr.getPercentage2()*100)/100%>
				</label></td>

				<td><a style="color: white;"
					href=<%= "\"result.jsp?Id=" + count + "\"" %>> <b>See
							Comparison Results </b></a></td>

				<% 
						session.setAttribute("obj" + count, pr); 
					%>
			</tr>


			<tr>
				<td>&nbsp;</td>
			</tr>

			<%
				count++;
			
				}
			%>

		</table>

	</div>

</body>
</html>