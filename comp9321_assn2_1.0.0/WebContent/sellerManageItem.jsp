<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="org.w3c.dom.*"%>
<%@ page import="java.util.*"%>
<%@ page import="assign2.bean.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>My order</title>
<%@ include file="sellerHeader.html"%>
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
			<td valign="top">
				<form action='user' method='POST'>
					<div id="navcontainer">
						<ul id="navlist">
							<li id="active"><a href="user?action=ManageSellingItem" id="current">Manage your items</a></li>
							<li><a href="user?action=SellerProfile">My Profile</a></li>
						</ul>
					</div>
				</form>
			</td>
			<td>
	<%
		ArrayList<Item> sellerItemList = (ArrayList<Item>) session.getAttribute("selleritemlist");
		int size = sellerItemList.size();
	%>
	<%
		if (size == 0) {
	%>
	<center>
		<br /> <br /> <br /> <font size=12>No Item is being selling!</font> <br />
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
		<br /> <font size=10>You have <%=size%> item here!</font> <br /> <br />
		<%
			} else if (size > 1) {
		%>
		<br /> <font size=10>You have <%=size%> items here!</font> <br /> <br />
		<%
			}
		%>
		<form action='user' method='POST'>
			<table align="center" style="text-align: center;" width="80%"
				border="0">
				<tr>
					<td></td>
					<td>Number</td>
					<td width="5%">Picture</td>
					<td width="10%">Type</td>
					<td>Title</td>
					<td>Author</td>
					<td width="5%">Year</td>
					<td width="8%">Price</td>
					<td>Pause State</td>
					<td></td>
				</tr>
				<%
					int number;
						for (int i = (curPage - 1) * 10; i < Math.min(size, curPage * 10); i++) {
							Item item = sellerItemList.get(i);
							number = i + 1;
				%>
				<tr>

					<td><input type="checkbox" name="rmoptions"
						value='<%=item.getId()%>' >
						
						</td>
					<td><%=number%></td>
					<td><img style="width:70px;height:100px" src="<%=item.getPicture()%>"/></td>
					<td><%=item.getType()%></td>
					<td><a href="user?action=sellerItemDetail&sellerSellectedItemId=<%=item.getId()%>"><%=item.getTitle()%></a></td>
					<td><%if (item.getAuthor() == null){%>--<%}else{%>
					<%=item.getAuthor()%><%} %></td>
					<td><%if (item.getYear() < 1000 || item.getYear() > 2015){%>--<%}else{%>
					<%=item.getYear()%><%} %></td>
					<td><%=item.getPrice()%></td>
					<%if(item.getPause() == 0){
					%>
					<td>No</td>
					<%
						}
					else{
					%>
					<td>Yes</td>
					<% 
						}
					%>
					<td>
					<form action='user' method='POST'>
						<input type="hidden" name="updateitemid" value='<%=item.getId()%>'/>
						<button style="height:20px;width:100px;" name="action" value="toupdatesellingitem"
								>UpdateThisItem</button>
					
						<%if(item.getPause() == 0){
					%>
					<input type="hidden" name="pauseItemId" value='<%=item.getId()%>'/>
					<button name="action" value="pause" style="height:20px;width:100px;">PAUSE</button>
					<%
						}
					else{
					%>
					<input type="hidden" name="pauseItemId" value='<%=item.getId()%>'/>
					<button name="action" value="unpause" style="height:20px;width:100px;">Cancel PAUSE</button>
					<% 
						}
					%>
					</form>


					</td>
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
					<a href="sellerManageItem.jsp?scurPage=<%=1%>">First Page</a>

					<tr>
			<span style="font-size:12px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
			</tr>
			<a href="sellerManageItem.jsp?scurPage=<%=curPage - 1%>">Previous Page</a>
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
				<a href="sellerManageItem.jsp?scurPage=<%=curPage + 1%>">Next Page</a>
				
				<tr>
			<span style="font-size:12px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
			</tr>
			<a href="sellerManageItem.jsp?scurPage=<%=pageCount%>">Last Page</a>
				<%
					}
				%>
			</tr>

		</table>
		<br />
					<br />
					<table>
		<tr>
		
		<button style="width: 150px; height: 30px" name='action' value='removesellingitem'>Remove seleted items</button>
				
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
			</td>
		</tr>
	</table>
</body>
</html>