<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="org.w3c.dom.*"%>
<%@ page import="java.util.*"%>
<%@ page import="assign2.bean.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	ArrayList<Item> order = (ArrayList<Item>) session.getAttribute("shoppingOrder");
	int pageCount = 0;
	int curPage = 0;
	int size = order.size();
	if (size % 10 != 0)
		pageCount = size / 10 + 1;
	else
		pageCount = size / 10;
	String temp = request.getParameter("curPage");
	if (temp==null)
		temp = "1";
	curPage = Integer.parseInt(temp);

%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>My order</title>
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
	<table align="center">
	<tr>
		<td valign="top" >
			<form class="searchbox" action='control' method='POST' padding="top">
		<div id="navcontainer">
			<ul id="navlist">
				<li id="active"><a href="buyerItemBoughtPage.jsp" id="current">Items Bought</a></li>
				<li><a href="buyerMyProfile.jsp">My Profile</a></li>
			</ul>
		</div></td>
		<td align="center"><form action='user' method='POST'>
			<table align="center" style="text-align: center;" width="80%"
				border="0">
				</br> </br>
				<tr>
					
					<td>Number</td>
					<td>Type</td>
					<td>Title</td>
					<td>Author</td>
					<td>Year</td>
					<td>Price</td>
					<td>Quantity</td>
				</tr>
				<%
					int number;
						for (int i = (curPage - 1) * 10; i < Math.min(size, curPage * 10); i++) {
							Item item = order.get(i);
							number = i + 1;
				%>
				<tr>

					<td><%=number%></td>
					<td><%=item.getType()%></td>
					<td><a href="user?action=itemDetail&itemId=<%=item.getId()%>"><%=item.getTitle()%></a></td>
					<td><%if (item.getAuthor() == null){%>--<%}else{%>
					<%=item.getAuthor()%><%} %></td>
					<td><%if (item.getYear() < 1000 || item.getYear() > 2015){%>--<%}else{%>
					<%=item.getYear()%><%} %></td>
					<td><%=item.getPrice()%></td>
					<td><%=item.getSold()%></td>

				</tr>
				<%
					}
				%>
			</table></center>
		<br/><br/>
		<center><table>
			<tr>
				<%if (curPage > 1) {%>
				<a href = "buyerItemBoughtPage.jsp?curPage=<%=1%>">First Page</a>
				
				<tr>
			<span style="font-size:12px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
			</tr>
			<a href = "buyerItemBoughtPage.jsp?curPage=<%=curPage-1%>">Previous Page</a>
				<%}%>
			</tr>
			<tr>
			<span style="font-size:12px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
			</tr>
			<tr>
				<%if (curPage < pageCount) {%>
				<a href = "buyerItemBoughtPage.jsp?curPage=<%=curPage+1%>">Next Page</a>
				<tr>
			<span style="font-size:12px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
			</tr>
			<a href = "buyerItemBoughtPage.jsp?curPage=<%=pageCount%>">Last Page</a>
				<%}%>
			</tr>

		</table>
		<br />
					<br />
					<table>
				<tr>
		<button style="width: 150px; height: 30px" name="back"
								value="back" onclick="javascript:history.go(-1);" />back</button>
			</tr>
			</table>
	</center>
		</td>
	</tr>
	
	
	</table>
	<br/><br/><br/><br/><br/><br/>
</body>
</html>