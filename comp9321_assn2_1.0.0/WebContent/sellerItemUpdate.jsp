<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="org.w3c.dom.*"%>
<%@ page import="java.util.*"%>
<%@ page import="assign2.bean.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	
	String pictureURL = (String)session.getAttribute("pictureURL");
	System.out.println(pictureURL);
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
				<form action='user' method='POST'>

			<center><table align="center" width="600px">
				</br> </br> </br>
				<tr align="center">
					<td colspan="4"><h1>Update your item</h1></td>
				</tr>
				<tr align="center"><td colspan="4">please add information of your item below</td></tr>
				<tr align="left">
					<td width="10%"><font color=red size=2></font>Title:</td>
					<td width="35%"><input type="text" name="title"
					style="width: 100%" placeholder="title" ></td>
				</tr>
				<tr>
					<td width="10%"><font color=red size=2></font>Author:</td>
					<td width="35%"><input type="text" name="author"
					style="width: 100%" placeholder="author" ></td>
				</tr>
				<tr>
					<td width="10%"><font color=red size=2></font>Editor:</td>
					<td width="35%"><input type="text" name="editor"
					style="width: 100%" placeholder="editor" ></td>
				</tr>
				<tr>
					<td width="10%">Year:</td>
					<td width="35%"><input type="text" name="year"
						style="width: 100%" placeholder="year"></td>
				</tr>
				<tr>
					<td width="10%">Type:</td>
					<td><select name="type">
							<option value="articletype"">article</option>
							<option value="inproceeding">inproceeding</option>
							<option value="proceeding">proceeding</option>
							<option value="book">book</option>
							<option value="incollection"">incollection</option>
							<option value="phdthesis">phdthesis</option>
							<option value="mastersthesis">mastersthesis</option>
					</select></td>
				</tr>
				<tr>
					<td width="10%">Journal:</td>
					<td width="35%"><input type="text" name="journal"
						style="width: 100%" placeholder="journal"></td>
				</tr>
				<tr>
					<td width="10%">Volume:</td>
					<td width="35%"><input type="text" name="volume"
						style="width: 100%" placeholder="volume"></td>
				</tr>
				<tr>
					<td width="10%">Number:</td>
					<td width="35%"><input type="text" name="number"
						style="width: 100%" placeholder="number"></td>
				</tr>
				<tr>
					<td width="10%">Publisher:</td>
					<td width="35%"><input type="text" name="publisher"
						style="width: 100%" placeholder="publisher"></td>
				</tr>
				<tr>
					<td width="10%">ISBN:</td>
					<td width="35%"><input type="text" name="isbn"
						style="width: 100%" placeholder="isbn"></td>
				</tr>
				<tr>
					<td width="10%">Pages:</td>
					<td width="35%"><input type="text" name="pages"
						style="width: 100%" placeholder="pages"></td>
				</tr>
				<tr>
					<td width="10%">Update pictures:</td>
					<td width="35%"><input type="text" name="pictureurl"
						style="width: 80%" placeholder="pictureurl"><button style="width: 18%" 
							name="action" value="picturepreviewforupdate">Preview</button></td>
				</tr>
				<tr>
					<td width="10%">Booktitle:</td>
					<td width="35%"><input type="text" name="booktitle"
						style="width: 100%" placeholder="booktitle"></td>
				</tr>
				<tr>
					<td width="10%">Price:</td>
					<td width="35%"><input type="text" name="price"
						style="width: 100%" placeholder="At most two decimal parts accepted, etc 99.99" color="grey"></td>
				</tr>
			</table>
		</center>
		<center><table>

<%-- 			<input type='hidden' name="ID" value='<%=dblp.getId()%>'> --%>
			<button style="width: 150px; height: 30px" name='action' value='updatesellingitem'>Complete</button>
			<input type='hidden' name="ID">
			<button style="width: 150px; height: 30px" name='action' value='toupdatesellingitem'>Reset</button>
			<button style="width: 150px; height: 30px" name="back"
								value="back" onclick="javascript:history.go(-1);" />back</button>
		</table></center>
		</form>
			</td>
		<td valign="top" align="right" width="25%">
			<img style="width:210px;height:300px" src="<%=pictureURL%>"/>
		</td>
		</tr>
	</table>
</body>
</html>