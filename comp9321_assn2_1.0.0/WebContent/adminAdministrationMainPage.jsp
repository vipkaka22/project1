<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String name = (String)session.getAttribute("adminName");
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin Main Page</title>
<%@ include file="adminHeader.html"%>
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
				<form class="searchbox" action='admin' method='POST'>
					<div id="navcontainer">
						<ul id="navlist">
							<li id="active"><a href="admin?action=toAdminBuyerListStart" id="current">Search Buyer</a></li>
							<li><a href="admin?action=toAdminSellerListStart">Search Seller</a></li>
						</ul>
					</div>
				</form>
			</td>
			<td>
				<center>
					</br> </br> </br> </br> </br> </br>
						<font size="150px">Hi! <%=name%></font>
				</center>
			</td>
		</tr>
	</table>
</body>
</html>