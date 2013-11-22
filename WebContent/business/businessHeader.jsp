<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="../css/bankSystem.css">
<link rel="stylesheet" href="../css/style.css" />
<link href='http://fonts.googleapis.com/css?family=Engagement' rel='stylesheet' type='text/css'>
<title>bank system</title>
</head>
<body>

<ul id = "menu">
<li><a href="individualBusinessService.jsp">普通个人业务</a></li> 
<li><a href="VIPBusinessService.jsp">VIP个人业务</a></li> 
<li><a href="enterpriseBusinessService.jsp">企业用户业务</a></li>
<li><a href="http://www.baidu.com">后台管理</a></li>
<li><a href="../logoff">注销</a></li>
</ul>
<%
	String username = (String)session.getAttribute("sessionUsername");
	out.print("<br><br>当前操作员：" + username); 
%>
</body>
</html>