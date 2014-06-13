<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.swproj.*"%> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"     
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
html, body {
	height: 100%;
	width: 100%;
	padding: 0;
	margin: 0;
}

#top {
	width: 100%;
	height: 100%;
	background: url('vDayImage4.jpg')  center center fixed;
	background-size: 100% 100%;
	margin: 0;
	padding: 0;
}
/* 
#botleft {
	width: 40%;
	height: 100%;
	background: url('backgroundSW5.jpg') no-repeat center center fixed;
	background-size: 100% 100%;
	margin: 0;
	padding: 0;
	float: left
}
 */
#botright {
	height: 100%;
	width: 60%;
	float: left;
	background: url('backgroundSW5.jpg') no-repeat center center fixed;
	background-size: 100% 100%;
	margin: 0;
	padding: 0;
}
</style>
<title>Welcome to SW Project</title>
</head>
<body style="background-color:#C0C0C0">
<center>
	<div id="top">

		<br> Semantic Web Course Project - Spring 14<br>
		<h3>
			<u>Veteran Affairs Geographic Distribution of Expenditures FY08</u>
		</h3>
		Created By <br>
		<b>Omkar Kannav</b>

	</div>

	<div id="botleft">
	
		<form action="ReportHandlerServlet" method="post">
			<br>
			<br> <b><u>Select a Dataset:</u></b><br> <INPUT
				TYPE="radio" name="dataset" value="dataset1154">Dataset
			1154: by State and County</input> <br> <INPUT TYPE="radio"
				NAME="dataset" VALUE="dataset1155">Dataset 1155: by
			Congressional District</input><br> <br>

			<jsp:useBean id="selectLists" class="com.swproj.SelectLists"
				scope="page" />
			<b><u>Select a State:</u></b><br> <select id="state"
				name="state">
				<c:forEach var="state" items='${selectLists.states}'>
					<option value='${state}'>${state}</option>
				</c:forEach>
			</select><br><br> 
			<b><u>Select an Attribute:</u></b><br> <select
				id="attribute" name="attribute">
				<c:forEach var="attr" items='${selectLists.attributes}'>
					<option id'${attr}'>${attr}</option>
				</c:forEach>
				


			</select><br> <br> <b><u>Select a report type:</u></b><br> <INPUT
				TYPE="radio" name="rType" value="chart">Column Chart
			Visualization</input><br> <INPUT TYPE="radio" NAME="rType" VALUE="map">Map
			Visualization</input> <br> <br> <input type="submit" value="Submit" />
		</form>
	</div>
	</center>
	<!-- <div id="botright"></div> -->
	
</body>
</html>