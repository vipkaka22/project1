<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="org.w3c.dom.*"%>
<%@ page import="java.util.*"%>
<%@ page import="assign2.bean.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	Admin admin = (Admin)session.getAttribute("profile");
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin profile</title>
<%@ include file="adminHeader.html"%>
</head>
<body>
	</br> </br> </br>
	<table align="center" style="text-align: center;" width="80%" border="0">
					<center><font size="15px">Information</font></center>
					</br> </br>
					<%
						if (admin.getUsername() != null) {
					%>
					<tr>
						<td align="center"><label>UserName</label></td>
						<td><%=admin.getUsername()%></td>
					</tr>
					<%
						}
					%>
					<%
						if (admin.getEmail() != null) {
					%>
					<tr>
						<td align="center"><label>Email</label></td>
						<td><%=admin.getEmail()%></td>
					</tr>
					<%
						}
					%>

					<%
						if (admin.getFirstname() != null) {
					%>
					<tr>
						<td align="center"><label>FirstName</label></td>
						<td><%=admin.getFirstname()%></td>
					</tr>
					<%
						}
					%>
					
					<%
						if (admin.getLastname() != null) {
					%>
					<tr>
						<td align="center"><label>LastName</label></td>
						<td><%=admin.getLastname()%></td>
					</tr>
					<%
						}
					%>
					
					<%
						if (admin.getAddress() != null) {
					%>
					<tr>
						<td align="center"><label>Address</label></td>
						<td><%=admin.getAddress()%></td>
					</tr>
					<%
						}
					%>

		</table>
		<center><table>
		<input type="button" style="width: 150px; height: 30px" name="back"
			value="back" onclick="javascript:history.go(-1);" />
		</table></center>
</body>
</html>