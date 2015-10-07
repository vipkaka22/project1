<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ page import="org.w3c.dom.*"%>
<%@ page import="java.util.*"%>
<%@ page import="org.apache.xerces.parsers.DOMParser"%>
<%@ page import="assign2.bean.Item"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	ArrayList<Item> result = (ArrayList<Item>) session.getAttribute("resultList");
	int pageCount = 0;
	int curPage = 0;
	int size = result.size();
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
<title>Book Search Result</title>
<%@ include file="Header.html"%>
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
	<p>
	<form class="searchbox" action='user' method='POST'>
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
				<input type='hidden' name="action" value='visitorSearch'/>
				<button class="one1" name='searchType' value='all'>Search</button>
			</p>
			<p>
				<input type="text" value="" id="" name="searchContent2" class="one"
					placeholder="Search by Title" />
				<input type='hidden' name="action" value='visitorSearch'/>
				<button class="one1" name='searchType' value='title'>Search</button>
			</p>
			<p>
				<input type="text" value="" id="" name="searchContent3" class="one"
					placeholder="Search by Author" />
				<input type='hidden' name="action" value='visitorSearch'/>
				<button class="one1" name='searchType' value='author'>Search</button>
			</p>
			<p>
				<input type="text" value="" id="" name="searchContent4" class="one"
					placeholder="Search by Publication type" />	
				<input type='hidden' name="action" value='visitorSearch'/>			
				<button class="one1" name='searchType' value='type'>Search</button>
			</p>
			<p>
				<input type="text" value="" id="" name="searchContent5" class="one"
					placeholder="Search by Publication year" />
				<input type='hidden' name="action" value='visitorSearch'/>
				<button class="one1" name='searchType' value='year'>Search</button>
			</p>
		</div>
	</form>
	<form>
	<p><center>
			<%if (size == 1) {%>
			</td>>>><font size=2><%=size%> result is found!</font> 
			<%}else if (size > 1){ %>
			</td>>>><font size=2><%=size%> results are found!</font>
			<%} %>
			</center></p>
			</form>
	<p><hr></p>
	<p><center>
		<table align="center" style="text-align: center;" width="70%"
			border="0">
			<tr>
			
				<td>Number</td>
				<td>Type</td>
				<td>Title</td>
				<td>Author</td>
				<td>Year</td>
				<td>Price</td>
			</tr>
			<%
				int number;
				for (int i = (curPage - 1) * 10; i < Math.min(size, curPage * 10); i++) {
					Item item = result.get(i);
					number = i + 1;
			%>
			<tr>
				<td><%=number%></td>
				<td><%=item.getType()%></td>
				<td><a href="user?action=visitorItemDetail&itemId=<%=item.getId()%>"><%=item.getTitle()%></a></td>
				<td><%if (item.getAuthor() == null){%>--<%}else{%>
					<%=item.getAuthor()%><%} %></td>
					<td><%if (item.getYear() < 1000 || item.getYear() > 2015){%>--<%}else{%>
					<%=item.getYear()%><%} %></td>
					<td><%=item.getPrice()%></td>

			</tr>
			<%
				}
			%>
		</table></center>
		<br/><br/>
		<center><table>
			<tr>
				<%if (curPage > 1) {%>
				<a href = "searchResultPage.jsp?curPage=<%=1%>">First Page</a>
				
				<tr>
			<span style="font-size:12px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
			</tr>
			<a href = "searchResultPage.jsp?curPage=<%=curPage-1%>">Previous Page</a>
				<%}%>
			</tr>
			<tr>
			<span style="font-size:12px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
			</tr>
			<tr>
				<%if (curPage < pageCount) {%>
				<a href = "searchResultPage.jsp?curPage=<%=curPage+1%>">Next Page</a>
				<tr>
			<span style="font-size:12px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
			</tr>
			<a href = "searchResultPage.jsp?curPage=<%=pageCount%>">Last Page</a>
				<%}%>
			</tr>

		</table></center>
	</center>
<br/><br/><br/><br/><br/><br/>
</body>
</html>