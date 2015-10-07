<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="org.w3c.dom.*"%>
<%@ page import="java.util.*"%>
<%@ page import="assign2.bean.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	Item item = (Item) session.getAttribute("sellerDetailedItemId");
%>
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
				<br />
		<br />
		<br /> <font size=16>Details of the item for sale</font> <br />
		<br />
		<table style="text-align: center;" border="0">
			<%
				if (item.getType() != null) {
			%>
			<tr>
				<td align="left"><label>Type</label></td>
				<td><%=item.getType()%></td>
			</tr>
			<%
				}
			%>
			<%
				if (item.getTitle() != null) {
			%>
			<tr>
				<td align="left"><label>Title</label></td>
				<td><%=item.getTitle()%></td>
			</tr>
			<%
				}
			%>

			<%
				if (item.getBooktitle() != null) {
			%>
			<tr>
				<td align="left"><label>BookTitle</label></td>
				<td><%=item.getBooktitle()%></td>
			</tr>
			<%
				}
			%>

			<%
				if (item.getAuthor() != null) {
			%>
			<tr>
				<td align="left"><label>Author</label></td>
				<td><%=item.getAuthor()%></td>
			</tr>
			<%
				}
			%>

			<%
				if (item.getEditor() != null) {
			%>
			<tr>
				<td align="left"><label>Editor</label></td>
				<td><%=item.getEditor()%></td>
			</tr>
			<%
				}
			%>

			<%
				if (item.getYear() > 1000) {
			%>
			<tr>
				<td align="left"><label>Year</label></td>
				<td><%=item.getYear()%></td>
			</tr>
			<%
				}
			%>

			<%
				if (item.getPublisher() != null) {
			%>
			<tr>
				<td align="left"><label>Publisher</label></td>
				<td><%=item.getPublisher()%></td>
			</tr>
			<%
				}
			%>

			<%
				if (item.getIsbn() != null) {
			%>
			<tr>
				<td align="left"><label>ISBN</label></td>
				<td><%=item.getIsbn()%></td>
			</tr>
			<%
				}
			%>

			<%
				if (item.getJournal() != null) {
			%>
			<tr>
				<td align="left"><label>Journal</label></td>
				<td><%=item.getJournal()%></td>
			</tr>
			<%
				}
			%>

			<%
				if (item.getVolume() > 0) {
			%>
			<tr>
				<td align="left"><label>Volume</label></td>
				<td><%=item.getVolume()%></td>
			</tr>
			<%
				}
			%>

			<%
				if (item.getNumber() > 0) {
			%>
			<tr>
				<td align="left"><label>Number</label></td>
				<td><%=item.getNumber()%></td>
			</tr>
			<%
				}
			%>

			<%
				if (item.getPages() != null) {
			%>
			<tr>
				<td align="left"><label>Pages</label></td>
				<td><%=item.getPages()%></td>
			</tr>
			<%
				}
			%>

			
			<tr>
				<td align="left"><label>Price</label></td>
				<td><%=item.getPrice()%></td>
			</tr>
			


		</table>
		<br/><br/>
		<table>
		<form action='user' method='POST'>


</table>
<br/>
<table>
		<button style="width: 150px; height: 30px" name="back"
			value="back" onclick="javascript:history.go(-1);" />back</button>
			</table>
			</form>
	</center>
	<br/><br/><br/><br/>
			</td>
		</tr>
	</table>
</body>
</html>