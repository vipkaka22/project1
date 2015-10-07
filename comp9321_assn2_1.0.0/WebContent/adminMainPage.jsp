<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ page import="java.util.*"%>
<%@ page import="assign2.bean.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String name = (String)session.getAttribute("adminName");
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin Main Page</title>
<%@ include file="adminHeader.html"%>
</head>
<body>
	<center>
		</br> </br> </br> </br> </br> </br>
		<tr>
			<td>
				<font size="150px">Hi! <%=name%></font>
			</td>
		</tr>
	</center>
</body>
</html>