<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="org.w3c.dom.*"%>
<%@ page import="java.util.*"%>
<%@ page import="org.apache.xerces.parsers.DOMParser"%>
<%@ page import="assign2.bean.Buyer"%>
<%@ page import="assign2.bean.SoldItem" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	Buyer buyer = (Buyer) session.getAttribute("buyer");
%>
<%
	ArrayList<SoldItem> order = (ArrayList<SoldItem>) session.getAttribute("history");
int pageCount = 0;
int curPage = 0;
int size=0;
	if (null != order) {
	
	size = order.size();
	if (size % 10 != 0)
		pageCount = size / 10 + 1;
	else
		pageCount = size / 10;
	String temp = request.getParameter("curPage");
	if (temp==null)
		temp = "1";
	curPage = Integer.parseInt(temp);
	}
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Search result detail</title>
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
.searchbox {
	width: 750px;
	height: 100px;
	margin: 0px auto 0 auto;
}

.searchbox ul {
	height: 35px;
	width: 600px;
	list-style: none;
	margin-left: 15px
}

.searchbox ul li {
	float: left
}

.searchbox ul li a {
	float: left;
	line-height: 35px;
	padding: 0 20px;
	text-decoration: none;
	color: #000;
	font-size: 14px;
	font-weight: bold;
}

.searchbox ul li .style1 {
	background-color: #729fcf;
	color: #fff
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
	<table><tr><td width="40%" valign="top">
	<center>
		<br />
		<br />
		<br /> <font size=10>Buyer details!</font> <br />
		<br />
		<table style="text-align: center;" border="1" style="border:1px solid #000000;">
			<%
				if (buyer.getUsername() != null) {
			%>
			<tr>
				<td align="left"><label>Username</label></td>
				<td><%=buyer.getUsername()%></td>
			</tr>
			<%
				}
			%>
			<%
				if (buyer.getEmail() != null) {
			%>
			<tr>
				<td align="left"><label>Email</label></td>
				<td><%=buyer.getEmail()%></td>
			</tr>
			<%
				}
			%>

			<%
				if (buyer.getFirstname() != null) {
			%>
			<tr>
				<td align="left"><label>Firstname</label></td>
				<td><%=buyer.getFirstname()%></td>
			</tr>
			<%
				}
			%>

			<%
				if (buyer.getLastname() != null) {
			%>
			<tr>
				<td align="left"><label>Lastname</label></td>
				<td><%=buyer.getLastname()%></td>
			</tr>
			<%
				}
			%>

			<%
				if (buyer.getNickname() != null) {
			%>
			<tr>
				<td align="left"><label>Nickname</label></td>
				<td><%=buyer.getNickname()%></td>
			</tr>
			<%
				}
			%>

			<%
				if (buyer.getBirth() > 1900) {
			%>
			<tr>
				<td align="left"><label>Birth</label></td>
				<td><%=buyer.getBirth()%></td>
			</tr>
			<%
				}
			%>

			<%
				if (buyer.getAddress() != null) {
			%>
			<tr>
				<td align="left"><label>Address</label></td>
				<td><%=buyer.getAddress()%></td>
			</tr>
			<%
				}
			%>

			<%
				if (buyer.getCredit() != null) {
			%>
			<tr>
				<td align="left"><label>Credit Card Number</label></td>
				<td><%=buyer.getCredit()%></td>
			</tr>
			<%
				}
			%>

			<%
				if (buyer.getBan() > 0) {
			%>
			<tr>
				<td align="left"><label>Ban State</label></td>
				<td>Yes</td>
			</tr>
			<%
				} else {
			%>
			<tr>
				<td align="left"><label>Ban State</label></td>
				<td>No</td>
			</tr>
			<%
				}
			%>
			<tr>
			<form action='admin' method='POST'>
				<%
				if (buyer.getBan() > 0) {
			    %>
				<td><input type='hidden' name="buyerName"
								value='<%=buyer.getUsername()%>' />
								<button name="action" value="cancelBanBuyer">Cancel BAN</button></td>
							<%
								} else {
							%>

							<td><input type='hidden' name="buyerName"
								value='<%=buyer.getUsername()%>' />
								<button name="action" value="banBuyer">BAN this
									customer</button></td>

							<%
								}
							%>
							</td>
							</form>
			</tr>
		</table>
		<br/><br/>
		<input type="button" style="width: 150px; height: 30px" name="back"
			value="back" onclick="javascript:history.go(-1);" />

	</center>
	<br/><br/><br/><br/>
	<% if(null == order || order.size() == 0){%>
	<td align="center" width="80%">
			<table align="center" style="text-align: center;" width="80%"
				border="0">
					<tr><td><font align="center" size="150px">No history for this customer!</font></td></tr></table>
	<%} 
	else{
	%>
	
	<td align="center">
			<table align="center" style="text-align: center;" width="80%"
				border="0">
				</br> </br>
				<tr>
					
					<td>Number</td>
					<td>Title</td>
					<td>Seller</td>
					<td>Event</td>
					<td>Quantity</td>
					<td>Time</td>
				</tr>
				<%
					int number;
						for (int i = (curPage - 1) * 10; i < Math.min(size, curPage * 10); i++) {
							SoldItem item = order.get(i);
							number = i + 1;
				%>
				<tr>
					<td><%=number%></td>
					<td><a href="admin?action=adminItemDetail&itemId=<%=item.getItemId()%>"><%=item.getTitle()%></a></td>
					<td><%=item.getSellername()%></td>
					<%
					if (item.getAddtime() != null) {
					%>
					<td>Add to Cart</td>
					<td><%=item.getCart()%></td>
					<td><%=item.getAddtime()%></td>
					<%
					} else if (item.getRemovetime() != null) {
					%>
					<td>Remove from Cart</td>
					<td><%=item.getCart()%></td>
					<td><%=item.getRemovetime()%></td>
					<%
					} else if (item.getSoldtime() != null) {
					%>
					<td>Place order</td>
					<td><%=item.getSold()%></td>
					<td><%=item.getSoldtime()%></td>
					<%
					}
					%>

				</tr>
				<%
					}
				
				%>
			</table></center>
		<br/><br/>
		<center><table>
			<tr>
				<%if (curPage > 1) {%>
				<a href = "adminBuyerDetail.jsp?curPage=<%=1%>">First Page</a>
				
				<tr>
			<span style="font-size:12px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
			</tr>
			<a href = "adminBuyerDetail.jsp?curPage=<%=curPage-1%>">Previous Page</a>
				<%}%>
			</tr>
			<tr>
			<span style="font-size:12px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
			</tr>
			<tr>
				<%if (curPage < pageCount) {%>
				<a href = "adminBuyerDetail.jsp?curPage=<%=curPage+1%>">Next Page</a>
				<tr>
			<span style="font-size:12px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
			</tr>
			<a href = "adminBuyerDetail.jsp?curPage=<%=pageCount%>">Last Page</a>
				<%}
				%>
			</tr>

		</table>
	</center>
		</td>
	</tr>
	<%} %>
	</table>
</body>
</html>