<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
table{
	border: 1px solid blue;
	width: 100%;
	height: 100%;
	color: rgb(255,255,255);
	text-align: center;
}
tr{
	width: 100%;
	height:50px;
	background-color:rgb(32,62,103);
}
input {
	background-color:rgb(32,62,103);
	border: none;
	outline: medium;
	width: 100%;
	height: 100%;
	color: rgb(255,255,255);
	font-size: 20px;
	text-align: center;
}
input::-webkit-outer-spin-button,
input::-webkit-inner-spin-button {
  -webkit-appearance: none;
}
input[type="number"]{
  -moz-appearance: textfield;
}
</style>
<title>更改用户信息</title>
</head>
<body>
	<table>
		<tr>
			<td colspan="2"><span id="name"><c:out value="${username}"></c:out></span><span>基本信息</span></td>
		</tr>
		<tr>
			<td>ID：</td>
			<td><input type="text" value="" id="userid" disabled="disabled"/></td>
		</tr>
		<tr>
			<td>姓名：</td>
			<td><input type="text" value="" id="username"/></td>
		</tr>
		<tr>
			<td>密码：</td>
			<td><input type="text" value="" id="password"/></td>
		</tr>
		<tr>
			<td>地址：</td>
			<td><input type="text" value="" id="address"/></td>
		</tr>
		<tr>
			<td>电话：</td>
			<td><input type="number" class="coll" value="" id="cellphone"/></td>
		</tr>
		<tr>
			<td>年龄：</td>
			<td><input type="number" value="" id="age"/></td>
		</tr>
		<tr>
			<td colspan="2"><button type="button" id="submit">确认修改</button></td>
		</tr>
	</table>
</body>
<script src="js/jquery-1.8.2.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$.ajax({
			url: 'UpdateUser',
			type: 'POST',
			data: {"name": $("#name").html(),"Pcavers":"1"},
			success:function(data){
				var user = JSON.parse(data);
				$("#userid").val(user.userid);
				$("#username").val(user.username);
				$("#password").val(user.password);
				$("#address").val(user.address);
				$("#cellphone").val(user.cellphone);
				$("#age").val(user.age);
			}
		});
		$("#submit").click(function(){
			$.ajax({
				url: 'UpdateUser',
				type: 'POST',
				data: {"userid":$("#userid").val(),
					"username":$("#username").val(),
					"password":$("#password").val(),
					"address":$("#address").val(),
					"cellphone":$("#cellphone").val(),
					"Pcavers":"2",
					"age":$("#age").val()},
				success:function(data){
					var d = JSON.parse(data);
					if(d.result=="true"){
						alert("用户信息跟新成功！");
					}else{
						alert("用户信息跟新失败！");
					}
					window.close();
				}
			});
		});
	});
</script>
</html>