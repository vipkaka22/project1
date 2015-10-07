<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="org.w3c.dom.*"%>
<%@ page import="java.util.*"%>
<%@ page import="org.apache.xerces.parsers.DOMParser"%>
<%@ page import="assign2.bean.Item"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Shopping cart</title>
<%@ include file="buyerHeader.html"%>
</head>
<body>

	<%
		ArrayList<Item> cart = (ArrayList<Item>) session.getAttribute("shoppingCart");
		int size = cart.size();
	%>
	<%
		if (size == 0) {
	%>
	<center>
		<br /> <br /> <br /> <font size=12>Shopping Cart is Empty!</font> <br />
		<br />
	</center>
	<%
		} else {
			int pageCount = 0;
			int curPage = 0;
			if (size % 10 != 0)
				pageCount = size / 10 + 1;
			else
				pageCount = size / 10;
			String temp = request.getParameter("scurPage");
			if (temp == null)
				temp = "1";
			curPage = Integer.parseInt(temp);
	%>
	<center>
		<%
			if (size == 1) {
		%>
		<br /> <br /> <br /> <font size=12><%=size%> item is in
			cart!</font> <br /> <br />
		<%
			} else if (size > 1) {
		%>
		<br /> <br /> <br /> <font size=12><%=size%> items are in
			cart!</font> <br /> <br />
		<%
			}
		%>
		<form action='user' method='POST'>
			<table align="center" style="text-align: center;" width="80%"
				border="0">
				<tr>
					<td></td>
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
							Item item = cart.get(i);
							number = i + 1;
				%>
				<tr>

					<td><input type="checkbox" name="cartIds"
						value='<%=item.getId()%>' >
						</td>
					<td><%=number%></td>
					<td><%=item.getType()%></td>
					<td><a href="user?action=itemDetail&itemId=<%=item.getId()%>"><%=item.getTitle()%></a></td>
					<td><%if (item.getAuthor() == null){%>--<%}else{%>
					<%=item.getAuthor()%><%} %></td>
					<td><%if (item.getYear() < 1000 || item.getYear() > 2015){%>--<%}else{%>
					<%=item.getYear()%><%} %></td>
					<td><%=item.getPrice()%></td>
					<td><input style="width:20px" type="text" name="quantities" value='<%=item.getCart()%>'></td>

				</tr>
				<%
					}
				%>
			</table>
			<br />
			<table>

				<tr>
					<%
						if (curPage > 1) {
					%>
					<a href="shoppingCart.jsp?scurPage=<%=1%>">First Page</a>

					<tr>
			<span style="font-size:12px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
			</tr>
			<a href="shoppingCart.jsp?scurPage=<%=curPage - 1%>">Previous Page</a>
				<%
					}
				%>
			</tr>
			<tr>
			<span style="font-size:12px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
			</tr>
			<tr>
				<%
					if (curPage < pageCount) {
				%>
				<a href="shoppingCart.jsp?scurPage=<%=curPage + 1%>">Next Page</a>
				
				<tr>
			<span style="font-size:12px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
			</tr>
			<a href="shoppingCart.jsp?scurPage=<%=pageCount%>">Last Page</a>
				<%
					}
				%>
			</tr>

		</table>
		<br />
					<br />
					<table>
		<tr>
		
<!-- 		<button style="width: 150px; height: 30px" name='action' value='removeCart'>Remove from cart</button> -->
		<button class="btn btn-5 btn-5a icon-remove" name='action' value='removeCart'><span>Remove</span></button>		
				</tr>
				<tr>
<!-- 		<button style="width: 150px; height: 30px" name='action' value='placeOrder'>Buy now</button> -->
		<button class="btn btn-5 btn-5a icon-truck" name='action' value='placeOrder'><span>Buy now</span></button>		
				</tr>
				<tr>
		<button style="width: 150px; height: 30px" name="back"
								value="back" onclick="javascript:history.go(-1);" />Back</button>
			</tr>
			</table>
			
				</form>
	</center>
	<%
		}
	%>
<br />
	<br />
	<br />
	<br />
	<br />
	<br />
</body>
</html>