<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.net.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script language="javascript">
function windowClose(){
	var oParent=window.dialogArguments;
	oParent.location.href="buyerMainPage.jsp";
	window.close();
}
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>
<center>
<br/><br/><br/>
<font size=5>Login</font>
<br/><br/>
	<form action="user" method="post">
		<table>
			<tr>
				<td><select name="loginType">
						<option value="Buyerlogin">Buyer</option>
						<option value="Sellerlogin">Seller</option>
				</select></td>
				<td>Email:</td>
				<td><input type="text" name="email"></td>
			</tr>
			<tr>
				<td></td>
				<td>Password:</td>
				<td><input type="text" name="password"></td>
			</tr>
				<td></td>
				<td>
					<button type="submit" name="action" value="login" onclick="windowClose()">Submit</button>
				</td>
				<td>
					<button type="cancel">Cancel</button>
				</td>
		</table>
	</form>
	</center>

</body>
</html>