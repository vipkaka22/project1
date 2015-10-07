<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.net.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script language="javascript">
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<style type="text/css">    
 body{    
      background-color:gray;      
 }    
 </style>    
</head>
<body background="gray">
<center>
<br/><br/><br/>
<font size=5>Login</font>
<br/><br/>
	<form action="admin" method="post">
		<table>
			<tr>
				<td></td>
				<td>Username:</td>
				<td><input type="text" name="adminname"></td>
			</tr>
			<tr>
				<td></td>
				<td>Password:</td>
				<td><input type="password" name="adminpassword"></td>
			</tr>
				<td></td>
				<td></td>
				<td>
					<button type="submit" name="action" value="adminlogin">Submit</button>
				</td>
		</table>
	</form>
	</center>

</body>
</html>