<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	
</script>

<title>欢迎登陆</title>
</head>
<body>
<center>
<input type="submit" value="huoqu" id="Pcavers">
	<div>
		<h1>欢迎登陆<c:out value="${username}"></c:out></h1>
	</div>
	<table>
		<thead>
			<tr>
				<th>id</th>
				<th>姓名</th>
				<th>密码</th>
				<th>地址</th>
				<th>电话</th>
				<th>年龄</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${users}" var="user">
			<tr>
				<td>${user['userid']}</td>
				<td>${user['username']}</td>
				<td>${user['password']}</td>
				<td>${user['address']}</td>
				<td>${user['cellphone']}</td>
				<td>${user['age']}</td>
			</tr>
		</c:forEach>
		<tr>
			<form action="success" method="GET" id="myForm">
			<td>
			<button type="submit" id="fistPage" value="1">首页</button>
			</td>
			<td><button type="submit" id="onePage" value="${page.pageIndex-1}">上一页</button></td>
			<td><button type="submit" id="downPage" value="${page.pageIndex+1}">下一页</button></td>
			<td><button type="submit" id="lastPage" value="${page.coutPage}">未页</button></td>
			<td><input type="button" disabled="true" value="第${page.pageIndex}页共${page.coutPage}页"/></td>
			<td><select id="count" name="count">
				<option value="${page.count}">${page.count}条/页</option>
				<option value="5">5条/页</option>
				<option value="10">10条/页</option>
				<option value="15">15条/页</option>
				<option value="20">20条/页</option> 
			</select></td>
				<input type="hidden" id="startRecord" name="startRecord" value="-1"/>
			</form>
		</tr>
		
		</tbody>
	</table>
</center>

</body>
<script src="js/jquery-1.8.2.min.js"></script>
<script type="text/javascript">
	   $("button[type='submit']").click(function(){
		  	$(this).attr('name',"pageIndex");
	   });
	   $("#count").change(function(){
		   $("#startRecord").val('${page.startRecord}');
		   $("#myForm").submit();
	   });
	   $("#Pcavers").click(function(){
		   var height='450';
		   var width = '420';
		   var top = ($(window).height()-height)/2;
		   var left = ($(window).width()-width)/2;
		   window.open ('updateUser.jsp', '', 'height='+height+', width='+width+', top='+top+', left='+left+', toolbar=no,resizable=no,location=no,status=no')
	   });
</script>

<c:if test="${page.pageIndex == 1}">
<script type="text/javascript">
	$(document).ready(function(){
		$("#fistPage").attr('disabled',true);
		$("#onePage").attr('disabled',true);
	});
</script>
</c:if>
<c:if test="${page.pageIndex == page.coutPage}">
<script type="text/javascript">
	$(document).ready(function(){
		$("#downPage").attr('disabled',true);
		$("#lastPage").attr('disabled',true);
	});
</script>
</c:if>
</html>