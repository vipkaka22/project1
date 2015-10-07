<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="org.w3c.dom.*"%>
<%@ page import="java.util.*"%>
<%@ page import="org.apache.xerces.parsers.DOMParser"%>
<%@ page import="assign2.bean.Item"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	Item item = (Item) session.getAttribute("selectedItem");
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

	<center>
		
		<p>
		<form class="searchbox" action='admin' method='POST'>
		<ul class="border1">
			<li><a href="#" class="style1">All</a></li>
			<li><a href="#">Title</a></li>
			<li><a href="#">Author</a></li>
			<li><a href="#">Publication Type</a></li>
			<li><a href="#">Publication Year</a></li>
		</ul>
		<div class="bodys">
			<p>
				<input type="text" value="" id="" name="searchContent1" class="one"
					placeholder="Combined search" />
				<input type='hidden' name="action" value='adminSearch'/>
				<button class="one1" name='searchType' value='all'>Search</button>
			</p>
			<p>
				<input type="text" value="" id="" name="searchContent2" class="one"
					placeholder="Search by Title" />
				<input type='hidden' name="action" value='adminSearch'/>
				<button class="one1" name='searchType' value='title'>Search</button>
			</p>
			<p>
				<input type="text" value="" id="" name="searchContent3" class="one"
					placeholder="Search by Author" />
				<input type='hidden' name="action" value='adminSearch'/>
				<button class="one1" name='searchType' value='author'>Search</button>
			</p>
			<p>
				<input type="text" value="" id="" name="searchContent4" class="one"
					placeholder="Search by Publication type" />	
				<input type='hidden' name="action" value='adminSearch'/>			
				<button class="one1" name='searchType' value='type'>Search</button>
			</p>
			<p>
				<input type="text" value="" id="" name="searchContent5" class="one"
					placeholder="Search by Publication year" />
				<input type='hidden' name="action" value='adminSearch'/>
				<button class="one1" name='searchType' value='year'>Search</button>
			</p>
		</div>
	</form>
	<p><hr></p>
		
		<table><tr><td width="30%" align="center">
			<img style="width:400px;height:550px" src="<%=item.getPicture() %>"/>
		</td>
		<td width="70%">
		<br />
		<br />
		<br /> <font size=16>Result details!</font> <br />
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
		
<form action='admin' method='POST'>
<table>
<input type='hidden' name="removeItemId" value='<%=item.getId()%>'>

		<button style="width: 150px; height: 30px" name='action' value='removeItem'>Remove this item</button>
		<button style="width: 150px; height: 30px" name="back"
			value="back" onclick="javascript:history.go(-1);" />Back</button>
			</table>
			</form>
			</td></tr></table>
	</center>
	<br/><br/><br/><br/>
</body>
</html>