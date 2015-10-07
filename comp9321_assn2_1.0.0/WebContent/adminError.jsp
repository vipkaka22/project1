<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error found!</title>
<%@ include file="adminHeader.html"%>
</head>
<body>
	<center>
		</br> </br> </br> </br> </br> </br>
		<tr><td>
		<font size="20px">
			<%=null == session.getAttribute("errMsg") ? "Sorry"
					: session.getAttribute("errMsg")%>
		</font>

		<table>
		</td></tr>
		</br> </br> </br> </br>
		
				<tr><td>
		<input type="button" style="width: 150px; height: 30px" name="back"
								value="back" onclick="javascript:history.go(-1);" />
			</td></tr>
			</table>
	</center>
</body>
</html>