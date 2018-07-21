<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.File"%>
<%@page import="model.PlagiarismResult"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

<!-- <script type="text/javascript" src="resources/js/jquery.js"></script> -->

<!-- jQuery library -->
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js">
</script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">


<script type="text/javascript" src="html2canvas.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.3/jspdf.min.js"></script>


<script>
window.takeScreenShot = function() {
    html2canvas(document.getElementById("content"), {        
        width:2500,
        height:2000,
        onclone: (doc) => {
        	console.log(doc);
        }
    }).then((canvas) => {
    		/* document.body.appendChild(canvas); */
    		saveImage(canvas);
    });
}

function saveImage(canvas) {
	var imgsrc = canvas.toDataURL("image/jpeg",1.0);
	var pdf = jsPDF();
	pdf.addImage(imgsrc,'JPEG',0,0);
	pdf.save("report.pdf");	
}

</script>
<title>PlagiarismCheck</title>

</head>
<body background="image.jpg">

	<nav class="navbar navbar-default">

	<div id="abc" class="container-fluid">

		<%
			if (session.getAttribute("userType").equals("admin")) {
		%>
		<a href="admin.jsp"> <img alt="Logo" src="logo.jpg">
		</a>
		<%
			}
		%>

		<%
			if (session.getAttribute("userType").equals("user")) {
		%>
		<a href="homePage.jsp"> <img alt="Logo" src="logo.jpg">
		</a>
		<%
			}
		%>

		<b> PlagiarismCheck </b>
	</div>

	<div class="w3-display-right">
		<a href="logout.jsp"><b> Logout </b></a>
	</div>
	</nav>

	<font size="4.75px">
		<button type="button" name="back" onclick="history.back()"><b>Back</b></button>
	</font>
	<font size="4.75px">
		<button onclick="takeScreenShot()">
			<b>Download File</b>
		</button>
	</font>
	<%
		String str = request.getParameter("Id");
		PlagiarismResult pr = (PlagiarismResult) session.getAttribute("obj" + str);
	%>

	<table id="content" align="center">
		<tr>
			<td>
				<div style="height: 1000px; overflow: auto;">
					<table class="table1" border="5" style="float: left;">

						<%
							File file1 = pr.getFile1();

							String eachLine1 = null;

							FileReader fileReader1 = new FileReader(file1);
							BufferedReader buffer1 = new BufferedReader(fileReader1);

							Integer count1 = 1;
						%>
						<tr>
							<td style="color: white;" class="alignment"><label>
									Student ID: <%=pr.getStudentId1()%></label></td>
						</tr>

						<tr>
							<td style="color: white;" class="alignment"><label>
									File Name: <%=file1.getName()%></label></td>
						</tr>


						<%
							while ((eachLine1 = buffer1.readLine()) != null) {
						%>

						<tr>
							<%
								if (pr.getLines().getLineFile1().contains(count1)) {
							%>
							<td class="alignment" bgcolor="orange"><label> <%=eachLine1%>
							</label></td>

							<%
								}

									else {
							%>
							<td style="color: white;" class="alignment"><label>
									<%=eachLine1%>
							</label></td>
							<%
								}

									count1++;
							%>
						</tr>

						<%
							}

							buffer1.close();
						%>

					</table>
				</div>
			</td>
			<td>
				<div style="height: 1000px; overflow: auto;">
					<table class="table2" border="5" style="float: left;">

						<%
							File file2 = pr.getFile2();

							String eachLine2 = null;

							FileReader fileReader2 = new FileReader(file2);
							BufferedReader buffer2 = new BufferedReader(fileReader2);

							Integer count2 = 1;
						%>

						<tr>
							<td style="color: white;"><label> Student ID: <%=pr.getStudentId2()%></label>
							</td>
						</tr>

						<tr>
							<td style="color: white;"><label> File Name: <%=file2.getName()%></label>
							</td>
						</tr>

						<%
							while ((eachLine2 = buffer2.readLine()) != null) {
						%>

						<tr>
							<%
								if (pr.getLines().getLineFile2().contains(count2)) {
							%>

							<td class="alignment" bgcolor="orange"><label> <%=eachLine2%>
							</label></td>

							<%
								}

									else {
							%>
							<td style="color: white;" class="alignment"><label>
									<%=eachLine2%>
							</label></td>

							<%
								}

									count2++;
							%>
						</tr>

						<%
							}

							buffer2.close();
						%>

					</table>
				</div>
			</td>
		</tr>
	</table>

</body>
</html>