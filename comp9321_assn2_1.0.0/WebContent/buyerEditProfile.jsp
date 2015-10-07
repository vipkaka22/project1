<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="org.w3c.dom.*"%>
<%@ page import="java.util.*"%>
<%@ page import="assign2.bean.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	Buyer buyer = (Buyer)session.getAttribute("buyer");
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DBLP result detail</title>
<script language="javascript">
/*
function changepassword(){
	var newpassword = prompt("Plz enter your new password:","");
	if(newpassword){
		location.href="user?action=updatepw "+newpassword;
//		alert("Success!");
	}
	else{
		alert("Fail!");
	}
}
*/
</script>
<%@ include file="buyerHeader.html"%>
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	$(function() {
		$(".bodys p").not(":first").hide();
		$(".searchbox ul li").mouseover(function() {
			var index = $(this).index();
			if (index == 0) {
				$(this).find("a").addClass("style1");
				$(".searchbox ul li").eq(1).find("a").removeClass("style1");
				$(".searchbox ul li").eq(2).find("a").removeClass("style1");
				$(".searchbox ul li").eq(3).find("a").removeClass("style1");
				$(".searchbox ul li").eq(4).find("a").removeClass("style1");
			}
			if (index == 1) {
				$(this).find("a").addClass("style1");
				$(".searchbox ul li").eq(0).find("a").removeClass("style1");
				$(".searchbox ul li").eq(2).find("a").removeClass("style1");
				$(".searchbox ul li").eq(3).find("a").removeClass("style1");
				$(".searchbox ul li").eq(4).find("a").removeClass("style1");
			}
			if (index == 2) {

				$(this).find("a").addClass("style1");
				$(".searchbox ul li").eq(0).find("a").removeClass("style1");
				$(".searchbox ul li").eq(1).find("a").removeClass("style1");
				$(".searchbox ul li").eq(3).find("a").removeClass("style1");
				$(".searchbox ul li").eq(4).find("a").removeClass("style1");
			}
			if (index == 3) {

				$(this).find("a").addClass("style1");
				$(".searchbox ul li").eq(0).find("a").removeClass("style1");
				$(".searchbox ul li").eq(1).find("a").removeClass("style1");
				$(".searchbox ul li").eq(2).find("a").removeClass("style1");
				$(".searchbox ul li").eq(4).find("a").removeClass("style1");
			}
			if (index == 4) {
				$(this).find("a").addClass("style1");
				$(".searchbox ul li").eq(1).find("a").removeClass("style1");
				$(".searchbox ul li").eq(2).find("a").removeClass("style1");
				$(".searchbox ul li").eq(3).find("a").removeClass("style1");
				$(".searchbox ul li").eq(0).find("a").removeClass("style1");
			}
			var index = $(this).index();
			$(".bodys p").eq(index).show().siblings().hide();
		});
	});
</script>

<style type="text/css">
* {
	margin: 0;
	padding: 0;
	list-style-type: none;
}

a, img {
	border: 0;
}

/* searchbox */
ul#navlist{
	margin: 0 0 0 .1px;
	padding: 0;
	width: 300px;
	}
#navlist li{
	list-style-type: none;
	background-color: #000;
	color: #ffffff;
	border: .01em solid #ffffff;
	font-weight: 600;
	text-align: center;
	padding: .5em;
	margin-bottom: .01em;
	}
#navlist li a{
	color: #ffffff;
	text-decoration: none;
	display: block;
	}
#navlist li a:hover{
	padding: .1em;
	background-color: #faebd7;
	color: #191970;
	}

.bodys input {
	height: 30px;
	line-height: 30px;
	width: 600px;
	padding: 0 10px;
	float: left;
}

.bodys .one {
	border: #000 3px solid
}

.bodys .one1 {
	background-color: #000;
}

