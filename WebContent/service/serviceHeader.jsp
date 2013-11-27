<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bankSystem.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
<link href='http://fonts.googleapis.com/css?family=Engagement' rel='stylesheet' type='text/css'>
<title>bank system</title>
</head>
<body>
<h1>银行管理系统</h1>
<ul id = "menu">
<li><a href="<%=request.getContextPath()%>/service/individualBusinessService.jsp">普通个人业务</a></li> 
<li><a href="<%=request.getContextPath()%>/service/VIPBusinessService.jsp">VIP个人业务</a></li> 
<li><a href="<%=request.getContextPath()%>/service/enterpriseBusinessService.jsp">企业用户业务</a></li>
<li><a href="<%=request.getContextPath()%>/service/departmentService.jsp">后台管理</a></li>
<li><a href="<%=request.getContextPath()%>/logoff.action">注销</a></li>
</ul>
<%
	String username = (String)session.getAttribute("sessionUsername");
	out.print("<br><br>当前操作员：" + username); 
%>
</body>
</html>