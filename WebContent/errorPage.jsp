<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="css/style.css" />
<link href='http://fonts.googleapis.com/css?family=Engagement' rel='stylesheet' type='text/css'>
<title>Error...</title>
</head>
<body>
<h1> 出错啦！</h1>
<%
	String errorMsg = (String)request.getAttribute("msg");
	String returnLink = (String)request.getAttribute("returnLink");
	out.println("<br>" + errorMsg);
	out.println("<br> <a href=" + returnLink + "> 返回 </a>");
%>
</body>
</html>