.bodys button {
	float: left;
	border: 0;
	height: 36px;
	width: 100px;
	color: #FFF;
	line-height: 36px;
	text-align: center;
	overflow: hidden;
}
</style>
</head>
<body>
	<table>
		<tr>
			<td width="30%" valign="top">
			<form class="searchbox" action='user' method='POST'>
		<div id="navcontainer">
			<ul id="navlist">
				<li id="active"><a href="user?action=toOrder" id="current">Items Bought</a></li>
				<li><a href="user?action=BuyerProfile">My Profile</a></li>
			</ul>
		</div>
	</form>
	
		</td>
		<form action='user' method='POST'>
			<td>
			<center><table>
				<tr>
					<td colspan="4"><h3>Personal Informations</h3></td>
				</tr>
				<tr align="left">
					<td width="10%"><font color=red size=2></font>UserName:</td>
					<%
						if (buyer.getUsername() != null) {
					%>
					<td><%=buyer.getUsername()%></td>
					<%
						}
					%>
				</tr>
<!--  				<tr>
					<td width="10%"><font color=red size=2></font>Password:</td>
					<%
	//					if (buyer.getPassword() != null) {
					%>
					<td><%="******"%></td>
					<%
	//					}
					%>
					<td><input type="submit" name="Submit2" value="change password" onclick="changepassword()" /></td>
				</tr> -->
				<tr>
					<td width="10%"><font color=red size=2></font>Password:</td>
					<td width="35%"><input type="text" name="password"
					style="width: 100%" placeholder="******" ></td>
				</tr>
				<tr>
					<td width="10%"><font color=red size=2></font>Email:</td>
					<td width="35%"><input type="text" name="email"
					style="width: 100%" placeholder="Email" ></td>
				</tr>
				<tr>
					<td width="10%">FirstName:</td>
					<td width="35%"><input type="text" name="firstName"
						style="width: 100%" placeholder="FirstName"></td>
				</tr>
				<tr>
					<td width="10%">LastName:</td>
					<td width="35%"><input type="text" name="lastName"
						style="width: 100%" placeholder="LastName"></td>
				</tr>
				<tr>
					<td width="10%">NickName:</td>
					<td width="35%"><input type="text" name="nickName"
						style="width: 100%" placeholder="NickName"></td>
				</tr>
				<tr>
					<td width="10%">CreditCardNum:</td>
					<td width="35%"><input type="text" name="creditCardNo"
						style="width: 100%" placeholder="CreditCardNo"></td>
				</tr>
				<tr>
					<td width="10%">BirthYear:</td>
					<td width="35%"><input type="text" name="birthYear"
						style="width: 100%" placeholder="BirthYear"></td>
				</tr>
				<tr>
					<td colspan="4"><h3>PostalInformations</h3></td>
				</tr>
				<tr>
					<td width="10%">StreetAddress:</td>
					<td width="35%"><input type="text" name="streetAddress"
						style="width: 100%" placeholder="StreetAddress"></td>
				</tr>
<!-- 
				<tr>
					<td width="10%">City:</td>
					<td width="35%"><input type="text" name="city"
						style="width: 100%" placeholder="Email"></td>
				</tr>
				<tr>
					<td width="10%">State:</td>
					<td width="35%"><input type="text" name="state"
						style="width: 100%" placeholder="Email"></td>
				</tr>
				<tr>
					<td width="10%">Country:</td>
					<td width="35%"><input type="text" name="country"
						style="width: 100%" placeholder="Email"></td>
				</tr>
				<tr>
					<td width="10%">PostalCode:</td>
					<td width="35%"><input type="text" name="postalCode"
						style="width: 100%" placeholder="Email"></td>
				</tr>
 -->				
			</table>
		</center>
		<center><table>
		
			<input type='hidden' name="ID" value='<%=buyer.getUsername()%>'>
			<button style="width: 150px; height: 30px" name='action' value='BuyerProfileComplete'>Complete</button>
			<input type='hidden' name="ID" herf="buyerMyProfile.jsp">
			<button style="width: 150px; height: 30px" name="back"
								value="back" onclick="javascript:history.go(-1);" />Cancel</buton></td>
		
		</table></center>
		</td>
		</form>
		</tr>
		</table>
</body>
</html>