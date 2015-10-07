<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script language="javascript">
function onclickLogin(){
	window.showModalDialog("loginDialog.jsp",window,"status:no;scroll:no;dialogWidth:500px;dialogHeight:300px");
}
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>
</head>
<body>
	<center><h1>Registration</h1><hr></center>
	<form action="user" method="post">
		<center>
			<table>
				<tr>
					<td	colspan="2"><font size=3; color=red>* means this blank must be filled.</font></td>
				</tr>
				<tr>
					<td colspan="4"><h3>Personal Informations</h3></td>
				</tr>
				<tr>
					<td width="10%"><font color=red size=2>*</font>Identity:</td>
					<td width="35%"><select name="identity" style="width: 100%">
						<option value="BuyerRegist">Buyer</option>
						<option value="SellerRegist">Seller</option>
				</select></td>
				</tr>
				<tr align="left">
					<td width="10%"><font color=red size=2>*</font>UserName:</td>
					<td width="35%"><input type="text" name="userName"
						style="width: 100%"></td>
				</tr>
				<tr>
					<td width="10%"><font color=red size=2>*</font>Password:</td>
					<td width="35%"><input type="password" name="passWord"
						style="width: 100%"></td>
				</tr>
				<tr>
					<td width="10%"><font color=red size=2>*</font>Confirm Password:</td>
					<td width="35%"><input type="password" name="ConfirmPassWord"
						style="width: 100%"></td>
				</tr>
				<tr>
					<td width="10%"><font color=red size=2>*</font>Email:</td>
					<td width="35%"><input type="text" name="email"
					style="width: 100%"></td>
				</tr>
				<tr>
					<td width="10%">FirstName:</td>
					<td width="35%"><input type="text" name="firstName"
						style="width: 100%"></td>
				</tr>
				<tr>
					<td width="10%">LastName:</td>
					<td width="35%"><input type="text" name="lastName"
						style="width: 100%"></td>
				</tr>
				<tr>
					<td width="10%">NickName:</td>
					<td width="35%"><input type="text" name="nickName"
						style="width: 100%"></td>
				</tr>
				<tr>
					<td width="10%"><font color=red size=2>*</font>Credit Card Num:</td>
					<td width="35%"><input type="text" name="creditCardNo"
						style="width: 100%"></td>
				</tr>
				<tr>
					<td width="10%">Year of Birth:</td>
					<td width="35%"><input type="text" name="birthYear"
						style="width: 100%"></td>
				</tr>
				<tr>
					<td colspan="4"><h3>Postal Informations</h3></td>
				</tr>
				<tr>
					<td width="10%">StreetAddress:</td>
					<td width="35%"><input type="text" name="streetAddress"
						style="width: 100%"></td>
				</tr>
<!-- 
				<tr>
					<td width="10%">City:</td>
					<td width="35%"><input type="text" name="city"
						style="width: 100%"></td>
				</tr>
				<tr>
					<td width="10%">State:</td>
					<td width="35%"><input type="text" name="state"
						style="width: 100%"></td>
				</tr>
				<tr>
					<td width="10%">Country:</td>
					<td width="35%"><input type="text" name="country"
						style="width: 100%"></td>
				</tr>
				<tr>
					<td width="10%">PostalCode:</td>
					<td width="35%"><input type="text" name="postalCode"
						style="width: 100%"></td>
				</tr>
 -->
				
				<tr align="center">
					<td width="30%" colspan="1.5">
					<button type="submit" name="action" value="registsubmit">Submit</button>
					</td>
					<td width="45%" colspan="1.5">
					<button type="reset" name="action" value="registreset">Reset</button>
					</td>
					<td width="45%" colspan="1.5">
					<input type="button" name="action" value="Back" onclick="window.location.href='welcome.jsp';"/>
					</td>
				</tr>
			</table>
		</center>
	</form>
	<center>Already registered? <a href="user?action=tologin" onclick="onclickLogin()">Back to Login</a></center>
</body>
</html>