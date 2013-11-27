<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="../css/style.css" />
<link href='http://fonts.googleapis.com/css?family=Engagement' rel='stylesheet' type='text/css'>
<title>后台管理</title>
</head>
<body>
<jsp:include page="businessHeader.jsp"/>
<br><br>
<!-- <center> -->
<br> 银行后台管理

<br><br>
<%
session.setAttribute("usertype", "department");
%>

<div>
<ul id = "navlist">
<li><a href="../logquery">日志查询</a></li>
<li><a href="../logstatistics">日志统计</a></li>
<%
String username = (String)session.getAttribute("sessionUsername");
if(username != null && username.equals("root")){
	out.print("<li><a href='addEmployee.jsp'>添加雇员</a></li>");
	out.print("<li><a href='deleteEmployee.jsp'>删除雇员</a></li>");
}
%>
<!---<li><a href="">删除雇员</a></li> --->
</ul>
</div>
<!--</center>  -->


</body>
</html>