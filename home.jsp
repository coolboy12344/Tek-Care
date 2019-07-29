<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Map.Entry"%>    
<%@page import="java.sql.Connection"%>  
<%@page import="java.sql.PreparedStatement"%>   
<%@page import="java.sql.ResultSet"%>   

<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Web App </title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	<script src="https://use.fontawesome.com/releases/v5.0.8/js/all.js"></script>
	<link href="style1.css" rel="stylesheet">
 <title>Insert title here</title>
 
<style>
th{
 background-color: #FF69B4;
}
tr:nth-child(even) {
  background-color: #D1DDF1;
}
 tr:nth th-child(odd) {
  background-color: #4CAF50;
  tr:hover {background-color: #f5f5f5;}
}
table{
  border: 15px solid black;
}  

</style>

</head>
<body>
<!-- Navigation -->
<nav class="navbar navbar-expand-md navbar-light bg-light sticky-top">
<div class="container-fluid">
	<a class="navbar-brand" href="#"><!-- <img src="tek_systems.png"> --></a> <h1>Tek-Care</h1>
	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive">
		<span class="navbar-toggler-icon"></span> 
	</button>
	<div class="collapse navbar-collapse" id="navbarResponsive">
		<ul class="navbar-nav ml-auto">
			<li class="active">
				<a class="nav-link" href="#"><strong>Home</strong></a>
			</li>
			<li class="nav-item active">
				<a class="nav-link" href="#"><strong>About</strong></a>
			</li>
			<li class="nav-item active">
				<a class="nav-link" href="#"><strong>Services</strong></a>
			</li>
			<li class="nav-item active">
				<a class="nav-link" href="#"><strong>Team</strong></a>
			</li>
			<li class="nav-item active">
				<a class="nav-link" href="#"><strong>Connect</strong></a>
			</li>
		</ul>
</div>	
</nav>
<!--- Image Slider -->
<div id="slides" class="carousel slide" data-ride="carousel">
<!-- 
<ul class="carousel-indicators">
	<li data-target="#slides" data-slide-to="0" class="active"></li>
	<li data-target="#slides" data-slide-to="1"></li>
	<li data-target="#slides" data-slide-to="2"></li>
</ul>
-->
<div class="carousel-inner">
	<div class="carousel-item active">
		<!-- <img src="healthh3.jpg"> -->
		
	</div>
	<div class="carousel-item">
		<!-- <img src="healthh1.jpg"> -->
	</div>
	<div class="carousel-item">
		<!-- <img src="health2.jpg">  -->
	</div>
</div>
</div>
<!--Button-->
<div class="buttn">
	<center>
	<br>
		<a href="Result Page.html"> <input type="button" class="btn btn-info" id="edit" value="Insights"> </a>
	</center>
</div>
<br> 
<form action="updateservlet.java" method="get">
  <div class="container-fluid">
	<div class="table-responsive">
	
	
	<button type="submit" class="btn btn-success" id="success">PREDICT THE LEAVES</button>
	
	<br>
	<br>
	
	<table class="table table-bordered table-striped table-sm" id="emp_id">
	
<tr>
<th><strong>Select</th>
<th><strong>Emp ID</th>
<th><strong>Predict Leave</th>
<th><strong>Age</th>
<th><strong>Gender</th>
<th><strong>Family History</th>
<th><strong>Treatment</th>
<th><strong>Work Interfere</th>
<th><strong>Remote Work</th>
<th><strong>Benefits</th>
<th><strong>Wellness Program</th>
<th><strong>Seek Help</th>
<th><strong>Anonimity</th>
<th><strong>Mental Health</th>
<th><strong>Physical Health</th>
<th><strong>Co-workers</th>
<th><strong>Supervisor</th>
<th><strong>Mental Health Interview</th>
<th><strong>Physical Health Interview</th>
<th><strong>Mental vs Physical</th>
</strong></font>
</tr>

<%
try
{
	Class.forName("com.mysql.jdbc.Driver");
	String url= "jdbc:mysql://localhost:3306/teksystems";
	String username="root";
	String password="kunal";
	String query="SELECT * FROM csv_table_dummy";
	Connection conn=DriverManager.getConnection(url, username, password);
	Statement stmt=conn.createStatement();
	ResultSet rs=stmt.executeQuery(query);

	while(rs.next())
	{
	%>
	<tr>
	<!-- <td><input type="checkbox" name="emp_checkbox" value="0" checked hidden ></td> -->
	<td><input type="checkbox" name="emp_checkbox" value=<%=rs.getInt("Emp_ID") %> ></td>
	<td><%=rs.getInt("Emp_ID") %></td>
	<td><%=rs.getString("leaves") %></td>
	<td><%=rs.getString("Age") %></td>
	<td><%=rs.getString("Gender") %></td>
	<td><%=rs.getString("Family_History") %></td>
	<td><%=rs.getString("Past_treatment") %></td>
	<td><%=rs.getString("Health_Obstruction_in_work") %></td>
	<td><%=rs.getString("remote_work") %></td>
	<td><%=rs.getString("benefits") %></td>
	<td><%=rs.getString("past_experience_of_wellness_program") %></td>
	<td><%=rs.getString("Help_seeked") %></td>
	<td><%=rs.getString("anonymity") %></td>
	<td><%=rs.getString("mental_health_consequence") %></td>
	<td><%=rs.getString("physical_heath_consequence") %></td>
	<td><%=rs.getString("coworkers") %></td>
	<td><%=rs.getString("supervisor") %></td>
	<td><%=rs.getString("mental_health_interview") %></td>
	<td><%=rs.getString("physical_health_interview") %></td>
	<td><%=rs.getString("mental_vs_physical_health") %></td>
	</tr>
	 <%
	}
	%>
	</table>
	<%
	
	query="DROP TABLE csv_table_dummy";
	stmt.executeUpdate(query);
	
	%>
	<br>
	<br>
	<%
	
	rs.close();
	stmt.close();
	conn.close();
	}
catch(Exception e)
{
	e.printStackTrace();
}
%>

</form>
</body>
</html>