<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<jsp:include page="serviceHeader.jsp"/>
<!DOCTYPE html ">
<html>
<head>
<title>后台管理</title>
</head>
<body>
<br><br>
<!-- <center> -->
<br> 银行后台管理

<br><br>
<%
session.setAttribute("usertype", "department");
%>

<div>
<ul id = "navlist">
<li><a href="<%=request.getContextPath()%>/logquery.action">日志查询</a></li>
<li><a href="<%=request.getContextPath()%>/logstatistics.action">日志统计</a></li>
<%
String username = (String)session.getAttribute("sessionUsername");
if(username != null && username.equals("root")){
	out.print("<li><a href='" + request.getContextPath() + "/management/addEmployee.jsp'>添加雇员</a></li>");
	out.print("<li><a href='" + request.getContextPath() + "/management/deleteEmployee.jsp'>删除雇员</a></li>");
}
%>
<!---<li><a href="">删除雇员</a></li> --->
</ul>
</div>
<!--</center>  -->


</body>
</html>