<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Book advanced search</title>
<%@ include file="buyerHeader.html"%>
</head>
<body>
	<center>
		<br />
		<br />
		<br /> <font size=16>Advanced Search!</font> <br />
		<br />
		<form action="user" method="post">
			<table>
				<tr>
					<td>Type:</td>
					<td><select name="type">
							<option value="article">Article</option>
							<option value="inproceedings">Inproceedings</option>
							<option value="proceedings">Proceedings</option>
							<option value="book">Book</option>
							<option value="incollection">Incollection</option>
							<option value="phdthesis">PHD Thesis</option>
							<option value="mastersthesis">Master Thesis</option>
							<option value="www">WWW</option>
					</select></td>
					<td>Title:</td>
					<td><input type="text" name="title"></td>
					<td>Author:</td>
					<td><input type="text" name="author"></td>
					<td>Year:</td>
					<td><input type="text" name="year"></td>
					<td><button name='action' value='adSearch'>Search</button></td>

				</tr>
			</table>
		</form>
	</center>

</body>
</html>