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
<title>My Profile</title>
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
			<td>
				<table align="center" style="text-align: center;" width="80%" border="0">
					<%
						if (buyer.getUsername() != null) {
					%>
					<tr>
						<td align="center"><label>UserName</label></td>
						<td><%=buyer.getUsername()%></td>
					</tr>
					<%
						}
					%>
					<%
						if (buyer.getEmail() != null) {
					%>
					<tr>
						<td align="center"><label>Email</label></td>
						<td><%=buyer.getEmail()%></td>
					</tr>
					<%
						}
					%>

					<%
						if (buyer.getFirstname() != null) {
					%>
					<tr>
						<td align="center"><label>FirstName</label></td>
						<td><%=buyer.getFirstname()%></td>
					</tr>
					<%
						}
					%>
					
					<%
						if (buyer.getLastname() != null) {
					%>
					<tr>
						<td align="center"><label>LastName</label></td>
						<td><%=buyer.getLastname()%></td>
					</tr>
					<%
						}
					%>
					
					<%
						if (buyer.getNickname() != null) {
					%>
					<tr>
						<td align="center"><label>NickName</label></td>
						<td><%=buyer.getNickname()%></td>
					</tr>
					<%
						}
					%>
					
					<%
						if (buyer.getCredit() != null) {
					%>
					<tr>
						<td align="center"><label>CreditCardNum</label></td>
						<td><%=buyer.getCredit()%></td>
					</tr>
					<%
						}
					%>
					
					<%
						if (buyer.getAddress() != null) {
					%>
					<tr>
						<td align="center"><label>StreetAddress</label></td>
						<td><%=buyer.getAddress()%></td>
					</tr>
					<%
						}
					%>
					
					<%
						if (buyer.getBirth() != 1900) {
					%>
					<tr>
						<td align="center"><label>BirthYear</label></td>
						<td><%=buyer.getBirth()%></td>
					</tr>
					<%
						}
					%>

		</table>
		<center><table>
		<form action='user' method='POST'>
			<button style="width: 150px; height: 30px" name='action' value='EditBuyerProfile'>Edit My Profile</button>
		</form>
<!-- 		<button style="width: 150px; height: 30px" name="back" -->
<!-- 			value="back" onclick="javascript:history.go(-1);" />back</button> -->
		</table></center>
		</td>
		</tr>
		</table>
</body>
</html>