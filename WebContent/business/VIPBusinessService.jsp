<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="../css/style.css" />
<link href='http://fonts.googleapis.com/css?family=Engagement' rel='stylesheet' type='text/css'>
<title>VIP个人业务</title>
</head>
<body>
<jsp:include page="businessHeader.jsp"/>
<br><br>
<!-- <center> -->
<br> 当前业务类型：VIP个人业务

<br><br>
<%
session.setAttribute("usertype", "vip");
%>

<div>
<ul id = "navlist">
<li><a href="iopenAccount.jsp">开户</a></li>
<li><a href="deposit.jsp">存款</a></li>
<li><a href="iwithdraw.jsp">取款</a></li>
<li><a href="query.jsp">查询</a></li>
<li><a href="transfer.jsp">转账</a></li>
<li><a href="changepasswd.jsp">改密码</a></li>
<li><a href="closeaccount.jsp">销户</a></li>
</ul>
</div>
<!--</center>  -->


</body>
</html>